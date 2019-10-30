package com.workants.eboleta2.servicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.workants.eboleta2.IService.IServicioUsuario;
import com.workants.eboleta2.dao.DaoPersona;
import com.workants.eboleta2.model.Usuario;

public class ServicioUsuario implements Serializable, IServicioUsuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioUsuario instance;
	
	public ServicioUsuario(){
		
	}
	
	public static ServicioUsuario getIntance(){
		
		if(instance == null){
			instance = new ServicioUsuario();
		}
		return instance;
	}
	
	public BeanItemContainer<Usuario> getUsuarios(){
		
		ArrayList<Usuario> usuarios;
		usuarios = DaoPersona.getIntance().getUsuarios();
		List<Usuario> listaUsuarios = usuarios;
		BeanItemContainer<Usuario> objects = new BeanItemContainer<Usuario>(Usuario.class, listaUsuarios);
		objects.addNestedContainerProperty("perfil.nombreDePerfil");
		return objects;
		
	}

	@Override
	public int guardarUsuario(Usuario usuario, Usuario usuarioLog) {
		
		return DaoPersona.getIntance().guardarUsuario(usuario,usuarioLog) ;
	}

	@Override
	public int modificarUsuario(Usuario usuario, Usuario usuarioLog) {
		
		return DaoPersona.getIntance().modificarUsuario(usuario,usuarioLog);
	}

	@Override
	public int eliminarUsuario(Usuario usuario, Usuario usuarioLog) {
		
		return DaoPersona.getIntance().eliminarUsuario(usuario,usuarioLog);
	}
	
	
	

}
