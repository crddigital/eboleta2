package com.workants.eboleta2.tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import com.workants.eboleta2.model.Receptor;

public class EnviadorMailBaja {




	//Gmail alerta
	final String username = "";
	final String password = "";

	//e-boleta
	final String miCorreo = "";
	final String miContraseña = "";		  
	final String servidorSMTP = "";
	final String puertoEnvio = "";



	ArrayList<Receptor> receptores;
	String mailReceptor = null;
	String asunto = null;
	String cuerpo = null;

	Receptor receptor;
	public static Logger log = Logger.getLogger(EnviadorMailBaja.class);

	public EnviadorMailBaja(Receptor receptor, String asunto, String cuerpo, String keyBaja){

		this.receptor = receptor;
		this.asunto = asunto;
		this.cuerpo = cuerpo;     
	}

	public EnviadorMailBaja(Receptor receptor, String asunto, String cuerpo) {

		this.receptor = receptor;
		this.asunto = asunto;
		this.cuerpo = cuerpo;     
	}
	

	public int configAndDispatch(String keyBaja) throws MessagingException, UnsupportedEncodingException{

		Properties props = new Properties();
		props.put("mail.smtp.user", miCorreo);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.socketFactory.port", puertoEnvio);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", false);
		props.put("mail.smtp.starttls.enable", false);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");			
		//SecurityManager security = System.getSecurityManager();




		int rta = 0;

		try {
			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(props, auth);
			MimeMessage  msg = new MimeMessage(session);  

			msg.setContent("Confirmacion de baja para los siguiente detalle: <br>" + cuerpo + " <br>"
					//+ "<br>Instrucciones del siguente link: http://www.comodoro.gov.ar/eboleta/#!viewBaja/"+receptor.getPartida()+"<br> con el siguiente codigo: <strong>" + keyBaja+"</strong>","text/html; charset=utf-8" );
					+ "<br>Instrucciones del siguente link: <a href='http://www.comodoro.gov.ar/eboleta2/#!viewBaja/"+receptor.getPartida()+"'><strong>Aqu�</strong></a><br> con el siguiente c�digo: <strong>" + keyBaja+"</strong>","text/html; charset=utf-8" );


			//	+ "<br>Habilitar recepcion aqu� -> <a href='http://www.comodoro.gov.ar/eboleta/#!viewSuscripcion/"+receptor.getPartida()+"'><strong>Impuesto</strong></a><br> Gracias. No responda este "



			msg.setSubject(asunto);			 
			String de = "Rentas MCR";
			msg.setFrom(new InternetAddress(miCorreo,de));	
			msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receptor.getCorreoElectronico().toString()));
			msg.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress("mgutierrez@comodoro.gov.ar"));
			log.error("Enviar a: " + receptor.getCorreoElectronico());
			Transport.send(msg);				
			rta = 1;
		} catch (MessagingException e) {		
			envioAlerta("Baja", receptor);
			log.error("Error de autentificacion: " + e);			
			//	e.printStackTrace();
			rta = 0;
		} catch (UnsupportedEncodingException e) {
			envioAlerta("Baja",  receptor);
			log.error("Error de Codificacion no soportada: " + e);
			//	e.printStackTrace();
			rta = 0;
		}		
		return rta;
	}



	public int configAndDispatch() throws MessagingException, UnsupportedEncodingException{

		Properties props = new Properties();
		props.put("mail.smtp.user", miCorreo);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.socketFactory.port", puertoEnvio);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", false);
		props.put("mail.smtp.starttls.enable", false);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");			
		//SecurityManager security = System.getSecurityManager();
		int rta = 0;

		try {
			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(props, auth);
			MimeMessage  msg = new MimeMessage(session);  

			log.info("Atencion: " + receptor.getPartida());			
			msg.setSubject(asunto);			 
			String de = "Rentas MCR";
			msg.setFrom(new InternetAddress(miCorreo,de));	
			msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receptor.getCorreoElectronico().toString()));
			msg.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress("mgutierrez@comodoro.gov.ar"));
			msg.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress("abethancourt@comodoro.gov.ar"));
			log.info("Enviar a: " + receptor.getCorreoElectronico());
			Transport.send(msg);				
			log.info("Enviado!");
			rta = 1;
		} catch (MessagingException e) {		
			envioAlerta(receptor);
			log.error("Error de autentificacion :" + miCorreo+" "+e);					
			//	e.printStackTrace();
			rta = 0;
		} catch (UnsupportedEncodingException e) {
			envioAlerta(receptor);
			log.error("Error de Codificacion no soportada" +" "+ e);
			//	e.printStackTrace();
			rta = 0;
		}		
		return rta;
	}



	private class autentificadorSMTP extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(miCorreo, miContraseña);
		}
	}

	private void envioAlerta(Receptor receptor) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			String de = "Comodoro Digital - Alerta E-boleta";
			message.setFrom(new InternetAddress(username,de));       	
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("mgutierrez@comodoro.gov.ar"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("abethancourt@comodoro.gov.ar"));
			message.setSubject("Alerta E-Boleta - Error envio de Subscripcion ");
			message.setText("Se detecto error en en envio de alta de suscription. Partida:"+receptor.getPartida()+" Correo:"+receptor.getCorreoElectronico()+". Verificar log. public_html/log_despachador/eboleta2.log");
			Transport.send(message);

			//https://support.google.com/accounts/answer/6010255?hl=es-419

			System.out.println("Done - Alerta enviada.");

		} catch (MessagingException e) {
			log.error("Alerta no enviada: " + e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}  

	private void envioAlerta(String baja, Receptor receptor) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS


		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			String de = "Comodoro Digital - Alerta E-boleta";
			message.setFrom(new InternetAddress(username,de));       	
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse("mgutierrez@comodoro.gov.ar, abethancourt@comodoro.gov.ar,comodorodigital2009@gmail.com")
					);
			message.setSubject("Alerta E-Boleta - Procesar baja ");
			message.setText("Se detecto error en proceso de baja partida: "+receptor.getPartida()+". Email: "+receptor.getCorreoElectronico()+". Verificar log. public_html/log_despachador/eboleta2.log");
			Transport.send(message);

			System.out.println("Done - Alerta enviada.");

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}  

}
