/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.intrasite.extract;

import br.edimarmanica.dataset.Configuration;
import br.edimarmanica.dataset.Site;
import br.edimarmanica.expressiveness.generate.beans.CypherRule;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandler;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandlerType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author edimar
 */
public class ExtractValues {

    private Neo4jHandler neo4j;
    private Site site;
    private Neo4jHandlerType type;
    private Set<CypherRule> rules;

    public ExtractValues(Site site, Neo4jHandlerType type, Set<CypherRule> rules) {
        this.site = site;
        this.type = type;
        this.rules = rules;
    }

    public void printExtractedValues() {
        
        deleteCurrentRules();
        
        neo4j = Neo4jHandler.getInstance(type, site);

        boolean append = false;
        int i = 0;
        for (CypherRule rule : rules) {

            printRuleInfo(rule, i, append);
            printExtractedValues(rule, i);

            append = true;
            i++;
        }

        if (type == Neo4jHandlerType.LOCAL) {
            neo4j.shutdown();
        }
    }

    private void printRuleInfo(CypherRule rule, int ruleID, boolean append) {
        File dir = new File(Configuration.PATH_INTRASITE + "/" + site.getPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        CSVFormat format;
        if (append) {
            format = CSVFormat.EXCEL;
        } else {
            String[] header = {"ID", "LABEL", "RULE"};
            format = CSVFormat.EXCEL.withHeader(header);
        }

        try (Writer out = new FileWriter(dir.getAbsolutePath() + "/rule_info.csv", append)) {

            try (CSVPrinter csvFilePrinter = new CSVPrinter(out, format)) {
                List<String> dataRecord = new ArrayList<>();
                dataRecord.add(ruleID + "");
                dataRecord.add(rule.getLabel());
                dataRecord.add(rule.getQueryWithoutParameters());
                csvFilePrinter.printRecord(dataRecord);
            }
        } catch (IOException ex) {
            Logger.getLogger(ExtractValues.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    private void printExtractedValues(CypherRule rule, int i) {

        File dir = new File(Configuration.PATH_INTRASITE + "/" + site.getPath() + "/extracted_values/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (Writer out = new FileWriter(dir.getAbsolutePath() + "/rule_" + i + ".csv")) {

            String[] header = {"URL", "EXTRACTED VALUE"};
            try (CSVPrinter csvFilePrinter = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(header))) {

                Map<String, String> extractedValues = neo4j.extract(rule.getQuery(), rule.getParams(), "URL", "VALUE");
                for (String url : extractedValues.keySet()) {
                    List<String> dataRecord = new ArrayList<>();
                    dataRecord.add(url);
                    dataRecord.add(extractedValues.get(url));
                    csvFilePrinter.printRecord(dataRecord);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ExtractValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteCurrentRules(){
        File dir = new File(Configuration.PATH_INTRASITE + "/" + site.getPath() + "/extracted_values/");
        if (dir.exists()) {
            for(File f: dir.listFiles()){
                f.delete();
            }
        }

    }
}
