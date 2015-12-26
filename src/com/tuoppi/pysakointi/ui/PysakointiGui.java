package com.tuoppi.pysakointi.ui;

/* Tuomas Toivonen
 * 17.11.2015
*/

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class PysakointiGui extends JFrame {
    
    private PysakointiController controller;
    private final JPanel content = new JPanel();
    private JScrollPane tablePanel;
    private JTable sakkoTable;
    
    private final JButton lisaaSakkoButton = new JButton("lisaa");
    private final JButton poistaSakkoButton = new JButton("poista");

    
    public PysakointiGui() {
        
        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addActionListeners();
        
        content.add(lisaaSakkoButton);
        content.add(poistaSakkoButton);
    }

    public void setController(PysakointiController controller) {
        
        this.controller = controller;
        sakkoTable = new JTable(controller.getSakkoTableModel());
        tablePanel = new JScrollPane(sakkoTable);
        content.add(tablePanel);
    }
    
    private void addActionListeners() {
        
        lisaaSakkoButton.addActionListener(e -> {
            new LisaaSakkoWindow();
        });
        
        poistaSakkoButton.addActionListener(e -> {
            
            int rowIndex = sakkoTable.getSelectedRow();
            
            if (rowIndex != -1) {
                int id = SakkoTableModel.indexToId(rowIndex);
                try {
                    controller.poistaSakko(id);
                } catch (Exception ex) {
                    Logger.getLogger(PysakointiGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    void activate() {
        pack();
        setVisible(true);
    }
    
    private class LisaaSakkoWindow extends JFrame {
        
        private final JPanel content = new JPanel();
        
        private final JTextField rekisteritunnus = new JTextField("rekisteritunnus");
        private final JTextField summa = new JTextField("summa");
        private final JTextField henkilotunnus = new JTextField("henkilotunnus");
        private final JTextField etunimi = new JTextField("etunimi");
        private final JTextField sukunimi = new JTextField("sukunimi");
        private final JTextField id = new JTextField("tyontekija ID");
        
        private final JButton lisaaButton = new JButton("Lisaa");
        
        
        public LisaaSakkoWindow() {
            
            setContentPane(content);
            
            content.add(rekisteritunnus);
            content.add(summa);
            content.add(henkilotunnus);
            content.add(etunimi);
            content.add(sukunimi);
            content.add(id);
            
            content.add(lisaaButton);
            lisaaButton.addActionListener(e -> {
                try {
                    String rekStr = rekisteritunnus.getText();
                    double sum = Double.parseDouble(summa.getText());
                    String hetu = henkilotunnus.getText();
                    String etunimiStr = etunimi.getText();
                    String sukunimiStr = sukunimi.getText();
                    int idx = Integer.parseInt(id.getText());
                    controller.lisaaSakko(rekStr, sum, hetu, etunimiStr, sukunimiStr, idx);
                }
                catch (Exception ex) {}
            });
            
            pack();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }
    }
    
}