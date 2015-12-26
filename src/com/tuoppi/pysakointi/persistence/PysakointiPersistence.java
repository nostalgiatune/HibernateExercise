package com.tuoppi.pysakointi.persistence;

/* Tuomas Toivonen
 * 17.11.2015
*/

import com.tuoppi.pysakointi.model.Ajoneuvo;
import com.tuoppi.pysakointi.model.Tyontekija;
import com.tuoppi.pysakointi.model.Haltija;
import com.tuoppi.pysakointi.model.Sakko;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/* Handles database persitence releated tasks and manages domain object model
 * by mapping all persisted/detached entities to their primary keys in-memory */
public class PysakointiPersistence {
    
    private final Map<Integer, Sakko> sakot;
    private final Map<Integer, Tyontekija> tyontekijat;
    private final Map<String, Ajoneuvo> ajoneuvot;
    private final Map<String, Haltija> haltijat;
    
    // Error messages
    public static final String VIRHEELLINEN_SAKKO_ID = "Virheellinen sakko ID";
    public static final String VIRHEELLINEN_TYONTEKIJA_ID = "Virheellinen tyontekija ID";
    

    public PysakointiPersistence() {
        
        haltijat = new HashMap<>();
        ajoneuvot = new HashMap<>();
        tyontekijat = new HashMap<>();
        sakot = new HashMap<>();
        
        lisaaTyontekijat();
    }
    
    public int lisaaSakko(String rekisteritunnus, double summa,
            String henkilotunnus, String etunimi, String sukunimi,
            int tyontekijaId) throws Exception {
        
        Tyontekija tyontekija = tyontekijat.get(tyontekijaId);
        if (tyontekija == null) {
            System.err.println(VIRHEELLINEN_TYONTEKIJA_ID);
            throw new Exception(VIRHEELLINEN_TYONTEKIJA_ID);
        }
        
        Haltija haltija = haltijat.get(henkilotunnus);
        if (haltija == null) {
            haltija = new Haltija(henkilotunnus, etunimi, sukunimi);
            haltijat.put(henkilotunnus, haltija);
        }
        
        Ajoneuvo ajoneuvo = ajoneuvot.get(rekisteritunnus);
        if (ajoneuvo == null) {
            ajoneuvo = new Ajoneuvo(rekisteritunnus, haltija);
            ajoneuvot.put(rekisteritunnus, ajoneuvo);
        }
        
        Sakko sakko = new Sakko(ajoneuvo, summa, tyontekija, new Date());
        
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            session.saveOrUpdate(haltija);
            session.saveOrUpdate(tyontekija);
            session.saveOrUpdate(ajoneuvo);
            int id = (int) session.save(sakko);
            
            tx.commit();
            
            sakot.put(id, sakko);
            System.out.println("LISAYS OK");
            return id;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

    public void poistaSakko(int id) throws Exception {
        
        Sakko sakko = sakot.get(id);
        if (sakko == null) {
            System.err.println(VIRHEELLINEN_SAKKO_ID);
            throw new Exception(VIRHEELLINEN_SAKKO_ID);
        }
        
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(sakko);
            System.out.println("OK");
            tx.commit();
            sakot.remove(id);
            System.out.println("POISTO OK");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    public Sakko getSakko(int id) throws Exception {
        
        Sakko sakko = sakot.get(id);
        if (sakko == null)
            throw new Exception(VIRHEELLINEN_SAKKO_ID);
        
        return sakko;
    }
    
    public void paivitaSakko(int id) throws Exception {
        
        Sakko sakko = sakot.get(id);
        if (sakko == null) {
            System.err.println(VIRHEELLINEN_SAKKO_ID);
            throw new Exception(VIRHEELLINEN_SAKKO_ID);
        }
        
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(sakko);
            tx.commit();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    private void lisaaTyontekijat() {
        
        Tyontekija tyontekija1 = new Tyontekija("Pirkko", "Parkkinen");
        Tyontekija tyontekija2 = new Tyontekija("Liisa", "Lappunen");
        Tyontekija tyontekija3 = new Tyontekija("Sakari", "Sakkonen");
        
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            int id1 = (int) session.save(tyontekija1);
            int id2 = (int) session.save(tyontekija2);
            int id3 = (int) session.save(tyontekija3);
            
            tx.commit();
            
            tyontekijat.put(id1, tyontekija1);
            tyontekijat.put(id2, tyontekija2);
            tyontekijat.put(id3, tyontekija3);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            session.close();
        }
    }

    public Map<Integer, Tyontekija> getTyontekijat() {
        return tyontekijat;
    }

    public Map<Integer, Sakko> getSakot() {
        return sakot;
    }

    public Map<String, Ajoneuvo> getAjoneuvot() {
        return ajoneuvot;
    }

    public Map<String, Haltija> getHaltijat() {
        return haltijat;
    }

    public void insertTestValues() {
        
        try {
            lisaaSakko("abc-123", 100, "123456-7890", "Erkki", "Esimerkki", 1);
        } catch (Exception ex) {
            Logger.getLogger(PysakointiPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}