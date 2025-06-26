package br.edu.ifpr.bsi.bibliotecapsa.dao;

import br.edu.ifpr.bsi.bibliotecapsa.model.Cliente;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

public class ClienteDAOTest {

    @Test
    public void inserir(){
        Cliente cliente = new Cliente();

        cliente.setNome_cliente("Pedro Vilbert ");
        cliente.setEmail_cliente("Pedro@gmail.com");
        cliente.setSenha_cliente("2005");

        ClienteDAO dao = new ClienteDAO();
        dao.inserir(cliente);
    }

    @Test
    public void remover(){
        Cliente cliente = new Cliente();

        cliente.setId(2L);

        ClienteDAO dao = new ClienteDAO();
        dao.remover(cliente);
    }

    @Test
    public void salvarAlterar(){
        Cliente cliente  = new Cliente();

        cliente.setId(1L);
        cliente.setNome_cliente("William o Maior Gremista");
        cliente.setEmail_cliente("WillGremioOmaior@gmail.com");
        cliente.setSenha_cliente("1903");

        ClienteDAO dao = new ClienteDAO();
        dao.salvarAlterar(cliente);
    }

    @Test
    public void listar(){
        ClienteDAO dao = new ClienteDAO();
        for(Cliente cliente : dao.listar()){
            System.out.print("\nID: " + cliente.getId());

            System.out.print("\tNome: " + cliente.getNome_cliente());
        }
    }
}
