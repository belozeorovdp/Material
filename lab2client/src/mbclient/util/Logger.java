package mbclient.util;

/**
 * Utility 
 *
 * @author hajo
 *
 */
public final class Logger{
    public static void handle( Exception e){
        // Print for now. Should use Log4J or other framework
        e.printStackTrace();
    }
}
