package br.edu.ifpr.bsi.bibliotecapsa.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_reserva")
public class Reserva extends GenericModel{

    @Column(name = "data_reserva", nullable = false)
    private Date data_reserva;

    @Column(name = "data_devolucao", nullable = false)
    private Date data_devolucao;

    @JoinColumn
    @OneToMany
    private Cliente cliente;

    @JoinColumn
    @OneToMany
    private Livro livro;

    @Column(name = "valor_multa", nullable = false)
    private Float valor_multa;

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Date getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(Date data_reserva) {
        this.data_reserva = data_reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Float getValor_multa() {
        return valor_multa;
    }

    public void setValor_multa(Float valor_multa) {
        this.valor_multa = valor_multa;
    }
}
