package com.workants.eboleta2.presenter;

import java.io.Serializable;





import com.vaadin.data.Item;
import com.vaadin.ui.UI;
import com.workants.eboleta2.tools.StringMD;
import com.workants.eboleta2.handler.ILayoutABMUsuariosHandler;
import com.workants.eboleta2.model.PerfilAdministrador;
import com.workants.eboleta2.model.PerfilAdministrativo;
import com.workants.eboleta2.model.PerfilAutomotor;
import com.workants.eboleta2.model.PerfilInmobiliario;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.servicio.ServicioUsuario;
import com.workants.eboleta2.ui.formularios.LayoutABMUsuarios;

public class PresenterLayoutABMUsuarios implements Serializable, ILayoutABMUsuariosHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LayoutABMUsuarios view;
	private ServicioUsuario service;
	
	
	public PresenterLayoutABMUsuarios(LayoutABMUsuarios view, ServicioUsuario service) {
		
		this.view = view;
		this.service = service;
	
	}


	@Override
	public void guardarUsuario() {
		
		
		Usuario usuarioLog = (Usuario) UI.getCurrent().getSession().getAttribute("usuario"); // <--para el log
		
		Usuario usuario = new Usuario();
		usuario.setIdPersona(0);
		usuario.setApellido(view.getTxtApellido().getValue());
		usuario.setNombre(view.getTxtNombre().getValue());
		usuario.setTipoDocumento(view.getCmbTipoDocumento().getValue().toString());
		usuario.setNumeroDeDocumento(view.getTxtNumeroDeDocumento().getValue());
		usuario.setNumeroInterno(view.getTxtNumeroInterno().getValue());
		usuario.setNumeroLegajo(view.getTxtNumeroLegajo().getValue());
		usuario.setNombreDeUsuario(view.getTxtNombreDeUsuario().getValue());
		usuario.setContrasenia(generarSha(view.getTxtContrasenia().getValue()));
		
		
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("administrador")){
			usuario.setPerfil(new PerfilAdministrador());
		}
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("administrativo")){
			usuario.setPerfil(new PerfilAdministrativo());
		}
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("automotor")){
			usuario.setPerfil(new PerfilAutomotor());
		}
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("inmobiliario")){
			usuario.setPerfil(new PerfilInmobiliario());
		}
		usuario.setEstadoPersona(true);
		
		int rta = service.guardarUsuario(usuario, usuarioLog);
		
		//rta = -1 - otro error
		//rta =  0  - dni ya cargado
 		//rta =  1  - ok guardado
		//rta =  2  - nombre de usuario repetido
		
		
		if( rta == 0){
			view.guardarUsuarioDniRepetido();
		}else if(rta == 1){
			view.guardarUsuarioOK();
			view.getTablaUsuarios().addItem(usuario);
			limpiar();
		}else if(rta == 2){
			view.guardarUsuarioUserRepeat();
		}else if(rta == -1){
			view.guardarUsuarioOtroError();
		}
		
		
		
	}


	@Override
	public void eliminarUsuario() {
		
		Usuario usuarioLog = (Usuario) UI.getCurrent().getSession().getAttribute("usuario"); // <--para el log
		
		Item itemTabla = view.getTablaUsuarios().getItem(view.getTablaUsuarios().getValue());
		Usuario usuario = new Usuario();
		usuario.setIdPersona((Integer)itemTabla.getItemProperty("idPersona").getValue());
		
		int rta = service.eliminarUsuario(usuario, usuarioLog);
		
		if(rta !=0){
			view.getTablaUsuarios().removeItem(view.getTablaUsuarios().getValue());
			view.getTablaUsuarios().refreshRowCache();
			limpiar();
			view.eliminarUsuarioOK();
		}else{
			view.eliminarUsuarioError();
		}
		
		
	}


	


	@SuppressWarnings("unchecked")
	@Override
	public void modificarUsuario() {
		
		Usuario usuarioLog = (Usuario) UI.getCurrent().getSession().getAttribute("usuario"); // <--para el log
		
		Item itemTabla = view.getTablaUsuarios().getItem(view.getTablaUsuarios().getValue());
		
		Usuario usuario = new Usuario();
		usuario.setIdPersona((Integer)itemTabla.getItemProperty("idPersona").getValue());
		usuario.setApellido(view.getTxtApellido().getValue());
		usuario.setNombre(view.getTxtNombre().getValue());
		usuario.setTipoDocumento(view.getCmbTipoDocumento().getValue().toString());
		usuario.setNumeroDeDocumento(view.getTxtNumeroDeDocumento().getValue());
		usuario.setNumeroInterno(view.getTxtNumeroInterno().getValue());
		usuario.setNumeroLegajo(view.getTxtNumeroLegajo().getValue());
		usuario.setNombreDeUsuario(view.getTxtNombreDeUsuario().getValue());
		
		if(view.getChbModificarContrasenia().getValue()){
			usuario.setContrasenia(generarSha(view.getTxtContrasenia().getValue())); //modifica contrase
		}else {
			usuario.setContrasenia(itemTabla.getItemProperty("contrasenia").getValue().toString()); //no modificar
		}
		
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("administrador")){
			usuario.setPerfil(new PerfilAdministrador());
		}
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("administrativo")){
			usuario.setPerfil(new PerfilAdministrativo());
		}
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("automotor")){
			usuario.setPerfil(new PerfilAutomotor());
		}
		if(view.getCmbPerfil().getValue().toString().equalsIgnoreCase("inmobiliario")){
			usuario.setPerfil(new PerfilInmobiliario());
		}
		
		usuario.setEstadoPersona(true);
		
		int rta = service.modificarUsuario(usuario, usuarioLog);
		
		//rta = 0 - error
		//rta = 1 - ok	
		
		if( rta !=0){
			view.modificarUsuarioOK();
		//	view.getTablaUsuarios().addItem(usuario);
			itemTabla.getItemProperty("apellido").setValue(usuario.getApellido());
			itemTabla.getItemProperty("nombre").setValue(usuario.getNombre());
			itemTabla.getItemProperty("tipoDocumento").setValue(usuario.getTipoDocumento());
			itemTabla.getItemProperty("numeroDeDocumento").setValue(usuario.getNumeroDeDocumento());
			itemTabla.getItemProperty("perfil.nombreDePerfil").setValue(usuario.getPerfil().getNombreDePerfil());
			itemTabla.getItemProperty("contrasenia").setValue(usuario.getContrasenia());
			view.getTablaUsuarios().refreshRowCache();
			limpiar();
		}else{
			view.modificarUsuarioError();
		}
		
		
	}
	
	
	@Override
	public void limpiar() {

		//System.out.println("estado txtnombre de usuario antes: " + view.getTxtNombreDeUsuario().isEnabled());
		
		
		view.getTxtApellido().clear();
		view.getTxtNombre().clear();	
		view.getTxtContrasenia().clear();
		view.getChbModificarContrasenia().clear();
		view.getTxtNumeroDeDocumento().clear();
		view.getCmbGenero().clear();
		view.getCmbPerfil().clear();
		view.getCmbTipoDocumento().clear();
		view.getTxtNumeroInterno().clear();
		view.getTxtNumeroLegajo().clear();		
		
		view.getTxtContrasenia().setEnabled(true);
		view.getChbModificarContrasenia().setEnabled(false);	
		view.getTxtContrasenia().setRequired(true);
		view.getTxtNombreDeUsuario().setEnabled(true);
		view.getTxtNombreDeUsuario().setEnabled(true);
		view.getTablaUsuarios().clear();
		
		
		//System.out.println("estado txtnombre de usuario depois: " + view.getTxtNombreDeUsuario().isEnabled());
		
	}
	private String generarSha(String contrasenia) {
		
		contrasenia = StringMD.getStringMessageDigest(contrasenia, StringMD.SHA1);		
		//System.out.println("nuevo pass: " + contrasenia);
		return contrasenia;
	}
	
	
	
	

}
