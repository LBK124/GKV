package gKVServer.database.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Autor Richard Moeckel
@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Drink findByNameAndBottletypeAndCellar(String name, String bottletype, Cellar cellar);



}