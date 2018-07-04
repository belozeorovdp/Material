package mbcommon;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Utility, wrapping bytes from a file.
 *
 * @author hajo
 *
 */
public class MBFile implements Serializable {
  
    private static final long serialVersionUID = Constants.SERIAL_V_ID;
    private final byte[] bytes;
    private final String fileName;
    
    public MBFile( String fileName, byte[] bytes ){
        this.bytes = Arrays.copyOf(bytes, bytes.length);
        this.fileName = fileName;
    }
   
    public byte[] getBytes(){
        return Arrays.copyOf(bytes, bytes.length);
    }
    
    public String getFileName(){
        return fileName;
    }
    
    @Override
    public String toString(){
      return String.valueOf(bytes);  
    }
}
