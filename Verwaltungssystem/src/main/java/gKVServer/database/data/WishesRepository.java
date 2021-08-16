package gKVServer.database.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Autor Richard Moeckel
@Repository
public interface WishesRepository  extends JpaRepository<Wishes, Long> {

}