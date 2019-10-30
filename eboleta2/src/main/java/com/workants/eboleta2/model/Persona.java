package com.workants.eboleta2.model;

public abstract class Persona {
	
	private int idPersona;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String numeroDeDocumento;
	private String numeroLegajo;
	private String numeroInterno;
	private String genero;
	private boolean estadoPersona;

	public abstract void setearPerfil(String perfil);
	
	
	
	public int getIdPersona() {
		return idPersona;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public boolean isEstadoPersona() {
		return estadoPersona;
	}
	public void setEstadoPersona(boolean estadoPersona) {
		this.estadoPersona = estadoPersona;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDeDocumento() {
		return numeroDeDocumento;
	}

	public void setNumeroDeDocumento(String numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNumeroLegajo() {
		return numeroLegajo;
	}

	public void setNumeroLegajo(String numeroLegajo) {
		this.numeroLegajo = numeroLegajo;
	}

	public String getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	



}
