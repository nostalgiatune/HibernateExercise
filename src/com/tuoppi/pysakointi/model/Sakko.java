package com.tuoppi.pysakointi.model;

/* Tuomas Toivonen
 * 17.11.2015
*/

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Sakko implements Serializable {
    
    private int id;
    private Ajoneuvo ajoneuvo;
    private double summa;
    private Tyontekija tyontekija;
    private Date paivamaara;

    
    public Sakko() {
    }

    public Sakko(Ajoneuvo ajoneuvo, double summa, Tyontekija tyontekija, Date paivamaara) {
        this.ajoneuvo = ajoneuvo;
        this.summa = summa;
        this.tyontekija = tyontekija;
        this.paivamaara = paivamaara;
    }

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @ManyToOne
    public Ajoneuvo getAjoneuvo() {
        return ajoneuvo;
    }

    public double getSumma() {
        return summa;
    }

    @ManyToOne
    public Tyontekija getTyontekija() {
        return tyontekija;
    }

    @Temporal (TemporalType.DATE)
    public Date getPaivamaara() {
        return paivamaara;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAjoneuvo(Ajoneuvo ajoneuvo) {
        this.ajoneuvo = ajoneuvo;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public void setTyontekija(Tyontekija tyontekija) {
        this.tyontekija = tyontekija;
    }

    public void setPaivamaara(Date paivamaara) {
        this.paivamaara = paivamaara;
    }
    
}