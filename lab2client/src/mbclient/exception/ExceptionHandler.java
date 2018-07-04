package mbclient.exception;

/**
 * Using Exception tunneling for checked exceptions
 * Used in catch clauses for checked exceptions
 * @author hajo
 *
 */
public final class ExceptionHandler {

   
  public static void rethrow(Exception e, String message) {   
    String details = e.getMessage();
    if (details != null) {
      if (details.indexOf(":") > 0) {
        details = details.substring(details.lastIndexOf(":"));
        throw new MBClientException(e, message + " [Details: " + details + "]");
      }
    }
    throw new MBClientException(e);
  }
}
