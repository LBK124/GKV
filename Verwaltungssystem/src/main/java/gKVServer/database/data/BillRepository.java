package gKVServer.database.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
//Autor Richard Moeckel

@Repository
    public interface BillRepository extends JpaRepository<Bill, Long>{
Bill findByPersonAndTimestampstart(Person user, LocalDateTime timestampstart);
    }