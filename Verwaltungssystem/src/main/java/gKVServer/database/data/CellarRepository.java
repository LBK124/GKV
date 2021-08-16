package gKVServer.database.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//Autor Richard Moeckel
@Repository
public interface CellarRepository extends JpaRepository<Cellar, Long>{
Optional<Cellar> findById(Long id);
}
