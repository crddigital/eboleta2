package com.workants.eboleta2.ui.view.ViewPrincipal;




import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;



public class ViewPrincipalEncabezado extends HorizontalLayout {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnAccesoMCR;
	private Button btnDesuscribir;
	private Label lblBienvenida;

	public ViewPrincipalEncabezado(){
		
		
		setMargin(true);
		setSpacing(true);
		setWidth("100%");
		setHeight("50px");
		
		HorizontalLayout label = new HorizontalLayout();
		label.addComponent(generarLabelBienvenida());
		addComponent(label);
		
		HorizontalLayout botonera = new HorizontalLayout();
		//botonera.setWidth(null);
		botonera.setSpacing(true);	 
		botonera.addComponent(generarBtnAccesoMCR());
		botonera.addComponent(generarBtnDesuscripcion());
		addComponent(botonera);	
		
		
		setComponentAlignment(label, Alignment.MIDDLE_LEFT);
		setComponentAlignment(botonera, Alignment.TOP_RIGHT);
		
		
	}

	private Component generarLabelBienvenida() {
		this.setLblBienvenida(new Label("<big><strong><font color ='white'>Gestor Recibos Electronicos - MCR",ContentMode.HTML));
		return this.getLblBienvenida();
	}
	
	private Component generarBtnDesuscripcion() {
		
		this.setBtnDesuscribir(new Button("Desuscribirse"));
		this.getBtnDesuscribir().setStyleName(ValoTheme.BUTTON_LINK); 	
		return this.getBtnDesuscribir();
	}

	private Component generarBtnAccesoMCR() {
	
		//this.setBtnAccesoMCR(new Button("Acceso MCR/Empresas"));
		this.setBtnAccesoMCR(new Button("Acceso MCR"));
		this.getBtnAccesoMCR().addStyleName(ValoTheme.BUTTON_LINK);
		this.getBtnAccesoMCR().setIcon(FontAwesome.KEY);
		return this.getBtnAccesoMCR();
	}

	
	public Button getBtnAccesoMCR() {
		return btnAccesoMCR;
	}

	public void setBtnAccesoMCR(Button btnAccesoMCR) {
		this.btnAccesoMCR = btnAccesoMCR;
	}

	public Label getLblBienvenida() {
		return lblBienvenida;
	}

	public void setLblBienvenida(Label lblBienvenida) {
		this.lblBienvenida = lblBienvenida;
	}

	public Button getBtnDesuscribir() {
		return btnDesuscribir;
	}

	public void setBtnDesuscribir(Button btnDesuscribir) {
		this.btnDesuscribir = btnDesuscribir;
	}
	
}
