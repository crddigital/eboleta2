package com.workants.eboleta2.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.UI;
import com.workants.eboleta2.model.Receptor;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.tools.EnviadorMailBaja;
import com.workants.eboleta2.tools.StringMD;



public class DaoValidar {

	
private static DaoValidar instance;
public static Logger log = Logger.getLogger(DaoValidar.class);	
	private DaoValidar(){
		
	}	
	
	public static DaoValidar getIntance(){
		
		if(instance == null){
			instance = new DaoValidar();
		}
		return instance;
	}

	public int validar(String codigo, String tipoImpuesto){
		
		//primero verificamos si la suscripcion no esta pendiente.
		// si rta == -1 no 
		String nombreImpuesto = "";
		
		if (tipoImpuesto.equalsIgnoreCase("1")){
			nombreImpuesto = "Impuesto automotor";
		}
		if (tipoImpuesto.equalsIgnoreCase("2")){
			nombreImpuesto = "Impuesto Inmobiliario";
		}
		if (tipoImpuesto.equalsIgnoreCase("3")){
			nombreImpuesto = "Impuesto Tasa de Higiene Urbana";
		}
		if (tipoImpuesto.equalsIgnoreCase("4")){
			nombreImpuesto = "Impuesto Derecho de Ocupante";
		}
		if (tipoImpuesto.equalsIgnoreCase("5")){
			nombreImpuesto = "Impuesto Derecho Ocupante Tasa de Higiene";
		}
		
		
		//verifico que no este dado anteriormente
		//99 son suscripciones canceladas por user del sistema
		String consulta = "select * from suscripciones where (codigoPagoElectronico = ? and tipoImpuesto = ?) and estadoSuscripcion <> 99 and estadoSuscripcion<> 2";
		Vector<Object>datos = new Vector<Object>();
		datos.add(codigo);
		datos.add(nombreImpuesto);
		ResultSet rs = null;
		Conexion conexion;
		int rta = -1;		
	
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			if(rs.next()){ // suscripcion pendiente
				rta = rs.getInt(5);				
				log.info("Suscripcion existente pendiente de aprobacion. Estado: " + rs.getString(5));
				return rta;
			}
			else {
					log.info("No se registra suscripción anterior. Ahora verificamos si datos son correctos ");
					datos.clear();				
					datos.add(codigo);
					if(tipoImpuesto.equalsIgnoreCase("1")){ //automotor 
					
					consulta = "select * from automotor where codigoRedLink = ?";									
					try {
						//conexion = new Conexion();						
						rs = conexion.queryConsulta(consulta, datos);
						if(rs.next()){
							UI.getCurrent().getSession().setAttribute("clave", rs.getString(1)); //recupero clave
							log.info("Clave recuperada automotor: " + rs.getString(1));
							return rta = 2;
//							if(!rs.getString(11).equalsIgnoreCase("0")){
//								rta = 4;								
//							}else{								
//							  return rta = 2;
//							}				
						} else {
							log.info("No existe ese numero de pago electronico:" + tipoImpuesto + " codigo:" + codigo);
							return 3;
						}
					} catch (SQLException e) {
						log.error("Error al verificar en impueto :" + tipoImpuesto);
						e.printStackTrace();
				}				
				
			}
					if(tipoImpuesto.equalsIgnoreCase("2")){//inmobiliario
				
						consulta = "select * from inmobiliario where nroPagoElectronico = ?";						
						try {
						//	conexion = new Conexion();
							rs = conexion.queryConsulta(consulta, datos);
							if(rs.next()){
								UI.getCurrent().getSession().setAttribute("clave", rs.getString(1)); //recupero clave
								log.info("Clave recuperada inmobiliario: " + rs.getString(1));
								rta = 2;
								return rta;
							} else {
								log.info("No existe ese numero de pago electronico:" + tipoImpuesto + " codigo:" + codigo);
								return 3;
							}
						} catch (SQLException e) {
							log.error("Error al verificar en impueto :" + tipoImpuesto);
							e.printStackTrace();
						}
				
					}
					if(tipoImpuesto.equalsIgnoreCase("3")){ //tasa de higiene
				
						consulta = "select * from inmobiliario where nroPagoElecThu = ?";			
						try {
							//conexion = new Conexion();
							rs = conexion.
									
									queryConsulta(consulta, datos);
							if(rs.next()){
								UI.getCurrent().getSession().setAttribute("clave", rs.getString(1)); //recupero clave
								log.info("Clave recuperada thu: " + rs.getString(1));
								rta = 2;
								return rta;
							} else {
								log.info("No existe ese numero de pago electronico:" + tipoImpuesto + " codigo:" + codigo);
								return 3;
							}
						} catch (SQLException e) {
							log.error("Error al verificar en impueto :" + tipoImpuesto);
							e.printStackTrace();
						}
				
					}
					if(tipoImpuesto.equalsIgnoreCase("4")){ //dereho de ocupante, se valida en tabla inmodiliario
				
						consulta = "select * from inmobiliario where nroPagoElectronico = ?";			
						try {
							//conexion = new Conexion();
							rs = conexion.queryConsulta(consulta, datos);
							if(rs.next()){
								UI.getCurrent().getSession().setAttribute("clave", rs.getString(1)); //recupero clave
								log.info("Clave recuperada derecho ocupante: " + rs.getString(1));
								rta = 2;								
								return rta;
							} else {
								log.info("No existe ese numero de pago electronico:" + tipoImpuesto + " codigo:" + codigo);
								return 3;
							}
						} catch (SQLException e) {
							log.error("Error al verificar en impueto :" + tipoImpuesto);
							e.printStackTrace();
						}
						
					}
					if(tipoImpuesto.equalsIgnoreCase("5")){ //dereho de ocupante thu, se valida en tabla inmodiliario
						
						consulta = "select * from inmobiliario where nroPagoElecThu = ?";			
						try {
							//conexion = new Conexion();
							rs = conexion.queryConsulta(consulta, datos);
							if(rs.next()){
								UI.getCurrent().getSession().setAttribute("clave", rs.getString(1)); //recupero clave
								rta = 2;
								log.info("Clave recuperada derecho ocupante thu: " + rs.getString(1));
								return rta;
							} else {
								log.info("No existe ese numero de pago electronico:" + tipoImpuesto + " codigo:" + codigo);
								return 3;
							}
						} catch (SQLException e) {
							log.error("Error al verificar en impueto :" + tipoImpuesto);
							e.printStackTrace();
						}
						
					}	
			}
	
		}
		 catch (SQLException e) {
			    log.error("Error al verificar suscripcion :" + tipoImpuesto);
				e.printStackTrace();
			}
	
