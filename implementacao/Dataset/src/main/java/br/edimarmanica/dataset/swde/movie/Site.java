/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.dataset.swde.movie;
import br.edimarmanica.dataset.swde.Domain;
import java.io.File;

/**
 *
 * @author edimar
 */
public enum Site implements br.edimarmanica.dataset.Site {

    ALLMOVIE("movie-allmovie"), AMCTV("movie-amctv"), BOXOFFICEMOJO("movie-boxofficemojo"), HOLLYWOOD("movie-hollywood"),
    IHEARTOVIES("movie-iheartmovies"), IMDB("movie-imdb"), METACRITIC("movie-metacritic"), MSN("movie-msn"), 
    ROTTENTOMATOES("movie-rottentomatoes"), YAHOO("movie-yahoo");
                               
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
        return Domain.MOVIE;
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