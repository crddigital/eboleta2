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
//import com.workants.despachador.dao.Conexion;
import com.workants.eboleta2.model.Receptor;

public class EnviadorMail {


	final String miCorreo = "";
	final String miContraseña = "";		  
	final String servidorSMTP = "";
	final String puertoEnvio = "587";
	ArrayList<Receptor> receptores;
	String mailReceptor = null;
	String asunto = null;
	String cuerpo = null;

	Receptor receptor;


	//masivo
	Properties propsMasivo = new Properties();

	public static Logger log = Logger.getLogger(EnviadorMail.class);








	//Una sesion para enviar todo
	public EnviadorMail(ArrayList<Receptor> receptores){

		this.receptores = receptores;
		this.asunto = "Envio Impuesto MCR";
		this.cuerpo = "";
		String de = "Rentas MCR";

		Properties props = new Properties();
		props.put("mail.smtp.user", miCorreo);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.socketFactory.port", puertoEnvio);
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.enable", false);
		props.put("mail.smtp.starttls.enable", false);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.transport.protocol", "smtp");

		//SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(props, auth);
			Transport transport = session.getTransport();
			transport.connect();			

			log.info("Proceso de envio de mails iniciando en www.comodoro.gov.ar");

			for (int i = 0; i < this.receptores.size(); i++){ 					

				Receptor receptor = new Receptor();
				receptor = this.receptores.get(i);

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(miCorreo,de));
				message.setSubject(asunto);
				InternetAddress[] address = {new InternetAddress(receptor.getCorreoElectronico())};
				message.setRecipients(Message.RecipientType.TO, address);
				message.setContent(cuerpo +" \n\n "+ composicion(receptor) + " <br>En caso de baja de del servicio complete los "
						+ "datos del formulario siguiente ->:<a href='http://goo.gl/GZRlLd'><strong>Aquí</strong></a><br>"
						+ "<br>Que tenga un buen día<br>","text/html; charset=utf-8" );	

				log.info("Enviando correo electronico a: " + receptor.getCorreoElectronico() +" partida: "+ receptor.getPartida());
				//transport.sendMessage(message, address);  					
			}

