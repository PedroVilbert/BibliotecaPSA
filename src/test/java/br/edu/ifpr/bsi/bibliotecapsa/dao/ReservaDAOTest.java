package br.edu.ifpr.bsi.bibliotecapsa.dao;



import br.edu.ifpr.bsi.bibliotecapsa.model.Reserva;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReservaDAOTest {


    @Test
    public void inserir(){
        try {
            ReservaDAO dao = new ReservaDAO();
            Reserva reserva = new Reserva();

            // Define um formato para as datas
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Preenchendo os campos
            reserva.setData_reserva(sdf.parse("23/06/2025"));
            reserva.setData_devolucao(sdf.parse("30/06/2025"));
            reserva.setCliente("Pedro Vilbert");
            reserva.setLivro("Introdução à Ciência da Computação");
            reserva.setValor_multa(10.50f);

            dao.inserir(reserva);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listar() {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> reservas = dao.listar();

        if (reservas != null && !reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                System.out.println("ID: " + reserva.getId());
                System.out.println("Livro: " + reserva.getLivro());
                System.out.println("Cliente: " + reserva.getCliente());
                System.out.println("Data da reserva: " + reserva.getData_reserva());
                System.out.println("Data de devolução: " + reserva.getData_devolucao());
                System.out.println("Valor da multa: " + reserva.getValor_multa());
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("Nenhuma reserva encontrada.");
        }
    }


}
