package net.codejava;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import javax.persistence.CascadeType;

@Entity
public class Tipoid {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tipo;
    private String tipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_tipo")
    private List<Estudiante> estudiantes;

	public Tipoid() {
	}

	public Tipoid(final Long id, final String tipo) {
		super();
		this.id_tipo = id;
        this.tipo = tipo;
    }

	public Long getId() {
		return id_tipo;
	}

	public void setId(final Long id) {
		this.id_tipo = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(final List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
}