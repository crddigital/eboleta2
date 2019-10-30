package com.workants.eboleta2.model;

public class Receptor extends Persona {
	
	private String correoElectronico;
	private String tipoImpuesto;
	private String partida; 
	private String esSolicitante;
	private String cpe;
	private String rutaArchivo;
	
	
	public Receptor(){
		
		
		
	}

	@Override
	public void setearPerfil(String perfil) {
		// TODO Auto-generated method stub
		
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getEsSolicitante() {
		return esSolicitante;
	}

	public void setEsSolicitante(String esSolicitante) {
		this.esSolicitante = esSolicitante;
	}

	public String getCpe() {
		return cpe;
	}

	public void setCpe(String cpe) {
		this.cpe = cpe;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

}
