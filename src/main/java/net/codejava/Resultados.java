package net.codejava;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
public class Resultados {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estudiante")
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="examen")
    private Examen examen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="area")
    private Area area;

	public Resultados() {
	}

	//public Resultados(Long id, Estudiante id_estudiante, Examen id_examen, Area id_area) {
	public Resultados(Long id, Area area, Estudiante estudiante, Examen examen) {
		super();
		this.id = id;
		this.estudiante = estudiante;
        this.examen = examen;
        this.area = area;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}