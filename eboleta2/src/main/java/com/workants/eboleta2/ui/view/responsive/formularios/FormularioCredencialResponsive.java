package com.workants.eboleta2.ui.view.responsive.formularios;





import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.workants.eboleta2.componentes.TextFieldNumero;



//public class FormularioCredencialResponsive extends FormLayout{
public class FormularioCredencialResponsive extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextFieldNumero txtNumeroDePartida;
	private OptionGroup seleccionadorImpuesto;
	

	private Button btnValidarNumero;
	
	
	public FormularioCredencialResponsive(){
		
		
		this.setMargin(true);		
		addComponent(generarTxtNumeroPartida());
		addComponent(new Label("<b><i><font color='#FF0000' size='1'>*</font><font size='1'>Ubicado en la parte central de la boleta impresa  mensual",ContentMode.HTML));
		addComponent(generarSeleccionadorImpuestos());
		addComponent(generarBtnValidarNumero());
		
	}


	private Component generarSeleccionadorImpuestos() {
		
		this.setSeleccionadorImpuesto(new OptionGroup("Seleccione Impuesto:"));		
		this.getSeleccionadorImpuesto().addItem(1);
		this.getSeleccionadorImpuesto().setItemCaption(1, "Impuesto Automotor");
		this.getSeleccionadorImpuesto().setItemIcon(1, FontAwesome.CAR);
		this.getSeleccionadorImpuesto().addItem(2);
		this.getSeleccionadorImpuesto().setItemCaption(2, "Impuesto Inmobiliario");
		this.getSeleccionadorImpuesto().setItemIcon(2, FontAwesome.HOME);
		this.getSeleccionadorImpuesto().addItem(3);
		this.getSeleccionadorImpuesto().setItemCaption(3, "Impuesto Tasa de Higiene Urbana"); 
		this.getSeleccionadorImpuesto().setItemIcon(3, FontAwesome.TRASH);
		this.getSeleccionadorImpuesto().addItem(4);
		this.getSeleccionadorImpuesto().setItemCaption(4, "Impuesto  Derecho de Ocupante");
		this.getSeleccionadorImpuesto().setItemIcon(4, FontAwesome.HOME);
//		this.getSeleccionadorImpuesto().addItem(5);
//		this.getSeleccionadorImpuesto().setItemCaption(5, "Impuesto  Derecho de Ocupante Tasa de Higiene");
//		this.getSeleccionadorImpuesto().setItemIcon(5, VaadinIcons.PACKAGE);
		return this.getSeleccionadorImpuesto();
		
	}


	private Component generarBtnValidarNumero() {
		
		this.setBtnValidarNumero(new Button("Ingresar"));
		this.getBtnValidarNumero().setClickShortcut(com.vaadin.event.ShortcutAction.KeyCode.ENTER);
		this.getBtnValidarNumero().setIcon(FontAwesome.SIGN_IN);
		this.getBtnValidarNumero().addStyleName(ValoTheme.BUTTON_PRIMARY);
		return this.getBtnValidarNumero();
	}


	private Component generarTxtNumeroPartida() {
		
		this.setTxtNumeroDePartida(new TextFieldNumero());
		this.getTxtNumeroDePartida().setInputPrompt("Ingrese 11 digitos");
		this.getTxtNumeroDePartida().setWidth("200px");
		//this.getTxtNumeroDePartida().focus();
		return this.getTxtNumeroDePartida();
	}

	public Button getBtnValidarNumero() {
		return btnValidarNumero;
	}


	public void setBtnValidarNumero(Button btnValidarNumero) {
		this.btnValidarNumero = btnValidarNumero;
	}
	
	public TextFieldNumero getTxtNumeroDePartida() {
		return txtNumeroDePartida;
	}


	public void setTxtNumeroDePartida(TextFieldNumero txtNumeroDePartida) {
		this.txtNumeroDePartida = txtNumeroDePartida;
	}


	public OptionGroup getSeleccionadorImpuesto() {
		return seleccionadorImpuesto;
	}


	public void setSeleccionadorImpuesto(OptionGroup seleccionadorImpuesto) {
		this.seleccionadorImpuesto = seleccionadorImpuesto;
	}

	

}
