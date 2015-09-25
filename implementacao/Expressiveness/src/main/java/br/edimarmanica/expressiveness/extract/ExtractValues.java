/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.expressiveness.extract;

import br.edimarmanica.dataset.Configuration;
import br.edimarmanica.dataset.Site;
import br.edimarmanica.expressiveness.generate.GenerateRules;
import static br.edimarmanica.expressiveness.generate.GenerateRules.getRules;
import br.edimarmanica.expressiveness.generate.beans.CypherRule;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandler;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandlerType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author edimar
 */
public class ExtractValues {

    private Neo4jHandler neo4j;
    private Site site;
    private Neo4jHandlerType type;

    public ExtractValues(Site site, Neo4jHandlerType type) {
        this.site = site;
        this.type = type;
    }

    /**
     *
     * @param site
     * @return Map<Attribute,Rule>
     */
    private Map<String, String> loadRules() {
        Map<String, String> rules = new HashMap<>();

        try (Reader in = new FileReader(Configuration.PATH_EXPRESSIVENESS + site.getPath() + "/generated_rules.csv")) {
            try (CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader())) {
                for (CSVRecord record : parser) {
                    rules.put(record.get("ATTRIBUTE"), record.get("RULE"));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractValues.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtractValues.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rules;
    }

    public void printExtractedValues() {
        neo4j = Neo4jHandler.getInstance(type, site);
        //Map<String, String> rules = loadRules();
        Map<String, CypherRule> rules = GenerateRules.getRules(site);

        for (String attr : rules.keySet()) {
            printExtractedValues(site, attr, rules.get(attr));
        }

        if (type == Neo4jHandlerType.LOCAL) {
            neo4j.shutdown();
        }
    }

    private void printExtractedValues(Site site, String attribute, CypherRule rule) {

        File dir = new File(Configuration.PATH_EXPRESSIVENESS + site.getPath() + "/extracted_values/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try (Writer out = new FileWriter(dir.getAbsolutePath() + "/" + attribute + ".csv")) {
            String[] header = {"URL", "EXTRACTED VALUE"};
            try (CSVPrinter csvFilePrinter = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(header))) {
                Map<String, String> extractedValues = neo4j.extract(rule.getQuery(), rule.getParams(), "URL", "VALUE");
                for (String url : extractedValues.keySet()) {
                    List<String> studentDataRecord = new ArrayList<>();
                    studentDataRecord.add(url);
                    studentDataRecord.add(extractedValues.get(url));
                    csvFilePrinter.printRecord(studentDataRecord);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ExtractValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ExtractValues extract = new ExtractValues(br.edimarmanica.dataset.weir.finance.Site.BIGCHARTS, Neo4jHandlerType.LOCAL);
        extract.printExtractedValues();
    }
}
