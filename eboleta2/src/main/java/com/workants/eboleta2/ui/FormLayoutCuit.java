package com.workants.eboleta2.ui;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.workants.eboleta2.componentes.TextFieldMail;
import com.workants.eboleta2.componentes.TextFieldNumero;
import com.workants.eboleta2.componentes.TextFieldString;
import com.workants.eboleta2.model.Juridica;

public class FormLayoutCuit extends FormLayout implements ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextFieldString txtRazonSocial;
	//private ComboBox cmbTipoDocumento;
	private TextFieldNumero txtCuit;
	private ComboBox cmbTipoTelefono;
	private TextField txtCaracteristica;
	private TextField txtNumeroDeTelefono;
	private ComboBox cmbTipoTelefonoAlternativo;
	private TextField txtCaracteristicaAlternativo;
	private TextField txtNumeroDeTelefonoAlternativo;
	private TextFieldMail txtEmail;
	private TextFieldMail txtEmailReingreso;
	private Button btnAceptar;
	private Button btnVolver;
	
	
	
	public FormLayoutCuit() {
	
		setCaption("Contribuyente Persona Juridica");
		setMargin(true);
		setSpacing(true);
		addComponent(generarTxtCuit());
		addComponent(generarTxtRazonSocial());
		//addComponent(generarCmbTipoDocumento());
		//addComponent(generarTxtNumeroDeDocumento());
		addComponent(generarCmbTipoDeTelefono());
		addComponent(generarTxtCaracteristica());
		addComponent(generarTxtNumeroDeTelefono());
		addComponent(generarCmbTipoDeTelefonoAleternativo());
		addComponent(generarTxtCaracteristicaAlternativo());
		addComponent(generarTxtNumeroDeTelefonoAlternativo());
		addComponent(generarTxtEmail());	
		addComponent(generarTxtEmailReingreso());
		addComponent(generarBotonera());
		
		
		
		//por defecto lo dejamos deshabilitados en funcion de la seleccion del cmbTipoTelefonoAlternativo
		this.getTxtNumeroDeTelefonoAlternativo().setRequired(false);
		this.getTxtCaracteristicaAlternativo().setRequired(false);
		this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
		this.getTxtCaracteristicaAlternativo().setEnabled(false);
		
		//focus
		this.getTxtCuit().focus();
		
		
	}
	
	
	public FormLayoutCuit(Juridica juridica) {
		
		setCaption("Contribuyente Persona Juridica");		
		GridLayout grid = new GridLayout();
		grid.setColumns(2);
		//grid.setMargin(true);
		grid.setSpacing(true);
		grid.addComponent(generarTxtCuit(juridica));
		grid.addComponent(generarTxtRazonSocial(juridica));
		grid.addComponent(generarCmbTipoDeTelefono(juridica));
		grid.addComponent(generarTxtCaracteristica(juridica));
		grid.addComponent(generarTxtNumeroDeTelefono(juridica));
		grid.addComponent(generarCmbTipoDeTelefonoAleternativo(juridica));
		grid.addComponent(generarTxtCaracteristicaAlternativo(juridica));
		grid.addComponent(generarTxtNumeroDeTelefonoAlternativo(juridica));
		grid.addComponent(generarTxtEmail(juridica));	
		grid.addComponent(generarTxtEmailReingreso(juridica));
		
		addComponent(grid);
	}


	private Component generarTxtEmailReingreso() {
		
		this.setTxtEmailReingreso(new TextFieldMail("Reingresar correo electronico:"));
		this.getTxtEmailReingreso().setWidth("300px");
		return this.getTxtEmailReingreso();
	}


	private Component generarTxtCaracteristicaAlternativo() {
		
		this.setTxtCaracteristicaAlternativo(new TextField("Caracteristica alternativo:"));
		this.getTxtCaracteristicaAlternativo().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristicaAlternativo().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().setWidth("300px");
		return this.getTxtCaracteristicaAlternativo();
	}


	
	private Component generarTxtNumeroDeTelefonoAlternativo() {
		
		this.setTxtNumeroDeTelefonoAlternativo(new TextField("Número de teléfono alternativo:"));		
		this.getTxtNumeroDeTelefonoAlternativo().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefonoAlternativo().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefonoAlternativo().setWidth("300px");
		return this.getTxtNumeroDeTelefonoAlternativo();
	}




	private Component generarCmbTipoDeTelefonoAleternativo() {
		
		this.setCmbTipoTelefonoAlternativo(new ComboBox("Tipo Telefono alternativo:"));
		this.getCmbTipoTelefonoAlternativo().setInvalidAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addItem("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().addItem("FIJO");
		this.getCmbTipoTelefonoAlternativo().addItem("CELULAR");
		this.getCmbTipoTelefonoAlternativo().setValue("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().setNullSelectionAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addValueChangeListener(this);
		this.getCmbTipoTelefonoAlternativo().setWidth("300px");
		return this.getCmbTipoTelefonoAlternativo();
	}




	private Component generarBotonera() {
		
		HorizontalLayout botonera = new HorizontalLayout();
		this.setBtnAceptar(new Button("Subscribirse"));
		this.setBtnVolver(new Button("Suscribir otro impuesto"));		
		this.getBtnVolver().setEnabled(false);
		this.getBtnVolver().setVisible(false);
		botonera.addComponent(this.getBtnAceptar());
		botonera.addComponent(this.getBtnVolver());
		botonera.setSpacing(true);
		return botonera;
	}

	private Component generarTxtEmail() {
		
		this.setTxtEmail(new TextFieldMail("Correo electronico:"));		
		this.getTxtEmail().addValidator(new EmailValidator("Dirección mal compuesta"));
		this.getTxtEmail().setWidth("300px");
		return this.getTxtEmail();
	}

	

	private Component generarTxtCaracteristica() {
		
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristica().setWidth("300px");
		return this.getTxtCaracteristica();		
	}
	
	private Component generarTxtNumeroDeTelefono() {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de teléfono:"));		
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefono().setWidth("300px");
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono() {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("NO POSEE");
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		this.getCmbTipoTelefono().addValueChangeListener(this);
		this.getCmbTipoTelefono().setWidth("300px");
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtCuit() {
		
		this.setTxtCuit(new TextFieldNumero("Número de CUIT:","cuit"));
		this.getTxtCuit().addValidator(new StringLengthValidator("Debe poseer 11 digitos", 11, 12, false));
	//	this.getTxtCuit().setInputPrompt("Sin guiones");
		this.getTxtCuit().setWidth("300px");
		this.getTxtCuit().focus();
		return this.getTxtCuit();
	}

//	private Component generarCmbTipoDocumento() {
//	
//		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
//		this.getCmbTipoDocumento().setInvalidAllowed(false);
//		this.getCmbTipoDocumento().setRequired(true);
//		this.getCmbTipoDocumento().addItem("DNI");
//		this.getCmbTipoDocumento().addItem("DU");
//		this.getCmbTipoDocumento().addItem("LC");
//		this.getCmbTipoDocumento().addItem("PASAPORTE");
//		this.getCmbTipoDocumento().setNullSelectionAllowed(false);
//		return this.getCmbTipoDocumento();
//	}

	private Component generarTxtRazonSocial() {
		
		this.setTxtRazonSocial(new TextFieldString("Razón Social:"));	
		this.getTxtRazonSocial().setWidth("300px");
		
//		this.getTxtRazonSocial().addBlurListener(new BlurListener() {
//			
//			@Override
//			public void blur(BlurEvent event) {
//				
//			
//				Notification notif = new Notification("Atenti Flor!!","Podes meter puntos si queres. Si lo pusiste todo bien :-)", Notification.Type.ERROR_MESSAGE);
//				notif.setDelayMsec(4000);
//				notif.setPosition(Position.MIDDLE_CENTER);
//				notif.show(Page.getCurrent());
//				
//			}
//		});
		
		return this.getTxtRazonSocial();
	}

//	private Component generarTxtApellido() {
//			
//		this.setTxtApellido(new TextFieldString("Apellido:"));
//		this.getTxtApellido().focus();
//		return this.getTxtApellido();
//	}
	
	public void reset(){
		
		this.getTxtRazonSocial().clear();
		//this.getTxtNombre().setValue("");
		this.getTxtCuit().clear();
		this.getTxtNumeroDeTelefono().setValue("");
		//this.getCmbTipoDocume.setValue(null);
		this.getCmbTipoTelefono().clear();
	}



	public TextFieldString getTxtRazonSocial() {
		return txtRazonSocial;
	}



	public void setTxtRazonSocial(TextFieldString txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}



	public TextFieldNumero getTxtCuit() {
		return txtCuit;
	}



	public void setTxtCuit(TextFieldNumero txtCuit) {
		this.txtCuit = txtCuit;
	}



	public ComboBox getCmbTipoTelefono() {
		return cmbTipoTelefono;
	}



	public void setCmbTipoTelefono(ComboBox cmbTipoTelefono) {
		this.cmbTipoTelefono = cmbTipoTelefono;
	}



	public TextField getTxtCaracteristica() {
		return txtCaracteristica;
	}



	public void setTxtCaracteristica(TextField txtCaracteristica) {
		this.txtCaracteristica = txtCaracteristica;
	}



	public TextField getTxtNumeroDeTelefono() {
		return txtNumeroDeTelefono;
	}



	public void setTxtNumeroDeTelefono(TextField txtNumeroDeTelefono) {
		this.txtNumeroDeTelefono = txtNumeroDeTelefono;
	}



	public ComboBox getCmbTipoTelefonoAlternativo() {
		return cmbTipoTelefonoAlternativo;
	}



	public void setCmbTipoTelefonoAlternativo(ComboBox cmbTipoTelefonoAlternativo) {
		this.cmbTipoTelefonoAlternativo = cmbTipoTelefonoAlternativo;
	}



	public TextField getTxtCaracteristicaAlternativo() {
		return txtCaracteristicaAlternativo;
	}



	public void setTxtCaracteristicaAlternativo(
			TextField txtCaracteristicaAlternativo) {
		this.txtCaracteristicaAlternativo = txtCaracteristicaAlternativo;
	}

	public TextField getTxtNumeroDeTelefonoAlternativo() {
		return txtNumeroDeTelefonoAlternativo;
	}

	public void setTxtNumeroDeTelefonoAlternativo(
			TextField txtNumeroDeTelefonoAlternativo) {
		this.txtNumeroDeTelefonoAlternativo = txtNumeroDeTelefonoAlternativo;
	}

	public TextFieldMail getTxtEmail() {
		return txtEmail;
	}



	public void setTxtEmail(TextFieldMail txtEmail) {
		this.txtEmail = txtEmail;
	}



	public TextFieldMail getTxtEmailReingreso() {
		return txtEmailReingreso;
	}



	public void setTxtEmailReingreso(TextFieldMail txtEmailReingreso) {
		this.txtEmailReingreso = txtEmailReingreso;
	}



	public Button getBtnAceptar() {
		return btnAceptar;
	}



	public void setBtnAceptar(Button btnAceptar) {
		this.btnAceptar = btnAceptar;
	}



	public Button getBtnVolver() {
		return btnVolver;
	}



	public void setBtnVolver(Button btnVolver) {
		this.btnVolver = btnVolver;
	}
	
	
	//Edicion !!!!
	
	
	private Component generarTxtEmailReingreso(Juridica juridica) {
		
		this.setTxtEmailReingreso(new TextFieldMail("Reingresar correo electronico:"));
		this.getTxtEmailReingreso().setWidth("300px");
		this.getTxtEmailReingreso().setValue(juridica.getCorreoElectronico());
		return this.getTxtEmailReingreso();
	}


	private Component generarTxtCaracteristicaAlternativo(Juridica juridica) {
		
		this.setTxtCaracteristicaAlternativo(new TextField("Caracteristica alternativo:"));
		this.getTxtCaracteristicaAlternativo().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristicaAlternativo().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().setWidth("300px");
		this.getTxtCaracteristicaAlternativo().setValue("297");
		return this.getTxtCaracteristicaAlternativo();
	}


	
	private Component generarTxtNumeroDeTelefonoAlternativo(Juridica juridica) {
		
		this.setTxtNumeroDeTelefonoAlternativo(new TextField("Número de teléfono alternativo:"));		
		this.getTxtNumeroDeTelefonoAlternativo().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefonoAlternativo().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefonoAlternativo().setWidth("300px");
		this.getTxtNumeroDeTelefonoAlternativo().setValue(juridica.getNumeroDeTelefonoAlternativo());
		return this.getTxtNumeroDeTelefonoAlternativo();
	}




	private Component generarCmbTipoDeTelefonoAleternativo(Juridica juridica) {
		
		this.setCmbTipoTelefonoAlternativo(new ComboBox("Tipo Telefono alternativo:"));
		this.getCmbTipoTelefonoAlternativo().setInvalidAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addItem("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().addItem("FIJO");
		this.getCmbTipoTelefonoAlternativo().addItem("CELULAR");
		this.getCmbTipoTelefonoAlternativo().setValue(juridica.getTipoTelefonoAlternativo());
		this.getCmbTipoTelefonoAlternativo().setNullSelectionAllowed(false);
		this.getCmbTipoTelefonoAlternativo().setWidth("300px");
		this.getCmbTipoTelefonoAlternativo().addValueChangeListener(this);
		return this.getCmbTipoTelefonoAlternativo();
	}




//	private Component generarBotonera() {
//		
//		HorizontalLayout botonera = new HorizontalLayout();
//		this.setBtnAceptar(new Button("Subscribirse"));
//		this.setBtnVolver(new Button("Suscribir otro impuesto"));		
//		this.getBtnVolver().setEnabled(false);
//		this.getBtnVolver().setVisible(false);
//		botonera.addComponent(this.getBtnAceptar());
//		botonera.addComponent(this.getBtnVolver());
//		botonera.setSpacing(true);
//		return botonera;
//	}

	private Component generarTxtEmail(Juridica juridica) {
		
		this.setTxtEmail(new TextFieldMail("Correo electronico:"));		
		this.getTxtEmail().addValidator(new EmailValidator("Dirección mal compuesta"));
		this.getTxtEmail().setWidth("300px");
		this.getTxtEmail().setValue(juridica.getCorreoElectronico());
		return this.getTxtEmail();
	}

	

	private Component generarTxtCaracteristica(Juridica juridica) {
		
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().setWidth("300px");
		this.getTxtCaracteristica().setValue("297");
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		return this.getTxtCaracteristica();		
	}
	
	private Component generarTxtNumeroDeTelefono(Juridica juridica) {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de teléfono:"));		
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefono().setWidth("300px");
		this.getTxtNumeroDeTelefono().setValue(juridica.getNumeroDeTelefono());
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono(Juridica juridica) {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("NO POSEE");
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setWidth("300px");
		this.getCmbTipoTelefono().setValue(juridica.getTipoTelefono());
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		this.getCmbTipoTelefono().addValueChangeListener(this);
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtCuit(Juridica juridica) {
		
		this.setTxtCuit(new TextFieldNumero("Número de CUIT:","cuit"));
		this.getTxtCuit().addValidator(new StringLengthValidator("Debe poseer 11 digitos", 11, 12, false));
	//	this.getTxtCuit().setInputPrompt("Sin guiones");
		this.getTxtCuit().setWidth("300px");
		this.getTxtCuit().focus();
		this.getTxtCuit().setValue(juridica.getCuit());
		return this.getTxtCuit();
	}

//	private Component generarCmbTipoDocumento() {
//	
//		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
//		this.getCmbTipoDocumento().setInvalidAllowed(false);
//		this.getCmbTipoDocumento().setRequired(true);
//		this.getCmbTipoDocumento().addItem("DNI");
//		this.getCmbTipoDocumento().addItem("DU");
//		this.getCmbTipoDocumento().addItem("LC");
//		this.getCmbTipoDocumento().addItem("PASAPORTE");
//		this.getCmbTipoDocumento().setNullSelectionAllowed(false);
//		return this.getCmbTipoDocumento();
//	}

	private Component generarTxtRazonSocial(Juridica juridica) {
		
		this.setTxtRazonSocial(new TextFieldString("Razón Social:"));	
		this.getTxtRazonSocial().setWidth("300px");
		this.getTxtRazonSocial().setValue(juridica.getRazonSocial());		
		return this.getTxtRazonSocial();
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
		
		//System.out.println(event.getProperty());
		
		if(event.getProperty() == this.getCmbTipoTelefono()){
			
			if(this.getCmbTipoTelefono().getValue() != null){
			
				
				System.out.println(this.getCmbTipoTelefono().getValue().toString());
				
				
			if(this.getCmbTipoTelefono().getValue().toString().equalsIgnoreCase("no posee")){
				
				 this.getCmbTipoTelefono().setRequired(false);
				 this.getTxtCaracteristica().setRequired(false);
				 this.getTxtNumeroDeTelefono().setRequired(false);
				 this.getTxtCaracteristica().setEnabled(false);
				 this.getTxtNumeroDeTelefono().setEnabled(false);
				 this.getCmbTipoTelefonoAlternativo().focus();
				 
			}else{
				
				 this.getCmbTipoTelefono().setRequired(true);
				 this.getTxtCaracteristica().setRequired(true);
				 this.getTxtNumeroDeTelefono().setRequired(true);
				 this.getTxtCaracteristica().setEnabled(true);
				 this.getTxtNumeroDeTelefono().setEnabled(true);
				 this.getTxtCaracteristica().focus();
			}
			
		  }
		}
		if(event.getProperty() == this.getCmbTipoTelefonoAlternativo()){
			
			if(this.getCmbTipoTelefonoAlternativo().getValue() != null){
			if(this.getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){
				
				 this.getCmbTipoTelefonoAlternativo().setRequired(false);
				 this.getTxtCaracteristicaAlternativo().setRequired(false);
				 this.getTxtNumeroDeTelefonoAlternativo().setRequired(false);
				 this.getTxtCaracteristicaAlternativo().setEnabled(false);
				 this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
				 this.getTxtEmail().focus();
				 
			}else{
				
				 this.getCmbTipoTelefonoAlternativo().setRequired(true);
				 this.getTxtCaracteristicaAlternativo().setRequired(true);
				 this.getTxtNumeroDeTelefonoAlternativo().setRequired(true);
				 this.getTxtCaracteristicaAlternativo().setEnabled(true);
				 this.getTxtNumeroDeTelefonoAlternativo().setEnabled(true);
				 this.getTxtCaracteristicaAlternativo().focus();
			}
		}
			
		}
		
		
	}
	
	
	

}
