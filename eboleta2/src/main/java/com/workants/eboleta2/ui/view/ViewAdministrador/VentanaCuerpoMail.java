package com.workants.eboleta2.ui.view.ViewAdministrador;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.workants.eboleta2.IView.IVentanaCuerpoMail;
import com.workants.eboleta2.handler.IVentanaCuerpoMailHandler;

public class VentanaCuerpoMail extends Window implements IVentanaCuerpoMail, ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea txtAreaCuerpoMail;
	private Button btnLimpiar;
	private Button bntGuardarCambios;
	private IVentanaCuerpoMailHandler handler;
	
	

	public VentanaCuerpoMail(String cuerpoMail){
		
		setCaption("Mensaje cuerpo mail");
		setWidth("520px");
		setHeight("520px");
		setResizable(false);
		center();
		
	
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);		
		
		layout.addComponent(generarTextAreaCuerpo(cuerpoMail));
		layout.addComponent(generarComandos());	
		layout.setComponentAlignment(this.getTxtAreaCuerpoMail(), Alignment.MIDDLE_CENTER);
		setContent(layout);
		
		
		
	}
	
	private Component generarTextAreaCuerpo(String cuerpoMail) {
		
		this.setTxtAreaCuerpoMail(new TextArea());
		this.getTxtAreaCuerpoMail().setValue(cuerpoMail);
		this.getTxtAreaCuerpoMail().setHeight("400px");
		this.getTxtAreaCuerpoMail().setWidth("400px");
		return this.getTxtAreaCuerpoMail();
	}

	private Component generarComandos() {
		
		HorizontalLayout layoutComandos = new HorizontalLayout();
		layoutComandos.setSpacing(true);
		layoutComandos.addComponent(generarBtnLimpiar());
		layoutComandos.addComponent(generarGuardar());
		
		
		return layoutComandos;
	}

	private Component generarGuardar() {
	
		this.setBntGuardarCambios(new Button("Guardar Cambios"));
		this.getBntGuardarCambios().addClickListener(this);
		return this.getBntGuardarCambios();
	}

	private Component generarBtnLimpiar() {
		
		this.setBtnLimpiar(new Button("Limpiar"));
		this.getBtnLimpiar().addClickListener(this);
		return this.getBtnLimpiar();
	}

	public TextArea getTxtAreaCuerpoMail() {
		return txtAreaCuerpoMail;
	}

	public void setTxtAreaCuerpoMail(TextArea txtAreaCuerpoMail) {
		this.txtAreaCuerpoMail = txtAreaCuerpoMail;
	}

	public Button getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(Button btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public Button getBntGuardarCambios() {
		return bntGuardarCambios;
	}

	public void setBntGuardarCambios(Button bntGuardarCambios) {
		this.bntGuardarCambios = bntGuardarCambios;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getBtnLimpiar()){
			handler.limpiar();
		}
		if(event.getSource() == this.getBntGuardarCambios()){
			
			ConfirmDialog.show(UI.getCurrent(), "Atención", "¿Esta seguro de modificar el cuerpo del correo electronico?", "Si", "No", new ConfirmDialog.Listener() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClose(ConfirmDialog rta) {
					if(rta.isConfirmed()){
						handler.guardarCambios();
					}
					
				}
			});
		}
			
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHandler(IVentanaCuerpoMailHandler handler) {
		
		this.handler = handler;
		
	}

	@Override
	public IVentanaCuerpoMailHandler getHandler() {
		
		return this.handler;
	}

	public void guardarCambiosOk() {
		
		//System.out.println("seeeeeeeeeeee");
		Notification notif = new Notification("Cambios efectuados correctamente", Notification.Type.TRAY_NOTIFICATION);
		notif.setDelayMsec(3000);
		notif.show(Page.getCurrent());
		//System.out.println(notif);
		//System.out.println("nooooooooo");
		
	}

	public void guardarCambiosError() {
		
		Notification.show("Cambios no realizado. Error al efectuar operación", Notification.Type.ERROR_MESSAGE);
	}

}
