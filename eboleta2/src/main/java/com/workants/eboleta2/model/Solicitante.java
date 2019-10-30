package com.workants.eboleta2.model;

import java.io.Serializable;

public class Solicitante extends Persona implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoDocumentoTitular;
	private String numeroDeDocumentoSolicitante;	
	private String numeroDeTelefonoSolicitante;
	private String tipoTelefonoSolicitante;
	private String numeroDeTelefonoAlternativoSolicitante;
	private String tipoTelefonoAlternativoSolicitante;
	private String correoElectronicoSolicitante;
	private Titular titular;

	public Solicitante(){
		
		
	}

	public String getTipoDocumentoTitular() {
		return tipoDocumentoTitular;
	}

	public void setTipoDocumentoTitular(String tipoDocumentoTitular) {
		this.tipoDocumentoTitular = tipoDocumentoTitular;
	}

	public String getNumeroDeDocumentoSolicitante() {
		return numeroDeDocumentoSolicitante;
	}

	public void setNumeroDeDocumentoSolicitante(String numeroDeDocumentoSolicitante) {
		this.numeroDeDocumentoSolicitante = numeroDeDocumentoSolicitante;
	}

	public String getNumeroDeTelefonoSolicitante() {
		return numeroDeTelefonoSolicitante;
	}

	public void setNumeroDeTelefonoSolicitante(String numeroDeTelefonoSolicitante) {
		this.numeroDeTelefonoSolicitante = numeroDeTelefonoSolicitante;
	}

	public String getTipoTelefonoSolicitante() {
		return tipoTelefonoSolicitante;
	}

	public void setTipoTelefonoSolicitante(String tipoTelefonoSolicitante) {
		this.tipoTelefonoSolicitante = tipoTelefonoSolicitante;
	}

	public String getNumeroDeTelefonoAlternativoSolicitante() {
		return numeroDeTelefonoAlternativoSolicitante;
	}

	public void setNumeroDeTelefonoAlternativoSolicitante(
			String numeroDeTelefonoAlternativoSolicitante) {
		this.numeroDeTelefonoAlternativoSolicitante = numeroDeTelefonoAlternativoSolicitante;
	}

	public String getTipoTelefonoAlternativoSolicitante() {
		return tipoTelefonoAlternativoSolicitante;
	}

	public void setTipoTelefonoAlternativoSolicitante(
			String tipoTelefonoAlternativoSolicitante) {
		this.tipoTelefonoAlternativoSolicitante = tipoTelefonoAlternativoSolicitante;
	}

	public String getCorreoElectronicoSolicitante() {
		return correoElectronicoSolicitante;
	}

	public void setCorreoElectronicoSolicitante(String correoElectronicoSolicitante) {
		this.correoElectronicoSolicitante = correoElectronicoSolicitante;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	@Override
	public void setearPerfil(String perfil) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
