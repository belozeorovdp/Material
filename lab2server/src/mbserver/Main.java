package mbserver;

import java.awt.EventQueue;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.Constants;
import common.IServer;
import mbserver.core.Server;
import mbserver.view.MainFrame;

/**
 * Server application Entry.
 *
 * @author hajo
 * 
 * NOTE: Application needs a policy file in
 * project directory, see security.policy
 *
 *    *********** NOTHING TO DO HERE SHOULD WORK ****************
 */ 
public class Main {

   /**
    * args[0] registry port
    * args[1] server port
    */


   public static void main(String[] args) {
      int registryPort = 8888;
      int serverPort = 7777;



//      if (args.length != 2) {
//         System.out.println("Must supply registry and server ports");
//         System.exit(1);
//      } else {
//         try {
//            registryPort = Integer.parseInt(args[0]);
//            serverPort = Integer.parseInt(args[1]);
//         } catch (NumberFormatException e) {
//            System.out.println("Bad registry and/or server ports");
//            System.exit(1);
//         }
//      }
//
//      System.out.println("Trying to start server with registryport " + args[0]
//            + " and server port " + args[1]);

      // Must have security manager and policy file
      SecurityManager sm = System.getSecurityManager();
      if (sm == null) {
         System.setSecurityManager(new SecurityManager());
      }

      try {
//         /*
//          * Create remote object
//          */
         final Server server = new Server(registryPort, serverPort);
//         /*
//          * Export object and get at stub (i.e. a proxy for the server,
//          * see Proxy design pattern
//          */
//         IServer stub = (IServer) UnicastRemoteObject.exportObject(server,
//               serverPort);
//         /*
//          * Create a registry
//          */
//         LocateRegistry.createRegistry(registryPort);
//         Registry registry = LocateRegistry.getRegistry(registryPort);
//         /*
//          * Bind the stub in the registry (a map) so that client can look it up
//          */
//         registry.rebind(Constants.MB_SERVER_NAME, stub);
//         System.out.println("Server stub bound in registry");

         // This is Swing don't need to understand for now
         EventQueue.invokeLater(new Runnable() {

            public void run() {
               new MainFrame(server).setVisible(true);
            }
         });
      } catch (Exception e) {
         System.out.println("Server exception: " + e.getMessage());
         e.printStackTrace();
      }
   }
}
