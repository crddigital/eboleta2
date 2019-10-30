package com.workants.eboleta2.ui.view.ViewAdministrador;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ViewAdministradorLateralIzquierdo extends VerticalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label lblEncabezado;
	private Button btnOperaciones;
	private Button btnAbmUsuario;
	private Button btnCuerpoMail;
	private Button btnExportar;	
	private Button btnLogout;
	
	
	
	
	
	
	public ViewAdministradorLateralIzquierdo(){
				
		addComponent(generarLateral());
	}
	
	private Component generarLateral() {
		
		VerticalLayout lateral = new VerticalLayout();
		lateral.setSpacing(true);
		lateral.setPrimaryStyleName(ValoTheme.ACCORDION_BORDERLESS);
	
		this.setLblEncabezado(new Label());
		this.getLblEncabezado().addStyleName("estilo-label");
		this.setBtnAbmUsuario(new Button("ABM Usuarios"));		
		
		this.setBtnOperaciones(new Button("Operaciones"));
		this.setBtnExportar(new Button("Exportar"));
		this.setBtnCuerpoMail(new Button("Cuerpo Mail"));
		this.setBtnLogout(new Button("Salir"));

		this.getBtnLogout().addStyleName("btn-lateral");
		this.getBtnAbmUsuario().addStyleName("btn-lateral");
		this.getBtnExportar().addStyleName("btn-lateral");
		this.getBtnOperaciones().addStyleName("btn-lateral");
		
		

		lateral.addComponent(this.getLblEncabezado());
		lateral.addComponent(this.getBtnAbmUsuario());
		lateral.addComponent(this.getBtnOperaciones());
		lateral.addComponent(this.getBtnExportar());
		lateral.addComponent(this.getBtnCuerpoMail());
		lateral.addComponent(this.getBtnLogout());	
		return lateral;
	}


	public Label getLblEncabezado() {
		return lblEncabezado;
	}


	public void setLblEncabezado(Label lblEncabezado) {
		this.lblEncabezado = lblEncabezado;
	}

	public Button getBtnOperaciones() {
		return btnOperaciones;
	}

	public void setBtnOperaciones(Button btnOperaciones) {
		this.btnOperaciones = btnOperaciones;
	}

	public Button getBtnAbmUsuario() {
		return btnAbmUsuario;
	}

	public void setBtnAbmUsuario(Button btnAbmUsuario) {
		this.btnAbmUsuario = btnAbmUsuario;
	}

	public Button getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}

	public Button getBtnLogout() {
		return btnLogout;
	}

	public void setBtnLogout(Button btnLogout) {
		this.btnLogout = btnLogout;
	}

	public Button getBtnCuerpoMail() {
		return btnCuerpoMail;
	}

	public void setBtnCuerpoMail(Button btnCuerpoMail) {
		this.btnCuerpoMail = btnCuerpoMail;
	}
}
