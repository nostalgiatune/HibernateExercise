package com.tuoppi.pysakointi.ui;

/* Tuomas Toivonen
 * 17.11.2015
*/

import com.tuoppi.pysakointi.persistence.PysakointiPersistence;
import com.tuoppi.pysakointi.model.Ajoneuvo;
import com.tuoppi.pysakointi.model.Tyontekija;
import com.tuoppi.pysakointi.model.Haltija;
import com.tuoppi.pysakointi.model.Sakko;
import java.util.Map;

/* Bridge between user interface and persistence layer */
public class PysakointiController {
    
    private final PysakointiPersistence persister;
    private final SakkoTableModel sakkoTableModel;
    private final PysakointiGui gui;

    
    public PysakointiController(PysakointiPersistence persister, PysakointiGui gui) {
        
        this.persister = persister;
        this.gui = gui;
        sakkoTableModel = new SakkoTableModel(persister);
    }
    
    public void lisaaSakko(String rekisteritunnus, double summa,String henkilotunnus,
            String etunimi, String sukunimi, int tyontekijaId) {
        
        try {
            persister.lisaaSakko(rekisteritunnus, summa, henkilotunnus,
                    etunimi, sukunimi, tyontekijaId);
            sakkoTableModel.fireTableDataChanged();
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void poistaSakko(int id) throws Exception {
        persister.poistaSakko(id);
        sakkoTableModel.fireTableDataChanged();
    }
    
    public Sakko getSakko(int id) throws Exception {
        return persister.getSakko(id);
    }
    
    public void paivitaSakko(int id) throws Exception {
        persister.paivitaSakko(id);
    }
    
    public Map<Integer, Tyontekija> getTyontekijat() {
        return persister.getTyontekijat();
    }
    
    public Map<Integer, Sakko> getSakot() {
        return persister.getSakot();
    }
    
     public Map<String, Ajoneuvo> getAjoneuvot() {
         return persister.getAjoneuvot();
     }
     
     public Map<String, Haltija> getHaltijat() {
         return persister.getHaltijat();
     }
     
     public SakkoTableModel getSakkoTableModel() {
         return sakkoTableModel;
     }
    
}