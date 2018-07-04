package mbclient.core.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import mbcommon.MBFile;
import mbclient.exception.ExceptionHandler;

/**
 * Utility for filehandling NOTE : See Java Best Practises 
 * for variations (web resource)
 * 
 * @author hajo
 * 
 *  THIS SHOULD WORK
 * 
 */
final class FileHandler {

  public static void store(MBFile file) {
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(Options.DOWNLOAD_DIR + "/"
          + file.getFileName());
      try {
        out.write(file.getBytes());
      } finally {
        out.close();
      }
    } catch (FileNotFoundException e) {
       ExceptionHandler.rethrow(e, "Couldn't find file: "
          + file.getFileName());
    } catch (IOException e) {
       ExceptionHandler.rethrow(e, "Problems reading file: "
             + file.getFileName());
    }
  }

  /*
   * NOTE: This will possibly use **A LOT** of memory Don't try to read a
   * movie!!
   */
  public static MBFile getFile(String fileName) {
    FileInputStream in = null;
    MBFile file = null;
    try {
      File f = new File(Options.UPLOAD_DIR + "/" + fileName);
      int size = (int) f.length();
      byte[] bytes = new byte[size];
      in = new FileInputStream(Options.UPLOAD_DIR + "/" + fileName);
      int nBytes = 0;
      try{
        nBytes = in.read(bytes);
      }finally{
        in.close(); 
      }
      if (nBytes != size) {
        throw new IOException("Exception while reading file");
      }
      file = new MBFile(fileName, bytes);
    } catch (IOException e) {
       ExceptionHandler.rethrow(e, "Problems getting file: "
             + fileName);
    } 
    return file;
  }
}
