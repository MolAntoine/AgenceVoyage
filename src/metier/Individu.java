/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;
import modele.Troncon;

/**
 *
 * @author Dober
 */
public class Individu {
    private final List<Troncon> tr;
    private double fitness;

    public List<Troncon> getTr() {
        return tr;
    }

    public Individu(List<Troncon> tr) {
        this.tr = tr;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }
    
}
