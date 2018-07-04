package mbclient.exception;

import mbcommon.Constants;

/**
 * Higher level exception
 * @author hajo
 *
 */
public class MBClientException extends RuntimeException {
    
    private static final long serialVersionUID = Constants.SERIAL_V_ID;
    private String userMessage;
 
    public MBClientException(Exception e, String userMessage) {
        super(e);
        this.userMessage = userMessage;
    }
    
    public MBClientException(String string) {
        super(string);
    }
    
    public MBClientException(Exception e) {
      super(e);
    }

    public String getUserMessege(){
    	return userMessage;
    }
    
}
