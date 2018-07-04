package mbcommon;

/**
 * Application global constants
 *
 * @author hajo
 *
 */
public class Constants {
   public final static String MB_SERVER_NAME = "mbServerName";
   public static final long SERIAL_V_ID = 1L;
   
   public enum StateChanges {
		   DISCONNECTED,
		   CONNECTED, 
		   USERS,
		   MESSAGE
		}
}
