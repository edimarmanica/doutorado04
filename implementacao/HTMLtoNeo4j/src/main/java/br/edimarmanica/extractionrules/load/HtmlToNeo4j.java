/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.extractionrules.load;

import br.edimarmanica.dataset.weir.videogame.Site;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandler;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandlerLocal;
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
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 *
 * load a webPage to the neo4j
 *
 * @author edimar
 */
public class HtmlToNeo4j {

    private Neo4jHandler neo4j;
    private String url;
    private FormatUniquePath formatter;

    public HtmlToNeo4j(String url, Neo4jHandler neo4j) {
        this.url = url;
        this.neo4j = neo4j;

        formatter = new FormatUniquePath(url); //só pode instanciar uma vez por página para funcionar corretamente
    }

    private void insertAllChildren(Element el, org.neo4j.graphdb.Node neo4jNode) {
        for (Iterator i = el.nodeIterator(); i.hasNext();) {
            Node child = (Node) i.next();

            if (child.getNodeType() == Node.ELEMENT_NODE) {
                
                /** blacklist **/
                if (child.getName().toLowerCase().equals("script") || child.getName().toLowerCase().equals("head")){
                    continue;
                }
                
                org.neo4j.graphdb.Node newNeo4jNode;
                try {
                    newNeo4jNode = insertNeo4j(child, neo4jNode);
                    insertAllChildren((Element) child, newNeo4jNode);
                    newNeo4jNode = null;
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
       // System.out.println("Inserindo nodo: " + node.getName());
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
            parser.setFeature("http://xml.org/sax/features/namespaces", false);
        } catch (SAXNotRecognizedException | SAXNotSupportedException ex) {
            Logger.getLogger(HtmlToNeo4j.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        Neo4jHandler neo4j = new Neo4jHandlerLocal(Site.CDUNIVERSE);
        
        try (Transaction tx1 = neo4j.beginTx()) {
            HtmlToNeo4j html = new HtmlToNeo4j("/media/Dados/bases/0001049305.html", neo4j);
            html.insertAllNodes();
            tx1.success();
            tx1.close();
            neo4j.shutdown();
        }


    }
}
