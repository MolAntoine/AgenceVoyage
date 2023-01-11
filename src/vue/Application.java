/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import metier.AlgoGenetique;
import metier.Requetes;
import modele.Gare;
import modele.TrajetUtilisateur;

/**
 *
 * @author komaeda
 */
public class Application extends javax.swing.JFrame {
//    private final EntityManagerFactory emf;
    private final EntityManager em;
    private String utilisateur;
    private String motDePasse;
    
    /**
     * Creates new form Accueil
     */
    public Application() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        this.em = emf.createEntityManager();
        initComponents();
        initFenetre();
    }

    public Application(EntityManager em, String username, String passwd) {
        this.em = em;
        utilisateur = username;
        motDePasse = passwd;
        initComponents();
        initFenetre();
    }
    
    public void initFenetre(){
        this.setVisible(true);
        this.setLocation(500, 500);
        this.setTitle("sncf.gouv");
        this.getContentPane().setBackground(new Color(245,250,235));
        rechercheTrajet.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLayeredPane1 = new javax.swing.JLayeredPane();
        menu = new javax.swing.JPanel();
        rechercheTrajetBoutton = new javax.swing.JButton();
        rechercheTrajet = new javax.swing.JPanel();
        recherche = new javax.swing.JButton();
        sliderParam = new javax.swing.JSlider();
        jScrollPane1 = new javax.swing.JScrollPane();
        rechercheList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gareDepart = new javax.swing.JComboBox<>();
        gareArrivee = new javax.swing.JComboBox<>();
        checkPrix = new javax.swing.JCheckBox();
        checkDuree = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        dateDepart = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        heureDepart = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        menu.setMaximumSize(new java.awt.Dimension(1120, 630));
        menu.setMinimumSize(new java.awt.Dimension(800, 450));
        menu.setPreferredSize(new java.awt.Dimension(800, 450));
        menu.setLayout(new java.awt.GridBagLayout());

        rechercheTrajetBoutton.setToolTipText("Recherche de trajets");
        rechercheTrajetBoutton.setMinimumSize(new java.awt.Dimension(200, 200));
        rechercheTrajetBoutton.setPreferredSize(new java.awt.Dimension(200, 200));
        rechercheTrajetBoutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheTrajetBouttonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        menu.add(rechercheTrajetBoutton, gridBagConstraints);

        recherche.setText("Rechercher");
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        rechercheList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(rechercheList);

        jLabel1.setText("Durée");

        jLabel2.setText("Prix");

        jLabel3.setText("Gare de Départ :");

        jLabel4.setText("Gare d'Arrivée :");

        gareDepart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gareDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gareDepartActionPerformed(evt);
            }
        });

        gareArrivee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        checkPrix.setText("Prix");
        checkPrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPrixActionPerformed(evt);
            }
        });

        checkDuree.setText("Durée");
        checkDuree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDureeActionPerformed(evt);
            }
        });

        jLabel5.setText("Trier par :");

        dateDepart.setText("DD/MM/YYYY");
        dateDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateDepartActionPerformed(evt);
            }
        });

        jLabel6.setText("Date : DD/MM/YYYY");

        jLabel7.setText("Heure : HH:MM");

        heureDepart.setText("HH:MM");
        heureDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heureDepartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rechercheTrajetLayout = new javax.swing.GroupLayout(rechercheTrajet);
        rechercheTrajet.setLayout(rechercheTrajetLayout);
        rechercheTrajetLayout.setHorizontalGroup(
            rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gareArrivee, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                                .addComponent(sliderParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(dateDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(heureDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(gareDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3))
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel4)))
                .addGap(18, 50, Short.MAX_VALUE)
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addComponent(checkPrix)
                        .addGap(18, 18, 18)
                        .addComponent(checkDuree))
                    .addComponent(jLabel5))
                .addGap(46, 46, 46))
            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recherche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        rechercheTrajetLayout.setVerticalGroup(
            rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gareDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rechercheTrajetLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkPrix)
                            .addComponent(checkDuree))
                        .addGap(18, 18, 18)))
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gareArrivee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(heureDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sliderParam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rechercheTrajetLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(10, 10, 10))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rechercheTrajetLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(10, 10, 10))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jLayeredPane1.setLayer(menu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rechercheTrajet, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rechercheTrajet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rechercheTrajet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jLayeredPane1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rechercheTrajetBouttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheTrajetBouttonActionPerformed
        // TODO add your handling code here:
        
       //gestion des GARES
       menu.setVisible(false);
       rechercheTrajet.setVisible(true);
       
       DefaultComboBoxModel gareSelection1 = new  DefaultComboBoxModel();
       DefaultComboBoxModel gareSelection2 = new  DefaultComboBoxModel();
       Requetes r = new Requetes(em);
       List<Gare> gares = r.getGares();
       for(Gare g : gares){
       gareSelection1.addElement(g);
       gareSelection2.addElement(g);
       }
       gareDepart.setModel(gareSelection1);
       gareArrivee.setModel(gareSelection2);
       
       //gestion des DATES
       
       
       MaskFormatter dateFormatter = null;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
       dateDepart.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
        try {
            dateFormatter = new MaskFormatter("##:##");
        } catch (ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        heureDepart.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
       
        //Gestion des checkbox
        checkDuree.setSelected(true);
       
    }//GEN-LAST:event_rechercheTrajetBouttonActionPerformed

    private void checkDureeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDureeActionPerformed
        // TODO add your handling code here:
        checkPrix.setSelected(false);
    }//GEN-LAST:event_checkDureeActionPerformed

    private void gareDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gareDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gareDepartActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        try {
            // TODO add your handling code here:
            AlgoGenetique algo = new AlgoGenetique(em);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            
            Date date = dateFormat.parse(dateDepart.getText());
            Date time = timeFormat.parse(heureDepart.getText());
            
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.setTime(time);
            
            dateCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
            dateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
            
            SimpleDateFormat finalFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
            Date finalDate = finalFormat.parse(finalFormat.format(dateCalendar.getTime()));
            double svalue = ((double)sliderParam.getValue())/((double)sliderParam.getMaximum()-(double)sliderParam.getMinimum());
            System.out.println(String.valueOf(svalue));
            TrajetUtilisateur tr = algo.trouverCheminCourt((Gare)gareDepart.getSelectedItem(),(Gare)gareArrivee.getSelectedItem(), date,6,1-svalue,svalue);
            while(tr.getTrajet().isEmpty()){
                tr = algo.trouverCheminCourt((Gare)gareDepart.getSelectedItem(),(Gare)gareArrivee.getSelectedItem(), date,6,1-svalue,svalue);
            }
            tr.calculate();
            DefaultListModel dlm = new DefaultListModel();
            dlm.addElement(tr);
            
            rechercheList.setModel(dlm);
        
           
        } catch (ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rechercheActionPerformed

    private void dateDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateDepartActionPerformed

    private void heureDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heureDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heureDepartActionPerformed

    private void checkPrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPrixActionPerformed
        // TODO add your handling code here:
        checkDuree.setSelected(false);
    }//GEN-LAST:event_checkPrixActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Application a = new Application();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkDuree;
    private javax.swing.JCheckBox checkPrix;
    private javax.swing.JFormattedTextField dateDepart;
    private javax.swing.JComboBox<String> gareArrivee;
    private javax.swing.JComboBox<String> gareDepart;
    private javax.swing.JFormattedTextField heureDepart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private javax.swing.JButton recherche;
    private javax.swing.JList<String> rechercheList;
    private javax.swing.JPanel rechercheTrajet;
    private javax.swing.JButton rechercheTrajetBoutton;
    private javax.swing.JSlider sliderParam;
    // End of variables declaration//GEN-END:variables
}
