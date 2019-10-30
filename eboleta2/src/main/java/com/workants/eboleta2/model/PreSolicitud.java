package com.workants.eboleta2.model;

import java.io.Serializable;

public class PreSolicitud implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSolicitud;
	private Titular titular;
	private Juridica juridica;
	private Solicitante solicitante;
	private String tipoImpuesto;
	private String codigoPagoElectronico;
	private boolean estadoPresolicitud;
	
	
	public PreSolicitud(){
		
		
		
	}
	
	
	
	public boolean isEstadoPresolicitud() {
		return estadoPresolicitud;
	}

	public void setEstadoPresolicitud(boolean estadoPresolicitud) {
		this.estadoPresolicitud = estadoPresolicitud;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	private boolean estadoSolicitud;

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(boolean estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}



	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}



	public Juridica getJuridica() {
		return juridica;
	}



	public void setJuridica(Juridica juridica) {
		this.juridica = juridica;
	}



	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}



	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}



}
