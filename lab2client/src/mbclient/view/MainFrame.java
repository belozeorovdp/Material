package mbclient.view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import mbcommon.Constants;
import mbcommon.IClient;
import mbcommon.IMessage;
import mbcommon.Message;
import mbcommon.User;
import mbclient.core.ILocalClient;
import mbclient.core.impl.Options;
import mbclient.exception.MBClientException;
import mbclient.util.ResourceLocator;

/**
 * Client application main window
 * 
 * @author hajo
 */
@SuppressWarnings("serial")
public class MainFrame extends javax.swing.JFrame implements
		PropertyChangeListener {

	private static final DefaultListModel model = new DefaultListModel();
	private ILocalClient client;
	
	public MainFrame() {
		initComponents();
		jListConnected.setModel(model);
		
		try {
			ResourceLocator.getClientAsObservable().addObserver(this);
			client = ResourceLocator.getLocalClient();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		// Should be done last
		this.setLocationRelativeTo(null);
	}

	// Methods ---------------------------------------------------------

	private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			client.connect();
		} catch (MBClientException e) {
			JOptionPane.showMessageDialog(this, e.getUserMessege());
		}
	}

	private void jButtonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			client.disconnect();
		} catch (MBClientException e) {
			JOptionPane.showMessageDialog(this, e.getUserMessege());
		}
	}

	private void jTextFieldSendActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			client.updateMessage(new Message(Options.getUser(), jTextFieldSend.getText()));
			jTextFieldSend.setText("");
		} catch (MBClientException e) {
			JOptionPane.showMessageDialog(this, e.getUserMessege());
		}
	}

	private void jMenuItemConnectionActionPerformed(
			java.awt.event.ActionEvent evt) {
		new ConnectionDialog(this).setVisible(true);
	}

	private void jMenuItemUserActionPerformed(java.awt.event.ActionEvent evt) {
		new UserDialog(this).setVisible(true);
	}

	private void jMenuItemDirectoriesActionPerformed(ActionEvent evt) {
		new DirectoriesDialog(this).setVisible(true);
	}

	private void exit() {
		client.disconnect();
		this.dispose();
		System.exit(0);
	}

	private void jListConnectedAction(MouseEvent e) {
		javax.swing.JList l = (JList) e.getSource();
		String other = (String) l.getSelectedValue();

		if (!other.equals(Options.getUser().getName())) 
			new PeerDialog(this, other).setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String pn = evt.getPropertyName();
		
		  if (pn.equals(Constants.StateChanges.CONNECTED.toString()))
			  setConnected();
		  else if (pn.equals(Constants.StateChanges.DISCONNECTED.toString()))
		  	  setDisconnected(); 
		  else if (pn.equals(Constants.StateChanges.MESSAGE.toString()))
			  updateMessage((IMessage) evt.getNewValue());
		  else if (pn.equals(Constants.StateChanges.USERS.toString()))
			  updateUsers((List<User>) evt.getNewValue());
		  else throw new IllegalArgumentException("Bad PropertyName");		 
	}

	private void setConnected() {
		jTextAreaReceive.setText("");
		jTextFieldSend.setText("");
		jLabelStatus.setText("Connected to: " + Options.getRegistryIp() + ":"
				+ Options.getRegistryPort() + " as "
				+ Options.getUser().getName());
		jTextFieldSend.setEnabled(true);
		jButtonConnect.setEnabled(false);
		jButtonDisconnect.setEnabled(true);
		jMenuOptions.setEnabled(false);
	}

	private void setDisconnected() {
		jTextAreaReceive.setText("");
		jTextFieldSend.setText("");
		model.clear();
		jLabelStatus.setText("Not connected");
		jTextFieldSend.setEnabled(false);
		jButtonConnect.setEnabled(true);
		jButtonDisconnect.setEnabled(false);
		jMenuOptions.setEnabled(true);
	}

	private void updateMessage(IMessage msg) {
		jTextAreaReceive.append(msg.getSender() + ":" + msg.getText()
				+ "\n");
		repaint();
	}

	private void updateUsers(List<User> users) {
		model.clear();
		for (User u : users)
			model.addElement(u.getName());
		repaint();
	}

	// DON'T TOUCH BELOW THIS -----------------------------------

	private void initComponents() {

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				MainFrame.this.exit();
			}
		});
		jToolBarMain = new javax.swing.JToolBar();
		jButtonConnect = new javax.swing.JButton();
		jButtonDisconnect = new javax.swing.JButton();
		jPanelInOut = new javax.swing.JPanel();
		jTextFieldSend = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaReceive = new javax.swing.JTextArea();
		jPanelList = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jListConnected = new javax.swing.JList();
		jPanel1 = new javax.swing.JPanel();
		jLabelStatus = new javax.swing.JLabel();
		jMenuBarMain = new javax.swing.JMenuBar();
		jMenuFile = new javax.swing.JMenu();
		jMenuItemExit = new javax.swing.JMenuItem();
		jMenuOptions = new javax.swing.JMenu();
		jMenuItemConnection = new javax.swing.JMenuItem();
		jMenuItemDirectories = new javax.swing.JMenuItem();
		jMenuItemUser = new javax.swing.JMenuItem();

		setTitle("MyBay");
		jToolBarMain.setRollover(true);
		jButtonConnect.setText("Connect");
		jButtonConnect.setFocusable(false);
		jButtonConnect
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButtonConnect
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButtonConnect.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonConnectActionPerformed(evt);
			}
		});
		jToolBarMain.add(jButtonConnect);
		jButtonDisconnect.setText("Disconnect");
		jButtonDisconnect.setFocusable(false);
		jButtonDisconnect.setEnabled(false);
		jButtonDisconnect
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButtonDisconnect
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButtonDisconnect
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonDisconnectActionPerformed(evt);
					}
				});
		jListConnected.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				jListConnectedAction(e);

			}

		});
		jToolBarMain.add(jButtonDisconnect);
		jPanelInOut.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Chat"));
		jTextFieldSend.setText("Enter = Send (broadcast)");
		jTextFieldSend.setEnabled(false);
		jTextFieldSend.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextFieldSendActionPerformed(evt);
			}
		});
		jTextAreaReceive.setColumns(20);
		jTextAreaReceive.setRows(5);
		jTextAreaReceive.setEditable(false);
		jScrollPane1.setViewportView(jTextAreaReceive);

		javax.swing.GroupLayout jPanelInOutLayout = new javax.swing.GroupLayout(
				jPanelInOut);
		jPanelInOut.setLayout(jPanelInOutLayout);
		jPanelInOutLayout
				.setHorizontalGroup(jPanelInOutLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelInOutLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelInOutLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																232,
																Short.MAX_VALUE)
														.addComponent(
																jTextFieldSend,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																232,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanelInOutLayout
				.setVerticalGroup(jPanelInOutLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelInOutLayout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												194, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextFieldSend,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(36, 36, 36)));

		jPanelList.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Connected"));
		jScrollPane2.setViewportView(jListConnected);
		javax.swing.GroupLayout jPanelListLayout = new javax.swing.GroupLayout(
				jPanelList);
		jPanelList.setLayout(jPanelListLayout);
		jPanelListLayout
				.setHorizontalGroup(jPanelListLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelListLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												172, Short.MAX_VALUE)
										.addContainerGap()));
		jPanelListLayout.setVerticalGroup(jPanelListLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanelListLayout
						.createSequentialGroup()
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.PREFERRED_SIZE, 189,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(89, Short.MAX_VALUE)));

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Status"));

		jLabelStatus.setText("Not connected");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabelStatus,
								javax.swing.GroupLayout.DEFAULT_SIZE, 444,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addComponent(jLabelStatus,
								javax.swing.GroupLayout.PREFERRED_SIZE, 27,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		jMenuFile.setText("File");
		jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				MainFrame.this.exit();
			}
		});
		jMenuItemExit.setText("Exit");
		jMenuFile.add(jMenuItemExit);
		jMenuBarMain.add(jMenuFile);
		jMenuOptions.setText("Options");
		jMenuItemConnection.setText("Connection");
		jMenuItemConnection
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemConnectionActionPerformed(evt);
					}
				});
		jMenuOptions.add(jMenuItemConnection);

		jMenuItemUser.setText("User");
		jMenuItemUser.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemUserActionPerformed(evt);
			}
		});
		jMenuOptions.add(jMenuItemUser);
		jMenuItemDirectories.setText("Directories");
		jMenuItemDirectories
				.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemDirectoriesActionPerformed(evt);
					}
				});

		jMenuOptions.add(jMenuItemDirectories);
		jMenuBarMain.add(jMenuOptions);
		setJMenuBar(jMenuBarMain);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jToolBarMain,
						javax.swing.GroupLayout.DEFAULT_SIZE, 478,
						Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jPanelInOut,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanelList,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jToolBarMain,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jPanelList,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jPanelInOut,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}

	private javax.swing.JButton jButtonConnect;
	private javax.swing.JButton jButtonDisconnect;
	private javax.swing.JLabel jLabelStatus;
	private javax.swing.JList jListConnected;
	private javax.swing.JMenuBar jMenuBarMain;
	private javax.swing.JMenu jMenuFile;
	private javax.swing.JMenuItem jMenuItemUser;
	private javax.swing.JMenuItem jMenuItemConnection;
	private javax.swing.JMenuItem jMenuItemDirectories;
	private javax.swing.JMenuItem jMenuItemExit;
	private javax.swing.JMenu jMenuOptions;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanelInOut;
	private javax.swing.JPanel jPanelList;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextAreaReceive;
	private javax.swing.JTextField jTextFieldSend;
	private javax.swing.JToolBar jToolBarMain;

}
