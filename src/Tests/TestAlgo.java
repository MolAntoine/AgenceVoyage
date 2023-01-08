/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import metier.AlgoGenetique;
import metier.PlusCourtChemin;
import metier.Requetes;
import modele.Gare;
import modele.TrajetUtilisateur;
import modele.Troncon;

/**
 *
 * @author Dober
 */
public class TestAlgo {
    
    
    private static final Random random = new Random();
    
    public static int testGenetique() throws ParseException{
        int points = 0;
        Requetes r = new Requetes();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        EntityManager em = emf.createEntityManager();
        AlgoGenetique g = new AlgoGenetique(em);
        for(int i =0;i<3;i++){
        int id1 = 1+random.nextInt(r.getNbGare(em));
        int id2 = 1+random.nextInt(r.getNbGare(em));
        while(id2 == id1){
            id2 = 1+random.nextInt(r.getNbGare(em));
        }
        Gare g1 = r.getGareById(em,1);
        Gare g2 = r.getGareById(em,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date d2 = simpleDateFormat.parse("07:00");
       
        //PlusCourtChemin c = new PlusCourtChemin();
        //List<Troncon> troncons = c.plusPetitTrajet(g1, g2, d2, em);
        TrajetUtilisateur tu = g.trouverCheminCourt(g1, g2, d2,5,0,0.9);
        List<Troncon> troncons  =   tu.getTrajet();
        if(!troncons.isEmpty()){
            points++;
            tu.calculate();
            System.out.println(String.valueOf(tu.getCout())+ " "+ String.valueOf(tu.getTemps()));
        }
        for(Troncon trs : troncons){
            System.out.println(trs.getGareDepart().getNom()+" "+Integer.toString(trs.gettimedep()) + "|" + trs.getGareArrivee().getNom()+" "+ Integer.toString(trs.gettimedep()+trs.gettime()));
        }
            System.out.println(" \n");
        
        }
        return points;
    }
    public static int testPlusCourtChemin() throws ParseException{
                int points = 0;
        Requetes r = new Requetes();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        EntityManager em = emf.createEntityManager();
        for(int i =0;i<10;i++){
        int id1 = 1+random.nextInt(r.getNbGare(em));
        int id2 = 1+random.nextInt(r.getNbGare(em));
        while(id2 == id1){
            id2 = 1+random.nextInt(r.getNbGare(em));
        }
        Gare g1 = r.getGareById(em,id1);
        Gare g2 = r.getGareById(em,id2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date d2 = simpleDateFormat.parse("07:00");
        PlusCourtChemin c = new PlusCourtChemin();
        if (!c.plusPetitTrajet(g1, g2, d2, em).isEmpty()){
            points++;
        }
        }
        return points;
    }
    
    
    
    
    
    public static void main(String[] args) throws ParseException{
        System.out.println(Integer.toString(testGenetique()));
//        System.out.println(Integer.toString(testPlusCourtChemin()));
    }
}
