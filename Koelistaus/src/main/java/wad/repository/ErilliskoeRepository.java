package wad.repository;

import wad.domain.Erilliskoe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ErilliskoeRepository extends JpaRepository<Erilliskoe, Long> {
    List<Erilliskoe> findByKurssinNimi(String kurssinNimi);
    
}