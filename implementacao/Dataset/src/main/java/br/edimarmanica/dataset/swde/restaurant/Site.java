/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.dataset.swde.restaurant;
import br.edimarmanica.dataset.swde.Domain;
import java.io.File;

/**
 *
 * @author edimar
 */
public enum Site implements br.edimarmanica.dataset.Site {

    FODORS("restaurant-fodors"), GAYOT("restaurant-gayot"), PICKARESTAURANT("restaurant-pickarestaurant"), TRIPADVISOR("restaurant-tripadvisor"),
    USDINNERS("restaurant-usdiners"), FROMMERS("restaurant-frommers"), OPENTABLE("restaurant-opentable"), RESTAURANTICA("restaurant-restaurantica"), 
    URBANSPOON("restaurant-urbanspoon"), ZAGAT("restaurant-zagat");
                               
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
        return Domain.RESTAURANT;
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