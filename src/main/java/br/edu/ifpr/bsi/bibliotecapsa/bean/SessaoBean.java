package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.model.Bibliotecario;
import br.edu.ifpr.bsi.bibliotecapsa.model.Cliente;
import br.edu.ifpr.bsi.bibliotecapsa.model.ContaLogada;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("sessaoBean")
@SessionScoped
public class SessaoBean implements Serializable {

    public Cliente getClienteLogado() {
        return ContaLogada.getInstance().getCliente();
    }

    public Bibliotecario getBibliotecarioLogado() {
        return ContaLogada.getInstance().getBibliotecario();
    }
}