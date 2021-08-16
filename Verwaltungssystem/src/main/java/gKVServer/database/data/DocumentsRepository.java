package gKVServer.database.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Autor Richard Moeckel
@Repository
public interface DocumentsRepository  extends JpaRepository<Documents, Long> {
Documents findByNameAndCellar(String name, Cellar cellar);
Documents findByName(String name);
}
