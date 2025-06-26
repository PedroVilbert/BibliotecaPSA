package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.dao.BibliotecarioDAO;
import br.edu.ifpr.bsi.bibliotecapsa.dao.ClienteDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.Bibliotecario;
import br.edu.ifpr.bsi.bibliotecapsa.model.Cliente;
import br.edu.ifpr.bsi.bibliotecapsa.model.ContaLogada;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("loginBean")
@ViewScoped // O ViewScoped serve para manter o estado do Bean durante a visualização da página
public class LoginBean implements Serializable {

    private String opcao;
    private String email;
    private String senha;

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String login() {
        ClienteDAO cl = new ClienteDAO();
        BibliotecarioDAO bb = new BibliotecarioDAO();

        if ("cliente".equals(opcao)) {
            Cliente cliente = cl.buscarCliente(email, senha);
            if (cliente != null) {
                // ✅ Armazena o cliente autenticado
                ContaLogada.getInstance().setCliente(cliente);
                return "clienteHome?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Erro de Login", "Email ou senha inválidos para cliente."));
            }

        } else if ("bibliotecario".equals(opcao)) {
            Bibliotecario bibliotecario = bb.buscarBibliotecario(email, senha);
            if (bibliotecario != null) {
                // ✅ Armazena o bibliotecário autenticado
                ContaLogada.getInstance().setBibliotecario(bibliotecario);
                return "bibliotecarioHome?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Erro de Login", "Email ou senha inválidos para bibliotecário."));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Aviso", "Selecione um perfil de acesso."));
        }

        return null; // Permanece na tela de login
    }
}

