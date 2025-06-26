package br.edu.ifpr.bsi.bibliotecapsa.bean;


import br.edu.ifpr.bsi.bibliotecapsa.dao.ReservaDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.Reserva;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
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

    @PostConstruct // Executa o método logo após a construção da pagina
    public void listar() {

        try {
            ReservaDAO dao = new ReservaDAO();
            this.reservas = dao.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar reservas: " + e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            e.printStackTrace(); // opcional: para log
        }
    }

    public void remover(ActionEvent evento){
        reserva = (Reserva)  evento.getComponent().getAttributes().get("reservaSelecionado");
        System.out.println(reserva.getId());
    }

    public void editar(ActionEvent evento){

    }


}
