package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.dao.LivroDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.Livro;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("LivroBean")
@SessionScoped // para manter estado entre páginas
public class LivroBean implements Serializable {

    private Livro livroCadastro; // para cadastro
    private Livro livroEdicao;   // para edição
    private List<Livro> livros;

    // Getters e setters

    public Livro getLivroCadastro() {
        return livroCadastro;
    }

    public void setLivroCadastro(Livro livroCadastro) {
        this.livroCadastro = livroCadastro;
    }

    public Livro getLivroEdicao() {
        return livroEdicao;
    }

    public void setLivroEdicao(Livro livroEdicao) {
        this.livroEdicao = livroEdicao;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @PostConstruct
    public void listar() {
        try {
            this.livroCadastro = new Livro(); // formulário cadastro vazio
            LivroDAO dao = new LivroDAO();
            this.livros = dao.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar livros: " + e.getMessage(), ""));
            e.printStackTrace();
        }
    }

    // Inserir livro novo usando livroCadastro
    public void inserir() {
        try {
            LivroDAO dao = new LivroDAO();
            dao.inserir(livroCadastro);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro cadastrado com sucesso!", ""));
            this.livroCadastro = new Livro(); // limpa formulário após cadastro
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar livro: " + e.getMessage(), ""));
        }
    }

    // Seleciona livro para edição e atribui a livroEdicao
    public String selecionarLivro(Livro livroSelecionado) {
        this.livroEdicao = livroSelecionado;
        return "bibliotecarioAtualizarLivro?faces-redirect=true";
    }

    // Atualiza livro usando livroEdicao
    public String atualizar() {
        try {
            LivroDAO dao = new LivroDAO();
            dao.salvarAlterar(livroEdicao);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro atualizado com sucesso!", ""));
            listar();
            return "bibliotecarioHome?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar livro: " + e.getMessage(), ""));
            return null;
        }
    }
}
