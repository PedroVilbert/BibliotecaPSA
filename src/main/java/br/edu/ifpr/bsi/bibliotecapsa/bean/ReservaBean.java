package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.dao.LivroDAO;
import br.edu.ifpr.bsi.bibliotecapsa.dao.ReservaDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.ContaLogada;
import br.edu.ifpr.bsi.bibliotecapsa.model.Livro;
import br.edu.ifpr.bsi.bibliotecapsa.model.Reserva;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// O Namede serve para identificar este Bean através da página web criada
@Named("ReservaBean")
@ViewScoped // O ViewScoped serve para manter o estado do Bean durante a visualização da página
public class ReservaBean implements Serializable {


    private Reserva reserva;
    private List<Reserva> reservas;

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reservas) {
        this.reserva = reserva;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


    @PostConstruct
    public void init() {
        if (ContaLogada.getInstance().getCliente() != null) {
            listar();
        } else {
            listarTodas();
        }
    }


    public void listar() {
        ReservaDAO dao = new ReservaDAO();
        try {
            Long idCliente = ContaLogada.getInstance().getCliente().getId();
            this.reservas = dao.listarPorCliente(idCliente);

            Date hoje = new Date();

            for (Reserva reserva : this.reservas) {
                Date dataDevolucao = reserva.getData_devolucao();

                if (dataDevolucao != null) {
                    if (hoje.after(dataDevolucao)) {
                        long diffEmMs = hoje.getTime() - dataDevolucao.getTime();
                        long diasAtraso = diffEmMs / (1000 * 60 * 60 * 24);

                        // Valor da multa R$ 1,00 por dia de atraso
                        float multa = diasAtraso * 1.0f;
                        reserva.setValor_multa(multa);
                    } else {
                        reserva.setValor_multa(0f);
                    }
                } else {
                    reserva.setValor_multa(0f);
                }
            }

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Erro ao listar reservas: " + e.getMessage(),
                    ""
            );
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            e.printStackTrace();
        }
    }

    public void listarTodas() {
        ReservaDAO dao = new ReservaDAO();
        try {
            this.reservas = dao.listar(); // método genérico que retorna todas as reservas

            Date hoje = new Date();
            for (Reserva reserva : this.reservas) {
                Date dataDevolucao = reserva.getData_devolucao();

                if (dataDevolucao != null && hoje.after(dataDevolucao)) {
                    long diffEmMs = hoje.getTime() - dataDevolucao.getTime();
                    long diasAtraso = diffEmMs / (1000 * 60 * 60 * 24);
                    float multa = diasAtraso * 1.0f;
                    reserva.setValor_multa(multa);
                } else {
                    reserva.setValor_multa(0f);
                }
            }

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Erro ao listar todas as reservas: " + e.getMessage(),
                    ""
            );
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            e.printStackTrace();
        }
    }

    public void reservar(ActionEvent event) {
        Livro livroSelecionado = (Livro) event.getComponent().getAttributes().get("livroSelecionado");
        livroSelecionado.setNum_ex_livro(livroSelecionado.getNum_ex_livro()-1);

        LivroDAO ldao = new LivroDAO();
        ldao.salvarAlterar(livroSelecionado);

        // Cria uma instância de Date com a data atual
        Date dataAtual = new Date();
        System.out.println("Data original: " + dataAtual);

        // Cria um objeto Calendar e define a data
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAtual);

        // Adiciona 7 dias
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        // Obtém a nova data
        Date novaData = calendar.getTime();

        Reserva nova = new Reserva();
        nova.setLivro(livroSelecionado.getNome_livro());
        nova.setCliente(ContaLogada.getInstance().getCliente().getNome_cliente());
        nova.setId_cliente(ContaLogada.getInstance().getCliente().getId());
        nova.setData_reserva(new Date());
        nova.setData_devolucao(novaData);

        ReservaDAO dao = new ReservaDAO();
        dao.inserir(nova);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro reservado com sucesso!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // Atualizar lista depois de inserir
        listar();
    }

    public void remover(ActionEvent evento) {
        reserva = (Reserva) evento.getComponent().getAttributes().get("reservaSelecionada");

        try {
            if (reserva.getValor_multa() > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Devolução não autorizada.",
                        String.format("A multa de R$ %.2f deve ser quitada com um bibliotecário.", reserva.getValor_multa()));
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }

            LivroDAO livroDAO = new LivroDAO();
            Livro livro = livroDAO.buscarPorNome(reserva.getLivro());

            livro.setNum_ex_livro(livro.getNum_ex_livro() + 1);
            livroDAO.salvarAlterar(livro);

            ReservaDAO dao = new ReservaDAO();
            dao.remover(reserva);

            // Atualiza a lista de livros no LivroBean após devolução
            LivroBean livroBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{LivroBean}", LivroBean.class);
            livroBean.listar();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Reserva devolvida com sucesso. Exemplar atualizado."));

            init(); // Recarrega as reservas

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao processar devolução.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }


    public void editar(ActionEvent evento){
    }

    public void quitarMulta(ActionEvent evento){
        reserva = (Reserva) evento.getComponent().getAttributes().get("reservaSelecionada");
        reserva.setValor_multa(0F);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Multa Quitada."));
    }

}
