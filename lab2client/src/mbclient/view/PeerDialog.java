package mbclient.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import mbcommon.IPeer;

/**
 * Window for direct connections to other client
 * (peer 2 peer)
 * 
 * @author hajo
 */
@SuppressWarnings("serial")
public class PeerDialog extends javax.swing.JDialog {

   private IPeer peer; // This is a reference to another Client

   // other is name of User in other Client
   public PeerDialog(JFrame parent, String other) {
      super(parent, "Peer", true);
      initComponents();
      this.setTitle("Files : " + other);
      this.setLocationRelativeTo(parent);
   }

   // Methods ---------------------------------------------------
  
   private void jButtonDownloadActionPerformed(java.awt.event.ActionEvent evt) {
      final String fileName = (String) jListFiles.getSelectedValue();
      if (fileName == null) {
         return;
      }
      // Possible time consuming use a SwingWorker
      SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
         public Void doInBackground() {
           
            	// TODO Use the client as a peer
        	 
            return null;  // Should return a Void object
         }

         public void done() {
            dispose();
         }
      };
      worker.execute();
   }

   private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
      dispose();
   }

   // --------------------------------------------------------------

   private void initComponents() {
      // Can use this to perform things
      // when window opens.
      /*
       * This is how to fill the file list
       * 
       *  DefaultListModel model = new DefaultListModel();
       *  for (String f : files) {
       *      model.addElement(f);
       *  }
       *  PeerDialog.this.jListFiles.setModel(model);
       *  
       *  NOTE: Must have "this" because it's an inner class
       *  this means access the outer class fields jListFiles
       */
      this.addWindowListener(new WindowAdapter() {

         public void windowOpened(WindowEvent evt) {
           // TODO 
         }
      });

      // DON'T TOUCH BELOW THIS -----------------------------------

      jPanel1 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jListFiles = new javax.swing.JList();
      jButtonDownload = new javax.swing.JButton();
      jButtonCancel = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Files"));
      jScrollPane1.setViewportView(jListFiles);

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
            jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addGroup(
            jPanel1Layout.createSequentialGroup().addContainerGap()
                  .addComponent(jScrollPane1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 204,
                        Short.MAX_VALUE).addContainerGap()));
      jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addGroup(
            jPanel1Layout.createSequentialGroup().addComponent(jScrollPane1,
                  javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                  .addContainerGap()));

      jButtonDownload.setText("Download");
      jButtonDownload.addActionListener(new java.awt.event.ActionListener() {

         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonDownloadActionPerformed(evt);
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
      layout
            .setHorizontalGroup(layout
                  .createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(
                        layout
                              .createSequentialGroup()
                              .addGroup(
                                    layout
                                          .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(
                                                layout
                                                      .createSequentialGroup()
                                                      .addGap(26, 26, 26)
                                                      .addComponent(
                                                            jButtonDownload)
                                                      .addGap(18, 18, 18)
                                                      .addComponent(
                                                            jButtonCancel,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            93,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(
                                                layout
                                                      .createSequentialGroup()
                                                      .addContainerGap()
                                                      .addComponent(
                                                            jPanel1,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addContainerGap(
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    Short.MAX_VALUE)));
      layout.setVerticalGroup(layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING).addGroup(
            javax.swing.GroupLayout.Alignment.TRAILING,
            layout.createSequentialGroup().addContainerGap().addComponent(
                  jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addGap(18, 18, 18).addGroup(
                        layout.createParallelGroup(
                              javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jButtonDownload).addComponent(
                                    jButtonCancel)).addContainerGap()));

      pack();
   }

   private javax.swing.JButton jButtonCancel;
   private javax.swing.JButton jButtonDownload;
   private javax.swing.JList jListFiles;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JScrollPane jScrollPane1;

}
