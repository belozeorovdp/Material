package common;

/**
 * Application global constants
 *
 * @author hajo
 *
 */
public class Constants {
   // public final static String MB_SERVER_NAME = "mbServerName";
   public final static String MB_SERVER_NAME = "127.0.0.1";
   public static final long SERIAL_V_ID = 1L;
   
   public enum StateChanges {
		   DISCONNECTED,
		   CONNECTED, 
		   USERS,
		   MESSAGE
		}
}
