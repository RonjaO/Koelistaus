package wad.repository;

import wad.domain.Kurssikoe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KurssikoeRepository extends JpaRepository<Kurssikoe, Long> {
    List<Kurssikoe> findByKurssinNimi(String kurssinNimi);
    
}