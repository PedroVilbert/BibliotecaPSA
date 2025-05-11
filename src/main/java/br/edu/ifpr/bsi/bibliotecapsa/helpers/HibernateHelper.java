package br.edu.ifpr.bsi.bibliotecapsa.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;

public class HibernateHelper {

    private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

    public static SessionFactory getFabricaDeSessoes() {
        return fabricaDeSessoes;
    }

    public static Connection getConexao() {
        Session sessao = fabricaDeSessoes.openSession();
        Connection conexao = sessao.doReturningWork(conn -> conn);
        return conexao;
    }

    private static SessionFactory criarFabricaDeSessoes() {
        try {

            StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registro).getMetadataBuilder().build();

            SessionFactory fabrica = metadata.getSessionFactoryBuilder().build();
            System.out.println("Fábrica de sessões criada corretamente.");
            return fabrica;
        } catch (Throwable ex) {
            System.err.println("A fábrica de sessões não pode ser criada." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
