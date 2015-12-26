package com.tuoppi.pysakointi.model;

/* Tuomas Toivonen
 * 17.11.2015
*/

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ajoneuvo implements Serializable {
    
    private String rekisteritunnus; // id
    private Haltija haltija;

    
    public Ajoneuvo() {
    }

    public Ajoneuvo(String rekisteritunnus, Haltija haltija) {
        this.rekisteritunnus = rekisteritunnus;
        this.haltija = haltija;
    }

    @Id
    public String getRekisteritunnus() {
        return rekisteritunnus;
    }

    @ManyToOne
    public Haltija getHaltija() {
        return haltija;
    }

    public void setRekisteritunnus(String rekisteritunnus) {
        this.rekisteritunnus = rekisteritunnus;
    }

    public void setHaltija(Haltija haltija) {
        this.haltija = haltija;
    }
    
}