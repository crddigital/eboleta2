package com.workants.eboleta2.dao;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.workants.eboleta2.model.PreSolicitudConfirmar;
import com.workants.eboleta2.model.Procesados;
import com.workants.eboleta2.model.Receptor;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.tools.CSVUtils;




public class DaoEnvio {

	
	private static DaoEnvio instance;
	
	public static Logger log = Logger.getLogger(DaoEnvio.class);	
	
	private DaoEnvio(){
		
	}
	
	public static DaoEnvio getIntance(){
		
		if(instance == null){
			instance = new DaoEnvio();
		}
		return instance;
	}
	
	
	
	
	/*public int generarProceso() {
		
		log.info("** Generamos proceso para personas fisicas **");
		String consulta = "select * from v_envios where estadoSubcripcion = 1";
		Vector<Object> datos = new Vector<Object>();
		ArrayList<Receptor>receptores = new ArrayList<Receptor>();
		Conexion conexion = null;
		String cadenaNula = null;
		String clave = null;	
		String codigoImputacion = "";
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			ResultSet rs = null;
			ResultSet rsRecibos = null;
			rs = conexion.select(consulta, datos);
			while(rs.next()){
			
					
				//personas fisicas
				//verifico si esta disponible en "recibos" para su envio		
				//aseguro que si o si es una cadena de un impuesto
				
				rs.getString(11); //automotor
				rs.getString(12); //inmobiliario, derecho ocupante
				rs.getString(13); //tasa de higiene, tasa higiene ocupante
				
				
				//1 - Inmobiliario
				//17 - Ocupante
				//97 - THU
				//03 - Automotor
				
				
				if (rs.getString(9).equalsIgnoreCase("Impuesto automotor")) {
					codigoImputacion = "03";
				}else if (rs.getString(9).equalsIgnoreCase("Impuesto Derecho de Ocupante")) {
					codigoImputacion = "17";
				}else if (rs.getString(9).equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")) {
					codigoImputacion = "97";
				}else if (rs.getString(9).equalsIgnoreCase("Impuesto Inmobiliario")) {
					codigoImputacion = "1";
				}
								
				if( rs.getString(11) != cadenaNula){
					clave = rs.getString(11);					
				}				
				if( rs.getString(12)!= cadenaNula){
					clave = rs.getString(12);					
				}				
				if( rs.getString(13)!= cadenaNula){
					clave = rs.getString(13);
					
				}
					
				consulta = "select * from recibo r where r.clave = ? and publicar = 1 and (r.codigo_imputacion= ?)";
				datos.clear();
				datos.add(clave);
				datos.add(codigoImputacion);
				rsRecibos = conexion.select(consulta, datos);
				
				while(rsRecibos.next()){ //receptores con publicaciones disponibles			
				
				
				if(rs.getInt(5) != 0){ //posee solicitante
						Receptor receptor = new Receptor();
						receptor.setIdPersona(rs.getInt(5));
						receptor.setNombre(rs.getString(2));
						receptor.setApellido(rs.getString(3));
						receptor.setEsSolicitante("Es solicitante");
						receptor.setCorreoElectronico(rs.getString(8));
						receptor.setTipoImpuesto(rs.getString(9));
						receptor.setCpe(rs.getString(10));
						if (rs.getString(9).equalsIgnoreCase("Impuesto automotor")){
							receptor.setPartida(rs.getString(11));							
						}
						if (rs.getString(9).equalsIgnoreCase("Impuesto Inmobiliario") ||
								rs.getString(9).equalsIgnoreCase("Impuesto Derecho de Ocupante")){
							receptor.setPartida(rs.getString(12));
						}
						if (rs.getString(9).equalsIgnoreCase("Impuesto Tasa de Higiene urbana") ||
								rs.getString(9).equalsIgnoreCase("Impuesto Derecho Ocupante Tasa de Higiene")){
							receptor.setPartida(rs.getString(13));
						}
						if (rs.getString(9).equalsIgnoreCase("Impuesto automotor")){
							receptor.setPartida(rs.getString(11));
						}			
					receptores.add(receptor);
					log.info("receptor solicitante agregado fisico: " + receptor.getCorreoElectronico());
					
				}else { //es titular
					
					Receptor receptor = new Receptor();
					receptor.setIdPersona(rs.getInt(1));
					receptor.setNombre(rs.getString(2));
					receptor.setApellido(rs.getString(3));
					receptor.setCorreoElectronico(rs.getString(4));
					receptor.setEsSolicitante("Es titular");
					receptor.setTipoImpuesto(rs.getString(9));
					receptor.setCpe(rs.getString(10));
					if (rs.getString(9).equalsIgnoreCase("Impuesto automotor")){
						receptor.setPartida(rs.getString(11));
					}
					if (rs.getString(9).equalsIgnoreCase("Impuesto Inmobiliario") ||
							rs.getString(9).equalsIgnoreCase("Impuesto Derecho de Ocupante")){
						receptor.setPartida(rs.getString(12));
					}
					if (rs.getString(9).equalsIgnoreCase("Impuesto Tasa de Higiene urbana") ||
							rs.getString(9).equalsIgnoreCase("Impuesto Derecho Ocupante Tasa de Higiene")){
						receptor.setPartida(rs.getString(13));
					}
					
					receptores.add(receptor);
					log.info("receptor titular agregado fisico: " + receptor.getCorreoElectronico());
				}
				
				
				
			}
			
		}//no estan aptos para ser publicados
		 
	
			
			
			log.info("** Generamos proceso para personas juridicas **");
			consulta = "select * from v_envios_juridica where estadoSubcripcion = 1"; 
			datos.clear();			
			rs = conexion.select(consulta, datos);
			while(rs.next()){
				
				
				//personas juridicas
				//verifico si esta disponible en "recibos" para su envio		
				//aseguro que si o si es una cadena de un impuesto
				
				rs.getString(7); //automotor
				rs.getString(8); //inmobiliario, derecho ocupante
				rs.getString(9); //tasa de higiene, tasa higiene ocupante
				
				
				//1 - Inmobiliario
				//17 - Ocupante
				//97 - THU
				//03 - Automotor
				
				
				if (rs.getString(9).equalsIgnoreCase("Impuesto automotor")) {
					codigoImputacion = "03";
				}else if (rs.getString(9).equalsIgnoreCase("Impuesto Derecho de Ocupante")) {
					codigoImputacion = "17";
				}else if (rs.getString(9).equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")) {
					codigoImputacion = "97";
				}else if (rs.getString(9).equalsIgnoreCase("Impuesto Inmobiliario")) {
					codigoImputacion = "1";
				}
				
				
				if( rs.getString(7) != cadenaNula){				
					clave = rs.getString(7);					
				}
				
				if( rs.getString(8) != cadenaNula){
					clave = rs.getString(8);					
				}				
				if( rs.getString(9) != cadenaNula){
					clave = rs.getString(9);					
				}
				
				consulta = "select * from recibo r where r.clave = ? and publicar = 1 " +
						"and (r.codigo_imputacion= ?)";
				datos.clear();
				datos.add(clave);
				datos.add(codigoImputacion);
				rsRecibos = conexion.select(consulta, datos);
				
				
				
				
				
				Receptor receptor = new Receptor();
				receptor.setCorreoElectronico(rs.getString(4));
				receptor.setCpe(rs.getString(6));
				receptor.setTipoImpuesto(rs.getString(5));
				if (rs.getString(5).equalsIgnoreCase("Impuesto automotor")){
					receptor.setPartida(rs.getString(7));
				}
				if (rs.getString(5).equalsIgnoreCase("Impuesto Inmobiliario") ||
						rs.getString(5).equalsIgnoreCase("Impuesto Derecho de Ocupante")){
					receptor.setPartida(rs.getString(8));
				}
				if (rs.getString(5).equalsIgnoreCase("Impuesto Tasa de Higiene Urbana") ||
						rs.getString(5).equalsIgnoreCase("Impuesto Derecho Ocupante Tasa de Higiene")){
					receptor.setPartida(rs.getString(9));
				}
				
				log.info("receptor agregado juridico: " + receptor.getCorreoElectronico());
				receptores.add(receptor);
				
				
			}
			//en este punto tengo los receptores para enviarles los correos
			
		
		
		log.info("Cantidad de receptores :" + receptores.size());   //Cantidad de personas a las que les enviaremos el impuesto x mail
		Iterator<Receptor> iterador = receptores.iterator();
		while(iterador.hasNext()){
						
			Receptor receptor = iterador.next();
			log.info("apellido y nombre: "+receptor.getApellido()+", "+ receptor.getNombre()+ " - correo: " + receptor.getCorreoElectronico()+ " - tipo impuestos: " +receptor.getTipoImpuesto() +
					" - partida: " + receptor.getPartida() +" - es solicitante � titular : " + receptor.getEsSolicitante());
			
			//generar un extractos de impuestos enviados
		}
		
		
		//Recupero cuerpo de mail. Esta idea es media pelotuda, pero bueno..
		consulta = "select * from correo";
		datos.clear();
		ResultSet rs1 = null;
		String cuerpo = "";
		rs1 = conexion.select(consulta, datos);
		while(rs1.next()){
			 cuerpo = rs1.getString(2);
			 log.info(cuerpo);
		}
		
		log.info("** Preparo check listo correos");
		
		//obtengo mes y a�o actual
		//Calendar c = Calendar.getInstance();
		//int year = c.get(Calendar.YEAR);
		//int month = c.get(Calendar.MONTH);
		
		java.util.Date fechaActual = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		int month1 = cal.get(Calendar.MONTH) + 1;
		int year1 = cal.get(Calendar.YEAR);


		
		
		
		
		log.info("** Si estan aca, es porque se les envio **");
		//String consultaProcesados = "select * from procesados where (correoElectronico = ? and cpe = ?)and (MONTH(fechaEnvio) = ? and YEAR(fechaEnvio) = ?)  and estadoEnvio = 1 ";
		String consultaProcesados = "select * from procesados where (correoElectronico = ? and cpe = ? and tipoImpuesto = ?) and estadoEnvio = 1 ";
		ResultSet rsProcesados = null;
		Vector<Object>datosProcesados = new Vector<Object>();
		datosProcesados.clear();
		
		ArrayList<Receptor> receptoresConfirmados = new ArrayList<Receptor>();
		
		for (int i = 0; i < receptores.size(); i++){			
			
			datosProcesados.add(receptores.get(i).getCorreoElectronico());
			datosProcesados.add(receptores.get(i).getCpe());
			datosProcesados.add(receptores.get(i).getTipoImpuesto());
			//datosProcesados.add(month1);
			//datosProcesados.add(year1);			
			rsProcesados = conexion.select(consultaProcesados, datosProcesados);
			if(!rsProcesados.next()){
				log.info("** A este correo no se le envio en este mes, entonces le enviamos **");
				consulta = "insert into procesados (correoElectronico,cpe,estadoEnvio,fechaEnvio,tipoImpuesto) values (?,?,?,?,?)";
				datos.add(receptores.get(i).getCorreoElectronico());
				datos.add(receptores.get(i).getCpe());
				datos.add(98);
				//datos.add(new Date(new java.util.Date().getTime()));
				datos.add(new Timestamp(new java.util.Date().getTime()));
				datos.add(receptores.get(i).getTipoImpuesto());
				conexion.insert(consulta, datos);
				receptoresConfirmados.add(receptores.get(i));
				datos.clear();
			}else log.info("** A este correo no se le envio en este mes, entonces le enviamos **");
				
			datosProcesados.clear();
		}		
		
		
		
		
		
//		
//		consulta = "insert into procesados (correoElectronico,cpe,estadoEnvio) values (?,?,?)";
//		datos.clear();
//			for (int i = 0; i < receptores.size(); i++){			
//				
//				datos.add(receptores.get(i).getCorreoElectronico());
//				datos.add(receptores.get(i).getCpe());
//				datos.add(0);
//				conexion.insert(consulta, datos);
//				datos.clear();
//			}		

		//EnviadorMail envioMail = new EnviadorMail(receptoresConfirmados); //envio todo aca adentro
		
		consulta = "update procesados set estadoEnvio = ? where (correoElectronico = ? and cpe = ? and estadoEnvio = 98)";
		datos.clear();
		
		for (int i = 0; i < receptoresConfirmados.size(); i++){	
			
			datos.add(1);
			datos.add(receptoresConfirmados.get(i).getCorreoElectronico());
			datos.add(receptoresConfirmados.get(i).getCpe());		
			EnviadorMail enviador = new EnviadorMail(receptoresConfirmados.get(i));
			conexion.update(consulta, datos);
			
			datos.clear();	
		}
		
		conexion.getConexion().commit();
		
	//	log.info("Procesado todo correctamente, se procede a eliminar la tabla PROCESADOS");
	//	consulta = "delete from procesados";
	//	datos.clear();
	//	int borrados = conexion.delete(consulta, datos);
	//	conexion.getConexion().commit();
	//	System.out.println("Cantidad de borrados:" + borrados);
				
		
		} catch (SQLException e) {
			log.error("Error al generar receptores: " + e);
			return 0;
		}
	
	
		
		return 1;
	}*/
	
	
	public int generarProceso() {
		
		log.info("** Generamos proceso para personas fisicas **");
		String consulta = "select * from suscripciones where estadoSuscripcion = 1";										                     
		Vector<Object> datos = new Vector<Object>();
		ArrayList<Receptor>receptores = new ArrayList<Receptor>();
		Conexion conexion = null;
		String cadenaNula = null;
		String clave = null;	
		String codigoImputacion = "";
		int contadorCorrecto = 0;
		int contadorSinRecibo = 0;
		String rutaArchivo = "";
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			
			ResultSet rs = null;
			ResultSet rsClave = null;
			ResultSet rsRecibos = null;
			rs = conexion.select(consulta, datos);			
			while(rs.next()){
				
				//seteo codigoImputacion y 
				//recupero la clave segun impuesto
				
				
				if (rs.getString(3).equalsIgnoreCase("Impuesto automotor")) {
					
					codigoImputacion = "03";					
					consulta = "select clave from automotor a where a.codigoRedLink = ?";
					datos.clear();
					datos.add(rs.getString(6));
					rsClave = conexion.select(consulta, datos);
					if(rsClave.next()){
						clave = rsClave.getString(1);
					}					
					
				}else if (rs.getString(3).equalsIgnoreCase("Impuesto Derecho de Ocupante")) {
					
					codigoImputacion = "17";
					consulta = "select clave from inmobiliario a where a.nroPagoElectronico = ?";
					datos.clear();
					datos.add(rs.getString(6));
					rsClave = conexion.select(consulta, datos);
					if(rsClave.next()){
						clave = rsClave.getString(1);
					}					
					
					
				}else if (rs.getString(3).equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")) {
					
					codigoImputacion = "97";
					consulta = "select clave from inmobiliario a where a.nroPagoElecThu = ?";
					datos.clear();
					datos.add(rs.getString(6));
					rsClave = conexion.select(consulta, datos);
					if(rsClave.next()){
						clave = rsClave.getString(1);
					}					
					
					
					
				}else if (rs.getString(3).equalsIgnoreCase("Impuesto Inmobiliario")) {
					
					codigoImputacion = "1";
					consulta = "select clave from inmobiliario where nroPagoElectronico = ?";
					datos.clear();
					datos.add(rs.getString(6));
					rsClave = conexion.select(consulta, datos);
					if(rsClave.next()){
						clave = rsClave.getString(1);
					}					
				}
				
				
				
				consulta = "select * from recibo r where r.clave = ? and publicar = 1 and (r.codigo_imputacion= ?)";
				datos.clear();
				datos.add(clave);
				datos.add(codigoImputacion);
				rsRecibos = conexion.select(consulta, datos);
				
				
				
				
				
				
				if(rsRecibos.next()){ //receptores con publicaciones disponibles. Solo busco saber si esta disponible publicacion
				//while(rsRecibos.next()){ //receptores con publicaciones disponibles		
					
					//log.info("receptor solicitante agregado fisico: " + receptor.getCorreoElectronico());
					contadorCorrecto = contadorCorrecto + 1;						
					rutaArchivo = rsRecibos.getString(7);
							
					
					
					if(rs.getString(7).equalsIgnoreCase("fisico")){
						
						ResultSet rsFisico = null;
						consulta = "select * from titular t where t.idTitular = ? and estado = 1";
						datos.clear();
						datos.add(rs.getInt(2));
						rsFisico = conexion.select(consulta, datos);
						if(rsFisico.next()){
							
							Receptor receptor = new Receptor();
							receptor.setRutaArchivo(rutaArchivo);
							
							if(rsFisico.getInt(12) !=0){  //posee solicitante
								
								ResultSet rsSolicitante = null;
								datos.clear();
								consulta = "select * from solicitante s where s.idSolicitante = ? and estado = 1";
								datos.add(rsFisico.getInt(12));
								rsSolicitante = conexion.select(consulta, datos);						
								if(rsSolicitante.next()){									
									
									receptor.setIdPersona(rsSolicitante.getInt(1));
									receptor.setNombre(rsSolicitante.getString(2));
									receptor.setApellido(rsSolicitante.getString(3));
									receptor.setEsSolicitante("Es solicitante");
									receptor.setCorreoElectronico(rsSolicitante.getString(10));
									receptor.setTipoImpuesto(rs.getString(3));
									receptor.setCpe(rs.getString(6));
									receptor.setPartida(rs.getString(8));							
									log.info("receptor *SOLICITANTE* agregado fisico: " + receptor.getCorreoElectronico());
									}			
								else{
									log.info("Solicitante con estado no apto para ser cargado. Posible estado 0, 99, etc" );
								}
							}else {
								//es titular solo						
								
								receptor.setIdPersona(rsFisico.getInt(1));
								receptor.setNombre(rsFisico.getString(2));
								receptor.setApellido(rsFisico.getString(3));								
								receptor.setCorreoElectronico(rsFisico.getString(10));
								receptor.setEsSolicitante("Es titular");
								receptor.setTipoImpuesto(rs.getString(3));
								receptor.setCpe(rs.getString(6));
								receptor.setPartida(rs.getString(8));						
								log.info("receptor *TITULAR* agregado fisico: " + receptor.getCorreoElectronico());
								
							}
							
							log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + receptor.getRutaArchivo());
							receptores.add(receptor);
						}
						else{
							log.info("Titular con estado no apto para ser cargado. Posible estado 0, 99, etc" );
						}
						
						
					}else{
						//es juridico
						ResultSet rsJuridico = null;
						consulta = "select * from juridica j where j.id = ? and estado = 1";
						datos.clear();
						datos.add(rs.getInt(2));
						rsJuridico = conexion.select(consulta, datos);
						if(rsJuridico.next()){
							
							Receptor receptor = new Receptor();
							receptor.setRutaArchivo(rutaArchivo);
							receptor.setNombre(rsJuridico.getString(2));
							receptor.setEsSolicitante("juridico");
							receptor.setCorreoElectronico(rsJuridico.getString(9));
							receptor.setCpe(rs.getString(6));
							receptor.setTipoImpuesto(rs.getString(3));
							receptor.setPartida(rs.getString(8));
							log.info("receptor agregado *JURIDICO*: " + receptor.getCorreoElectronico());
							log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + receptor.getRutaArchivo());
							receptores.add(receptor);
							//contadorCorrecto = contadorCorrecto + 1;
							
							
						}
						else{
							log.info("Juridico con estado no apto para ser cargado. Posible estado 0, 99, etc" );
						}
						
						
					}
					
					
				}//recibos
				else{
					
					
					
					contadorSinRecibo = contadorSinRecibo + 1;
					log.info("********* NO POSEE  RECIBO PARA ENVIAR: " + contadorSinRecibo);
				}
				
				
			}//suscripciones
			
			log.info("Cantidad de receptores :" + receptores.size());   //Cantidad de personas a las que les enviaremos el impuesto x mail. Array arranca de 0 
			log.info("Cantidad de receptores CONTADOR:" + contadorCorrecto);   //Cantidad de personas a las que les enviaremos el impuesto x mail
			log.info("Cantidad de sin recibos :" + contadorSinRecibo);   //Cantidad de personas a las que les enviaremos el impuesto x mail
			Iterator<Receptor> iterador = receptores.iterator();
			while(iterador.hasNext()){
							
				Receptor receptor = iterador.next();
				if(!receptor.getEsSolicitante().equalsIgnoreCase("juridico")){				
				log.info("apellido y nombre: "+receptor.getApellido()+", "+ receptor.getNombre()+ " - correo: " + receptor.getCorreoElectronico()+ " - tipo impuestos: " +receptor.getTipoImpuesto() +
						" - partida: " + receptor.getPartida() +" - es solicitante  titular : " + receptor.getEsSolicitante());
				}else{
					log.info("Razon social: "+receptor.getNombre()+ " - correo: " + receptor.getCorreoElectronico()+ " - tipo impuestos: " +receptor.getTipoImpuesto() +
							" - partida: " + receptor.getPartida() +" - es : " + receptor.getEsSolicitante());
				}
				
				//generar un extractos de impuestos enviados
			}
			
			log.info("** Preparo check listo correos");
			
			

			log.info("** Si estan aca, es porque se les envio **");
			//String consultaProcesados = "select * from procesados where (correoElectronico = ? and cpe = ? and tipoImpuesto = ?) and estadoEnvio = 1 ";
			//ResultSet rsProcesados = null;
			Vector<Object>datosProcesados = new Vector<Object>();
			datosProcesados.clear();
			ArrayList<Receptor> receptoresConfirmados = new ArrayList<Receptor>();
			
			/* modificacion envios
			
			for (int i = 0; i < receptores.size(); i++){			
				
				datosProcesados.add(receptores.get(i).getCorreoElectronico());
				datosProcesados.add(receptores.get(i).getCpe());
				datosProcesados.add(receptores.get(i).getTipoImpuesto());
				//datosProcesados.add(month1);
				//datosProcesados.add(year1);			
				rsProcesados = conexion.select(consultaProcesados, datosProcesados);
				if(!rsProcesados.next()){
					log.info("** A este correo no se le envio en este mes, entonces le enviamos **");
					datos.clear();
					consulta = "insert into procesados (correoElectronico,cpe,estadoEnvio,fechaEnvio,tipoImpuesto) values (?,?,?,?,?)";
					datos.add(receptores.get(i).getCorreoElectronico());
					datos.add(receptores.get(i).getCpe());
					datos.add(98); //condicional, 98 procesado pero no enviado
					//datos.add(new Date(new java.util.Date().getTime()));
					datos.add(new Timestamp(new java.util.Date().getTime()));
					datos.add(receptores.get(i).getTipoImpuesto());
					conexion.insert(consulta, datos);
					receptoresConfirmados.add(receptores.get(i));					
				}else log.info("** A este correo no se le envio en este mes, entonces le enviamos **");
					
				datosProcesados.clear();
			}	
			
			
			consulta = "update procesados set estadoEnvio = ? where (correoElectronico = ? and cpe = ? and estadoEnvio = 98)";
			datos.clear();
			
			
			
			
			
			*/
			
			
			
			receptoresConfirmados = receptores;
			
			
			
			
			String consulta3 = "insert into receptores_temp_2 (email,cuerpo, codigoImputacion,estadoEnvio, fechaEnvio, clave, cpe) values (?,?,?,?,?,?,?)";			
			//String consulta2 = "insert into receptores_temp (email,cuerpo, codigoImputacion,estadoEnvio, fechaEnvio, clave) values (?,?,?,?,?,?)";
			Vector<Object>dato2 = new Vector<Object>();
			
			for (int i = 0; i < receptoresConfirmados.size(); i++){	
				
				datos.add(1);
				datos.add(receptoresConfirmados.get(i).getCorreoElectronico());
				datos.add(receptoresConfirmados.get(i).getCpe());		
				//EnviadorMail enviador = new EnviadorMail(receptoresConfirmados.get(i));
				//EnviadorMail enviador = new EnviadorMail();
				
				dato2.clear();
				
				dato2.add(receptoresConfirmados.get(i).getCorreoElectronico());
				dato2.add("Estimado/a Contribuyente:<br>"
				    	+"Ud. adhirió al servicio de e-boleta para el tributo: <strong>"+ receptoresConfirmados.get(i).getTipoImpuesto()+"</strong>->: <a href='"+composicion(receptoresConfirmados.get(i))+"'><strong>e-boleta</strong></a><br>"  
				    	+"Para darse de baja complete los datos del formulario en el siguiente enlace: <a href='http://www.comodoro.gov.ar/eboleta2/#!viewDesuscripcion'><strong>Baja</strong></a><br>" 
				    	+"Recuerde que de acuerdo a la <a href='http://www.comodoro.gov.ar/?p=21365'<strong>Normativa</strong></a>, la baja no implica modificación alguna de las obligaciones<br>"
				    	+"tributarias ni de los datos registrados en nuestros sistemas impositivos.<br><br><br>"
				    	+"Que tenga un buen día.<br>"
				    	+"Dirección General de Rentas<br>"
				    	+"Municipalidad de Comodoro Rivadavia");
				
				
				if (receptoresConfirmados.get(i).getTipoImpuesto().equalsIgnoreCase("Impuesto automotor")) {					
					codigoImputacion = "03";						
					}					
				if (receptoresConfirmados.get(i).getTipoImpuesto().equalsIgnoreCase("Impuesto Derecho de Ocupante")) {					
					codigoImputacion = "17";				
				    }
				if (receptoresConfirmados.get(i).getTipoImpuesto().equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")) {				
					codigoImputacion = "97";
				    }
				if (receptoresConfirmados.get(i).getTipoImpuesto().equalsIgnoreCase("Impuesto Inmobiliario")) {					
					codigoImputacion = "1";						
				    }
				
				dato2.add(codigoImputacion);
				dato2.add(0); //0 mail a ser enviado
				dato2.add(new Timestamp(new java.util.Date().getTime()));		
				dato2.add(receptoresConfirmados.get(i).getPartida());
				dato2.add(receptoresConfirmados.get(i).getCpe());
				//conexion.insert(consulta2, dato2);
				if (conexion.insert(consulta3, dato2) == 0){					
					conexion.getConexion().rollback();
				}		
				
//				if (enviador.enviarImpuesto(receptoresConfirmados.get(i)) !=0){
//					conexion.update(consulta, datos);
//				}			
				
				datos.clear();	
			}
			
						
			//conexion.getConexion().commit();
			log.info("Finalizado. Cantidad de receptores: " + receptoresConfirmados.size());
			return 1;
				
			} catch (SQLException e) {
				log.error("Error al generar receptores: " + e);
				return 0;
			}
		
		//return 0;
	}
	
	
	private String composicion(Receptor receptor) {
		
		String link = "";
		String rutaArchivo = receptor.getRutaArchivo().substring(3);				
		
		
		
		if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto automotor")){  	    	 
			link = "https://www.comodoro.gov.ar/impuestos/"+rutaArchivo+"?clave="+receptor.getPartida()+"&imputacion=%203";			
			log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + link);
			return link;
  	     }
  	     if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Inmobiliario")){  	    	
  	    	link = "https://www.comodoro.gov.ar/impuestos/"+rutaArchivo+"?clave="+receptor.getPartida()+"&imputacion=%201";
  	    	log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + link);
  	    	return link;
  	     }
  	     if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")){	    	 
  	    	link = "https://www.comodoro.gov.ar/impuestos/"+rutaArchivo+"?clave="+receptor.getPartida()+"&imputacion=%2097";
  	    	log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + link);
  	    	return link;  	    	  	    	
	         }
  	     if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Derecho Ocupante Tasa de Higiene")){	    	 
  	    	link = "https://www.comodoro.gov.ar/impuestos/"+rutaArchivo+"?clave="+receptor.getPartida()+"&imputacion=%2097";
  	    	log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + link);
  	    	return link;
  	     }    
  	     if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Derecho de Ocupante")){ 	    	 
  	    	 link = "https://www.comodoro.gov.ar/impuestos/"+rutaArchivo+"?clave="+receptor.getPartida()+"&imputacion=%2017";
  	    	log.info("receptor:" + receptor.getCorreoElectronico() + " ruta:" + link);
  	    	return link;
         }		
		return link;
	}

	public String obtenerCuerpoMail() {
		
		String consulta = "select * from correo";
		Vector<Object> datos = new Vector<Object>();
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			if(rs.next()){
				return rs.getString(2);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return "";
	}

	public int guardarCambios(String nuevoCuerpoMail) {
		
		String consulta = "update correo set cuerpo = ?";
		Vector<Object> datos = new Vector<Object>();
		datos.add(nuevoCuerpoMail);
		int rta = 0;
		Conexion conexion = null;
		try {
			conexion = new Conexion();
			rta = conexion.queryActualizacion(consulta, datos);
			System.out.println("soy rta:" + rta);
			return rta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}

	public ArrayList<PreSolicitudConfirmar> presuscripcionesFisicas(java.util.Date fechaDesde, java.util.Date fechaHasta) {
		
		
		//String consulta = "select * from v_envios where estadoSubcripcion = 0 and date(fechaSuscripcion) = ?";
		//String consulta = "select * from v_envios where estadoSubcripcion = 0 and date(fechaSuscripcion) = ?";
		//BETWEEN '2005-07-16' AND '2005-07-16'
		String consulta = "select * from v_envios ve where ve.estadoSubcripcion = 0 and fechaSuscripcion BETWEEN ? and ?"+
				          " order by fechaSuscripcion desc";		
		Vector<Object>datos = new Vector<Object>();
		datos.add(new Date(fechaDesde.getTime()));
		datos.add(new Date(fechaHasta.getTime()));
		ArrayList<PreSolicitudConfirmar> preSolicitudesConfirmar = new ArrayList<PreSolicitudConfirmar>(); 
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while (rs.next()){
				
				PreSolicitudConfirmar presolicitudConfirmar = new PreSolicitudConfirmar();			
				presolicitudConfirmar.setTipoSolicitante("fisico");
				if(rs.getInt(5) == 0){
					//titular
					//System.out.println("titular:"+rs.getString(2));
					//System.out.println("titular:"+rs.getString(1));
					presolicitudConfirmar.setId(rs.getInt(1));
					presolicitudConfirmar.setNombre(rs.getString(2)+", "+rs.getString(3));
					presolicitudConfirmar.setTipoImpuesto(rs.getString(9));
					presolicitudConfirmar.setCpe(rs.getString(10));
					presolicitudConfirmar.setCorreo(rs.getString(4));
					presolicitudConfirmar.setFechaSuscripcion(rs.getDate(15));					
					
				}else{					
					//solicitante
				    //System.out.println("solicitante:"+rs.getString(2));
				    //System.out.println("solicitante:"+rs.getString(1));
					presolicitudConfirmar.setId(rs.getInt(5));
					presolicitudConfirmar.setNombre(rs.getString(7)+", "+rs.getString(6));
					presolicitudConfirmar.setTipoImpuesto(rs.getString(9));
					presolicitudConfirmar.setCpe(rs.getString(10));
					presolicitudConfirmar.setCorreo(rs.getString(8));
					presolicitudConfirmar.setFechaSuscripcion(rs.getDate(15));				
				}				
			preSolicitudesConfirmar.add(presolicitudConfirmar);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return preSolicitudesConfirmar;
		
	}
	
	
	public ArrayList<PreSolicitudConfirmar> presuscripcionesJuridicas(java.util.Date fechaDesde, java.util.Date fechaHasta) {
		
		//String consulta = "select * from v_envios_juridica where estadoSubcripcion = 0 and date(fechaSuscripcion) = ?";
		String consulta = "select * from v_envios_juridica ve where ve.estadoSubcripcion = 0 and fechaSuscripcion BETWEEN ? and ?"+
		          " order by fechaSuscripcion desc";		
		Vector<Object>datos = new Vector<Object>();
		datos.add(new Date(fechaDesde.getTime()));
		datos.add(new Date(fechaHasta.getTime()));
		ArrayList<PreSolicitudConfirmar> preSolicitudesConfirmar = new ArrayList<PreSolicitudConfirmar>(); 
		ResultSet rs = null;
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while (rs.next()){
				
				PreSolicitudConfirmar presolicitudConfirmar = new PreSolicitudConfirmar();			
					
					presolicitudConfirmar.setId(rs.getInt(1));
					presolicitudConfirmar.setNombre(rs.getString(2));
					presolicitudConfirmar.setTipoImpuesto(rs.getString(5));
					presolicitudConfirmar.setCpe(rs.getString(6));
					presolicitudConfirmar.setCorreo(rs.getString(4));
					presolicitudConfirmar.setFechaSuscripcion(rs.getDate(11));		
					presolicitudConfirmar.setTipoSolicitante("juridico");
				preSolicitudesConfirmar.add(presolicitudConfirmar);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return preSolicitudesConfirmar;
	}

	public int cancelarPresuscripcion(PreSolicitudConfirmar preSolicitudConfirmar, Usuario usuario) {

		
		String consulta = "select * from suscripciones where codigoPagoElectronico = ? and estadoSuscripcion = 0";
		Vector<Object>datos = new Vector<Object>();
		datos.add(preSolicitudConfirmar.getCpe());
		ResultSet rs = null;
		Conexion conexion = null;
		int rta = 0;
		try {
			conexion = new Conexion();
			conexion.getConexion().setAutoCommit(false);
			rs = conexion.select(consulta, datos);
			if(rs.next()){  //cumple condicion
				
				//solo cambio estado en suscripcion ya que puede suceder
				//que para otro impuesto si se dio de alta
				//con lo cual el estado del titular/solicitante/juridica
				//pudo ser aprobado con esos impuestos
				consulta = "update suscripciones s set s.estadoSuscripcion = 99 where s.codigoPagoElectronico = ? and s.estadoSuscripcion = 0";
				datos.clear();
				datos.add(preSolicitudConfirmar.getCpe());
				if(conexion.update(consulta, datos) != 0){
					
					 //log
					//reset consulta,datos y luego insert en log
					datos.clear();
					consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
					datos.add(new Timestamp(new java.util.Date().getTime()));
					datos.add("servicioEnvio");
					datos.add("cancelarPresuscripcion(presolicitud:"+preSolicitudConfirmar.getCpe()+")");
					datos.add("usuario sistema");
					datos.add(usuario.getIdPersona());		
					WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
					String ipAddress = webBrowser.getAddress();
					datos.add(ipAddress);								
					rta = conexion.insert(consulta, datos);
					
				}
				
				if(rta != 0){
					
					conexion.getConexion().commit();
					return rta;
				}else{					
					conexion.getConexion().rollback();
				}
				
			}
		} catch (SQLException e) {
			log.error("Error sql: " + e);
			
		}
		return rta;
	}

	public ArrayList<Procesados> cargarProcesados(String tipoImpuesto) {
		
		//String consulta = "select * from receptores_temp rt where rt.codigoImputacion = ? and estadoEnvio = 1";
		String consulta = "select rt.idReceptores as idProcesado, rt.email as correoElectronico, s.codigoPagoElectronico as cpe,"+
						  "rt.fechaEnvio as fechaEnvio, rt.codigoImputacion as codigoImputacion, rt.clave as clave "+
						  "from suscripciones s inner join receptores_temp rt on (s.clave = rt.clave) "+
					      "where rt.codigoImputacion = ? and (s.estadoSuscripcion <> 0 and s.estadoSuscripcion <> 99 "+
					      "and s.estadoSuscripcion <> 2)";		
		Vector<Object>datos = new Vector<>();
		String codigoImputacion = "";		
		ArrayList<Procesados> arrayProcesados = new ArrayList<>();
		
		
		if (tipoImpuesto.equalsIgnoreCase("Impuesto automotor")) {					
			codigoImputacion = "3";						
		}
		if (tipoImpuesto.equalsIgnoreCase("Impuesto Derecho Ocupante")) {					
			codigoImputacion = "17";				
		    }
		if (tipoImpuesto.equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")) {				
			codigoImputacion = "97";
		    }
		if (tipoImpuesto.equalsIgnoreCase("Impuesto Inmobiliario")) {					
			codigoImputacion = "1";						
		    }
		datos.add(codigoImputacion);
		ResultSet rs = null;		
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()){
				
				Procesados procesados = new Procesados(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5));
				arrayProcesados.add(procesados);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrayProcesados;
		
		
		/*String consulta = "select * from procesados where estadoEnvio = 1 and tipoImpuesto = ?";
		Vector<Object>datos = new Vector<Object>();
		datos.add(tipoImpuesto);
		ResultSet rs = null;		
		ArrayList<Procesados> procesados = new ArrayList<Procesados>();
		try {
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()){
				
				procesados.add(new Procesados(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return procesados;
		*/
		
		//return null;
	}

	public int eliminarEmision(String tipoImpuestos) {
		
		String consulta = "update procesados set estadoEnvio = 99 where (tipoImpuesto = ? and estadoEnvio = 1)"; //tiene que ser verdad
		Vector<Object>datos = new Vector<Object>();
		datos.add(tipoImpuestos);
		try {
			Conexion conexion = new Conexion();
			conexion.queryActualizacion(consulta, datos);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int exportarAdheridos(java.util.Date fechaDesde, java.util.Date fechaHasta) {
		
		
		String consulta = "select vp.codigoPagoElectronico as cpe,"
				 +"date(vp.fechaInscripcion) as fechaInscripcion,"
				 +"vp.email as emailFisico,"
				 +"vp.emailSolicitante as emailFisicoSolicitante,"
				 +"vp.jEmail as emailJuridico,"
				 +"vp.estadoSolicitud as estadoSolicitud " 
		         +"from v_presolicitudes vp where fechaInscripcion BETWEEN  ? and ? "; // desde, hasta
		Vector<Object>datos = new Vector<Object>();
		datos.add(new Date(fechaDesde.getTime()));
		datos.add(new Date(fechaHasta.getTime()));
		ResultSet rs = null;
		String cadenaNula = null;
		List<String> list = new ArrayList<String>();
		int rta = 0;
		
		  //String csvFile = "C://Users/tbeloqui//Desktop//feeder//abc.csv"; //local!		  
		  String csvFile = "//home//comodoro//public_html//eb/files//eboleta.csv"; //hosting!	
		  try {
			FileWriter writer = new FileWriter(csvFile);
		
		
		  
		
			Conexion conexion = new Conexion();
			rs = conexion.queryConsulta(consulta, datos);
			while(rs.next()){
				
				list.clear();
				for (int i = 1; i < 7; i++) {
					//array_type array_element = array[i];
					
					//log.info("mira:" + rs.getString(i));
					
					
					if((rs.getString(i) != cadenaNula)){
						if(!rs.getString(i).equalsIgnoreCase("no aplica")){
							//System.out.print(rs.getString(i)+",");
							 //CSVUtils.writeLine(writer, Arrays.asList((rs.getString(i))));
							//CSVUtils.writeLine(writer, Arrays.asList((rs.getString(i))));
							list.add(rs.getString(i));
						}						
					}					
				}
				CSVUtils.writeLine(writer, list);
				//CSVUtils.writeLine(writer, list,';'); //1 por celda!
				//System.out.println();
				
				//System.out.println(rs.getString(1)+","+rs.getDate(2)+","
				//+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6));
				System.out.println();
			}
			
			String line = "";
			
			writer.flush();
	        writer.close();
	        BufferedReader br = new BufferedReader(new FileReader(csvFile));
	       // System.out.println("br:" + br);
	        while ((line = br.readLine()) != null) {
	        		        	
	        	//System.out.println(line);
	        	if(!line.equalsIgnoreCase("")){
	        		rta = 1; // verifico existencia de archivo con informacion
	        	}
	        	
	        	
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		
		
		return rta;
	}

	public int importarAdheridos(Usuario usuario) {

		
		 //String csvFile = "C://pendrive//hola//abc.csv"; //local!
		 String csvFile = "//home//comodoro//public_html//eb/files//FacturaElectronica.csv"; //hosting!		 
		 String line = "";
		 try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			Conexion conexion = new Conexion();
			Vector<Object>datos = new Vector<Object>();
			conexion.getConexion().setAutoCommit(false);
			ResultSet rs = null;
			ResultSet rsTitular = null;
			ResultSet rsJuridica = null;
			int idLog  = 0;
			while ((line = br.readLine()) != null) {
				
				//System.out.println(line.split(","));
				
				//(imputacion)tipoImpuesto, fechaSuscripcion, codigoPagoElectronico,estado,tipoContribuyente(tipoEnte),clave,nombre (razonSocial),
				//apellido,tipoDocumento,numeroDoc(cuit si es juridico),tipoTelefono,numeroTelefono,correo
				
				//3;06/01/1999;00000017358;ACTIVO;Persona física;CLF250;KARINA ANDREA;VALENCIA;DNI;25722349;Tel;0-154030461;juanmanuel3027@yahoo.com.ar
				String[] country = line.split(";");
			    //System.out.println("Ve:" + country[8]); //DNI, CUIT, ETC
				String estadoDa = country[3];
				if(estadoDa.equalsIgnoreCase("ACTIVO")){
				
					String cpe = country[2];
					datos.clear();
					//verifico que no este dado de baja anteriormente
				    //y que no este pendiente
					String consulta = "select * from suscripciones s where s.codigoPagoElectronico = ? and estadoSuscripcion <> 2 and estadoSuscripcion <> 0"; 
					datos.add(cpe);
				
				
				if(!country[8].equalsIgnoreCase("cuit")){ //persona fisica 					 
					System.out.println("Apellido y Nombre: " +country[7]+"," +country[6]);				
					rs = conexion.select(consulta, datos); //<- verifico si cpe ya fue solicitado
					if(!rs.next()){
						
						
						//verifico que dni no este dado de alta
						consulta = "select * from titular t where t.numeroDeDocumento = ? and estado <> 0";
						datos.clear();
						datos.add(country[9]); //numeroDeDoc		
						rsTitular = conexion.select(consulta, datos);
						if(rsTitular.next()){
							
							//recupero idTitular
							int idTitular = rsTitular.getInt(1);
							log.info("idTitular existente: " + idTitular);
							if (insertarSuscripcion(idTitular, country, datos, "fisico", conexion) != 0){
								
								 //log
								//reset consulta,datos y luego insert en log
								datos.clear();
								consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
								datos.add(new Timestamp(new java.util.Date().getTime()));
								datos.add("servicioEnvio");
								datos.add("suscribir(fisico:"+idTitular+", imputacion:"+country[0]+", clave:"+country[5]+")");
								datos.add("fisico");
								datos.add(usuario.getIdPersona());		
								WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
								String ipAddress = webBrowser.getAddress();
								datos.add(ipAddress);								
								idLog = conexion.insert(consulta, datos);
							}
						
						
						}else{ //titular inexistente
							log.info("idTitular inexistente por lo cual lo insertamos nuevo");
						//<- cpe no fue solicitado anteriormente, entonces inserto el titular para obtener id
						datos.clear(); // limpio vector preparedstatemen
						
						consulta = "insert into titular (nombre, " //6
													 + "apellido," //7
													 + "tipoDocumento," //8
													 + "numeroDeDocumento," //9
													 + "tipoTelefono," //10
								                     + "numeroTelefono, "//12
								                     + "tipoTelefonoAlternativo, " // "no posee"
								                     + "numeroTelefonoAlternativo, " // "no posee"
								                     + "correoElectronico, " //13
								                     + "estado)" //1								                     
								                     + " values (?,?,?,?,?,?,?,?,?,?)";
						
						
						datos.add(country[6]); //nombre
						datos.add(country[7]); //apellido
						datos.add(country[8]); //tipoDocumento
						datos.add(country[9]); //numeroDeDoc				
						if (country[12].startsWith("15")){
							datos.add("celular"); //tipoTelefono							
							datos.add(country[12]);
						}else{
							datos.add("fijo"); //tipoTelefono
							datos.add(country[12]);
						}						
						datos.add("no posee"); //tipoTelefonoAlternativo
						datos.add("no posee"); //numeroTelefonoAlternativo
						datos.add(country[13]);//correoElectronico						
						datos.add(1);
						datos.add(null);						
						int idSuscriptor = conexion.insert(consulta, datos);					
						
						if (idSuscriptor !=0 ){
							 if (insertarSuscripcion(idSuscriptor,country,datos, "fisico", conexion) !=0){
								 
								 //log
									//reset consulta,datos y luego insert en log
									datos.clear();
									consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
									datos.add(new Timestamp(new java.util.Date().getTime()));
									datos.add("servicioEnvio");
									datos.add("suscribir(fisico:"+idSuscriptor+", imputacion:"+country[0]+", clave:"+country[5]+")");
									datos.add("fisico");
									datos.add(usuario.getIdPersona());		
									WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
									String ipAddress = webBrowser.getAddress();
									datos.add(ipAddress);								
									idLog = conexion.insert(consulta, datos);
																	 
								 
							 }
						}		
						
						
						//0 //1;             
						//1 //06/11/2017;
						//2 //01000748046;
						//3 //ACTIVO;
						//4 //Persona f�sica;
						//5 //74804;
						//6 //HAYDEE;
						//7 //GONZALEZ BENIGNA;
						//8 //DNI;
						//9 //11518563;
						//10 //Tel;
						//11 //297;
						//12 //154210826;
						//13 //haydeegonzalez101@hotmail.com
						
						}
						
					}else{
						log.info("Este cpe ya existe en tabla suscripones: " + cpe);
					}
							
				}else{
					System.out.println("Razon Social: " +country[7]);
					rs = conexion.select(consulta, datos);
					if(!rs.next()){
						System.out.println("agregar cuit!");							
						//verifico que cuit no este dado de alta
						consulta = "select * from juridica j where j.cuit = ? and estado <> 0";
						datos.clear();
						datos.add(country[9]); //cuit		
						rsJuridica = conexion.select(consulta, datos);
						if(rsJuridica.next()){
							
							//recupero idJuridica
							int idJuridica = rsJuridica.getInt(1);
							log.info("idJuridica existente: " + idJuridica);
							if (insertarSuscripcion(idJuridica, country, datos, "juridica", conexion) != 0){
								
								 //log
								//reset consulta,datos y luego insert en log
								datos.clear();
								consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
								datos.add(new Timestamp(new java.util.Date().getTime()));
								datos.add("servicioEnvio");
								datos.add("suscribir(juridica:"+idJuridica+", imputacion:"+country[0]+", clave:"+country[5]+")");
								datos.add("juridica");
								datos.add(usuario.getIdPersona());		
								WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
								String ipAddress = webBrowser.getAddress();
								datos.add(ipAddress);								
								idLog = conexion.insert(consulta, datos);
							}
						}
						
						else{ //cuit inexistente
							
						log.info("idJuridica inexistente insertamos en tabla juridica ");	
						datos.clear(); // limpio vector preparedstatemen
						
						consulta = "insert into juridica (razonSocial,"
								+"cuit,"
								+"tipo,"
								+"numeroTelefono,"
								+"tipoTelefono,"
								+"numeroTelefonoAlternativo,"
								+"tipoTelefonoAlternativo,"
								+"correoElectronico,"
								+"estado) values (?,?,?,?,?,?,?,?,?)";
						
						datos.add(country[7]);
						datos.add(country[9]);
						datos.add(""); //?
						
						if (country[12].startsWith("15")){
							datos.add(country[12]);
							datos.add("celular"); //tipoTelefono							
						}else{
							datos.add(country[12]);
							datos.add("fijo"); //tipoTelefono						
						}	
						datos.add("no posee"); //tipoTelefonoAlternativo
						datos.add("no posee"); //numeroTelefonoAlternativo
						datos.add(country[13]);
						datos.add(1);
						
						int idSuscriptor = conexion.insert(consulta, datos);
						
						if (idSuscriptor !=0 ){
							 if (insertarSuscripcion(idSuscriptor,country,datos, "juridico", conexion) !=0){
								 
								 //log
									//reset consulta,datos y luego insert en log
									datos.clear();
									consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
									datos.add(new Timestamp(new java.util.Date().getTime()));
									datos.add("servicioEnvio");
									datos.add("suscribir(juridico:"+idSuscriptor+", imputacion:"+country[0]+", clave:"+country[5]+")");
									datos.add("juridico");
									datos.add(usuario.getIdPersona());		
									WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
									String ipAddress = webBrowser.getAddress();
									datos.add(ipAddress);								
									idLog = conexion.insert(consulta, datos);
																	 
								 
							 }//insertarSuscripcion
						}//idSuscriptor									
							
						
						
						//0 //3;       
						//1 //17/01/2011;
						//2 //00000035292;
						//3 //ACTIVO;
						//4 //S.R.L.;
						//5 //EZT959;
						//6 //;
						//7 //LUCANIA S.R.L.;
						//8 //CUIT;
						//9 //30691979071;
						//10 //Tel;
						//11 //297;
						//12 //4499300;
						//13 //maxitorraca@lucania-palazzo.com			
					
						}				
				}else{ //tiene cpe en suscrpciones
					log.info("Este cpe ya existe en tabla suscripones: " + cpe);
				}
		      } // else de razonSocial
		
			}else{
				log.info("BAJA: " + datos.add(country[9]));
			}
				
		}//while breakline
		
		if(idLog != 0){
			datos.clear();
			String consulta = "insert into log (fecha, servicio , metodo, tipo, idPersona,ip) values (?,?,?,?,?,?)";
			datos.add(new Timestamp(new java.util.Date().getTime()));
			datos.add("servicioEnvio");
			datos.add("importarAdheridos(): usuario ->" + usuario.getIdPersona());
			datos.add("sistema");
			datos.add(usuario.getIdPersona());		
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();				
			String ipAddress = webBrowser.getAddress();
			datos.add(ipAddress);								
			idLog = conexion.insert(consulta, datos);		
			
		   conexion.getConexion().commit();
		   System.out.println("finish_:" + new java.util.Date());
		   return 1;
		}else{
		   conexion.getConexion().rollback();
		   return 0;
		}			
			
		} catch (FileNotFoundException e) {
		    log.error("No se encuentra el archivo: " + e);
			//e.printStackTrace();
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		
		 
		
		return 0;
	}

	private int insertarSuscripcion(int idSuscriptor, String[] country, Vector<Object> datos, String tipo, Conexion conexion) {
		
		if (idSuscriptor !=0 ){
			
			datos.clear();
			String consulta = "insert into suscripciones (idSuscriptor, "
					+ "tipoImpuesto, "
					+ "fechaSuscripcion, "
					+ "estadoSuscripcion, "
					+ "codigoPagoElectronico, "
					+ "tipo, "
					+ "clave) values (?,?,?,?,?,?,?)  ";
			datos.add(idSuscriptor);
	
								
			if (country[0].equalsIgnoreCase("3")) {
				datos.add("Impuesto automotor");
			}
			if (country[0].equalsIgnoreCase("17")) {
				datos.add("Impuesto Derecho de Ocupante");
			}
			if (country[0].equalsIgnoreCase("97")) {
				datos.add("Impuesto Tasa de Higiene Urbana");
			}
			if (country[0].equalsIgnoreCase("1")) {
				datos.add("Impuesto Inmobiliario");
			}
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			java.util.Date date;
			try {
				date = (java.util.Date) dateFormat.parse(country[1]);
							
			
			datos.add(new Date(date.getTime()));
			datos.add(1);
			datos.add(country[2]);
			datos.add(tipo);
			datos.add(country[5]);		
			
			int idSuscripcion = conexion.insert(consulta, datos); 
				if ( idSuscripcion != 0){
					return 1;
				}
			} catch (SQLException e) {
				log.error("Error al generar conexion: " + e);				
			} catch (ParseException e1) {
				log.error("Error al generar Parse en fecha: " + e1);				
			}	
			
		  }
		return 0;

		}
	
	
}
