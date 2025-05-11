package br.edu.ifpr.bsi.bibliotecapsa.helpers;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class HibernateHelperTest {

    @Test
    public void conectar(){
        //capturar a fabrica de sessões e abre uma sessão
        //a fabrica é criada no HibernateHelper
        Session sessao = HibernateHelper.getFabricaDeSessoes().openSession();

        //fecha sessão
        sessao.close();

    }
}
