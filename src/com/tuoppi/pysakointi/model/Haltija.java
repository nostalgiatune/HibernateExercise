package com.tuoppi.pysakointi.model;

/* Tuomas Toivonen
 * 17.11.2015
*/

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Haltija implements Serializable {
    
    private String henkilotunnus; // id
    private String etunimi;
    private String sukunimi;

    
    public Haltija() {
    }

    public Haltija(String henkilotunnus, String etunimi, String sukunimi) {
        this.henkilotunnus = henkilotunnus;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    @Id
    public String getHenkilotunnus() {
        return henkilotunnus;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setHenkilotunnus(String henkilotunnus) {
        this.henkilotunnus = henkilotunnus;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }
    
}