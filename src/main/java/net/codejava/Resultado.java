package net.codejava;

public class Resultado {
   
    private Long id_area;
	private String area;
	private Integer valor;
	private Long idestudiante;
    private Integer ano;

    private Long id_estudiante;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private Long identificacion;
    private Tipoid id_tipo;

	protected Resultado() {
	}

    public Resultado(Long id_area, String area, Integer valor, Long idestudiante, Integer ano, Long id_estudiante,
    String nombre1, String nombre2, String apellido1, String apellido2, Long identificacion, Tipoid id_tipo) {
    this.id_area = id_area;
    this.area = area;
    this.valor = valor;
    this.idestudiante = idestudiante;
    this.ano = ano;
    this.id_estudiante = id_estudiante;
    this.nombre1 = nombre1;
    this.nombre2 = nombre2;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.identificacion = identificacion;
    this.id_tipo = id_tipo;
    }

    public Long getId_area() {
        return id_area;
    }

    public void setId_area(Long id_area) {
        this.id_area = id_area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Long getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Long id_estudiante) {
        this.id_estudiante = id_estudiante;
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

    

}
