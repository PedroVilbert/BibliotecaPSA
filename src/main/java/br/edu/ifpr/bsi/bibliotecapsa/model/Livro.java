package br.edu.ifpr.bsi.bibliotecapsa.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_livro")
public class Livro extends GenericModel {

    @Column(name = "nome_livro")
    private String nome_livro;

    @Column(name = "num_ex_livro")
    private int num_ex_livro;

    @Column(name = "autor_livro")
    private String autor_livro;

    @Column(name = "categoria_livro")
    private String categoria_livro;

    @Column(name = "local_livro")
    private String local_livro;

    public String getNome_livro() {
        return nome_livro;
    }

    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    public int getNum_ex_livro() {
        return num_ex_livro;
    }

    public void setNum_ex_livro(int num_ex_livro) {
        this.num_ex_livro = num_ex_livro;
    }

    public String getAutor_livro() {
        return autor_livro;
    }

    public void setAutor_livro(String autor_livro) {
        this.autor_livro = autor_livro;
    }

    public String getCategoria_livro() {
        return categoria_livro;
    }

    public void setCategoria_livro(String categoria_livro) {
        this.categoria_livro = categoria_livro;
    }

    public String getLocal_livro() {
        return local_livro;
    }

    public void setLocal_livro(String local_livro) {
        this.local_livro = local_livro;
    }

}
