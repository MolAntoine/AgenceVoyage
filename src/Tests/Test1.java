/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.Gare;

/**
 *
 * @author Dober
 */
public class Test1 {
        public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        final EntityManager em = emf.createEntityManager();
        try{
            final EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                Gare g = new Gare("test","testtown", 62370,10.0, 15.0);
                em.persist(g);

                et.commit();
                } catch (Exception ex) {
                et.rollback();
                }
             } finally {
                if(em != null && em.isOpen()){
                em.close();
                }
                if(emf != null && emf.isOpen()){
                emf.close();
                }   
         }
    }
}
