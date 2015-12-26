package com.tuoppi.pysakointi.model;

/* Tuomas Toivonen
 * 17.11.2015
*/

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Tyontekija implements Serializable {
    
    private int id;
    private String etunimi;
    private String sukunimi;

    
    public Tyontekija() {
    }

    public Tyontekija(String etunimi, String sukunimi) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }
    
}