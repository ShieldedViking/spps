package net.codejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstudianteService {

	@Autowired
	private EstudianteRepository repo;
	
	public List<Estudiante> listAll() {
        Iterable<Estudiante> iter =  repo.findAll();
        Collection<Estudiante> collection = new ArrayList<Estudiante>();
		for (Estudiante e : iter) {
			collection.add(e);
        }  
        ArrayList<Estudiante> list = (ArrayList<Estudiante>) collection;
		return list;
	}
	
	public void save(Estudiante product) {
		repo.save(product);
	}
	
	public Estudiante get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
