package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.dao.ClienteDAO;
import br.edu.ifpr.bsi.bibliotecapsa.dao.GenericDAO;
import br.edu.ifpr.bsi.bibliotecapsa.model.Cliente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;

    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
    }

    // Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String inserir() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (senha == null || confirmarSenha == null || !senha.equals(confirmarSenha)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro", "As senhas não coincidem."));
            return null;
        }

        cliente.setNome_cliente(nome);
        cliente.setEmail_cliente(email);
        cliente.setSenha_cliente(senha);

        try {
            ClienteDAO dao = new ClienteDAO();  // usa a subclasse, que informa o tipo generico
            dao.inserir(cliente);

            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cliente cadastrado com sucesso!"));

            cliente = new Cliente(); // limpa formulário
            senha = null;
            confirmarSenha = null;

            return "login?faces-redirect=true";

        } catch (Exception e) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao cadastrar cliente."));
            e.printStackTrace();
            return null;
        }
    }
}