package com.workants.eboleta2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.PreSolicitud;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.model.Usuario;


public class DaoSolicitudes {

	
	private static DaoSolicitudes instance;
	public static Logger log = Logger.getLogger(DaoSolicitudes.class);
	
	
	private DaoSolicitudes(){
		
	}
	
	
	public static DaoSolicitudes getInstance(){
		
		if(instance == null){
			instance = new DaoSolicitudes();
		}
		return instance;
	}

	public ArrayList<PreSolicitud> getPresolicitudesJuridicas(){
	
		String consulta = "select * from v_presolicitudes where estadoSolicitud = 0";
		Vector<Object>datos = new Vector<Object>();
		ArrayList<PreSolicitud> preSolicitudes = new ArrayList<PreSolicitud>();
		ResultSet rs = null;	
		Conexion conexion;
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()){
				
				PreSolicitud preSolicitud = new PreSolicitud();			
				if((rs.getInt(2) == 0)){ 
					
					preSolicitud.setJuridica(new Juridica());
					preSolicitud.getJuridica().setId(rs.getInt(23));
					preSolicitud.getJuridica().setRazonSocial(rs.getString(24));
					preSolicitud.getJuridica().setCuit(rs.getString(25));
					
					preSolicitud.getJuridica().setNumeroDeTelefono(rs.getString(27));
					preSolicitud.getJuridica().setTipoTelefono(rs.getString(28));
					
					preSolicitud.getJuridica().setCorreoElectronico(rs.getString(29));					
					
					preSolicitud.getJuridica().setNumeroDeTelefonoAlternativo(rs.getString(31));
					preSolicitud.getJuridica().setTipoTelefonoAlternativo(rs.getString(32));
					
					preSolicitud.setTipoImpuesto(rs.getString(20));
					preSolicitud.setCodigoPagoElectronico(rs.getString(30));
					preSolicitudes.add(preSolicitud);
				}
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	return preSolicitudes;	
	}
	
	
	public ArrayList<PreSolicitud> getPresolicitudes() {
		
		String consulta = "select * from v_presolicitudes where estadoSolicitud = 0";
		Vector<Object>datos = new Vector<Object>();
		ArrayList<PreSolicitud> preSolicitudes = new ArrayList<PreSolicitud>();
		ResultSet rs = null;	
		Conexion conexion;
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()){
				
			//	log.info("get int 2 :" + rs.getInt(2));
			//	log.info("get int 23 :" + rs.getInt(23));
				PreSolicitud preSolicitud = new PreSolicitud();		
				preSolicitud.setCodigoPagoElectronico(rs.getString(30)); //sea fisico o jurudico siempre estara!
				if((rs.getInt(2) != 0)){  // es fisico. Para juridico analizo con otro metodo getPresolicitudesJuridicas() 	
				
				//PreSolicitud preSolicitud = new PreSolicitud();
				preSolicitud.setIdSolicitud(rs.getInt(1));
				preSolicitud.setTitular(new Titular());
				preSolicitud.getTitular().setIdPersona(rs.getInt(2));
				preSolicitud.getTitular().setNombre(rs.getString(3));
				preSolicitud.getTitular().setApellido(rs.getString(4));
				preSolicitud.getTitular().setTipoDocumentoTitular(rs.getString(5));
				preSolicitud.getTitular().setNumeroDeDocumentoTitular(rs.getString(6));
				preSolicitud.getTitular().setTipoTelefonoTitular(rs.getString(7));
				preSolicitud.getTitular().setNumeroDeTelefonoTitular(rs.getString(8));
				preSolicitud.getTitular().setTipoTelefonoAlternativoTitular(rs.getString(9));
				preSolicitud.getTitular().setNumeroDeTelefonoAlternativoTitular(rs.getString(10));
				preSolicitud.getTitular().setCorreoElectronicoTitular(rs.getString(11));
				
					if(rs.getInt(12) != 0){ //posee solicitante
					
						preSolicitud.setSolicitante(new Solicitante());
						preSolicitud.getSolicitante().setIdPersona(rs.getInt(12));
						//preSolicitud.getSolicitante().setTipoDocumento(rs.getString(4));
						preSolicitud.getSolicitante().setNombre(rs.getString(13));
						preSolicitud.getSolicitante().setApellido(rs.getString(14));
						preSolicitud.getSolicitante().setTipoDocumento(rs.getString(15));
						preSolicitud.getSolicitante().setNumeroDeDocumentoSolicitante(rs.getString(16));
						preSolicitud.getSolicitante().setTipoTelefonoSolicitante(rs.getString(17));
						preSolicitud.getSolicitante().setNumeroDeTelefonoSolicitante(rs.getString(18));
						preSolicitud.getSolicitante().setCorreoElectronicoSolicitante(rs.getString(19));
				    	}else {
				    		preSolicitud.setSolicitante(null);
				    		//preSolicitud.setTipoImpuesto(rs.getString(20));
				    		//preSolicitud.setEstadoSolicitud(rs.getByte(22) > 0);
				    		
				    	}
					preSolicitud.setTipoImpuesto(rs.getString(20));
		    		preSolicitud.setEstadoSolicitud(rs.getByte(22) > 0);
					preSolicitudes.add(preSolicitud);
				      }	
				
			}
		} catch (SQLException e) {
			log.error("Error al instanciar Conexion()" + e);
			//e.printStackTrace();
		}
		System.out.println(preSolicitudes.size());
		return preSolicitudes;
	}


	public int acpetarSuscripcion(PreSolicitud presolicitud, Usuario usuario) {
																				
		String consulta = "update suscripciones set estadoSuscripcion = 1 where idSubcripcion = ? and idTitular = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(presolicitud.getIdSolicitud());
		datos.add(presolicitud.getTitular().getIdPersona());
		int rta = 0;
		Conexion conexion;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rta = conexion.update(consulta, datos);
			if(rta > 0){
				
				//reset consulta,datos y luego insert en log
				datos.clear();
				consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona) values (?,?,?,?,?)";
				datos.add(new Timestamp(new Date().getTime()));
				datos.add("servicioSolicitudes");
				datos.add("acpetarSuscripcion(presolicitud:"+presolicitud.getIdSolicitud()+", usuario:"+usuario.getIdPersona()+")");
				datos.add("usuario MCR");
				datos.add(usuario.getIdPersona());					
				int idLog = conexion.insert(consulta, datos);				
				conexion.getConexion().commit();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return rta;
	}


	public int cancelarSuscripcion(PreSolicitud presolicitud, Usuario usuario) {
		
		String consulta = "update suscripciones set estadoSuscripcion = 2 where idSubcripcion = ? and idTitular = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(presolicitud.getIdSolicitud());
		datos.add(presolicitud.getTitular().getIdPersona());
		int rta = 0;
		Conexion conexion;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rta = conexion.update(consulta, datos);
			if(rta > 0){
				
				//reset consulta,datos y luego insert en log
				datos.clear();
				consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona) values (?,?,?,?,?)";
				datos.add(new Timestamp(new Date().getTime()));
				datos.add("servicioSolicitudes");
				datos.add("cancelarSuscripcion(presolicitud:"+presolicitud.getIdSolicitud()+", usuario:"+usuario.getIdPersona()+")");
				datos.add("usuario MCR");
				datos.add(usuario.getIdPersona());					
				int idLog = conexion.insert(consulta, datos);				
				conexion.getConexion().commit();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return rta;
	}
	
	public ArrayList<PreSolicitud> buscarSuscriptro(String tipoDeDocumento, String numeroDeDocumento) {
		
		String consulta = "";
		Vector<Object>datos = new Vector<Object>();
		ArrayList<PreSolicitud> preSolicitudes = new ArrayList<PreSolicitud>();
		ResultSet rs = null;	
		Conexion conexion;
		try {
			conexion = new Conexion();
			
			if(!tipoDeDocumento.equalsIgnoreCase("cuit")){
				consulta = "select * from v_presolicitudes where tipoDocumento = ? and numeroDocumento = ?";
				datos.add(tipoDeDocumento);
				datos.add(numeroDeDocumento);
				rs = conexion.queryConsulta(consulta, datos);				
				while(rs.next()){
					
					//	log.info("get int 2 :" + rs.getInt(2));
					//	log.info("get int 23 :" + rs.getInt(23));
						PreSolicitud preSolicitud = new PreSolicitud();			
						if((rs.getInt(2) != 0)){  // es fisico				
						
						//PreSolicitud preSolicitud = new PreSolicitud();
						preSolicitud.setIdSolicitud(rs.getInt(1));
						preSolicitud.setTitular(new Titular());
						preSolicitud.getTitular().setIdPersona(rs.getInt(2));
						preSolicitud.getTitular().setNombre(rs.getString(3));
						preSolicitud.getTitular().setApellido(rs.getString(4));
						preSolicitud.getTitular().setTipoDocumentoTitular(rs.getString(5));
						preSolicitud.getTitular().setNumeroDeDocumentoTitular(rs.getString(6));
						preSolicitud.getTitular().setTipoTelefonoTitular(rs.getString(7));
						preSolicitud.getTitular().setNumeroDeTelefonoTitular(rs.getString(8));
						preSolicitud.getTitular().setTipoTelefonoAlternativoTitular(rs.getString(9));
						preSolicitud.getTitular().setNumeroDeTelefonoAlternativoTitular(rs.getString(10));
						preSolicitud.getTitular().setCorreoElectronicoTitular(rs.getString(11));
						
							if(rs.getInt(12) != 0){ //posee solicitante
							
								preSolicitud.setSolicitante(new Solicitante());
								preSolicitud.getSolicitante().setIdPersona(rs.getInt(12));
								preSolicitud.getSolicitante().setNombre(rs.getString(13));
								preSolicitud.getSolicitante().setApellido(rs.getString(14));
								preSolicitud.getSolicitante().setTipoDocumentoTitular(rs.getString(15));
								preSolicitud.getSolicitante().setNumeroDeDocumentoSolicitante(rs.getString(16));
								preSolicitud.getSolicitante().setTipoTelefonoSolicitante(rs.getString(17));
								preSolicitud.getSolicitante().setNumeroDeTelefonoSolicitante(rs.getString(18));
								preSolicitud.getSolicitante().setCorreoElectronicoSolicitante(rs.getString(19));
						    	}else {
						    		preSolicitud.setSolicitante(null);
						    		preSolicitud.setTipoImpuesto(rs.getString(20));
						    		preSolicitud.setEstadoSolicitud(rs.getByte(22) > 0);						    		
						    	}
							preSolicitudes.add(preSolicitud);
						      }							
					}
			}else{
				
				consulta = "select * from v_presolicitudes where cuit = ?";
				datos.add(numeroDeDocumento);				
				rs = conexion.queryConsulta(consulta, datos);				
				while(rs.next()){
					
					PreSolicitud preSolicitud = new PreSolicitud();			
					if((rs.getInt(2) == 0)){ 
						
						preSolicitud.setJuridica(new Juridica());
						preSolicitud.getJuridica().setId(rs.getInt(23));
						preSolicitud.getJuridica().setRazonSocial(rs.getString(24));
						preSolicitud.getJuridica().setCuit(rs.getString(25));
						preSolicitud.getJuridica().setTipo(rs.getString(26));
						preSolicitud.getJuridica().setNumeroDeTelefono(rs.getString(27));
						preSolicitud.getJuridica().setTipoTelefono(rs.getString(28));
						preSolicitud.getJuridica().setCorreoElectronico(rs.getString(29));
						preSolicitud.setTipoImpuesto(rs.getString(20));
						preSolicitudes.add(preSolicitud);
						
					}
					
				}
			}
		} catch (SQLException e) {
			log.error("Error al instanciar Conexion()" + e);			
		}		
		return preSolicitudes;
	}
}