			log.info("Proceso de envio de mails finalizado en www.comodoro.gov.ar");
		} catch (MessagingException e) {		
			log.error("Error de autentificacion: " +e);							
		} catch (UnsupportedEncodingException e) {
			log.error("Error de Codificacion no soportada: " +e);
		}		
	}


	public EnviadorMail(ArrayList<Receptor> receptores, String asunto,
			String cuerpo){

		this.receptores = receptores;
		this.asunto = asunto;
		this.cuerpo = cuerpo;

		Properties props = new Properties();
		props.put("mail.smtp.user", miCorreo);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.socketFactory.port", puertoEnvio);
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.enable", false);
		props.put("mail.smtp.starttls.enable", false);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(props, auth);
			for (int i = 0; i < this.receptores.size(); i++) {

				Receptor receptor = new Receptor();
				receptor = this.receptores.get(i);			 
				MimeMessage  msg = new MimeMessage(session);  

				msg.setText(cuerpo +" \n\n "+ composicion(receptor) + " \n\n En caso de baja de del servicio sigas las "
						+ "instrucciones del siguente link: <strong>http://goo.gl/GZRlLd </strong>","text/html; charset=utf-8" );
				msg.setSubject(asunto);			 
				String de = "Rentas MCR";
				msg.setFrom(new InternetAddress(miCorreo,de));	
				msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receptor.getCorreoElectronico().toString()));
				System.out.println("enviar a: " + receptor.getCorreoElectronico());
				Transport.send(msg);	

				// String consulta = "insert into procesados values "

			}	 
		} catch (MessagingException e) {		
			System.out.println("Error de autentificacion ");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error de Codificacion no soportada");
			e.printStackTrace();
		}
	}


	//public EnviadorMail(Receptor receptor2) {
	public EnviadorMail() {


		this.asunto = "Envio Impuesto MCR";
		this.cuerpo = "";
		String de = "Rentas MCR";

		//Properties props = new Properties();
		propsMasivo.put("mail.smtp.user", miCorreo);
		propsMasivo.put("mail.smtp.host", servidorSMTP);
		propsMasivo.put("mail.smtp.port", puertoEnvio);
		propsMasivo.put("mail.smtp.socketFactory.port", puertoEnvio);
		propsMasivo.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.debug", "true");
		propsMasivo.put("mail.smtp.ssl.enable", false);
		propsMasivo.put("mail.smtp.starttls.enable", false);
		propsMasivo.put("mail.smtp.auth", "true");
		propsMasivo.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		propsMasivo.put("mail.transport.protocol", "smtp");



		//SecurityManager security = System.getSecurityManager();
		log.info("** Constructor creado **");		
		log.info("** Proceso de envio de mails iniciando en www.comodoro.gov.ar **");

	}



	public int enviarImpuesto(Receptor receptor2){

		String de = "Rentas MCR";
		int rta = 1;

		try {
			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(propsMasivo, auth);
			Transport transport = session.getTransport();
			transport.connect();			

			Receptor receptor = new Receptor();
			receptor = receptor2;

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(miCorreo,de));
			message.setSubject(asunto);
			InternetAddress[] address = {new InternetAddress(receptor.getCorreoElectronico())};
			message.setRecipients(Message.RecipientType.TO, address);


			message.setContent("<big>** TEST ENVIO DE PRUEBA **</big><br>"+"Señor Contribuyente:<br>"
					+"Ud. adhirió al servicio de e-boleta para el tributo: <strong>"+ receptor.getTipoImpuesto()+"</strong>->: <a href='"+composicion(receptor)+"'><strong>e-boleta</strong></a><br>"  
					+"Para darse de baja complete los datos del formulario en el siguiente enlace: <a href='https://www.comodoro.gov.ar/eboleta2/#!viewDesuscripcion'><strong>Baja</strong></a><br>" 
					+"Recuerde que de acuerdo a la <a href='https://www.comodoro.gov.ar/?p=21365'<strong>Normativa*</strong></a>, la baja no implica modificación alguna de las obligaciones<br>"
					+"tributarias ni de los datos registrados en nuestros sistemas impositivos.<br><br><br>"
					+"Que tenga un buen día.<br>"
					+"Dirección General de Rentas<br>"
					+"Municipalidad de Comodoro Rivadavia","text/html; charset=utf-8" );




			/*
			 * 
			 * 			msg.setContent("Señor Contribuyente<br> Confirme su suscripción al servicio de e-boleta "
						+ "para el "+receptor.getTipoImpuesto().toLowerCase()+" en el siguiente link: "
						+ "<a href='http://www.comodoro.gov.ar/eboleta/#!viewSuscripcion/"+receptor.getPartida()+"'><strong>"+receptor.getTipoImpuesto()+"</strong></a>"
						+ "<br>Recuerde que una vez confirmada el alta, Usted comenzará a recibir por e-mail las e–boletas a partir de la próxima emisión."
						+ "<br>Si desea obtener las boleta antes, puede descargarla directamente de <a href='http://www.comodoro.gov.ar/impuestos/'><strong>http://www.comodoro.gov.ar/impuestos/</strong></a>, "
						+ "<br>al igual que las boletas vencidas."
						+ "<br><strong>IMPORTANTE:</strong> Recuerde que al momento de la adhesión, pudiera recibir la factura impresa del mes en curso,  "
						+ "o que el bimestre posterior "
						+ "se encuentre en proceso de entrega.","text/html; charset=utf-8");			

			 * 
			 */


			//    "<a href='http://www.comodoro.gov.ar/eboleta/#!viewSuscripcion/"+receptor.getPartida()+"'><strong>"+e-boleta+"</strong></a>



			//			    message.setContent("Estimado(a) Vecino(a):<br>"
			//			    	+"Ud. adhirió al servicio de e-boleta para el tributo:"+ receptor.getTipoImpuesto()+" ->: e-boleta"  
			//			    	+"Para darse de baja complete los datos del formulario en el siguiente enlace: Baja" 
			//			    	+"Recuerde que de acuerdo a la Normativa*, la baja no implica modificación alguna de las obligaciones tributarias "
			//			    	+ "ni de los datos registrados en nuestros sistemas impositivos."
			//			    	+"Que tenga un buen día.<br>"
			//			    	+"Firma y datos de contacto ¿¿¿????","text/html; charset=utf-8" );


			//			    message.setContent(cuerpo + composicion(receptor) + " <br>En caso de baja de del servicio complete los "
			//					 		+ "datos del formulario siguiente ->:<a href='http://goo.gl/GZRlLd'><strong>Aquí</strong></a><br>"
			//					 		+ "<br>Que tenga un buen día<br>","text/html; charset=utf-8" );	








			log.info("Enviando correo electronico a: " + receptor.getCorreoElectronico() +" partida: "+ receptor.getPartida());
			transport.sendMessage(message, address);  		

			//	}

			log.info("** Proceso de envio de mails finalizado en www.comodoro.gov.ar **");
		} catch (MessagingException e) {		
			log.error("Error de autentificacion: " +e);		
			log.info("** No se envió correo a : ** " + receptor.getCorreoElectronico() +" partida: "+ receptor.getPartida());
			return 0;
		} catch (UnsupportedEncodingException e) {
			log.error("Error de Codificacion no soportada: " +e);
			log.info("** No se envió correo a : ** " + receptor.getCorreoElectronico() +" partida: "+ receptor.getPartida());
			return 0;
		}
		return rta;
	}

	private String composicion(Receptor receptor) {

		String link = "";
		String inicio = "Estimado(a) Contribuyente:<br>";
		if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto automotor")){

			link = "http://www.comodoro.gov.ar/impuestos/html/autoPdfU.php?clave="+receptor.getPartida()+"&imputacion=%203";
			//System.out.println(TinyUrl.getTinyUrl(link));
			//return inicio+"Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+"<a href='"+TinyUrl.getTinyUrl(link)+"'><strong>Impuesto</strong></a>";  
			//<a href='http://goo.gl/GZRlLd'><strong>AQUI</strong></a>
			return link;
		}
		if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Inmobiliario")){

			link = "http://www.comodoro.gov.ar/impuestos/html/pdfinmoU.php?clave="+receptor.getPartida()+"&imputacion=%201";
			//System.out.println(TinyUrl.getTinyUrl(link));
			//return "Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+TinyUrl.getTinyUrl(link);
			//return inicio+"Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+"<a href='"+TinyUrl.getTinyUrl(link)+"'><strong>Impuesto</strong></a>";
			return link;
		}
		if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Tasa de Higiene Urbana")){

			link = "http://www.comodoro.gov.ar/impuestos/html/thuPdfU.php?clave="+receptor.getPartida()+"&imputacion=%2097";
			//System.out.println(TinyUrl.getTinyUrl(link));
			//return "Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+TinyUrl.getTinyUrl(link);
			//return inicio+"Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+"<a href='"+TinyUrl.getTinyUrl(link)+"'><strong>Impuesto</strong></a>";
			return link;

		}
		if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Derecho Ocupante Tasa de Higiene")){

			link = "http://www.comodoro.gov.ar/impuestos/html/PdfOcuThu.php?clave="+receptor.getPartida()+"&imputacion=%2097";
			// System.out.println(TinyUrl.getTinyUrl(link));
			//return "Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+TinyUrl.getTinyUrl(link);
			//return inicio+"Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+"<a href='"+TinyUrl.getTinyUrl(link)+"'><strong>Impuesto</strong></a>";
			return link;
		}    
		if (receptor.getTipoImpuesto().equalsIgnoreCase("Impuesto Derecho de Ocupante")){

			link = "http://www.comodoro.gov.ar/impuestos/html/pdfOcu.php?clave="+receptor.getPartida()+"&imputacion=%2017";
			// System.out.println(TinyUrl.getTinyUrl(link));
			//return "Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+TinyUrl.getTinyUrl(link);
			//return inicio+"Tipo de impuesto registrado : " + receptor.getTipoImpuesto()+" ->: "+"<a href='"+TinyUrl.getTinyUrl(link)+"'><strong>Impuesto</strong></a>";
			return link;
		}
		return link;
	}

	private class autentificadorSMTP extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(miCorreo, miContraseña);
		}


	}




}
