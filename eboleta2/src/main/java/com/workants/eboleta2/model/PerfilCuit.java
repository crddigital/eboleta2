package com.workants.eboleta2.model;

import java.io.Serializable;

public class PerfilCuit extends Perfil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PerfilCuit(){
		
		this.setNombreDePerfil("cuit".toUpperCase());
	}

}
