package com.workants.eboleta2.model;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private String nombreDeUsuario;
	private String contrasenia;
	private Perfil perfil; 
	
	public Usuario(){
	
		
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	

	@Override
	public void setearPerfil(String perfil) {
				
		if(perfil.equalsIgnoreCase("administrativo")){
			this.setPerfil(new PerfilAdministrativo());
		} 
		if(perfil.equalsIgnoreCase("administrador")){
			this.setPerfil(new PerfilAdministrador());
		} 
		
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	

}
