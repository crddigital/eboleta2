package com.workants.eboleta2.model;

import java.io.Serializable;
import java.util.Date;

public class PreSolicitudConfirmar implements Serializable{

	/**
	 * Clase generica usada para chequear solicitudes enviadas pero que no fueron confirmadas
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String correo;
	private String tipoImpuesto;
	private String cpe;
	private Date fechaSuscripcion;
	private String tipoSolicitante;
	
	
	public PreSolicitudConfirmar() {
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTipoImpuesto() {
		return tipoImpuesto;
	}


	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}


	public String getCpe() {
		return cpe;
	}


	public void setCpe(String cpe) {
		this.cpe = cpe;
	}


	public Date getFechaSuscripcion() {
		return fechaSuscripcion;
	}


	public void setFechaSuscripcion(Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}


	public String getTipoSolicitante() {
		return tipoSolicitante;
	}


	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}
	
	
	
	

}
