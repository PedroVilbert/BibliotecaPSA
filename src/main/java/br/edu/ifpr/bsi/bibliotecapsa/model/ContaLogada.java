package br.edu.ifpr.bsi.bibliotecapsa.model;

public class ContaLogada {

    private static ContaLogada instance;
    private Cliente cliente; // <- Aqui é onde armazena o usuário logado

    private ContaLogada() {
    }

    public static ContaLogada getInstance() {
        synchronized (ContaLogada.class) {
            if (instance == null) {
                instance = new ContaLogada();
            }
        }
        return instance;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}



