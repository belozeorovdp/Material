package mbclient.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import mbclient.core.impl.Connection;
import mbclient.core.impl.Options;

/**
 * Set connection data
 * @author  hajo
 */

// DON'T TOUCH BELOW THIS -----------------------------------

@SuppressWarnings("serial")
public class ConnectionDialog extends javax.swing.JDialog {

  public ConnectionDialog(JFrame parent) {
    super(parent, "Connection", true);
    initComponents();
    this.setLocationRelativeTo(parent);
  }

  // Methods -----------------------------------------------------------

  private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {
    Connection s = new Connection(jTextFieldServerIp.getText(), Integer
        .parseInt(jTextFieldServerPort.getText()), Integer
        .parseInt(jTextFieldMyPort.getText()));
    Options.note(s);
    this.dispose();

  }

  private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
    this.dispose();
  }

  // -----------------------------------------------------------

  private void initComponents() {
    this.addWindowListener(new WindowAdapter() {

      public void windowOpened(WindowEvent e) {
        ConnectionDialog.this.jTextFieldServerIp.setText(String.valueOf(Options
            .getRegistryIp()));
        ConnectionDialog.this.jTextFieldServerPort.setText(String
            .valueOf(Options.getRegistryPort()));
        ConnectionDialog.this.jTextFieldMyPort.setText(String.valueOf(Options
            .getMyPort()));
      }

    });

 

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldServerIp = new javax.swing.JTextField();
    jTextFieldServerPort = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jTextFieldMyPort = new javax.swing.JTextField();
    jButtonOk = new javax.swing.JButton();
    jButtonCancel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Connection");
    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Server"));
    jLabel1.setText("IP");
    jLabel2.setText("Port");
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
                jTextFieldServerPort).addComponent(jTextFieldServerIp,
                javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
            .addContainerGap(42, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jTextFieldServerIp, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel2).addComponent(jTextFieldServerPort,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("My"));

    jLabel3.setText("Port");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(
            jLabel3).addGap(33, 33, 33).addComponent(jTextFieldMyPort,
            javax.swing.GroupLayout.PREFERRED_SIZE, 113,
            javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(45,
            Short.MAX_VALUE)));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(
            jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel3).addComponent(jTextFieldMyPort,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.TRAILING).addComponent(
                    jPanel2, javax.swing.GroupLayout.Alignment.LEADING,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1,
                        javax.swing.GroupLayout.Alignment.LEADING,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
            jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27)
            .addGroup(
                layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                    jButtonCancel).addComponent(jButtonOk)).addContainerGap(21,
                Short.MAX_VALUE)));

    pack();
  }

  private javax.swing.JButton jButtonCancel;
  private javax.swing.JButton jButtonOk;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JTextField jTextFieldMyPort;
  private javax.swing.JTextField jTextFieldServerIp;
  private javax.swing.JTextField jTextFieldServerPort;

}
