   package mbclient.core.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import mbcommon.User;

/**
 * To hold user option (menu options)
 *
 * @author hajo
 *
 */

//DON'T TOUCH BELOW THIS -----------------------------------

public final class Options {
  
    // Default options
    // TODO these must be written to file
    public static final String UPLOAD_DIR = "upload";
    public static final String DOWNLOAD_DIR = "download"; 
    private static Connection connection;
    private static User user;
    
    private static final Map<String, File> dirs = new HashMap<String, File>();
    
    static{
        dirs.put(UPLOAD_DIR, new File("upload"));
        dirs.put(DOWNLOAD_DIR, new File("download"));
    }
    
    private Options(){}
    
    public static void note(Connection connection) {
       Options.connection = connection; 
    }  
    
    public static void note(User user) {
        Options.user = user;
    }
    
    public static String getRegistryIp(){
        return connection.getRegistryIp();
    }
    
    public static int getRegistryPort(){
        return connection.getRegistryPort();
    }
    
    public static int getMyPort(){
        return connection.getMyPort();
    }
    
    // User immutable
    public static User getUser(){
        return user;
    }
        
    public static void put(String key, File file) {
       dirs.put(key, file);
    }

    public static File get(String key){
        return dirs.get(key);
    }
}
