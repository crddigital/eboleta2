package com.workants.eboleta2.ui.view.ViewBaja;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.IViewABMSuscripciones;
import com.workants.eboleta2.handler.IViewABMSuscripcionesHandler;


public class ViewBaja extends VerticalLayout  implements IViewABMSuscripciones, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IViewABMSuscripcionesHandler handler;	
	public static final String NAME = "viewBaja";
	private String codigoPagoElectronico;
	private TextField txtCodogoKeyBaja;
	private Button btnConfirmarBaja;
	private Button btnRediriguir;
	
	public ViewBaja(){
		
		//this.codigoPagoElectronico = cpe;
		setMargin(true);
		setSpacing(true);
		setCaption("** Confirmación y baja de recepción de impuesto electronico**");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		  if(event.getParameters() != null){
			  
			  String[] msgs = event.getParameters().split("/");
			   // split at "/", add each part as a label
	           for (String msg : msgs) {
	             //  addComponent(new Label(msg));
	        	   Notification.show(msg);
	        	   this.setCodigoPagoElectronico(msg);
	        	   handler.verificarCpe();
	           }
	       }	
	}

	public void generarLayoutBajaOK() {
		
		addComponent(new Label("<p><b>Bienvenido. Para desuscribirse a la recepción de correo electronico ingrese el código enviado por correo y haga click en el botón. Gracias.</b></p>",ContentMode.HTML));		
		this.setBtnConfirmarBaja(new Button("Confirmar Baja"));
		this.getBtnConfirmarBaja().addClickListener(this);
		
		this.setTxtCodogoKeyBaja(new TextField("Código:"));
		this.getTxtCodogoKeyBaja().setInputPrompt("Código enviado");
		this.getTxtCodogoKeyBaja().setRequired(true);
		
		addComponent(this.getTxtCodogoKeyBaja());
		addComponent(this.getBtnConfirmarBaja());
		
		
	}
	public void generarLayoutBajaError() {
		
		Notification notif = new Notification("Error","No es posible encontrar el impuesto para realizar la baja. ",Notification.Type.ERROR_MESSAGE);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
	}


	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}

	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}


	public Button getBtnConfirmarBaja() {
		return btnConfirmarBaja;
	}

	public void setBtnConfirmarBaja(Button btnConfirmarBaja) {
		this.btnConfirmarBaja = btnConfirmarBaja;
	}

	public IViewABMSuscripcionesHandler getHandler() {
		return handler;
	}

	public void setHandler(IViewABMSuscripcionesHandler handler) {
		this.handler = handler;
	}
	public Button getBtnRediriguir() {
		return btnRediriguir;
	}

	public void setBtnRediriguir(Button btnRediriguir) {
		this.btnRediriguir = btnRediriguir;
	}


	

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getBtnConfirmarBaja()){
			if(this.getTxtCodogoKeyBaja().getValue() != null){
			handler.ejecutarBaja();
			}
		}
		if(event.getSource() == this.getBtnRediriguir()){
			handler.redirigir();
		}
	}

	public void bajaOk() {
		
		removeAllComponents();
		addComponent(new Label("<p><b>Suscripción dado de baja correctamente. Gracias.</b></p>",ContentMode.HTML));
		
		this.setBtnRediriguir(new Button("Finalizar"));
		this.getBtnRediriguir().addClickListener(this);
		addComponent(this.getBtnRediriguir());
		
		
	}

	public void bajaError() {
		// TODO Auto-generated method stub
		
	}

	public TextField getTxtCodogoKeyBaja() {
		return txtCodogoKeyBaja;
	}

	public void setTxtCodogoKeyBaja(TextField txtCodogoKeyBaja) {
		this.txtCodogoKeyBaja = txtCodogoKeyBaja;
	}

	
	
}
