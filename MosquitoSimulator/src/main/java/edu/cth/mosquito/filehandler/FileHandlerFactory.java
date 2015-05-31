/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.filehandler;

/**
 * A filehandler factory that returns a type of filehandler
 * 
 * @author Mosquito
 */
public class FileHandlerFactory {
    
    public IFileHandler getFileHandler(String fileHandlerType){
        if (fileHandlerType == null){
            return null;
        } else if (fileHandlerType.equals("FileHandler")){
            return new FileHandler();
        }
        return null;
    }
}
