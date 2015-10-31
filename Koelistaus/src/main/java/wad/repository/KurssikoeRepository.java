package wad.repository;

import wad.domain.Kurssikoe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KurssikoeRepository extends JpaRepository<Kurssikoe, Long> {
    
}