package com.workants.eboleta2.model;

import java.io.Serializable;

public class Titular extends Persona implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoDocumentoTitular;
	private String numeroDeDocumentoTitular;	
	private String numeroDeTelefonoTitular;
	private String tipoTelefonoTitular;
	private String numeroDeTelefonoAlternativoTitular;
	private String tipoTelefonoAlternativoTitular;
	private String correoElectronicoTitular;
	private int poseeSolicitante;
	

	public int getPoseeSolicitante() {
		return poseeSolicitante;
	}



	public void setPoseeSolicitante(int poseeSolicitante) {
		this.poseeSolicitante = poseeSolicitante;
	}



	public Titular(){
		
	}



	public String getTipoDocumentoTitular() {
		return tipoDocumentoTitular;
	}



	public void setTipoDocumentoTitular(String tipoDocumentoTitular) {
		this.tipoDocumentoTitular = tipoDocumentoTitular;
	}



	public String getNumeroDeDocumentoTitular() {
		return numeroDeDocumentoTitular;
	}



	public void setNumeroDeDocumentoTitular(String numeroDeDocumentoTitular) {
		this.numeroDeDocumentoTitular = numeroDeDocumentoTitular;
	}



	public String getNumeroDeTelefonoTitular() {
		return numeroDeTelefonoTitular;
	}



	public void setNumeroDeTelefonoTitular(String numeroDeTelefonoTitular) {
		this.numeroDeTelefonoTitular = numeroDeTelefonoTitular;
	}



	public String getTipoTelefonoTitular() {
		return tipoTelefonoTitular;
	}



	public void setTipoTelefonoTitular(String tipoTelefonoTitular) {
		this.tipoTelefonoTitular = tipoTelefonoTitular;
	}



	public String getNumeroDeTelefonoAlternativoTitular() {
		return numeroDeTelefonoAlternativoTitular;
	}



	public void setNumeroDeTelefonoAlternativoTitular(
			String numeroDeTelefonoAlternativoTitular) {
		this.numeroDeTelefonoAlternativoTitular = numeroDeTelefonoAlternativoTitular;
	}



	public String getTipoTelefonoAlternativoTitular() {
		return tipoTelefonoAlternativoTitular;
	}



	public void setTipoTelefonoAlternativoTitular(
			String tipoTelefonoAlternativoTitular) {
		this.tipoTelefonoAlternativoTitular = tipoTelefonoAlternativoTitular;
	}



	public String getCorreoElectronicoTitular() {
		return correoElectronicoTitular;
	}



	public void setCorreoElectronicoTitular(String correoElectronicoTitular) {
		this.correoElectronicoTitular = correoElectronicoTitular;
	}



	@Override
	public void setearPerfil(String perfil) {
		// TODO Auto-generated method stub
		
	}
	
	

}
