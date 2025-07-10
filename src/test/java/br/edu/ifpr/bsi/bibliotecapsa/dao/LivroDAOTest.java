package br.edu.ifpr.bsi.bibliotecapsa.dao;

import br.edu.ifpr.bsi.bibliotecapsa.model.Livro;
import org.junit.jupiter.api.Test;

public class LivroDAOTest {

    @Test
    public void inserir(){
        Livro livro = new Livro();

        livro.setNome_livro("Teste");
        livro.setCategoria_livro("Teste");
        livro.setAutor_livro("Teste");
        livro.setNum_ex_livro(7);
        livro.setLocal_livro("Teste");

        LivroDAO dao = new LivroDAO();
        dao.inserir(livro);
    }

    @Test
    public void remover(){
        Livro livro = new Livro();

        livro.setId(11L);

        LivroDAO dao = new LivroDAO();
        dao.remover(livro);
    }

    @Test
    public void listar(){
        LivroDAO dao = new LivroDAO();
        for(Livro livro : dao.listar()){
            System.out.print("\nID: " + livro.getId());
            System.out.print("\nNome: " + livro.getNome_livro());
            System.out.print(("\nNumero exemplares: " + livro.getNum_ex_livro()));
        }
    }

    @Test
    public void salvarAlterar(){
        Livro livro = new Livro();

        livro.setId(1L);
        livro.setNome_livro("Inferno de Dante");
        livro.setCategoria_livro("Filosofia");
        livro.setAutor_livro("Dante");
        livro.setNum_ex_livro(10);
        livro.setLocal_livro("Estante B2");

        LivroDAO dao = new LivroDAO();
        dao.salvarAlterar(livro);
    }
}
