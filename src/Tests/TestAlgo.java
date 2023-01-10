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
        Requetes r = new Requetes();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        EntityManager em = emf.createEntityManager();
        AlgoGenetique g = new AlgoGenetique(em);
        Gare g1 = r.getGareDepuisNom(em, "angers st. laud");
        Gare g2 = r.getGareDepuisNom(em, "reims centre");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
        Date d2 = simpleDateFormat.parse("25:01:2023:07:00");
        for(int i = 0;i<10;i++){
        TrajetUtilisateur tu = g.trouverCheminCourt(g1, g2, d2,5,0,1);
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
