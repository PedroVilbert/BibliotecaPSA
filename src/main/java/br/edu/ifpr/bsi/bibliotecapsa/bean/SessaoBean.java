package br.edu.ifpr.bsi.bibliotecapsa.bean;

import br.edu.ifpr.bsi.bibliotecapsa.model.Cliente;
import br.edu.ifpr.bsi.bibliotecapsa.model.ContaLogada;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("sessaoBean")
@RequestScoped
public class SessaoBean implements Serializable {

    public Cliente getClienteLogado() {
        return ContaLogada.getInstance().getCliente();
    }
}