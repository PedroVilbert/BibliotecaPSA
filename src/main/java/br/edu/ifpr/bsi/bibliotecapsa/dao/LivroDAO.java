package br.edu.ifpr.bsi.bibliotecapsa.dao;

import br.edu.ifpr.bsi.bibliotecapsa.helpers.HibernateHelper;
import br.edu.ifpr.bsi.bibliotecapsa.model.Livro;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class LivroDAO extends GenericDAO<Livro> {

    public Livro buscarPorNome(String nomeLivro) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Livro livro = null;

        try {
            Query<Livro> query = session.createQuery(
                    "FROM Livro WHERE nome_livro = :nome", Livro.class);
            query.setParameter("nome", nomeLivro);
            livro = query.uniqueResult();
        } finally {
            session.close();
        }

        return livro;
    }
}
