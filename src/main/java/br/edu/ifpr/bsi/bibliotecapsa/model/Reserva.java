package br.edu.ifpr.bsi.bibliotecapsa.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_reserva")
public class Reserva extends GenericModel{

    @Column(name = "data_reserva")
    private Date data_reserva;

    @Column(name = "data_devolucao")
    private Date data_devolucao;

    @Column(name = "cliente_reserva")
    private String cliente;

    @Column(name = "livro_reserva")
    private String livro;

    @Column(name = "valor_multa")
    private Float valor_multa;

    @Column(name = "id_cliente")
    private Long id_cliente;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

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

    public String getCliente() {

        return cliente;
    }

    public void setCliente(String cliente) {

        this.cliente = cliente;
    }

    public String getLivro() {

        return livro;
    }

    public void setLivro(String livro) {

        this.livro = livro;
    }

    public Float getValor_multa() {

        return valor_multa;
    }

    public void setValor_multa(Float valor_multa) {

        this.valor_multa = valor_multa;
    }
}
