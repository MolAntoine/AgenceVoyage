/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import metier.AlgoGenetique;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import metier.Fichier;
import metier.Individu;
import metier.PlusCourtChemin;
import metier.Requetes;
import modele.Gare;
import modele.TrajetUtilisateur;
import modele.Troncon;

/**
 *
 * @author komaeda
 */
public class TestReq {
    public static void main(String[] args) throws ParseException, InterruptedException {
        Requetes r = new Requetes();
        Fichier fGares = new Fichier("gares_test.txt");
        Fichier fTrajets = new Fichier("trajets_test.txt");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        final EntityManager em = emf.createEntityManager();
//        try{
//            final EntityTransaction et = em.getTransaction();
//            try{
//                et.begin();
//                
//                fGares.traiterFicGares(em);
//                
//                et.commit();
//                
//                et.begin();
//                
//                fTrajets.traiterFicTrajets(em);
//                
//                et.commit();
//            } catch (IOException ex) {
//                et.rollback();
//            } catch (ParseException ex) {
//                Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
//                et.rollback();
//            }
//            
//        } finally {
//            if(em != null && em.isOpen()){
//                em.close();
//            }
//            if(emf != null && emf.isOpen()){
//                emf.close();
//            }   
//        }
        Gare g1 = r.getGareDepuisNom(em, "le mans");
        Gare g2 = r.getGareDepuisNom(em, "nancy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date d2 = simpleDateFormat.parse("06:00");
        AlgoGenetique g = new AlgoGenetique();
        PlusCourtChemin c = new PlusCourtChemin();
        //TrajetUtilisateur tu = c.plusPetitTrajet(g1, g2, d2, em);
        TrajetUtilisateur tu = g.trouverCheminCourt(g1, g2, d2,em,5);
        tu.calculate();
        //System.out.println(tu.toString());
        System.out.println(String.valueOf(tu.getCout())+ " "+ String.valueOf(tu.getTemps()));
        List<Troncon> troncons  = tu.getTrajet();
        for(Troncon trs : troncons){
            System.out.println(trs.getGareDepart().getNom()+" "+Integer.toString(trs.gettimedep()) + "|" + trs.getGareArrivee().getNom()+" "+ Integer.toString(trs.gettimedep()+trs.gettime()));
        }
            System.out.println(" \n");
        
        
//        tu = c.plusPetitTrajet(g1, g2, d2, em);
//        tu.calculate();
//        System.out.println(String.valueOf(tu.getCout())+ " "+ String.valueOf(tu.getTemps()));
//        troncons  = tu.getTrajet();
//        for(Troncon trs : troncons){
//            System.out.println(trs.getGareDepart().getNom()+" "+Integer.toString(trs.gettimedep()) + "|" + trs.getGareArrivee().getNom()+" "+ Integer.toString(trs.gettimedep()+trs.gettime()));
//        }
//            System.out.println(" \n");
//          
//        
        
        
    }
}
