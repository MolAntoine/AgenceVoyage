/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import metier.Fichier;

/**
 *
 * @author komaeda
 */
public class Test2 {
    
    
    public static void main(String[] args) {
        Fichier fGares = new Fichier("gares.txt");
        Fichier fTrajets = new Fichier("trajets.txt");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        final EntityManager em = emf.createEntityManager();
        try{
            final EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                
                fGares.traiterFicGares(em);
                
                et.commit();
                
                et.begin();
                
                fTrajets.traiterFicTrajets(em);
                
                et.commit();
            } catch (IOException ex) {
                et.rollback();
            } catch (ParseException ex) {
                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
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
