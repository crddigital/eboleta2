package com.workants.eboleta2.model;

import java.io.Serializable;

public class PerfilAdministrador extends Perfil implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PerfilAdministrador(){
		
	 this.setNombreDePerfil("administrador".toUpperCase());
	}
	
	
	

}
