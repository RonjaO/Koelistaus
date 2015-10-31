package wad.domain;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Kurssikoe extends AbstractPersistable<Long> {
    
    private String kurssinNimi;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "d.M.y H:m")    
    private Date aika;
    private String paikka;
    private String kurssikoodi;
    private String kuulustelija;
    
    public String getKurssinNimi() {
        return this.kurssinNimi;
    }
    
    public void setKurssinNimi(String nimi) {
        this.kurssinNimi = nimi;
    }
    
    public Date getAika() {
        return this.aika;
    }
    
    public void setAika(Date aika) {
        this.aika = aika;
    }
        
    public String getPaikka() {
        return this.paikka;
    }
    
    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }
    
    public String getKurssikoodi() {
        return this.kurssikoodi;
    }
    
    public void setKurssikoodi(String kurssikoodi) {
        this.kurssikoodi = kurssikoodi;
    }
    
    public String getKuulustelija() {
        return this.kuulustelija;
    }
    
    public void setKuulustelija(String kuulustelija) {
        this.kuulustelija = kuulustelija;
    }
    
}