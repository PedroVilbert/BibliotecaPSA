package br.edu.ifpr.bsi.bibliotecapsa.bean;


import br.edu.ifpr.bsi.bibliotecapsa.dao.LivroDAO;
import br.edu.ifpr.bsi.bibliotecapsa.dao.ReservaDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.Livro;
import br.edu.ifpr.bsi.bibliotecapsa.model.Reserva;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

// O Namede serve para identificar este Bean através da página web criada
@Named("LivroBean")
@ViewScoped // O ViewScoped serve para manter o estado do Bean durante a visualização da página
public class LivroBean implements Serializable {

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    private Livro livro;
    private List<Livro> livros;

    @PostConstruct // Executa o método logo após a construção da pagina
    public void listar() {

        try {
            LivroDAO dao = new LivroDAO();
            this.livros = dao.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar Livros: " + e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            e.printStackTrace(); // opcional: para log
        }
    }



}
