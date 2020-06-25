package net.codejava;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
public class Estudiante {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private Long identificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipo")
    private Tipoid id_tipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private List<Resultados> resultados;

	public Estudiante() {
	}

	public Estudiante(Long id, String nombre1, String nombre2, String apellido1, String apellido2, Long identificacion, Tipoid pId_tipo) {
		super();
		this.id = id;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
		this.identificacion = identificacion;
		this.id_tipo = pId_tipo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipoid getTipoid() {
		return id_tipo;
	}

	public void setTipoid(Tipoid tipoid) {
		this.id_tipo = tipoid;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
    }


	public Tipoid getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(Tipoid id_tipo) {
		this.id_tipo = id_tipo;
	}

	public List<Resultados> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultados> resultados) {
		this.resultados = resultados;
	}  
    
    
}