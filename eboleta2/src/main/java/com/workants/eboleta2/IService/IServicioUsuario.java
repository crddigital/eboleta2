package com.workants.eboleta2.IService;

import com.workants.eboleta2.model.Usuario;

public interface IServicioUsuario {

	
	public int guardarUsuario(Usuario usuario, Usuario usuarioLog);
	public int modificarUsuario(Usuario usuario, Usuario usuarioLog);
	public int eliminarUsuario(Usuario usuario, Usuario usuarioLog);
}
