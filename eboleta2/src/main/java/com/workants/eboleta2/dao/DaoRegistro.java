package com.workants.eboleta2.dao;


import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;



import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.Receptor;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.tools.EnviadorMailBaja;

public class DaoRegistro {

	
	private static DaoRegistro instance;
	public static Logger log = Logger.getLogger(DaoRegistro.class);
	
	private DaoRegistro(){
		
	}	
	
	public static DaoRegistro getIntance(){
		
		if(instance == null){
			instance = new DaoRegistro();
		}
		return instance;
	}
	
	
//	public int subscribir(Titular titular, Solicitante solicitante, String nombreDeImpuesto, String codigo){
//		
//		String consulta = "insert into (nombre, apellido, tipoDocumento, numeroDeDocumento, tipoTelefono, numeroTelefono, "
//				+ "tipoTelefonoAlternativo,	numeroTelefonoAlternativo, correoElectronico, estado) values "
//				+ "(?,?,?,?,?,?,?,?,?,?)";
//		Vector<Object> datos = new Vector<Object>();
//		datos.add(titular.getNombre());
//		datos.add(titular.getApellido());
//		datos.add(titular.getTipoDocumentoTitular());
//		datos.add(titular.getNumeroDeDocumentoTitular());
//		datos.add(titular.getTipoTelefonoTitular());
//		datos.add(titular.getNumeroDeDocumentoTitular());
//		datos.add(titular.getTipoTelefonoAlternativoTitular());
//		datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
//		datos.add(titular.getCorreoElectronicoTitular());
//		datos.add((titular.isEstadoPersona()) ? 1 : 0);
//		int idTitular = 0;
//		Conexion conexion = null;
//		try {
//			conexion = new Conexion();
//			conexion.getConexion().setAutoCommit(false);
//			idTitular = conexion.insert(consulta, datos);
//			if(idTitular > 0){
//				
//				consulta = "insert into (idTitular, tipoImpuesto, "
//						+ "fechaSubcripcion ,estadoSubscripcion, codigoPagoElectronico`) values (?,?,?,?,?)";
//				boolean estadoSubscripcion = false;
//				datos.clear();
//				datos.add(idTitular);
//				datos.add(nombreDeImpuesto);
//				datos.add(new Timestamp(new Date().getTime()));
//				datos.add(estadoSubscripcion ? 1:0);
//				datos.add(codigo);
//				int idSubscripcion = conexion.insert(consulta, datos);
//				conexion.getConexion().commit();
//				return idSubscripcion;
//			}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return 0;
//	}
	
