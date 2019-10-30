package com.workants.eboleta2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.workants.eboleta2.model.Usuario;

public class DaoPersona {



	private static DaoPersona instance;
	
	private DaoPersona(){
		
	}
	
	public static DaoPersona getIntance(){
		
		if(instance == null){
			instance = new DaoPersona();
		}
		return instance;
	}

	public ArrayList<Usuario> getUsuarios() {
		
		String consulta = "select * from v_sesion where estado = 1";
		Vector<Object> datos = new Vector<Object>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rs = null;
		
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()){
			
				Usuario usuario = new Usuario();
				usuario.setNombreDeUsuario(rs.getString(2));
				usuario.setContrasenia(rs.getString(3));
				usuario.setearPerfil(rs.getString(4));
				usuario.setIdPersona(rs.getInt(5));
				usuario.setNombre(rs.getString(7));
				usuario.setApellido(rs.getString(8));
				usuario.setGenero(rs.getString(9).toUpperCase());
				usuario.setTipoDocumento(rs.getString(11));
				usuario.setNumeroDeDocumento(rs.getString(10));
				usuario.setEstadoPersona(rs.getInt(12) > 0);
				usuario.setNumeroLegajo(rs.getString(13));
				usuario.setNumeroInterno(rs.getString(14));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuarios;
	}

	public int guardarUsuario(Usuario usuario, Usuario usuarioLog) {
		
		
		String consulta = "select * from v_sesion where (numeroDeDocumento = ? and tipoDocumento = ?) and estado = 1";
		Vector<Object>datos = new Vector<Object>();
		datos.add(usuario.getNumeroDeDocumento());
		datos.add(usuario.getTipoDocumento());		
		Conexion conexion = null;
		ResultSet rsDocumento = null;
		ResultSet rsNombreDeUsuario = null;
		int rta = -1;
		int idUsuarioNuevo = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rsDocumento = conexion.select(consulta, datos);
			if(!rsDocumento.next()){  //no existe ese tipo y documento cargado, seguimos
				
				consulta = "select * from v_sesion where nombreDeUsuario = ? and estado = 1";
				datos.clear();
				datos.add(usuario.getNombreDeUsuario());
				rsNombreDeUsuario = conexion.select(consulta, datos);
				if(!rsNombreDeUsuario.next()){ //tampoco existe ese nombre de ser, asi que avanamos
					
					consulta = "insert into usuarios (nombre,apellido, genero,numeroDeDocumento,tipoDocumento,estado,numeroLegajo,"
							+ "numeroInterno) values (?,?,?,?,?,?,?,?)";
					datos.clear();
					datos.add(usuario.getNombre());
					datos.add(usuario.getApellido());
					datos.add(99); //genero. Se seteo en 99 ya que no se requiere este campo .........primero dicen que si, despues que no.
					datos.add(usuario.getNumeroDeDocumento());
					datos.add(usuario.getTipoDocumento());
					datos.add(usuario.isEstadoPersona());
					datos.add(usuario.getNumeroLegajo());
					datos.add(usuario.getNumeroInterno());
					idUsuarioNuevo = conexion.insert(consulta, datos);
					if( idUsuarioNuevo !=0){ //todo bien ahora insertamos en creenciales 
						
						consulta = "insert into credenciales (nombreDeUsuario, contrasenia, perfil, idPropietario) values (?,?,?,?)";
						datos.clear();
						datos.add(usuario.getNombreDeUsuario());
						datos.add(usuario.getContrasenia());
						datos.add(usuario.getPerfil().getNombreDePerfil().toUpperCase());
						datos.add(idUsuarioNuevo);
						if(conexion.insert(consulta, datos) != 0){
							
							consulta = "insert into log (fecha,servicio,metodo,tipo,idPersona,ip) values (?,?,?,?,?,?)";
							datos.clear();
							datos.add(new Timestamp(new Date().getTime()));		
							datos.add("servicioUsuario");
							datos.add("guardarUsuario():" + idUsuarioNuevo);
							datos.add("usuarioMCR");					
							WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
							String ipAddress = webBrowser.getAddress();
							datos.add(usuarioLog.getIdPersona());
							datos.add(ipAddress);
							conexion.insert(consulta, datos);
							conexion.getConexion().commit();
							return 1; //todo ok							
						}else{
							return rta; //otro error
						}					
					}else{
						return rta; //otro error
					}					
				}else{
					return 2; //nombre de usuario ya utilizado
				}			
			}else{
				return 0; //documento y tipo ya cargados
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int modificarUsuario(Usuario usuario, Usuario usuarioLog) {
		
		String consulta = "update usuarios set nombre = ?, apellido = ?, numeroDeDocumento = ?, tipoDocumento = ?, numeroLegajo = ?, numeroInterno = ? "
				+ "where idUsuario = ? and estado = 1";
		Vector<Object>datos = new Vector<Object>();
		datos.add(usuario.getNombre());
		datos.add(usuario.getApellido());
		datos.add(usuario.getNumeroDeDocumento());
		datos.add(usuario.getTipoDocumento());
		datos.add(usuario.getNumeroLegajo());
		datos.add(usuario.getNumeroInterno());
		datos.add(usuario.getIdPersona());
		Conexion conexion = null;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
		    conexion.update(consulta, datos);
		    
		    consulta = "update credenciales set contrasenia = ?, perfil = ? where nombreDeUsuario = ? and idPropietario = ?";
		    datos.clear();
		    datos.add(usuario.getContrasenia());
		    datos.add(usuario.getPerfil().getNombreDePerfil());
		    datos.add(usuario.getNombreDeUsuario());
		    datos.add(usuario.getIdPersona());
		    conexion.update(consulta, datos);
		    
			consulta = "insert into log (fecha,servicio,metodo,tipo,idPersona,ip) values (?,?,?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new Date().getTime()));		
			datos.add("servicioUsuario");
			datos.add("modificarUsuario():" + usuario.getIdPersona());
			datos.add("usuarioMCR");					
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(usuarioLog.getIdPersona());
			datos.add(ipAddress);
			conexion.insert(consulta, datos);
			conexion.getConexion().commit();
			return 1;
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//apellido = ?, nombre =?, cargo =?, legajo =? where idPropietario =? ";
		return 0;
	}

	public int eliminarUsuario(Usuario usuario, Usuario usuarioLog) {
		
		String consulta = "update usuarios set estado = 0 where idUsuario = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(usuario.getIdPersona());
		Conexion conexion = null;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
			consulta = "delete from credenciales where idPropietario = ?";
			datos.clear();
			datos.add(usuario.getIdPersona());
			conexion.update(consulta, datos);
			
			consulta = "insert into log (fecha,servicio,metodo,tipo,idPersona,ip) values (?,?,?,?,?,?)";
			datos.clear();
			datos.add(new Timestamp(new Date().getTime()));		
			datos.add("servicioUsuario");
			datos.add("modificarUsuario():" + usuario.getIdPersona());
			datos.add("usuarioMCR");					
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(usuarioLog.getIdPersona());
			datos.add(ipAddress);
			conexion.insert(consulta, datos);
			conexion.getConexion().commit();
			return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
