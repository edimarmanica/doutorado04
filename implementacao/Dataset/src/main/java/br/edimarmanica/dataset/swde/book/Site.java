/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.dataset.swde.book;
import br.edimarmanica.dataset.swde.Domain;
import java.io.File;

/**
 *
 * @author edimar
 */
public enum Site implements br.edimarmanica.dataset.Site {

    ADEBOOKS("book-abebooks"), AMAZON("book-amazon"), BARNANDNOBLE("book-barnesandnoble"), BOOKDEPOSITORY("book-bookdepository"),
    BOOKSAMILLION("book-booksamillion"), BORDERS("book-borders"), BUY("book-buy"), CHRISTIANBOOK("book-christianbook"), 
    DEEPDISCOUNT("book-deepdiscount"), WATERSTONES("book-waterstones");
    
    private String folderName;

    private Site(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String getFolderName() {
        return folderName;
    }

    @Override
    public Domain getDomain() {
        return Domain.BOOK;
    }

    @Override
    public String getPath() {
        return getDomain().getPath() + File.separator + getFolderName();
    }
    
    @Override
    public String getGroundTruthPath(){
        return getDomain().getDataset().getFolderName() + File.separator + "groundtruth/" + getDomain().getFolderName() + File.separator + getFolderName()+"-!attrName!.txt";
    }

    @Override
    public String getEntityPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}