	public int subscribir(Titular titular, String nombreDeImpuesto, String codigo, String clave){
		
		
		//verifico que dni no este dado de alta
		String consulta = "select * from titular t where t.numeroDeDocumento = ? and estado <> 0";
		ResultSet rsTitular = null;
		Vector<Object> datos = new Vector<Object>();
		datos.add(titular.getNumeroDeDocumentoTitular());
		Conexion conexion = null;
		int idTitular = 0;
		int idLog = 0;
		int rta = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rsTitular = conexion.select(consulta, datos);			
			if(rsTitular.next()){
			 	idTitular = rsTitular.getInt(1);
				log.info("idTitular existente: " + idTitular);
				
					//actualizo datos del titular
					consulta = "update titular set nombre = ?, apellido = ?, tipoDocumento = ?, numeroDeDocumento = ?, tipoTelefono = ?, "
							+ "numeroTelefono = ?, tipoTelefonoAlternativo = ?, numeroTelefonoAlternativo = ?, correoElectronico = ? "
							+ "where idTitular = ? and estado <> 0";
				    datos.clear();
				    datos.add(titular.getNombre());
				    datos.add(titular.getApellido());
				    datos.add(titular.getTipoDocumentoTitular());
				    datos.add(titular.getNumeroDeDocumentoTitular());
				    datos.add(titular.getTipoTelefonoTitular());
				    datos.add(titular.getNumeroDeTelefonoTitular());
				    datos.add(titular.getTipoTelefonoAlternativoTitular());
				    datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
				    datos.add(titular.getCorreoElectronicoTitular());
				    datos.add(idTitular);
				    
				    
				    conexion.update(consulta, datos); // simple update de datos del titular				
				
					if (insertarSuscripcion(idTitular, nombreDeImpuesto, datos, "fisico", codigo, clave, conexion) != 0){
					
					//log
					//reset consulta,datos y luego insert en log
					datos.clear();
					consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
					datos.add(new Timestamp(new java.util.Date().getTime()));
					datos.add("servicioRegistro");
					datos.add("suscribir(fisico:"+idTitular+", imputacion:"+nombreDeImpuesto+", clave:"+clave+")");
					datos.add("fisico");
					datos.add(idTitular);		
					WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
					String ipAddress = webBrowser.getAddress();
					datos.add(ipAddress);								
					idLog = conexion.insert(consulta, datos); 
					}				
				    
				}  
			else {
		
				log.info("** idTitular inexistente inserto y genero id: " + idTitular + " **");
				consulta = "insert into titular (nombre, apellido, tipoDocumento, numeroDeDocumento, tipoTelefono, numeroTelefono "
				+ ",tipoTelefonoAlternativo, numeroTelefonoAlternativo, correoElectronico, estado) values "
				+ "(?,?,?,?,?,?,?,?,?,?)";
				//Vector<Object> datos = new Vector<Object>();
				 datos.clear();
				 datos.add(titular.getNombre());
				 datos.add(titular.getApellido());
				 datos.add(titular.getTipoDocumentoTitular());
				 datos.add(titular.getNumeroDeDocumentoTitular());
				 datos.add(titular.getTipoTelefonoTitular());
				 datos.add(titular.getNumeroDeTelefonoTitular());
				 datos.add(titular.getTipoTelefonoAlternativoTitular());
				 datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
				 datos.add(titular.getCorreoElectronicoTitular());
				 datos.add(99);// en stand by a confirmar
				 //		int idTitular = 0;
				 //		Conexion conexion = null;
				 //		try {
				 //conexion = new Conexion();
				 //conexion.getConexion().setAutoCommit(false);
				 idTitular = conexion.insert(consulta, datos);
				 //int rta = 0;
				 if(idTitular > 0){
				
					 
					if (insertarSuscripcion(idTitular, nombreDeImpuesto, datos, "fisico", codigo, clave, conexion) != 0){							
						 //log
						//reset consulta,datos y luego insert en log
						datos.clear();
						consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
						datos.add(new Timestamp(new java.util.Date().getTime()));
						datos.add("servicioRegistro");
						datos.add("suscribir(fisico:"+idTitular+", imputacion:"+nombreDeImpuesto+", clave:"+clave+")");
						datos.add("fisico");
						datos.add(idTitular);		
						WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
						String ipAddress = webBrowser.getAddress();
						datos.add(ipAddress);								
						idLog = conexion.insert(consulta, datos);
						}				
				   }else{
					   log.info("No es posible insertar titular");
					   return 0;
				   }				 
				} 		
				
				if(idTitular != 0 ){	
					
					Receptor receptor = new Receptor();
					receptor.setCorreoElectronico(titular.getCorreoElectronicoTitular());
					receptor.setPartida(codigo);
					receptor.setTipoImpuesto(nombreDeImpuesto);
					receptor.setTipoDocumento(titular.getTipoDocumentoTitular());
					receptor.setNumeroDeDocumento(titular.getNumeroDeDocumentoTitular());
				
				//receptor.setEsSolicitante("Nombre de la persona que dio alta el impuesto: " + rsSolicitante.getString(2)+" " + rsSolicitante.getString(3));
				
				
					String cuerpo = "Alta para recibir  boleta electronica " + codigo +" para "+ nombreDeImpuesto ;				
					EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Alta boleta electronica", cuerpo);
				
					try {
						if (enviador.configAndDispatch() == 0){
							log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
							conexion.getConexion().rollback();
							return rta = 0;
						}
					} catch (UnsupportedEncodingException e) {
						log.error("Error de Codificacion no soportada");
						conexion.getConexion().rollback();
						return rta = 0;
					} catch (MessagingException e) {
						log.error("Error de autentificacion ");
						conexion.getConexion().rollback();
						return rta = 0;
					}			
					conexion.getConexion().commit();
					}
				else{
					conexion.getConexion().rollback();
					log.error("No se inserto titular, rollback");
				}
				return idLog;		
		} catch (SQLException e) {
			log.error("Error al instanciar Conexion():" + e);
			
		}
		
		
		return 0;
	}
	
	public int subscribirSolicitante(Titular titular, Solicitante solicitante, String nombreDeImpuesto, String codigo, String clave) {
		
				
		//verifico que dni no este dado de alta
		String consulta = "select * from solicitante s where s.numeroDeDocumento = ? and s.estado <> 0";
		ResultSet rsSolicitante = null;
		Vector<Object> datos = new Vector<Object>();
		datos.add(solicitante.getNumeroDeDocumentoSolicitante());
		Conexion conexion = null;
		int idSolicitante = 0;
		int idTitular = 0;
		int idLog = 0;
		int rta = 0;
		try {
		
		conexion = new Conexion();
		conexion.getConexion().setAutoCommit(false);
		rsSolicitante = conexion.select(consulta, datos);			
		if(rsSolicitante.next()){
			
		 	idSolicitante = rsSolicitante.getInt(1);
			log.info("idSolicitante existente: " + idSolicitante);
			
			//actualizo datos del solicitante
			consulta = "update solicitante set nombre = ?, apellido = ?, tipoDocumento = ?, numeroDeDocumento = ?, tipoTelefono = ?, "
					+ "numeroTelefono = ?, tipoTelefonoAlternativo = ?, numeroTelefonoAlternativo = ?, correoElectronico = ? "
					+ "where idSolicitante = ? and estado <> 0";
			datos.clear();
			datos.add(solicitante.getNombre());
			datos.add(solicitante.getApellido());
			datos.add(solicitante.getTipoDocumentoTitular());
			datos.add(solicitante.getNumeroDeDocumentoSolicitante());
			datos.add(solicitante.getTipoTelefonoSolicitante());
			datos.add(solicitante.getNumeroDeTelefonoSolicitante());
			datos.add("no aplica");
			datos.add("no aplica");
			datos.add(solicitante.getCorreoElectronicoSolicitante());
			datos.add(idSolicitante);
			
			if(conexion.update(consulta, datos) != 0){ // simple update de datos del solicitante	
			
			consulta = "insert into titular (nombre, apellido, tipoDocumento, numeroDeDocumento, tipoTelefono, numeroTelefono "
					+ ",tipoTelefonoAlternativo, numeroTelefonoAlternativo, correoElectronico, estado, idSolicitante) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?)";
			datos.clear();
			datos.add(titular.getNombre());
			datos.add(titular.getApellido());
			datos.add(titular.getTipoDocumentoTitular());
			datos.add(titular.getNumeroDeDocumentoTitular());
			datos.add(titular.getTipoTelefonoTitular());
			datos.add(titular.getNumeroDeTelefonoTitular());
			datos.add(titular.getTipoTelefonoAlternativoTitular());
			datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
			datos.add("no aplica");
			//datos.add((titular.isEstadoPersona()) ? 1 : 0);
			datos.add(99);//en stand by a confirmar
			datos.add(idSolicitante);
			
			idTitular = 0;
			idTitular = conexion.insert(consulta, datos);
			if(idTitular > 0){
				
				if (insertarSuscripcion(idTitular, nombreDeImpuesto, datos, "fisico", codigo, clave, conexion) != 0){
					
					 //log
					//reset consulta,datos y luego insert en log
					datos.clear();
					consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
					datos.add(new Timestamp(new java.util.Date().getTime()));
					datos.add("servicioRegistro");
					datos.add("suscribir(fisico:"+idTitular+", imputacion:"+nombreDeImpuesto+", clave:"+clave+")");
					datos.add("fisico");
					datos.add(idTitular);		
					WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
					String ipAddress = webBrowser.getAddress();
					datos.add(ipAddress);								
					idLog = conexion.insert(consulta, datos);
					
				}
			 }else{
				   log.info("No es posible insertar titular");
				   return 0;
			   }
				
			}		
		}else{
			
			log.info("** idSolicitante inexistente inserto y genero id: **");
			
			consulta = "insert into solicitante (nombre, apellido, tipoDocumento, numeroDeDocumento, "
					+ "tipoTelefono, numeroTelefono, tipoTelefonoAlternativo, "
					+ "numeroTelefonoAlternativo, correoElectronico, estado) values (?,?,?,?,?,?,?,?,?,?)"; 
			datos.clear();
			datos.add(solicitante.getNombre());
			datos.add(solicitante.getApellido());
			datos.add(solicitante.getTipoDocumentoTitular());
			datos.add(solicitante.getNumeroDeDocumentoSolicitante());
			datos.add(solicitante.getTipoTelefonoSolicitante());
			datos.add(solicitante.getNumeroDeTelefonoSolicitante());
			datos.add("no aplica");
			datos.add("no aplica");
			//datos.add(solicitante.getTipoTelefonoAlternativoSolicitante());
			//datos.add(solicitante.getNumeroDeTelefonoAlternativoSolicitante());
			datos.add(solicitante.getCorreoElectronicoSolicitante());
			//datos.add((solicitante.isEstadoPersona()) ? 1 : 0);
			datos.add(99);//en stand by a confirmar...
			
			//int idSolicitante = 0;
			//Conexion conexion = null;
			//int rta =0;
						
			idSolicitante = conexion.insert(consulta, datos);
			if (idSolicitante > 0){
				
				consulta = "insert into titular (nombre, apellido, tipoDocumento, numeroDeDocumento, tipoTelefono, numeroTelefono "
						+ ",tipoTelefonoAlternativo, numeroTelefonoAlternativo, correoElectronico, estado, idSolicitante) values "
						+ "(?,?,?,?,?,?,?,?,?,?,?)";
				datos.clear();
				datos.add(titular.getNombre());
				datos.add(titular.getApellido());
				datos.add(titular.getTipoDocumentoTitular());
				datos.add(titular.getNumeroDeDocumentoTitular());
				datos.add(titular.getTipoTelefonoTitular());
				datos.add(titular.getNumeroDeDocumentoTitular());
				datos.add(titular.getTipoTelefonoAlternativoTitular());
				datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
				datos.add("no aplica");
				//datos.add((titular.isEstadoPersona()) ? 1 : 0);
				datos.add(99);//en stand by a confirmar
				datos.add(idSolicitante);
				
				idTitular = 0;
				idTitular = conexion.insert(consulta, datos);
				if(idTitular > 0){
					
					if (insertarSuscripcion(idTitular, nombreDeImpuesto, datos, "fisico", codigo, clave, conexion) != 0){
						
						 //log
						//reset consulta,datos y luego insert en log
						datos.clear();
						consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
						datos.add(new Timestamp(new java.util.Date().getTime()));
						datos.add("servicioRegistro");
						datos.add("suscribir(fisico:"+idTitular+", imputacion:"+nombreDeImpuesto+", clave:"+clave+")");
						datos.add("fisico");
						datos.add(idTitular);		
						WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
						String ipAddress = webBrowser.getAddress();
						datos.add(ipAddress);								
						idLog = conexion.insert(consulta, datos);
						
					}
				 }else{
					   log.info("No es posible insertar titular");
					   return 0;
				   }		
				
			}else{
				   log.info("No es posible insertar solicitante");
				   return 0;
			}
		}
		
//		
//	
//		
//		
//	
//				if (idSolicitante > 0){
//					consulta = "insert into titular (nombre, apellido, tipoDocumento, numeroDeDocumento, tipoTelefono, numeroTelefono "
//							+ ",tipoTelefonoAlternativo, numeroTelefonoAlternativo, correoElectronico, estado, idSolicitante) values "
//							+ "(?,?,?,?,?,?,?,?,?,?,?)";
//					datos.clear();
//					datos.add(titular.getNombre());
//					datos.add(titular.getApellido());
//					datos.add(titular.getTipoDocumentoTitular());
//					datos.add(titular.getNumeroDeDocumentoTitular());
//					datos.add(titular.getTipoTelefonoTitular());
//					datos.add(titular.getNumeroDeDocumentoTitular());
//					datos.add(titular.getTipoTelefonoAlternativoTitular());
//					datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
//					datos.add("no aplica");
//					//datos.add((titular.isEstadoPersona()) ? 1 : 0);
//					datos.add(99);//en stand by a confirmar
//					datos.add(idSolicitante);
//					
//					int idTitular = 0;
//					idTitular = conexion.insert(consulta, datos);
//						if(idTitular > 0){
//						
//							//inserto en subscripcion
//							consulta = "insert into suscripciones (idSuscriptor, tipoImpuesto, "
//								+ "fechaSuscripcion, estadoSuscripcion, codigoPagoElectronico,tipo, clave) values (?,?,?,?,?,?,?)";
//							boolean estadoSubscripcion = true; // queda por defecto el estado en true .....deberia validarse por una persona los datos pero se decide que pase derecho
//							datos.clear();
//							datos.add(idTitular);
//							datos.add(nombreDeImpuesto);
//							datos.add(new Timestamp(new Date().getTime()));
//							//datos.add(estadoSubscripcion ? 1:0);
//							datos.add(0);
//							datos.add(codigo);
//							datos.add("fisico");
//							datos.add(clave);
//							conexion.insert(consulta, datos);			
//										
//							//reset consulta,datos y luego insert en log
//							datos.clear();
//							consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
//							datos.add(new Timestamp(new Date().getTime()));
//							datos.add("servicioRegistrar");
//							datos.add("suscribir(titular:"+idTitular+", nombreDeImpuesto:"+nombreDeImpuesto+", codigo:"+codigo+", clave:"+clave+")");
//							datos.add("contribuyente");
//							datos.add(idTitular);		
//							WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
//							String ipAddress = webBrowser.getAddress();
//							datos.add(ipAddress);
//							int idLog = conexion.insert(consulta, datos);	
							
							if(idTitular != 0 ){
							Receptor receptor = new Receptor();
							receptor.setCorreoElectronico(solicitante.getCorreoElectronicoSolicitante());
							receptor.setPartida(codigo);
							receptor.setTipoImpuesto(nombreDeImpuesto);
							receptor.setTipoDocumento(titular.getTipoDocumentoTitular());
							receptor.setNumeroDeDocumento(titular.getNumeroDeDocumentoTitular());
							
														
							String cuerpo = "Alta para recibir boleta electronica " + codigo +" para "+ nombreDeImpuesto ; 
							EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Alta boleta electronica", cuerpo);
							
							try {
								if (enviador.configAndDispatch() == 0){
									log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
									conexion.getConexion().rollback();
									rta = 0;
								}
							} catch (UnsupportedEncodingException e) {
								log.error("Error de Codificacion no soportada: " + e);

							} catch (MessagingException e) {
								log.error("Error de autentificacion: " + e);					
							}		
							
							conexion.getConexion().commit();
							}else{
								conexion.getConexion().rollback();
								log.error("No se inserto titular, rollback");
							}
							return idLog;
		
		} catch (SQLException e) {
			log.error("Error: "  + e);
		}
		
	
		
		return 0;
	}
	
	
	
	public int subscribirJuridica(Juridica juridica, String nombreDeImpuesto, String codigo, String clave) {
	
	String consulta = "select * from juridica j where j.cuit = ? and estado <> 0"; 
	ResultSet rsJuridica = null;
	Vector<Object> datos = new Vector<Object>();
	datos.add(juridica.getCuit());
	Conexion conexion = null;
	int idJuridica = 0;
	int idLog = 0;
	int rta = 0;
	
	try {
		conexion = new Conexion();
		conexion.getConexion().setAutoCommit(false);
		rsJuridica = conexion.select(consulta, datos);		
		if(rsJuridica.next()){
			idJuridica = rsJuridica.getInt(1);
			log.info("idJuridica existente: " + idJuridica);
			if (insertarSuscripcion(idJuridica, nombreDeImpuesto, datos, "juridico", codigo, clave, conexion) != 0){
				
				//log
				//reset consulta,datos y luego insert en log
				datos.clear();
				consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
				datos.add(new Timestamp(new java.util.Date().getTime()));
				datos.add("servicioRegistro");
				datos.add("suscribir(juridico:"+idJuridica+", imputacion:"+nombreDeImpuesto+", clave:"+clave+")");
				datos.add("juridico");
				datos.add(idJuridica);		
				WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
				String ipAddress = webBrowser.getAddress();
				datos.add(ipAddress);								
				idLog = conexion.insert(consulta, datos); 
			}
		 }
		else{
			log.info("** idJuridica inexistente inserto y genero id: **");
			consulta = "insert into juridica (razonSocial, cuit, tipo, numeroTelefono, tipoTelefono, numeroTelefonoAlternativo "
					+ ",tipoTelefonoAlternativo, correoElectronico, estado) values "
					+ "(?,?,?,?,?,?,?,?,?)";
			datos.clear();
			datos.add(juridica.getRazonSocial());
			datos.add(juridica.getCuit());
			datos.add(juridica.getTipo());
			datos.add(juridica.getNumeroDeTelefono());
			datos.add(juridica.getTipoTelefono());
			datos.add(juridica.getNumeroDeTelefonoAlternativo());
			datos.add(juridica.getTipoTelefonoAlternativo());
			datos.add(juridica.getCorreoElectronico());		
			datos.add(99);// en stand by a confirmar
			idJuridica = conexion.insert(consulta, datos);
			 if(idJuridica > 0){
				 
				 if (insertarSuscripcion(idJuridica, nombreDeImpuesto, datos, "juridico", codigo, clave, conexion) != 0){	
					 
					 //log
						//reset consulta,datos y luego insert en log
						datos.clear();
						consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
						datos.add(new Timestamp(new java.util.Date().getTime()));
						datos.add("servicioRegistro");
						datos.add("suscribir(juridico:"+idJuridica+", imputacion:"+nombreDeImpuesto+", clave:"+clave+")");
						datos.add("fisico");
						datos.add(idJuridica);		
						WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
						String ipAddress = webBrowser.getAddress();
						datos.add(ipAddress);								
						idLog = conexion.insert(consulta, datos);
				 }	 
			 }else{
				   log.info("No es posible insertar juridica");
				   return 0;
			   }
			}
			 
			 if(idJuridica != 0 ){	
				 
				 Receptor receptor = new Receptor();
				 receptor.setCorreoElectronico(juridica.getCorreoElectronico());
				 receptor.setPartida(codigo);
				 receptor.setTipoImpuesto(nombreDeImpuesto);
				 
				 String cuerpo = "Alta para recibir  boleta electronica " + codigo +" para "+ nombreDeImpuesto ; 				
				 EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Alta boleta electronica", cuerpo);
				 
				 try{
				 if (enviador.configAndDispatch() == 0){
					 	
					 log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
						conexion.getConexion().rollback();
						return rta = 0;
				 	
				 	} 
			 
				 } catch (UnsupportedEncodingException e) {
						log.error("Error de Codificacion no soportada");
						conexion.getConexion().rollback();
						return rta = 0;
				 } catch (MessagingException e) {
						log.error("Error de autentificacion ");
						conexion.getConexion().rollback();
						return rta = 0;
				 }			
					conexion.getConexion().commit();
				 }
				else{
					conexion.getConexion().rollback();
					log.error("No se inserto juridica, rollback");
				 }
				return idLog;		
		} catch (SQLException e) {
			log.error("Error al instanciar Conexion():" + e);
			
		}
		
		
		return 0;
	}

	/*public int subscribirJuridica(Juridica juridica, String nombreDeImpuesto, String codigo, String clave) {
		
		//Primero deberia verificar si existe....lo veo luego
		
		
		String consulta = "insert into juridica (razonSocial, cuit, tipo, numeroTelefono, tipoTelefono, numeroTelefonoAlternativo "
				+ ",tipoTelefonoAlternativo, correoElectronico, estado) values "
				+ "(?,?,?,?,?,?,?,?,?)";
		Vector<Object> datos = new Vector<Object>();
		datos.add(juridica.getRazonSocial());
		datos.add(juridica.getCuit());
		datos.add(juridica.getTipo());
		datos.add(juridica.getNumeroDeTelefono());
		datos.add(juridica.getTipoTelefono());
		datos.add(juridica.getNumeroDeTelefonoAlternativo());
		datos.add(juridica.getTipoTelefonoAlternativo());
		datos.add(juridica.getCorreoElectronico());		
		datos.add(99);// en stand by a confirmar
		int idJuridica = 0;
		Conexion conexion = null;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			idJuridica = conexion.insert(consulta, datos);
			int rta = 0;
			if(idJuridica > 0){
				
				//inserto en subscripcion
				consulta = "insert into suscripciones (idSuscriptor, tipoImpuesto, fechaSuscripcion, estadoSuscripcion, codigoPagoElectronico, tipo, clave) values (?,?,?,?,?,?,?)";
				boolean estadoSubscripcion = false; // queda por defecto el estado en false .....deberia validarse por una persona los datos pero se decide que pase derecho
				datos.clear();
				datos.add(idJuridica);
				datos.add(nombreDeImpuesto);
				datos.add(new Timestamp(new Date().getTime()));
				datos.add(0);// en stand by a confirmar
				datos.add(codigo);
				datos.add("juridico");
				datos.add(clave);
				if (conexion.insert(consulta, datos) != 0)	{		
				
				//reset consulta,datos y luego insert en log
				datos.clear();
				consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
				datos.add(new Timestamp(new Date().getTime()));
				datos.add("servicioRegistrar");
				datos.add("suscribir(titular:"+idJuridica+", nombreDeImpuesto:"+nombreDeImpuesto+", codigo:"+codigo+", clave:"+clave+")");
				datos.add("juridico");
				datos.add(idJuridica);		
				WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
				String ipAddress = webBrowser.getAddress();
				datos.add(ipAddress);
				
				
				
				int idLog = conexion.insert(consulta, datos);				
				
				Receptor receptor = new Receptor();
				receptor.setCorreoElectronico(juridica.getCorreoElectronico());
				receptor.setPartida(codigo);
				receptor.setTipoImpuesto(nombreDeImpuesto);
				//receptor.setTipoDocumento(juridica.getTipoDocumentoTitular());
				//receptor.setNumeroDeDocumento(juridica.getNumeroDeDocumentoTitular());
				
				//receptor.setEsSolicitante("Nombre de la persona que dio alta el impuesto: " + rsSolicitante.getString(2)+" " + rsSolicitante.getString(3));
				
				
				String cuerpo = "Alta para recibir  boleta electronica " + codigo +" para "+ nombreDeImpuesto ; 				
				EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Alta boleta electronica", cuerpo);
				
				try {
					if (enviador.configAndDispatch() == 0){
						log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
						conexion.getConexion().rollback();
						rta = 0;
					}
				} catch (UnsupportedEncodingException e) {
					log.error("Error de Codificacion no soportada");

				} catch (MessagingException e) {
					log.error("Error de autentificacion ");					
				}		
				
				conexion.getConexion().commit();
				return idLog;
				}else{
					
					log.info("No es posible insertar suscripcion");
					conexion.getConexion().rollback();
				}
			}else {
				conexion.getConexion().rollback(); 
				log.error("No se inserto titular, rollback");
				//System.out.println("No se inserto titular, rollback");			
			}
		} catch (SQLException e) {
			log.error("Error al instanciar Conexion:" + e);
			e.printStackTrace();
		}

		
		return 0;
	}

	*/

	public int actualizarSuscriptor(Titular titular, String nombreImpuesto,
			String codigo) {
		
		String consulta = "update titular set nombre =?, apellido =?, tipoDocumento =?, numeroDeDocumento =?, tipoTelefono =?, numeroTelefono =?, tipoTelefonoAlternativo =?, "
				+ "numeroTelefonoAlternativo =?, correoElectronico =? where estado = 0 and idSolicitante = null and idTitular = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(titular.getNombre());
		datos.add(titular.getApellido());
		datos.add(titular.getTipoDocumentoTitular());
		datos.add(titular.getNumeroDeDocumentoTitular());
		datos.add(titular.getTipoTelefonoTitular());
		datos.add(titular.getNumeroDeTelefonoTitular());
		datos.add(titular.getTipoTelefonoAlternativoTitular());
		datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
		datos.add(titular.getCorreoElectronicoTitular());
		datos.add(titular.getIdPersona());
		Conexion conexion = null;
		int rta = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			
			conexion.update(consulta, datos);
			
			
			//reset consulta,datos y luego insert en log
			datos.clear();
			consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
			datos.add(new Timestamp(new Date().getTime()));
			datos.add("servicioSolcitud");
			datos.add("actualizarSuscriptor(titular)");
			datos.add("contribuyente");
			datos.add(titular.getIdPersona());		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);
			
			
			
			Receptor receptor = new Receptor();
			receptor.setCorreoElectronico(titular.getCorreoElectronicoTitular());
			receptor.setPartida(codigo);
			receptor.setTipoImpuesto(nombreImpuesto);
			receptor.setTipoDocumento(titular.getTipoDocumentoTitular());
			receptor.setNumeroDeDocumento(titular.getNumeroDeDocumentoTitular());
			
			//receptor.setEsSolicitante("Nombre de la persona que dio alta el impuesto: " + rsSolicitante.getString(2)+" " + rsSolicitante.getString(3));
			int idLog = conexion.insert(consulta, datos);		
			
			String cuerpo = "Actualización para recibir  boleta electronica " + codigo +" para "+ nombreImpuesto; 
			
			EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Actualización Alta boleta electronica", cuerpo);
			
			try {
				if (enviador.configAndDispatch() == 0){
					log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
					conexion.getConexion().rollback();
					return rta = 0;
				}
			} catch (UnsupportedEncodingException e) {
				log.error("Error de Codificacion no soportada");
				conexion.getConexion().rollback();
				return rta = 0;

			} catch (MessagingException e) {
				log.error("Error de autentificacion ");
				conexion.getConexion().rollback();
				return rta = 0;
			}		
			
			conexion.getConexion().commit();
			return idLog;
			
			
		} catch (SQLException e) {
			log.error("Error al iniciar Conexion ");
			e.printStackTrace();
		}
		
		
		
		
		
		return 0;
	}

	public int actualizarSolicitante(Titular titular, Solicitante solicitante,
			String nombreImpuesto, String codigo) {
		
		String consulta = "update solicitante set nombre = ?, apellido = ?, tipoDocumento = ?, numeroDeDocumento = ?, tipoTelefono = ?, "
				+ "numeroTelefono = ?, tipoTelefonoAlternativo = ?, numeroTelefonoAlternativo = ?, correoElectronico = ? where idSolicitante = ? and estado = 0";
		Vector<Object>datos = new Vector<Object>();
		datos.add(solicitante.getNombre());
		datos.add(solicitante.getApellido());
		datos.add(solicitante.getTipoDocumento());
		datos.add(solicitante.getNumeroDeDocumentoSolicitante());
		datos.add(solicitante.getTipoTelefonoSolicitante());
		datos.add(solicitante.getNumeroDeTelefonoSolicitante());
		datos.add("");
		datos.add("");
		datos.add(solicitante.getCorreoElectronicoSolicitante());
		datos.add(solicitante.getIdPersona());
		Conexion conexion = null;
		int rta =0;		
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			consulta = "update titular set nombre =?, apellido =?, tipoDocumento =?, numeroDeDocumento =?, tipoTelefono =?, numeroTelefono =?, tipoTelefonoAlternativo =?, "
					+ "numeroTelefonoAlternativo =?, correoElectronico =? where estado = 0 and idSolicitante = null and idTitular = ?";
			datos.clear();
			datos.add(titular.getNombre());
			datos.add(titular.getApellido());
			datos.add(titular.getTipoDocumentoTitular());
			datos.add(titular.getNumeroDeDocumentoTitular());
			datos.add(titular.getTipoTelefonoTitular());
			datos.add(titular.getNumeroDeTelefonoTitular());
			datos.add(titular.getTipoTelefonoAlternativoTitular());
			datos.add(titular.getNumeroDeTelefonoAlternativoTitular());
			datos.add(titular.getCorreoElectronicoTitular());
			datos.add(titular.getIdPersona());
			conexion.update(consulta, datos);
			
			//reset consulta,datos y luego insert en log
			datos.clear();
			consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
			datos.add(new Timestamp(new Date().getTime()));
			datos.add("servicioRegistrar");
			datos.add("actualizarSolicitante(titular,solicitante,nombreDeImpuesto,codigo)");
			datos.add("contribuyente");
			datos.add(titular.getIdPersona());		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);
			int idLog = conexion.insert(consulta, datos);	
			
			
			Receptor receptor = new Receptor();
			receptor.setCorreoElectronico(solicitante.getCorreoElectronicoSolicitante());
			receptor.setPartida(codigo);
			receptor.setTipoImpuesto(nombreImpuesto);
			receptor.setTipoDocumento(titular.getTipoDocumentoTitular());
			receptor.setNumeroDeDocumento(titular.getNumeroDeDocumentoTitular());
			
										
			String cuerpo = "Actualización para recibir boleta electronica " + codigo +" para "+ nombreImpuesto ; 
			EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Actualización Alta boleta electronica", cuerpo);
			
			try {
				if (enviador.configAndDispatch() == 0){
					log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
					conexion.getConexion().rollback();
					rta = 0;
					return rta;
				}
			} catch (UnsupportedEncodingException e) {
				log.error("Error de Codificacion no soportada: " + e);
				return rta;

			} catch (MessagingException e) {
				log.error("Error de autentificacion: " + e);		
				return rta;
			}		
			
			conexion.getConexion().commit();
			return idLog;
