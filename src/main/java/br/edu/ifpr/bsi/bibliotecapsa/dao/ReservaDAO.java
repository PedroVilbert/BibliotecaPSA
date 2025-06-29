package br.edu.ifpr.bsi.bibliotecapsa.dao;

import br.edu.ifpr.bsi.bibliotecapsa.helpers.HibernateHelper;
import br.edu.ifpr.bsi.bibliotecapsa.model.Reserva;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReservaDAO extends GenericDAO<Reserva>{

    public List<Reserva> listarPorCliente(Long idCliente) {
        List<Reserva> resultado = new ArrayList<>();
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
            Root<Reserva> root = criteria.from(Reserva.class);

            criteria.select(root)
                    .where(builder.equal(root.get("id_cliente"), idCliente));

            resultado = session.createQuery(criteria).getResultList();
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar reservas por cliente: " + e.getMessage());
        } finally {
            session.close();
        }

        return resultado;
    }
}
