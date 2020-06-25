package net.codejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AreaService {

	@Autowired
	private AreaRepository repo;
	
	public List<Area> listAll() {
        Iterable<Area> iter =  repo.findAll();
        Collection<Area> collection = new ArrayList<Area>();
		for (Area e : iter) {
			collection.add(e);
        }  
        ArrayList<Area> list = (ArrayList<Area>) collection;
		return list;
	}
	
	public void save(Area product) {
		repo.save(product);
	}
	
	public Area get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
