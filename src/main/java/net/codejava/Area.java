package net.codejava;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Area {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Integer valor;
	private Long idestudiante;
    private Integer ano;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<Resultados> resultados;

	protected Area() {
	}

	protected Area(Long id, String nombre, Integer valor, Long idestudiante, Integer ano) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
		this.idestudiante = idestudiante;
		this.ano = ano;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Long getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(Long idestudiante) {
        this.idestudiante = idestudiante;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
    
}