	return rta;
	}

	public Usuario login(String nombreDeUsuario, String contrasenia) {
		
		
		String consulta = "select * from v_sesion where (nombreDeUsuario = ? and contrasenia = ? and estado = 1)";
		Vector<Object>datos = new Vector<Object>();
		datos.add(nombreDeUsuario);
		datos.add(generarSha(contrasenia));
		ResultSet rs = null;
		Conexion conexion = null;
		Usuario usuario = null;
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			if(rs.next()){
				usuario = new Usuario();
				usuario.setIdPersona(rs.getInt(6));
				usuario.setNombre(rs.getString(7));
				usuario.setApellido(rs.getString(8));
				usuario.setearPerfil(rs.getString(4));
			}
			else usuario = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(usuario);
		return usuario;
	}
	
	
	private String generarSha(String contrasenia) {
		
		contrasenia = StringMD.getStringMessageDigest(contrasenia, StringMD.SHA1);		
		return contrasenia;	
		}

	public String buscarImpuestoBaja(String tipoContribuyente,
			String numeroDeDocumento, String codigoPagoElectronico) {
		
		String consulta = "";
		Vector<Object>datos = new Vector<Object>();
		ResultSet rs = null;
		Conexion conexion;
		String rta = "";
		
		if (!tipoContribuyente.equalsIgnoreCase("cuit")){ //fisica
		
		consulta = "select * from v_predesuscripcion where tipoDocumento = ? "
				+ "and numeroDeDocumento = ? and codigoPagoElectronico = ? ";
		datos.add(tipoContribuyente);
		datos.add(numeroDeDocumento);
		datos.add(codigoPagoElectronico);
		
		rta = "<h3>Impuestos afectados para confirmar baja</h3>"
				+"<table>"
				+"<tr>"
				+"<td width='10%'><strong>Titular</strong></td>"
				+"<td width='10%'><strong>Tipo y Numero de Documento</strong></td>"
				+"<td width='10%'><strong>Tipo Impuesto</strong></td>"
				+"<td width='10%'><strong>Correo Electronico</strong></td>"
				//+"<td width='10%'><strong>¿Alta de recepción realizada por titular?</strong></td>"
				+"</tr>";	
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);			
			if(rs.next()){
				rta = rta + "<td>"+ rs.getString(7).toUpperCase()+", "+rs.getString(8).toUpperCase()+"</td>"
						  + "<td>"+ rs.getString(5).toUpperCase()+" "+rs.getString(6)+"</td>"
						  + "<td>"+ rs.getString(2).toUpperCase()+"</td>"
						  + "<td>"+ rs.getString(9).toLowerCase()+"</td>"
						  + "<td>"+ modificadorNull(rs.getString(10))+"</td>";
			}else{
				log.info("Resulset vacio fisico.");
				rta = "";
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		}
		else{ //juridica
		
		consulta = "select * from v_predesuscripcion where cuit = ?"
					+ " and codigoPagoElectronico = ?";
		datos.add(numeroDeDocumento);
		datos.add(codigoPagoElectronico);
		
		rta = "<h3>Impuestos afectados para confirmar baja</h3>"
				+"<table>"
				+"<tr>"
				+"<td width='10%'><strong>Razón Social</strong></td>"
				+"<td width='10%'><strong>CUIT</strong></td>"
				+"<td width='10%'><strong>Tipo Impuesto</strong></td>"
				+"<td width='10%'><strong>Correo Electronico</strong></td>"				
				+"</tr>";	
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			log.info("Soy rs antes del if juridico: " + rs);
			if(rs.next()){				
				rta = rta + "<td>"+ rs.getString(12).toUpperCase()+"</td>"
						  + "<td>"+ rs.getString(13)+"</td>"
						  + "<td>"+ rs.getString(2).toUpperCase()+"</td>"
						  + "<td>"+ rs.getString(14).toUpperCase()+"</td>"
						  +"</tr>";
			}
			else{
				log.info("Resulset vacio juridico.");
				rta = "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}			
		return rta;	
	}
		
		
		

	private String modificadorNull(String cadena) {
		
		String rta = "NO";
	
				if(cadena == null){
					rta = "SI";
				}
		return rta;
	}

	//public int generarMailBaja(String codigoPagoElectronico,String tipoDeDocumento, String numeroDeDocumento, String cuerpo) {
	public int generarMailBaja(String codigoPagoElectronico,String tipoDeContribuyente,String datosContribuyente,String cuerpo){
		
		String consulta = "";
		Vector<Object>datos = new Vector<Object>();
		ResultSet rs = null;
		Conexion conexion = null;
		final WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
		int rta = 0;
		if (!tipoDeContribuyente.equalsIgnoreCase("cuit")){ //fisica
			
			consulta = "select * from v_predesuscripcion where tipoDocumento = ?"
					+ "and numeroDeDocumento = ? and codigoPagoElectronico = ?";
			datos.add(tipoDeContribuyente);
			datos.add(datosContribuyente);
			datos.add(codigoPagoElectronico);
			int idSolicitante = 0;			
			try {
				conexion = new Conexion();
				conexion.getConexion().setAutoCommit(false);
				rs = conexion.select(consulta, datos);
				while(rs.next()){
					
					idSolicitante = rs.getInt(10);
					
					if(idSolicitante != 0){ //la suscripcion fué solicitada por ua persona que no es el titular
						
						consulta = "select * from solicitante where idSolicitante = ? and estado = 1";
						datos.clear();
						datos.add(idSolicitante);
						ResultSet rsSolicitante = null;
						rsSolicitante = conexion.select(consulta, datos);
						while(rsSolicitante.next()){
												
							//guarda en bajas para validar
							String keyBaja = RandomStringUtils.randomAlphanumeric(5);
							
							consulta = "insert into bajas (idSuscripcion,cpe,ip,keyBaja,time,estadoBaja) values(?,?,?,?,?,?)"; //<-- insertado para validar cuando acepten el mail
							datos.clear();
							datos.add(rs.getInt(1));
							datos.add(rs.getString(3));
							datos.add(webBrowser.getAddress());
							datos.add(keyBaja);
							datos.add(new Timestamp(new Date().getTime()));
							datos.add(1>0); //siempre va a ser true;
							conexion.insert(consulta, datos);
								
							rta = 1;							
							
							log.info("Se envia directamente al solicitante:" + rsSolicitante.getString(10));
							Receptor receptor = new Receptor();
							receptor.setCorreoElectronico(rsSolicitante.getString(10));
							receptor.setPartida(rs.getString(3));
							receptor.setTipoDocumento(rs.getString(2));
							receptor.setEsSolicitante("Nombre de la persona que dio alta el impuesto: " + rsSolicitante.getString(2)+" " + rsSolicitante.getString(3));
							
							EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Baja Boleta electronica", cuerpo, keyBaja);
							try {
								if (enviador.configAndDispatch(keyBaja) == 0){
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
						}				
					}else  {
						log.info("Correo enviado directamente al titular:" + rs.getString(9));
						
							
							//guarda en bajas para validar
							String keyBaja = RandomStringUtils.randomAlphanumeric(5);
							
							consulta = "insert into bajas (idSuscripcion,cpe,ip,keyBaja,time,estadoBaja) values(?,?,?,?,?,?)";
							datos.clear();
							datos.add(rs.getInt(1));
							datos.add(rs.getString(3));
							datos.add(webBrowser.getAddress());
							datos.add(keyBaja);
							datos.add(new Timestamp(new Date().getTime()));
							datos.add(1>0); //siempre va a ser true;
							conexion.insert(consulta, datos);
								
							rta = 1;
							
							log.info("se lo mando directamente al solicitante:" + idSolicitante);// rsSolicitante.getString(10));
							Receptor receptor = new Receptor();
							//receptor.setCorreoElectronico(rsSolicitante.getString(10));
							receptor.setCorreoElectronico(rs.getString(9));
							receptor.setPartida(rs.getString(3));
							receptor.setTipoDocumento(rs.getString(2));
							receptor.setEsSolicitante("Nombre de la persona que dio alta el impuesto: " + rs.getString(2)+" " + rs.getString(3));
							
							EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Baja Boleta electronica", cuerpo, keyBaja);
							try {
								if (enviador.configAndDispatch(keyBaja) == 0){
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
						
						
					}			
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error al generar conexion para el envio de mails de baja: " + e);
			}
			
			return rta;
			
		}
		else{ //juridica
			
			consulta = "select * from v_predesuscripcion where cuit = ?"
					+ " and codigoPagoElectronico = ?";
			datos.add(datosContribuyente);
			datos.add(codigoPagoElectronico);
			try {
				conexion = new Conexion();
				conexion.getConexion().setAutoCommit(false);
				rs = conexion.select(consulta, datos);
				while(rs.next()){
					
					//guarda en bajas para validar
					String keyBaja = RandomStringUtils.randomAlphanumeric(5);
					
					consulta = "insert into bajas (idSuscripcion,cpe,ip,keyBaja,time,estadoBaja) values(?,?,?,?,?,?)";
					datos.clear();
					datos.add(rs.getInt(1));
					datos.add(rs.getString(3));
					datos.add(webBrowser.getAddress());
					datos.add(keyBaja);
					datos.add(new Timestamp(new Date().getTime()));
					datos.add(1>0); //siempre va a ser true;
					conexion.insert(consulta, datos);
					
					rta = 1;
					
					log.info("se lo envio directamente al correo asociado organizacion:" + rs.getString(14));// rsSolicitante.getString(10));
					Receptor receptor = new Receptor();
					//receptor.setCorreoElectronico(rsSolicitante.getString(10));
					receptor.setCorreoElectronico(rs.getString(14));
					receptor.setPartida(rs.getString(3));
					receptor.setTipoDocumento(rs.getString(13)); //Aca es el CUIT de la empresa. En algun momento CUANDO TENGA GANAS cambiere el atributo
					receptor.setEsSolicitante("Nombre de la Organizacion que dio alta el impuesto: " + rs.getString(12));
					
					EnviadorMailBaja enviador = new EnviadorMailBaja(receptor, "Confirmacion Baja Boleta electronica", cuerpo, keyBaja);
					try {
						if (enviador.configAndDispatch(keyBaja) == 0){
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
					
					//return rta;	
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return rta;
		
		
		
		
	
		
		
		
	

	}

	public int verificarCpe(String cpe) {
		
		String consulta = "select * from bajas where cpe = ? and estadoBaja = 1";
		Vector<Object>datos = new Vector<Object>();
		datos.add(cpe);
		ResultSet rs = null;
		Conexion conexion;
		int rta = 0;
		try {
			conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			if(rs.next()){
				rta = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rta;
	}

	public int ejecutarBaja(String cpe, String codigoKeyBaja) {
		
		
		String consulta = "select * from bajas where keyBaja = ? and cpe = ? and estadoBaja = 1"; //verifico si la baja fue solicitada, 1 = solicitada
		Vector<Object>datos = new Vector<Object>();
		datos.add(codigoKeyBaja);
		datos.add(cpe);
		Conexion conexion;
		ResultSet rs = null;
		int rta = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rs = conexion.select(consulta, datos);
			if(rs.next()){
				consulta = "update bajas set estadoBaja = 0 where cpe = ? and keyBaja = ?"; //si baja solicitada cambio estado y confirmo baja. Seteo 0 = confirmada
				datos.clear();
				datos.add(cpe);
				datos.add(codigoKeyBaja);
				conexion.update(consulta, datos);			
				consulta = "update suscripciones set estadoSuscripcion = 2 where codigoPagoElectronico = ?"; 
				datos.clear();
				datos.add(cpe);
				conexion.update(consulta, datos);
				conexion.getConexion().commit();
				rta = 1;
			}
			else{
				rta = 0;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rta;
	}

	public int verificarCpeSuscripcion(String cpe) {
		
		log.info("**Verificamos estado suscripcion....: " + cpe+" **");
		String consulta = "select * from suscripciones s where s.codigoPagoElectronico = ? and s.estadoSuscripcion = 0";
		Vector<Object>datos = new Vector<Object>();
		datos.add(cpe);
		Conexion conexion = null;
		ResultSet rs = null;
		ResultSet rsTitular = null;
		int rta = 0;
		try {
		conexion = new Conexion();
		conexion.getConexion().setAutoCommit(false);
		rs = conexion.select(consulta, datos);
		if(rs.next()){
						
			log.info("**Suscripcion aprobada**");
			consulta = "update suscripciones set estadoSuscripcion = 1 where codigoPagoElectronico = ? and estadoSuscripcion = 0"; // 29/11/2017
			datos.clear();
			datos.add(cpe);
			conexion.update(consulta, datos);
			
			if(rs.getString(7).equalsIgnoreCase("fisico")){		
			
			log.info("**CONTRIBUYENTE FISICO**");	
			log.info("**Aprobamos titular**");
			consulta = "update titular set estado = 1 where idTitular = ? and estado = 99";
			datos.clear();
			datos.add(rs.getInt(2));
			conexion.update(consulta, datos);
			
			log.info("**Verificamos si titular tiene solicitante**");
			consulta = "select * from titular where idTitular = ? and estado = ? and idSolicitante is not null";
			datos.add(1); //solo agrego dato del estado, id Titular lo tengo
			rsTitular = conexion.select(consulta, datos);
			if(rsTitular.next()){
				
				log.info("**Posee solicitante, por lo cual aprobamos al solicitante tambien**");
				consulta = "update solicitante set estado = 1 where idSolicitante = ? and estado = 99";
				datos.clear();
				datos.add(rsTitular.getInt(12));
				conexion.update(consulta, datos);			
			}
			
			else{
				
				log.info("**No posee solicitante, por lo cual finalizamos controles**");
			}
			
			}else{
				
				log.info("**CONTRIBUYENTE JURIDICO**");
				log.info("**Aprobamos juridica**");
				consulta = "update juridica set estado = 1 where id = ? and estado = 99";
				datos.clear();
				datos.add(rs.getInt(2));
				conexion.update(consulta, datos);
			}
			
			
			//reset consulta,datos y luego insert en log			
			datos.clear();
			consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
			datos.add(new Timestamp(new Date().getTime()));
			datos.add("servicioValidar");
			datos.add("verificarCpeSuscripcion(cpe:"+cpe+")");
			datos.add("contribuyente: " + rs.getString(7));
			datos.add(rs.getInt(1));		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);			
			int idLog = conexion.insert(consulta, datos);	
			conexion.getConexion().commit();	
			return idLog;
			
			
		}else {
			log.info("**Sucripcion ya dada de alta o inexistente: " + cpe);
			rta = 2;
		}
		
		
		} catch (SQLException e) {			
			rta = 0;
			log.error("Error Conexion: " + e);		
			try {
				conexion.getConexion().rollback();
			} catch (SQLException e1) {
				log.error("Error en rollback : " + e1);
			}
			
		}	
		
		return rta;
	}

}
