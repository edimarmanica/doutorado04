/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.extractionrules.load;

import br.edimarmanica.extractionrules.neo4j.Neo4JHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.neo4j.graphdb.Transaction;
import org.xml.sax.SAXException;
import org.cyberneko.html.parsers.DOMParser;

/**
 *
 * load a webPage to the neo4j
 *
 * @author edimar
 */
public class HtmlToNeo4j {

    private Neo4JHandler neo4j;
    private String url;
    private FormatUniquePath formatter;

    public HtmlToNeo4j(String url, Neo4JHandler neo4j) {
        this.url = url;
        this.neo4j = neo4j;
        
        formatter = new FormatUniquePath(url); //só pode instanciar uma vez por página para funcionar corretamente
    }

    private void insertAllChildren(Element el, org.neo4j.graphdb.Node neo4jNode) {
        for (Iterator i = el.nodeIterator(); i.hasNext();) {
            Node child = (Node) i.next();

            if (child.getNodeType() == Node.ELEMENT_NODE) {
                org.neo4j.graphdb.Node newNeo4jNode;
                try {
                    newNeo4jNode = insertNeo4j(child, neo4jNode);
                    insertAllChildren((Element) child, newNeo4jNode);
                } catch (InvalidTextNode ex) {
                    //só ignora o nodo
                    //  Logger.getLogger(HtmlToNeo4j.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (child.getNodeType() == Node.TEXT_NODE) {
                //      System.out.println("child: "+child.getPath()+"-"+child.getName()+""+"-"+child.getStringValue());
                try {
                    insertNeo4j(child, neo4jNode);
                } catch (InvalidTextNode ex) {
                    //só ignora o nodo
                    //   Logger.getLogger(HtmlToNeo4j.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * insert all nodes in neo4j
     */
    public void insertAllNodes() {
        Element root = getDocument().getRootElement();
        org.neo4j.graphdb.Node rootNeo4jNode;
        try {
            rootNeo4jNode = insertNeo4j(root, null);
            insertAllChildren(root, rootNeo4jNode);
        } catch (InvalidTextNode ex) {
            //só ignora o nodo
            //Logger.getLogger(HtmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param node jdom node
     * @param parentNodeNeo4j
     * @return the neo4j node inserted
     */
    private org.neo4j.graphdb.Node insertNeo4j(Node node, org.neo4j.graphdb.Node parentNodeNeo4j) throws InvalidTextNode {
        /**
         * filtros *
         */
        //if (!t.getText().replaceAll("(\\s)+", "").trim().isEmpty()) {
        //if (t.getParent().getName().toLowerCase().equals("script")) {
        Map<String, String> properties = new HashMap<>();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            properties.put("VALUE", node.getName());
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            properties.put("VALUE", FormatTextNode.format(node.getStringValue()));
        } else {
            return null;
        }
        
        String formattedUniquePath = formatter.format(node.getUniquePath(), node.getNodeType());

        properties.put("NODE_TYPE", node.getNodeType() + "");
        properties.put("PATH", node.getPath());
        properties.put("UNIQUE_PATH", formattedUniquePath);
        properties.put("URL", url);
        properties.put("POSITION", Util.getNodePosition(formattedUniquePath) + "");


        org.neo4j.graphdb.Node newNode = neo4j.insertNode(properties, parentNodeNeo4j);
        return newNode;
    }
    
    

    private Document getDocument() {
        DOMParser parser = new DOMParser();
        try {
            // parser.parse(new org.xml.sax.InputSource(new InputStreamReader(new FileInputStream(url), "ISO-8859-15")));
            parser.parse(url);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(HtmlToNeo4j.class.getName()).log(Level.SEVERE, null, ex);
        }
        org.w3c.dom.Document w3cDoc = parser.getDocument();
        DOMReader domReader = new DOMReader();
        return domReader.read(w3cDoc);
    }

    public static void main(String[] args) {

        Neo4JHandler neo4j = new Neo4JHandler();
        HtmlToNeo4j hh = new HtmlToNeo4j("file:///media/Dados/bases/WEIR/finance/quote.barchart.com/coms.html", neo4j);
        //HtmlToNeo4j hh = new HtmlToNeo4j("/media/Dados/doutorado04/bases/WEIR/book/bookmooch.com/0001049305.html", neo4j);
        

        try (Transaction tx1 = hh.neo4j.beginTx()) {
            //   hh.neo4j.deleteAll();//excluindo tudo antes
            hh.insertAllNodes();
            tx1.success();
        }
    }
}
