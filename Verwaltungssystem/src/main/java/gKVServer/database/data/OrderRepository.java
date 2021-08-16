package gKVServer.database.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

//Autor Richard Moeckel
@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
Order findByUserAndDate(Person user, LocalDateTime date);
Order findByName(String name);
}