package mbclient.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import mbcommon.User;
import mbclient.core.impl.Options;

/**
 * Dialog box to set user data
 * (connects to server as this user)
 * @author  hajo
 */
//  DON'T TOUCH BELOW THIS -----------------------------------

@SuppressWarnings("serial")
public class UserDialog extends javax.swing.JDialog {

  public UserDialog(JFrame parent) {
    super(parent, "Peer", true);
    initComponents();
    this.setLocationRelativeTo(parent);
  }

  // Methods ------------------------------------

  private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {
    User u = new User(jTextFieldUserName.getText(), jTextFieldUserPasswd
        .getText());
    Options.note(u);
    this.dispose();
  }

  private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
    this.dispose();
  }

  // ----------------------------------------------

  private void initComponents() {
    // Don't need to understand this right now, it's an inner class
    this.addWindowListener(new WindowAdapter() {

      public void windowOpened(WindowEvent e) {
        User u = Options.getUser();
        UserDialog.this.jTextFieldUserName.setText(u.getName());
        UserDialog.this.jTextFieldUserPasswd.setText(u.getPasswd());
      }

    });

   

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldUserName = new javax.swing.JTextField();
    jTextFieldUserPasswd = new javax.swing.JTextField();
    jButtonOk = new javax.swing.JButton();
    jButtonCancel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("User");

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));

    jLabel1.setText("Name");

    jLabel2.setText("Passwd");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 51,
                Short.MAX_VALUE).addComponent(jLabel2)).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(
                jTextFieldUserPasswd).addComponent(jTextFieldUserName,
                javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
            .addContainerGap(42, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel2).addComponent(jTextFieldUserPasswd,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(34,
            Short.MAX_VALUE)));

    jButtonOk.setText("OK");
    jButtonOk.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonOkActionPerformed(evt);
      }
    });

    jButtonCancel.setText("Cancel");
    jButtonCancel.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonCancelActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
        getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        javax.swing.GroupLayout.Alignment.TRAILING,
        layout.createSequentialGroup().addGap(37, 37, 37).addComponent(
            jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42,
            Short.MAX_VALUE).addComponent(jButtonCancel).addGap(37, 37, 37))
        .addGroup(
            layout.createSequentialGroup().addContainerGap().addComponent(
                jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addGap(79, 79, 79)
            .addGroup(
                layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                    jButtonCancel).addComponent(jButtonOk)).addContainerGap(17,
                Short.MAX_VALUE)));

    pack();
  }

  private javax.swing.JButton jButtonCancel;
  private javax.swing.JButton jButtonOk;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField jTextFieldUserName;
  private javax.swing.JTextField jTextFieldUserPasswd;

}
