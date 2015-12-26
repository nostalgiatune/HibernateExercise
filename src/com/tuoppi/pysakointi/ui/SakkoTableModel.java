package com.tuoppi.pysakointi.ui;

/* Tuomas Toivonen
 * 17.11.2015
*/

import com.tuoppi.pysakointi.persistence.PysakointiPersistence;
import com.tuoppi.pysakointi.model.Sakko;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class SakkoTableModel extends AbstractTableModel {
    
    private final PysakointiPersistence perister;
    private final Map<Integer, Sakko> sakot;
    private final String[] columns = { "ID", "Rekisteritunnus", "Summa",
        "Paivamaara"};
    
    
    public SakkoTableModel(PysakointiPersistence perister) {
        this.perister = perister;
        sakot = perister.getSakot();
    }

    @Override
    public int getRowCount() {
        return sakot.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Sakko sakko = sakot.get(indexToId(rowIndex));
        if (sakko == null) {
            System.err.println("NULL");
            return null;
        }
        
        switch (columnIndex) {
            case 0: return sakko.getId();
            case 1: return sakko.getAjoneuvo().getRekisteritunnus();
            case 2: return sakko.getSumma();
            case 3: return sakko.getPaivamaara();
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Double.class;
            case 3: return Date.class;
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Sakko sakko = sakot.get(indexToId(rowIndex));
        switch (columnIndex) {
            case 2: sakko.setSumma((Double) aValue);
                fireTableRowsUpdated(rowIndex, rowIndex);
                break;
            default: break;
        }
    }
    
    public static int indexToId(int rowIndex) {
        return rowIndex + 1;
    }

    @Override
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        
        for (int i=firstRow; i<=lastRow; i++) {
            int id = indexToId(i);
            try {
                perister.paivitaSakko(id);
            } catch (Exception ex) {
                Logger.getLogger(SakkoTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}