//		}else {
//			conexion.getConexion().rollback(); 
//			log.error("No se inserto titular, rollback");
//		}							
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public int actualizarJuridica(Juridica juridica, String nombreImpuesto,String codigo) {
		
		String consulta = "update juridica set razonSocial = ?, cuit = ?, numeroTelefono = ?, tipoTelefono = ?, numeroTelefonoAlternativo = ?, "
				+ " tipoTelefonoAlternativo = ?, correoElectronico = ? where id = ? and estado = 0";
		Vector<Object>datos = new Vector<Object>();
		datos.add(juridica.getRazonSocial());
		datos.add(juridica.getCuit());
		datos.add(juridica.getNumeroDeTelefono());
		datos.add(juridica.getTipoTelefono());
		datos.add(juridica.getNumeroDeTelefonoAlternativo());
		datos.add(juridica.getTipoTelefonoAlternativo());
		datos.add(juridica.getCorreoElectronico());
		datos.add(juridica.getId());
		Conexion conexion = null;
		int rta = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			conexion.update(consulta, datos);
			
			
			//reset consulta,datos y luego insert en log
			datos.clear();
			consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
			datos.add(new Timestamp(new Date().getTime()));
			datos.add("servicioRegistrar");
			datos.add("actualizarJuridica(juridica,nombreDeImpuesto,codigo)");
			datos.add("juridica");
			datos.add(juridica.getId());		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);
			int idLog = conexion.insert(consulta, datos);	
			
			Receptor receptor = new Receptor();
			receptor.setCorreoElectronico(juridica.getCorreoElectronico());
			receptor.setPartida(codigo);
			receptor.setTipoImpuesto(nombreImpuesto);
			//receptor.setTipoDocumento(titular.getTipoDocumentoTitular());
			//receptor.setNumeroDeDocumento(titular.getNumeroDeDocumentoTitular());
			
										
			String cuerpo = "Actualización para recibir boleta electronica " + codigo +" para "+ nombreImpuesto ; 
			EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Actualización Alta boleta electronica", cuerpo);
			
			try {
				if (enviador.configAndDispatch() == 0){
					log.error("No se ha podido enviar correo electronico, se deshacen operaciones");
					conexion.getConexion().rollback();
					rta = 0;
					return rta;
				}
			} catch (UnsupportedEncodingException e) {
				log.error("Error de Codificacion no soportada: " + e);
				conexion.getConexion().rollback();
				return rta;

			} catch (MessagingException e) {
				log.error("Error de autentificacion: " + e);
				conexion.getConexion().rollback();
				return rta;
			}
			
			conexion.getConexion().commit();
			return idLog;
			
		} catch (SQLException e) {
			log.error("Error en consulta: " + e);
			try {
				conexion.getConexion().rollback();
			} catch (SQLException e1) {
				log.error("Error en rollback: " + e1);
				
			}
			e.printStackTrace();
		}
		return 0;
	}
	
	
	private int insertarSuscripcion(int idSuscriptor, String nombreDeimpuesto, Vector<Object> datos, String tipo, String codigo, String clave, Conexion conexion) {
		
		if (idSuscriptor !=0 ){
			
			datos.clear();
			String consulta = "insert into suscripciones (idSuscriptor, "
					+ "tipoImpuesto, "
					+ "fechaSuscripcion, "
					+ "estadoSuscripcion, "
					+ "codigoPagoElectronico, "
					+ "tipo,"
					+ "clave) values (?,?,?,?,?,?,?)  ";
			datos.add(idSuscriptor);	
			datos.add(nombreDeimpuesto);		
			datos.add(new Timestamp(new Date().getTime()));
			datos.add(0);// en stand by a confirmar aca no uso 99, solo 0.
			datos.add(codigo);
			datos.add(tipo);
			datos.add(clave);				
			int idSuscripcion;
			try {
				idSuscripcion = conexion.insert(consulta, datos);
				if ( idSuscripcion != 0){
					return 1;
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return 0;
	
		
	}



}
