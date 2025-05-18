package br.edu.ifpr.bsi.bibliotecapsa.dao;

import br.edu.ifpr.bsi.bibliotecapsa.model.Bibliotecario;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.basic.BasicBorders;

public class BibliotecarioDAOTest {


    @Test
    public void inserir(){
        Bibliotecario bibliotecario = new Bibliotecario();

        bibliotecario.setNome_bibliotecario("Pedro Vilbert");
        bibliotecario.setEmail_bibliotecario("PedroVilbert@gmail.com");
        bibliotecario.setSenha_bibliotecario("123");

        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        //bibliotecarioDAO.inserir(bibliotecario);

        Bibliotecario bibliotecario2 = new Bibliotecario();

        bibliotecario2.setNome_bibliotecario("Vinicius");
        bibliotecario2.setEmail_bibliotecario("Vini@gmail.com");
        bibliotecario2.setSenha_bibliotecario("456");

        bibliotecarioDAO.inserir(bibliotecario2);
    }

    @Test
    public void remover(){
        Bibliotecario bibliotecario = new Bibliotecario();

        bibliotecario.setId(102L);

        BibliotecarioDAO dao = new BibliotecarioDAO();
        dao.remover(bibliotecario);
    }

    @Test
    public void salvarAlterar(){

        Bibliotecario bibliotecario = new Bibliotecario();

        bibliotecario.setId(52L);
        bibliotecario.setNome_bibliotecario("Vinicius Miuler");
        bibliotecario.setEmail_bibliotecario("viniReiDelas@gmail.com");
        bibliotecario.setSenha_bibliotecario("456");

        BibliotecarioDAO dao = new BibliotecarioDAO();
        dao.salvarAlterar(bibliotecario);
    }

    @Test
    public void listar(){
        BibliotecarioDAO dao = new BibliotecarioDAO();
        for (Bibliotecario bibliotecario : dao.listar()){
            System.out.print("\nID: " + bibliotecario.getId());
            System.out.print("\tNome: " + bibliotecario.getNome_bibliotecario());
        }
    }
}


















