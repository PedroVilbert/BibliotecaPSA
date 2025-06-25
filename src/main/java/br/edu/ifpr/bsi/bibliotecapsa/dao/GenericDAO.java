package br.edu.ifpr.bsi.bibliotecapsa.dao;

import br.edu.ifpr.bsi.bibliotecapsa.helpers.HibernateHelper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class GenericDAO <Entidade>{
    private Class<Entidade> classe;

    @SuppressWarnings("unchecked")
    public GenericDAO(){
        this.classe = ( Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void inserir(Entidade entidade){
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try{

            transacao = session.beginTransaction();
            session.persist(entidade); // O metodo persist vai inserir dados no banco de dados
            transacao.commit();

        }catch (RuntimeException erro) {
            if (transacao != null){
                transacao.rollback();
            }
            System.out.println("Ocorreu um erro ao inserir os dados.");
        } finally {
            session.close();
        }
    }

    public void remover(Entidade entidade){
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try{

            transacao = session.beginTransaction();
            session.remove(entidade);
            transacao.commit();

        }catch (RuntimeException erro) {
            if (transacao != null){
                transacao.rollback();
            }
            System.out.println("Ocorreu um erro ao deletar os dados.");
        } finally {
            session.close();
        }
    }

    public void salvarAlterar(Entidade entidade){
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try{

            transacao = session.beginTransaction();
            // o metodo merge serve tanto para inserir quanto para atulaizar
            // Se o dado ja estiver no dado, ele vai alterar o que mudou
            // Se o dado não estiver no banco, ele vai inseri-lo
            session.merge(entidade);
            transacao.commit();

        }catch (RuntimeException erro) {
            if (transacao != null){
                transacao.rollback();
            }
            System.out.println("Ocorreu um erro ao alterar os dados.");
        } finally {
            session.close();
        }
    }


    public List<Entidade> listar(){

        List<Entidade> resultado = null;

        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entidade> criteria = builder.createQuery(classe);

            Root<Entidade> root = criteria.from(classe);
            criteria.select(root);
            resultado = session.createQuery(criteria).getResultList();

        }catch(RuntimeException erro){
            //Não precisa fazer rollback
            //porque não existe transação em uma consulta
            System.out.println("Ocorreu um erro ao listar os dados.");
        }finally{
            session.close();
        }
        return resultado;
    }


    public  Entidade buscarPorId(long _ID){

        Entidade resultado = null;

        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entidade> criteria = builder.createQuery(classe);

            Root<Entidade> root = criteria.from(classe);
            // Os critérios de busca são definidos aqui
            // o _ID é o nome de atributo que está nos parâmetros deste metodo
            // O where quer dizer que o comando SQL que estamos emulando é a clausula "WHERE"
            criteria.select(root).where(builder.equal(root.get("id"), Optional.of(_ID)));
            resultado = session.createQuery(criteria).getSingleResult();

        }catch(RuntimeException erro){
            //Não precisa fazer rollback
            //porque não existe transação em uma consulta
            System.out.println("Ocorreu um erro ao buscar a entidade.");
        }finally{
            session.close();
        }

        return resultado;

    }
}
