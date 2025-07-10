package br.edu.ifpr.bsi.bibliotecapsa.model;

public class ContaLogada {

    private static ContaLogada instance;
    private Cliente cliente;
    private Bibliotecario bibliotecario;

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

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
}



