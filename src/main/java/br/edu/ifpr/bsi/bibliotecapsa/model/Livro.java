package br.edu.ifpr.bsi.bibliotecapsa.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_livro")
public class Livro extends GenericModel {

    @Column(name = "nome_livro", nullable = false)
    private String nome_livro;

    @Column(name = "num_ex_livro", nullable = false)
    private int num_ex_livro;

    @Column(name = "autor_livro")
    private String autor_livro;

    @Column(name = "categoria_livro")
    private String categoria_livro;

    @Column(name = "local_livro")
    private String local_livro;


}
