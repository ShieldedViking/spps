package net.codejava;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoidRepository extends CrudRepository<Tipoid,Long>{
//public interface TipoidRepository extends JpaRepository<Tipoid, Long> {

}