/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.Gare;
import modele.Train;
import modele.TrajetUtilisateur;
import modele.Troncon;
import modele.Utilisateur;

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
                Gare g2 = new Gare("test2","testtown2", 62470,10.6, 15.98);
                Gare g3 = new Gare("test3","testtown3", 62490,23.0, 18.0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date d = simpleDateFormat.parse("12:05");
                Date d2 = simpleDateFormat.parse("12:35");
                Troncon t = new Troncon(g,g2,152,d,d2);
                Date d3 = simpleDateFormat.parse("13:00");
                Troncon t2 = new Troncon(g2,g3,15,d2,d3);
                List<Troncon> ls = new ArrayList<>();
                ls.add(t);
                ls.add(t2);
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                Date tdate = simpleDateFormat2.parse("05/11/2022");
                Train tr = new Train(tdate, 2390, ls);
                TrajetUtilisateur tU = new TrajetUtilisateur(ls);
                tU.calculate();
                List<TrajetUtilisateur> ltu = new ArrayList<>();
                ltu.add(tU);
                Utilisateur u = new Utilisateur("1234", "mdp","jean", "kevin", "123 RUE JSP",tdate, false,ltu);
               
                em.persist(tr);
                em.persist(u);
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
