package br.edimarmanica.metrics;

import br.edimarmanica.configuration.Paths;
import br.edimarmanica.dataset.Site;
import br.edimarmanica.metrics.swde.ResultsSWDE;
import br.edimarmanica.metrics.weir.ResultsWeir;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edimar
 */
public abstract class Results {

    protected Site site;

    public Results(Site site) {
        this.site = site;
    }

    public static Results getInstance(Site site) {
        switch (site.getDomain().getDataset()) {
            case SWDE:
                return new ResultsSWDE(site);
            case WEIR:
                return new ResultsWeir(site);
            default:
                return null;
        }
    }

    /**
     *
     * @return Map<Rule, Map<Url,Value>>
     */
    public Map<String, Map<String, String>> loadAllRules() {
        Map<String, Map<String, String>> myResults = new HashMap<>();

        File dir = new File(Paths.PATH_INTRASITE + "/" + site.getPath() + "/extracted_values");
        for (File rule : dir.listFiles()) {
            myResults.put(rule.getName(), loadRule(rule));
        }
        return myResults;
    }

    public Map<String, String> loadRule(File rule) {
        Map<String, String> values = new HashMap<>();
        try (Reader in = new FileReader(rule.getAbsolutePath())) {
            try (CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader())) {
                for (CSVRecord record : parser) {
                    values.put(formatUrl(record.get("URL")), formatValue(record.get("EXTRACTED VALUE")));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResultsWeir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResultsWeir.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }

    protected abstract String formatUrl(String url);

    protected abstract String formatValue(String value);
}