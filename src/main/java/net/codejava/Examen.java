package net.codejava;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Examen {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String examen;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private List<Resultados> resultados;
    
	protected Examen() {
	}

	protected Examen(Long id, String examen) {
		super();
		this.id = id;
		this.examen = examen;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }
    
}
