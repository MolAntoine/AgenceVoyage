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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        EntityManager em = emf.createEntityManager();
        Requetes r = new Requetes(em);
        AlgoGenetique g = new AlgoGenetique(em);
        for(int i =0;i<3;i++){
        int id1 = 1+random.nextInt(r.getNbGare());
        int id2 = 1+random.nextInt(r.getNbGare());
        while(id2 == id1){
            id2 = 1+random.nextInt(r.getNbGare());
        }
        Gare g1 = r.getGareById(1);
        Gare g2 = r.getGareById(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
        Date d2 = simpleDateFormat.parse("22:11:2022:07:00");
       
        TrajetUtilisateur tu = g.trouverCheminCourt(g1, g2, d2,5,0.9,0);
        List<Troncon> troncons  =   tu.getTrajet();
        if(!troncons.isEmpty()){
            points++;
            tu.calculate();
            System.out.println(String.valueOf(tu.getCout())+ " "+ String.valueOf(tu.getTemps()+tu.getTrajet().get(0).getTimeDep()-420));
        }
        for(Troncon trs : troncons){
            System.out.println(trs.getGareDepart().getNom()+" "+Integer.toString(trs.getTimeDep()) + "|" + trs.getGareArrivee().getNom()+" "+ Integer.toString(trs.getTimeDep()+trs.getTime()));
        }
            System.out.println(" \n");
        
        }
        return points;
    }

    
    
    
    
    
    public static void main(String[] args) throws ParseException{
        System.out.println(Integer.toString(testGenetique()));
//        System.out.println(Integer.toString(testPlusCourtChemin()));
    }
}
