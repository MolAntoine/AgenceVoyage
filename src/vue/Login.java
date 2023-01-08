package vue;

import java.awt.Color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import metier.Requetes;



/**
 *
 * @author komaeda
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        initFenetre();
        
    }
    
    public void initFenetre(){
        this.setVisible(true);
        this.setLocation(500, 500);
        this.setTitle("sncf.gouv");
        this.getContentPane().setBackground(new Color(245,250,235));
        this.jLabel1.hide();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        login = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        login.setLabel("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
        setMinimumSize(new java.awt.Dimension(344, 100));
        setPreferredSize(new java.awt.Dimension(500, 250));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 250));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        loginButton.setText("Se connecter");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(loginButton, gridBagConstraints);

        registerButton.setText("Inscription");
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 20);
        getContentPane().add(registerButton, gridBagConstraints);

        password.setText("jPasswordField1");
        password.setPreferredSize(new java.awt.Dimension(120, 18));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 16, 0);
        getContentPane().add(password, gridBagConstraints);

        username.setText("Nom d'utilisateur");
        username.setPreferredSize(new java.awt.Dimension(120, 18));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.5;
        getContentPane().add(username, gridBagConstraints);

        usernameLabel.setText("Nom d'utilisateur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.5;
        getContentPane().add(usernameLabel, gridBagConstraints);

        passwordLabel.setText("Mot de passe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.5;
        getContentPane().add(passwordLabel, gridBagConstraints);

        jLabel1.setForeground(new java.awt.Color(222, 50, 50));
        jLabel1.setText("⚠ Mauvais identifiants");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginActionPerformed
    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed
    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseClicked
        Register r = new Register();
    }//GEN-LAST:event_registerButtonMouseClicked

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        Requetes req = new Requetes();
        String username = this.username.getText();
        String passwd= "";
        char[] chars =this.password.getPassword();
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        final EntityManager em = emf.createEntityManager();
        
        for(char c : chars) passwd += c;
        
        if (username.isEmpty() || passwd.isEmpty()){
            JOptionPane.showMessageDialog(this, "Entrée invalide");
        }
        else {
            if(req.checkUtilisateur(em, username, passwd)){
                //connexion OK
            }
            else{
                //échec connexion
                this.jLabel1.show();
            }
        }
        
    }//GEN-LAST:event_loginButtonMouseClicked

    public static void main(String args[]) {
        Login l = new Login();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private java.awt.Button login;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
