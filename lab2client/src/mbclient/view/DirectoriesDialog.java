package mbclient.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import mbclient.core.impl.Options;

/**
 * Set upload/download directories 
 * @author  hajo
 */

// DON'T TOUCH BELOW THIS -----------------------------------

@SuppressWarnings("serial")
public class DirectoriesDialog extends javax.swing.JDialog {

  private File up, down;

  public DirectoriesDialog(JFrame parent) {
    super(parent, "Directories", true);
    initComponents();
    this.setLocationRelativeTo(parent);
  }

  // METODS ---------------------------------------------------------------

  private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {
    Options.put(Options.UPLOAD_DIR, up);
    Options.put(Options.DOWNLOAD_DIR, down);
    dispose();
  }

  private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
    dispose();
  }

  private void jButtonBrowseUploadActionPerformed(java.awt.event.ActionEvent evt) {
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      up = fc.getSelectedFile();
      this.jTextFieldUploadDir.setText(up.getAbsolutePath());

    }
  }

  private void jButtonBrowseDownloadActionPerformed(
      java.awt.event.ActionEvent evt) {
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      down = fc.getSelectedFile();
      this.jTextFieldDownloadDir.setText(down.getAbsolutePath());
    }
  }

  //--------------------------------------------------------------------------

  private void initComponents() {
    this.addWindowListener(new WindowAdapter() {

      public void windowOpened(WindowEvent e) {

        DirectoriesDialog.this.jTextFieldDownloadDir
            .setText(Options.DOWNLOAD_DIR);
        DirectoriesDialog.this.jTextFieldUploadDir.setText(Options.UPLOAD_DIR);
      }

    });

   

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldUploadDir = new javax.swing.JTextField();
    jTextFieldDownloadDir = new javax.swing.JTextField();
    jButtonBrowseUpload = new javax.swing.JButton();
    jButtonBrowseDownload = new javax.swing.JButton();
    jButtonOk = new javax.swing.JButton();
    jButtonCancel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Directories");

    jPanel1.setBorder(javax.swing.BorderFactory
        .createTitledBorder("Directories"));

    jLabel1.setText("Upload");

    jLabel2.setText("Download");

    jButtonBrowseUpload.setText("Browse");
    jButtonBrowseUpload.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonBrowseUploadActionPerformed(evt);
      }
    });

    jButtonBrowseDownload.setText("Browse");
    jButtonBrowseDownload
        .addActionListener(new java.awt.event.ActionListener() {

          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonBrowseDownloadActionPerformed(evt);
          }
        });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64,
                Short.MAX_VALUE).addComponent(jLabel2)).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jTextFieldUploadDir, javax.swing.GroupLayout.PREFERRED_SIZE,
                111, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jTextFieldDownloadDir, javax.swing.GroupLayout.DEFAULT_SIZE,
                111, Short.MAX_VALUE)).addGap(12, 12, 12).addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(
                jButtonBrowseDownload, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBrowseUpload,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap()));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jTextFieldUploadDir, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jButtonBrowseUpload)).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
            jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel2).addComponent(jTextFieldDownloadDir,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jButtonBrowseDownload)).addContainerGap(32, Short.MAX_VALUE)));

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
        layout.createSequentialGroup().addGap(33, 33, 33).addComponent(
            jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120,
            Short.MAX_VALUE).addComponent(jButtonCancel).addGap(40, 40, 40))
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
            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
            javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jButtonOk).addComponent(jButtonCancel)).addContainerGap(30,
            Short.MAX_VALUE)));

    pack();
  }

  private javax.swing.JButton jButtonBrowseDownload;
  private javax.swing.JButton jButtonBrowseUpload;
  private javax.swing.JButton jButtonCancel;
  private javax.swing.JButton jButtonOk;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField jTextFieldUploadDir;
  private javax.swing.JTextField jTextFieldDownloadDir;

}
