/*
 * ServerMainFrame.java
 *
 * Created on January 15, 2009, 9:45 AM
 */

package mbserver.view;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import common.Constants;
import common.IClient;
import common.IMessage;
import common.User;
import mbserver.core.Server;

/**
 * Server main window
 * @author  hajo
 * 
 *  *********** NOTHING TO DO HERE SHOULD WORK ****************
 *  
 */
public class MainFrame extends JFrame {

  private static final long serialVersionUID = Constants.SERIAL_V_ID;

  public MainFrame(Server server) {
    server.setView(this);
    initComponents();
    this.setTitle("SERVER Registry: " + server.getRegistryPort() + " Server: "
        + server.getServerPort());
    this.jTextAreaConnections.setEditable(false);
    this.jTextAreaMessages.setEditable(false);
    this.setLocationRelativeTo(null);
  }

  final DefaultListModel userData = new DefaultListModel();

  public void update(Map<User, IClient> users) {
    userData.clear();
    jTextAreaConnections.setText("");
    for (Entry<User, IClient> e : users.entrySet()) {
      userData.addElement(e.getKey());
      jTextAreaConnections.append(e.getKey().getName() + ":" + e.getValue()
          + "\n");
    }
    this.jListUsers.setModel(userData);
  }

  public void update(IMessage m) {
    this.jTextAreaMessages.append(m.getSender().getName() + ":" + m.getText()
        + "\n");
  }

  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextAreaConnections = new javax.swing.JTextArea();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    jTextAreaMessages = new javax.swing.JTextArea();
    jPanel3 = new javax.swing.JPanel();
    jScrollPaneUsers = new javax.swing.JScrollPane();
    jListUsers = new javax.swing.JList();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setBorder(javax.swing.BorderFactory
        .createTitledBorder("Connections"));

    jTextAreaConnections.setColumns(20);
    jTextAreaConnections.setRows(5);
    jScrollPane1.setViewportView(jTextAreaConnections);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1,
        javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        jPanel1Layout.createSequentialGroup().addComponent(jScrollPane1,
            javax.swing.GroupLayout.PREFERRED_SIZE, 100,
            javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(14,
            Short.MAX_VALUE)));

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Messages"));

    jTextAreaMessages.setColumns(20);
    jTextAreaMessages.setRows(5);
    jScrollPane2.setViewportView(jTextAreaMessages);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2,
        javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2,
        javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE));

    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Users"));
    jScrollPaneUsers.setViewportView(jListUsers);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(
        jScrollPaneUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 168,
        Short.MAX_VALUE));
    jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(
        jScrollPaneUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 160,
        Short.MAX_VALUE));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
        getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(
                    layout.createSequentialGroup().addComponent(jPanel2,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(
                            javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            Short.MAX_VALUE))).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
            javax.swing.GroupLayout.Alignment.TRAILING,
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.TRAILING).addComponent(
                    jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE)));

    pack();
  }

  private javax.swing.JList jListUsers;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPaneUsers;
  private javax.swing.JTextArea jTextAreaConnections;
  private javax.swing.JTextArea jTextAreaMessages;

}
