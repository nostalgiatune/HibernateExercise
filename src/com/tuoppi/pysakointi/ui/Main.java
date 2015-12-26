package com.tuoppi.pysakointi.ui;


import com.tuoppi.pysakointi.persistence.PysakointiPersistence;

/* Tuomas Toivonen
 * 17.11.2015
*/

public class Main {
    
    public static void main(String[] args) {

        PysakointiPersistence model = new PysakointiPersistence();
        model.insertTestValues();
        
        PysakointiGui gui = new PysakointiGui();
        PysakointiController controller = new PysakointiController(model, gui);
        
        gui.setController(controller);
        gui.activate();
    }
    
}