package com.workants.eboleta2.model;

import java.io.Serializable;

public class Juridica implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String razonSocial;
	private String cuit;
	private String tipo;
	private String numeroDeTelefono;
	private String tipoTelefono;
	private String numeroDeTelefonoAlternativo;
	private String tipoTelefonoAlternativo;
	private String correoElectronico;
	private boolean estado;
	
	
	public Juridica() {
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNumeroDeTelefono() {
		return numeroDeTelefono;
	}


	public void setNumeroDeTelefono(String numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}


	public String getTipoTelefono() {
		return tipoTelefono;
	}


	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}


	public String getNumeroDeTelefonoAlternativo() {
		return numeroDeTelefonoAlternativo;
	}


	public void setNumeroDeTelefonoAlternativo(String numeroDeTelefonoAlternativo) {
		this.numeroDeTelefonoAlternativo = numeroDeTelefonoAlternativo;
	}


	public String getTipoTelefonoAlternativo() {
		return tipoTelefonoAlternativo;
	}


	public void setTipoTelefonoAlternativo(String tipoTelefonoAlternativo) {
		this.tipoTelefonoAlternativo = tipoTelefonoAlternativo;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
