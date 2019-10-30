package com.workants.eboleta2.model;

import java.io.Serializable;
import java.util.Date;

public class Procesados implements Serializable {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProcesado;
	private String correoElectronico;
	private String cpe;
	private Date fechaEnvio;
	private String tipoImpuesto;
		

	public Procesados() {
		
	}
	
	


	public Procesados(int idProcesado, String correoElectronico, String cpe, Date fechaEnvio, String tipoImpuesto) {
	
		super();
		this.idProcesado = idProcesado;
		this.correoElectronico = correoElectronico;
		this.cpe = cpe;
		this.fechaEnvio = fechaEnvio;
		this.tipoImpuesto = tipoImpuesto;
	}
	
	




	public int getIdProcesado() {
		return idProcesado;
	}


	public void setIdProcesado(int idProcesado) {
		this.idProcesado = idProcesado;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public String getCpe() {
		return cpe;
	}


	public void setCpe(String cpe) {
		this.cpe = cpe;
	}


	public Date getFechaEnvio() {
		return fechaEnvio;
	}


	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}


	public String getTipoImpuesto() {
		return tipoImpuesto;
	}


	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProcesado;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Procesados other = (Procesados) obj;
		if (idProcesado != other.idProcesado)
			return false;
		return true;
	}


	

	
	

}
