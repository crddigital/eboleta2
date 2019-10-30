package com.workants.eboleta2.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.workants.eboleta2.model.PreSolicitud;

public class VentanaDetalleTitular extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaDetalleTitular(PreSolicitud preSolicitud){
		
		setCaption("Detalle del Solicitante");
		setResizable(false);		
		setWidth("550px");
		setHeight("450px");		
	
//		Label lbl = new Label("In HTML mode, all HTML formatting tags, such as \n" +
//			    "<ul>"+
//			    "  <li><b>"+preSolicitud.getSolicitante().getNombre()+"</b></li>"+
//			    "  <li>itemized lists</li>"+
//			    "  <li>etc.</li>"+
//			    "</ul> "+
//			    "are preserved.",
//			    ContentMode.HTML);
		Label lbl = new Label("<ul>"
							 +"<li>Nombre:</li><b>"+preSolicitud.getSolicitante().getNombre().toUpperCase()+"</b> " +
							  "<li>Apellido:</li><b>"+preSolicitud.getSolicitante().getApellido().toUpperCase()+"</b>" +
							  "<li>Tipo Documento:</li><b>"+preSolicitud.getSolicitante().getTipoDocumentoTitular().toUpperCase() +"</b>" +
							  "<li>N de Documento:</li><b>"+preSolicitud.getSolicitante().getNumeroDeDocumentoSolicitante().toUpperCase() +"</b>" +
							  "<li>Tipo Telefono:</li><b>"+preSolicitud.getSolicitante().getTipoTelefonoSolicitante().toUpperCase() +"</b>" +
							  " <li>N de Telefono:</li><b>"+preSolicitud.getSolicitante().getNumeroDeTelefonoSolicitante().toUpperCase() +"</b>" +
							  " <li>Correo electronico:</li><b>"+preSolicitud.getSolicitante().getCorreoElectronicoSolicitante() +"</b>"
							  + "</ul>",ContentMode.HTML);
		HorizontalLayout l = new HorizontalLayout();
		l.setMargin(true);
		center();
		//this.setModal(true);
		l.addComponent(lbl);
		setContent(l);
		
	}

}
