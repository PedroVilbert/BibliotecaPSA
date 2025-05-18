package br.edu.ifpr.bsi.bibliotecapsa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_bibliotecario")
public class Bibliotecario extends GenericModel{

    @Column(name = "nome_bibliotecario", length = 100)
    private String nome_bibliotecario;

    @Column(name = "senha_bibliotecario")
    private String senha_bibliotecario;

    @Column(name = "email_bibliotecario", length = 120)
    private String email_bibliotecario;

    public String getNome_bibliotecario() {
        return nome_bibliotecario;
    }

    public void setNome_bibliotecario(String nome_bibliotecario) {
        this.nome_bibliotecario = nome_bibliotecario;
    }

    public String getSenha_bibliotecario() {
        return senha_bibliotecario;
    }

    public void setSenha_bibliotecario(String senha_bibliotecario) {
        this.senha_bibliotecario = senha_bibliotecario;
    }

    public String getEmail_bibliotecario() {
        return email_bibliotecario;
    }

    public void setEmail_bibliotecario(String email_bibliotecario) {
        this.email_bibliotecario = email_bibliotecario;
    }


}
