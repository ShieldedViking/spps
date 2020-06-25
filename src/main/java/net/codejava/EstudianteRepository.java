package net.codejava;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante,Long>{
//public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}