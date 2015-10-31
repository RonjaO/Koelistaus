package wad.domain;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
public class Erilliskoe extends AbstractPersistable<Long> {
    
    private String kurssinNimi;
    private String kurssikoodi;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date aika;
    private String paikka;
    private String tyyppi;
    private String kuulustelija;
    private String kommentit;
    
    public String getKurssinNimi() {
        return this.kurssinNimi;
    }
    
    public void setKurssinNimi(String nimi) {
        this.kurssinNimi = nimi;
    }
    
    public String getKurssikoodi() {
        return this.kurssikoodi;
    }
    
    public void setKurssikoodi(String kurssikoodi) {
        this.kurssikoodi = kurssikoodi;
    }
    
    public Date getAika() {
        return this.aika;
    }
    
    public void setAika(Date aika) {
        this.aika = aika;
    }
    
    public String getTyyppi() {
        return this.tyyppi;
    }
    
    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }
    
    public String getKuulustelija() {
        return this.kuulustelija;
    }
    
    public void setKuulustelija(String kuulustelija) {
        this.kuulustelija = kuulustelija;
    }
    
    public String getKommentit() {
        return this.kommentit;
    }
    
    public void setKommentit(String kommentit) {
        this.kommentit = kommentit;
    }
    
    public String getPaikka() {
        return this.paikka;
    }
    
    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }
}
    