package vue;

import java.awt.Color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author komaeda
 */
public class Application extends javax.swing.JFrame {
//    private final EntityManagerFactory emf;
    private final EntityManager em;
    private String utilisateur;
    private String motDePasse;
    

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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLayeredPane1 = new javax.swing.JLayeredPane();
        homePage = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        homePage.setMaximumSize(new java.awt.Dimension(1120, 630));
        homePage.setMinimumSize(new java.awt.Dimension(800, 450));
        homePage.setPreferredSize(new java.awt.Dimension(800, 450));
        homePage.setLayout(new java.awt.GridBagLayout());

        searchButton.setIcon(new javax.swing.ImageIcon("/home/komaeda/Downloads/icons8-rechercher-plus-100.png")); // NOI18N
        searchButton.setToolTipText("Recherche de trajets");
        searchButton.setFocusPainted(false);
        searchButton.setMinimumSize(new java.awt.Dimension(200, 200));
        searchButton.setPreferredSize(new java.awt.Dimension(200, 200));
        searchButton.setRequestFocusEnabled(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        homePage.add(searchButton, new java.awt.GridBagConstraints());

        jButton4.setIcon(new javax.swing.ImageIcon("/home/komaeda/Downloads/icons8-disconnect-100.png")); // NOI18N
        jButton4.setToolTipText("Recherche de trajets");
        jButton4.setMinimumSize(new java.awt.Dimension(200, 200));
        jButton4.setPreferredSize(new java.awt.Dimension(200, 200));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        homePage.add(jButton4, gridBagConstraints);

        jButton5.setIcon(new javax.swing.ImageIcon("/home/komaeda/Downloads/icons8-close-100.png")); // NOI18N
        jButton5.setToolTipText("Recherche de trajets");
        jButton5.setMinimumSize(new java.awt.Dimension(200, 200));
        jButton5.setPreferredSize(new java.awt.Dimension(200, 200));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        homePage.add(jButton5, gridBagConstraints);

        adminButton.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/home/komaeda/Downloads/icons8-add-100.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        adminButton.setToolTipText("Création d'utilisateurs");
        adminButton.setMinimumSize(new java.awt.Dimension(200, 200));
        adminButton.setPreferredSize(new java.awt.Dimension(200, 200));
        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        homePage.add(adminButton, gridBagConstraints);

        jButton7.setIcon(new javax.swing.ImageIcon("/home/komaeda/Downloads/icons8-list-100.png")); // NOI18N
        jButton7.setToolTipText("Vos trajets");
        jButton7.setMinimumSize(new java.awt.Dimension(200, 200));
        jButton7.setPreferredSize(new java.awt.Dimension(200, 200));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        homePage.add(jButton7, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(homePage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homePage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homePage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jLayeredPane1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void adminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminButtonActionPerformed
        // TODO add your handling code here:
        this.adminButton.setVisible(false);
    }//GEN-LAST:event_adminButtonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    public static void main(String args[]) {
        Application a = new Application();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminButton;
    private javax.swing.JPanel homePage;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
