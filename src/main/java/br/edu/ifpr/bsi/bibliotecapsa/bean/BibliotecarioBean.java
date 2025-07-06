package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.dao.BibliotecarioDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.Bibliotecario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("bibliotecarioBean")
@ViewScoped
public class BibliotecarioBean implements Serializable {

    private Bibliotecario bibliotecario;
    private String senha;
    private String confirmarSenha;

    @PostConstruct
    public void init() {
        bibliotecario = new Bibliotecario();
    }

    public void inserir() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (senha == null || confirmarSenha == null || !senha.equals(confirmarSenha)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro", "As senhas não coincidem."));
            return;
        }

        bibliotecario.setSenha_bibliotecario(senha);

        try {
            BibliotecarioDAO dao = new BibliotecarioDAO();
            dao.inserir(bibliotecario);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso", "Bibliotecário cadastrado com sucesso!"));

            // Limpa os campos para novo cadastro
            bibliotecario = new Bibliotecario();
            senha = null;
            confirmarSenha = null;

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro", "Erro ao cadastrar bibliotecário."));
            e.printStackTrace();
        }
    }

    // Getters e Setters

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
}
