/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edimarmanica.expressiveness.tool;

import br.edimarmanica.configuration.General;
import br.edimarmanica.configuration.Paths;
import br.edimarmanica.dataset.Dataset;
import br.edimarmanica.dataset.Domain;
import br.edimarmanica.dataset.Site;
import br.edimarmanica.expressiveness.evaluate.EvaluateWEIR;
import br.edimarmanica.expressiveness.extract.ExtractValues;
import static br.edimarmanica.expressiveness.generate.GenerateRules.printRules;
import br.edimarmanica.extractionrules.load.DirectoryToNeo4j;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandler;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandlerLocal;
import br.edimarmanica.extractionrules.neo4j.Neo4jHandlerType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author edimar
 */
public class Tela extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     */
    public Tela() {
        initComponents();

        jCBDataset.setModel(new DefaultComboBoxModel(Dataset.values()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jCBDataset = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCBDomain = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jCBSite = new javax.swing.JComboBox();
        jBTLoad = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaLog = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jcbView = new javax.swing.JComboBox();
        jbtView = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbView = new javax.swing.JTable();
        jbtGenerateRules = new javax.swing.JButton();
        jbtExtract = new javax.swing.JButton();
        jbtEvaluate = new javax.swing.JButton();
        jbtDelete = new javax.swing.JButton();
        jbtLoad = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tarefas");

        jCBDataset.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCBDatasetFocusLost(evt);
            }
        });

        jLabel2.setText("Dataset");

        jLabel3.setText("Domain:");

        jCBDomain.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCBDomainFocusLost(evt);
            }
        });

        jLabel4.setText("Site:");

        jBTLoad.setText("Generate Attribute Info");
        jBTLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTLoadActionPerformed(evt);
            }
        });

        jLabel5.setText("Log:");

        jtaLog.setEditable(false);
        jtaLog.setColumns(20);
        jtaLog.setRows(5);
        jScrollPane1.setViewportView(jtaLog);

        jLabel6.setText("View:");

        jcbView.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nada", "Attribute Info", "Generated Rules", "Results", "Extraction Log", "Gabarito", "Nr Loaded Pages" }));
        jcbView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbViewActionPerformed(evt);
            }
        });

        jbtView.setText("Open");
        jbtView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtViewActionPerformed(evt);
            }
        });

        jtbView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jtbView);

        jbtGenerateRules.setText("Generate Rules");
        jbtGenerateRules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGenerateRulesActionPerformed(evt);
            }
        });

        jbtExtract.setText("Extract Values");
        jbtExtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExtractActionPerformed(evt);
            }
        });

        jbtEvaluate.setText("Evaluate");
        jbtEvaluate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEvaluateActionPerformed(evt);
            }
        });

        jbtDelete.setText("Delete database");
        jbtDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDeleteActionPerformed(evt);
            }
        });

        jbtLoad.setText("HTMLtoNeo4j");
        jbtLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(4, 4, 4)
                                                .addComponent(jCBDataset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCBDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCBSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jbtDelete)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtLoad)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jBTLoad)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtGenerateRules)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtExtract))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtView)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtEvaluate))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBDataset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jCBDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jCBSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtDelete)
                    .addComponent(jbtLoad)
                    .addComponent(jBTLoad)
                    .addComponent(jbtGenerateRules)
                    .addComponent(jbtExtract)
                    .addComponent(jbtEvaluate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcbView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtView))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBDatasetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCBDatasetFocusLost
        // TODO add your handling code here:
        Dataset dataset = (Dataset) jCBDataset.getSelectedItem();
        jCBDomain.setModel(new DefaultComboBoxModel(dataset.getDomains()));

    }//GEN-LAST:event_jCBDatasetFocusLost

    private void jCBDomainFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCBDomainFocusLost
        // TODO add your handling code here:
        Domain domain = (Domain) jCBDomain.getSelectedItem();
        jCBSite.setModel(new DefaultComboBoxModel(domain.getSites()));
    }//GEN-LAST:event_jCBDomainFocusLost

    private void jBTLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTLoadActionPerformed
        /*        jCBDataset.setEnabled(false);
         jCBDomain.setEnabled(false);
         jCBSite.setEnabled(false);*/

        // TODO add your handling code here:
        Controller controller = new Controller(this);
        try {
            controller.printAttributeInfo((Site) jCBSite.getSelectedItem());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller = null;
        System.gc();
    }//GEN-LAST:event_jBTLoadActionPerformed

    private void jcbViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbViewActionPerformed

    private void jbtViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtViewActionPerformed
        // TODO add your handling code here:
        Site site = (Site) jCBSite.getSelectedItem();;
        if (site == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecione um site!");
            return;
        }
        String opcao = (String) jcbView.getSelectedItem();
        switch (opcao) {
            case "Nada":
                //jtbView = new JTable();
                break;
            case "Attribute Info":
                try {
                    Controller.csvToJTable(jtbView, new File(Paths.PATH_EXPRESSIVENESS + "/" + site.getPath() + "/attributes_info.csv"));
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Problemas!");
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Problemas!");
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Generated Rules":
                try {
                    Controller.csvToJTable(jtbView, new File(Paths.PATH_EXPRESSIVENESS + "/" + site.getPath() + "/generated_rules.csv"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Problemas!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Problemas!");
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Results":
                try {
                    Controller.csvToJTable(jtbView, new File(Paths.PATH_EXPRESSIVENESS + "/" + site.getPath() + "/result.csv"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Problemas!");
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Problemas!");
                }
                break;
            case "Extraction Log":
                try {
                    Controller.txtToJTextArea(jtaLog, new File(Paths.PATH_EXPRESSIVENESS + "/" + site.getPath() + "/log.txt"));
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Problemas!");
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Problemas!");
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Gabarito":
                try {
                    Runtime.getRuntime().exec("soffice --calc " + Paths.PATH_BASE + "/" + site.getGroundTruthPath());
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Nr Loaded Pages":
                Neo4jHandler neo4j = new Neo4jHandlerLocal(site);
                 String columnName = "NR_URLS_LOADED";
                String cypherQuery = "MATCH n RETURN COUNT(DISTINCT n.URL) AS " + columnName;
                jtaLog.setText("***********************");
                jtaLog.setText("Number of loaded pages for "+site.getFolderName()+": "+neo4j.querySingleColumn(cypherQuery, columnName).get(0).toString());
                neo4j.shutdown();
                break;
            default:
                break;
        }

    }//GEN-LAST:event_jbtViewActionPerformed

    private void jbtGenerateRulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGenerateRulesActionPerformed
        // TODO add your handling code here:
        Site site = (Site) jCBSite.getSelectedItem();;
        if (site == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecione um site!");
            return;
        }
        printRules(site);
        jtaLog.append("\n**** generated_rules.csv gerado!");
    }//GEN-LAST:event_jbtGenerateRulesActionPerformed

    private void jbtExtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExtractActionPerformed
        // TODO add your handling code here:
        Site site = (Site) jCBSite.getSelectedItem();;
        if (site == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecione um site!");
            return;
        }
        
        ExtractValues extract = new ExtractValues(site);
        extract.printExtractedValues();
        extract = null;
        System.gc();
        jtaLog.append("\n**** values extracted!");
    }//GEN-LAST:event_jbtExtractActionPerformed

    private void jbtEvaluateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEvaluateActionPerformed
        // TODO add your handling code here:
        Site site = (Site) jCBSite.getSelectedItem();;
        if (site == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecione um site!");
            return;
        }
        EvaluateWEIR eval = new EvaluateWEIR(site);
        eval.printMetrics();
        eval = null;
        System.gc();
        jtaLog.append("\n**** evaluation done!");
    }//GEN-LAST:event_jbtEvaluateActionPerformed

    private void jbtDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteActionPerformed
        // TODO add your handling code here:
        Site site = (Site) jCBSite.getSelectedItem();
        if (site == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecione um site!");
            return;
        }

        if (General.NEO4J_TYPE == Neo4jHandlerType.LOCAL) {
            Neo4jHandlerLocal.deleteDatabase(site);
            jtaLog.append("\n**** Current database deleted!");
        }else{
            jtaLog.append("\n**** Não é possível excluir database remoto!");
        }
    }//GEN-LAST:event_jbtDeleteActionPerformed

    private void jbtLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLoadActionPerformed
        // TODO add your handling code here:
        Site site = (Site) jCBSite.getSelectedItem();;
        if (site == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecione um site!");
            return;
        }
        
        DirectoryToNeo4j load = new DirectoryToNeo4j(site, false);
        try {
            load.loadPages();
            jtaLog.append("\n**** HTML pages loaded to Neo4J!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            jtaLog.append("\n**** Problemas ao carregar as páginas HTML para o Neo4J!");
        }
        load = null;
        System.gc();
    }//GEN-LAST:event_jbtLoadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBTLoad;
    private javax.swing.JComboBox jCBDataset;
    private javax.swing.JComboBox jCBDomain;
    private javax.swing.JComboBox jCBSite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtDelete;
    private javax.swing.JButton jbtEvaluate;
    private javax.swing.JButton jbtExtract;
    private javax.swing.JButton jbtGenerateRules;
    private javax.swing.JButton jbtLoad;
    private javax.swing.JButton jbtView;
    private javax.swing.JComboBox jcbView;
    protected javax.swing.JTextArea jtaLog;
    private javax.swing.JTable jtbView;
    // End of variables declaration//GEN-END:variables
}
