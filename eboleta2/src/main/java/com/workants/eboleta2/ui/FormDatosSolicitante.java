package com.workants.eboleta2.ui;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
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
import com.workants.eboleta2.model.Solicitante;

public class FormDatosSolicitante extends FormLayout implements ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextFieldString txtApellido;
	private TextFieldString txtNombre;
	private ComboBox cmbTipoDocumento;
	private TextFieldNumero txtNumeroDeDocumento;
	private ComboBox cmbTipoTelefono;
	private TextField txtCaracteristica;
	private TextField txtNumeroDeTelefono;
	private TextField txtEmail;
	private TextFieldMail txtEmailReingreso;
	private Button btnAceptar;
	private Button btnVolver;
	
	private Button btnEditar;
	
	public FormDatosSolicitante(){
		
		setCaption("Datos del receptor solicitante del impuesto");
		setSpacing(true);
		setMargin(true);		
		addComponent(generarTxtApellido());
		addComponent(generarTxtNombre());
		addComponent(generarCmbTipoDocumento());
		addComponent(generarTxtNumeroDeDocumento());
		addComponent(generarCmbTipoDeTelefono());
		addComponent(generarCmbCaracteristica());
		addComponent(generarTxtNumeroDeTelefono());
		addComponent(generarTxtEmail());
		addComponent(generarTxtEmailReingreso());
		addComponent(generarBotonera());

	}
	
	public FormDatosSolicitante(Solicitante solicitante) {
		
		setCaption("Datos del receptor solicitante del impuesto");
		setSpacing(true);
		setMargin(true);		
		
		GridLayout grid = new GridLayout();
		grid.setSpacing(true);
		grid.setColumns(3);		
		
		grid.addComponent(generarTxtApellido(solicitante));
		grid.addComponent(generarTxtNombre(solicitante));
		grid.addComponent(generarCmbTipoDocumento(solicitante));
		grid.addComponent(generarTxtNumeroDeDocumento(solicitante));
		grid.addComponent(generarCmbTipoDeTelefono(solicitante));
		grid.addComponent(generarCmbCaracteristica(solicitante));
		grid.addComponent(generarTxtNumeroDeTelefono(solicitante));
		grid.addComponent(generarTxtEmail(solicitante));
		grid.addComponent(generarTxtEmailReingreso(solicitante));
		//grid.addComponent(generarBotoneraEdicion());
		
		addComponent(grid);
		//addComponent(generarBotoneraEdicion());
		
	}

//	private Component generarBotoneraEdicion() {
//	
//		HorizontalLayout botonera = new HorizontalLayout();
//		this.setBtnEditar(new Button("Editar"));
//		//this.setBtnAceptar(new Button("Subscribirse"));
//		//this.setBtnVolver(new Button("Suscribir otro impuesto"));		
//		//this.getBtnVolver().setEnabled(false);
//		//this.getBtnVolver().setVisible(false);
//		//botonera.addComponent(this.getBtnAceptar());
//		//botonera.addComponent(this.getBtnVolver());
//		botonera.addComponent(this.getBtnEditar());
//		botonera.setSpacing(true);
//		return botonera;
//	}

	private Component generarTxtEmailReingreso() {
		
		this.setTxtEmailReingreso(new TextFieldMail("Reingrese Correo electronico:"));
		this.getTxtEmailReingreso().setInvalidAllowed(false);
		this.getTxtEmailReingreso().setRequired(true);		
		return this.getTxtEmailReingreso();
		
	}

	private Component generarCmbCaracteristica() {
		
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Sin cero");
		//this.getTxtCaracteristica().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristica().setRequired(true);
		return this.getTxtCaracteristica();
	}

	private Component generarBotonera() {
		
		HorizontalLayout botonera = new HorizontalLayout();
		this.setBtnAceptar(new Button("Subscribirse"));
		this.setBtnVolver(new Button("Suscribir otro impuesto"));
		botonera.setSpacing(true);
		botonera.addComponent(this.getBtnAceptar());
		botonera.addComponent(this.getBtnVolver());
		this.getBtnVolver().setEnabled(false);
		this.getBtnVolver().setVisible(false);
		return botonera;
	}
	
    private Component generarTxtEmail() {
		
		this.setTxtEmail(new TextField("Correo electronico:"));
		this.getTxtEmail().setInvalidAllowed(false);
		this.getTxtEmail().setRequired(true);		
		return this.getTxtEmail();
	}

	private Component generarTxtNumeroDeTelefono() {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de telefono:"));
		//this.getTxtNumeroDeTelefono().addValidator(new StringLengthValidator("Minimo 7 n�meros", 7, 10, false));
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono() {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtNumeroDeDocumento() {
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));	
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 n�mero, m�ximo 8", 7, 8, false));
		return this.getTxtNumeroDeDocumento();
	}

	private Component generarCmbTipoDocumento() {
	
		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
		this.getCmbTipoDocumento().setInvalidAllowed(false);
		this.getCmbTipoDocumento().setRequired(true);
		this.getCmbTipoDocumento().addItem("DNI");
		this.getCmbTipoDocumento().addItem("DU");
		this.getCmbTipoDocumento().addItem("LC");
		this.getCmbTipoDocumento().addItem("PASAPORTE");
		this.getCmbTipoDocumento().setNullSelectionAllowed(false);
		this.getCmbTipoDocumento().addValueChangeListener(this);
		return this.getCmbTipoDocumento();
	}

	private Component generarTxtNombre() {
		
		this.setTxtNombre(new TextFieldString("Nombre"));		
		return this.getTxtNombre();
	}

	private Component generarTxtApellido() {
		
		this.setTxtApellido(new TextFieldString("Apellido:"));		
		return this.getTxtApellido();
	}
	
	
	//Edicion !!!
	
	
	private Component generarTxtEmailReingreso(Solicitante solicitante) {
		
		this.setTxtEmailReingreso(new TextFieldMail("Reingrese Correo electronico:"));
		this.getTxtEmailReingreso().setInvalidAllowed(false);
		this.getTxtEmailReingreso().setRequired(true);
		this.getTxtEmailReingreso().setValue(solicitante.getCorreoElectronicoSolicitante());
		return this.getTxtEmailReingreso();
		
	}

	private Component generarCmbCaracteristica(Solicitante solicitante) {
		
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Sin cero");
		//this.getTxtCaracteristica().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristica().setRequired(true);
		this.getTxtCaracteristica().setValue("297");
		return this.getTxtCaracteristica();
	}



//	private Component generarBotonera(Solicitante solicitante) {
//		
//		HorizontalLayout botonera = new HorizontalLayout();
//		this.setBtnAceptar(new Button("Subscribirse"));
//		this.setBtnVolver(new Button("Suscribir otro impuesto"));
//		botonera.setSpacing(true);
//		botonera.addComponent(this.getBtnAceptar());
//		botonera.addComponent(this.getBtnVolver());
//		this.getBtnVolver().setEnabled(false);
//		this.getBtnVolver().setVisible(false);
//		return botonera;
//	}
	
    private Component generarTxtEmail(Solicitante solicitante) {
		
		this.setTxtEmail(new TextField("Correo electronico:"));
		this.getTxtEmail().setInvalidAllowed(false);
		this.getTxtEmail().setRequired(true);		
		this.getTxtEmail().setValue(solicitante.getCorreoElectronicoSolicitante());
		return this.getTxtEmail();
	}

	private Component generarTxtNumeroDeTelefono(Solicitante solicitante) {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de telefono:"));
		//this.getTxtNumeroDeTelefono().addValidator(new StringLengthValidator("Minimo 7 n�meros", 7, 10, false));
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefono().setValue(solicitante.getNumeroDeTelefonoSolicitante());
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono(Solicitante solicitante) {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		this.getCmbTipoTelefono().setValue(solicitante.getTipoTelefonoSolicitante());
		this.getCmbTipoTelefono().addValueChangeListener(this);
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtNumeroDeDocumento(Solicitante solicitante) {
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));	
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 n�mero, m�ximo 8", 7, 8, false));
		this.getTxtNumeroDeDocumento().setValue(solicitante.getNumeroDeDocumentoSolicitante());
		return this.getTxtNumeroDeDocumento();
	}

	private Component generarCmbTipoDocumento(Solicitante solicitante) {
	
		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
		this.getCmbTipoDocumento().setInvalidAllowed(false);
		this.getCmbTipoDocumento().setRequired(true);
		this.getCmbTipoDocumento().addItem("DNI");
		this.getCmbTipoDocumento().addItem("DU");
		this.getCmbTipoDocumento().addItem("LC");
		this.getCmbTipoDocumento().addItem("PASAPORTE");
		this.getCmbTipoDocumento().setNullSelectionAllowed(false);
		this.getCmbTipoDocumento().setValue(solicitante.getTipoDocumento());
		this.getCmbTipoDocumento().addValueChangeListener(this);
		return this.getCmbTipoDocumento();
	}

	private Component generarTxtNombre(Solicitante solicitante) {
		
		this.setTxtNombre(new TextFieldString("Nombre:"));		
		this.getTxtNombre().setValue(solicitante.getNombre());
		return this.getTxtNombre();
	}

	private Component generarTxtApellido(Solicitante solicitante) {
		
		this.setTxtApellido(new TextFieldString("Apellido:"));	
		this.getTxtApellido().setValue(solicitante.getApellido());
		return this.getTxtApellido();
	}
	
	
	public void reset(){
		
		this.getTxtApellido().setValue("");
		this.getTxtNombre().setValue("");
		this.getTxtNumeroDeDocumento().setValue("");
		this.getTxtNumeroDeTelefono().setValue("");
		this.getCmbTipoDocumento().setValue(null);
		this.getCmbTipoTelefono().setValue(null);
	}



	public TextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public TextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(TextFieldString txtApellido) {
		this.txtApellido = txtApellido;
	}

	public TextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(TextFieldString txtNombre) {
		this.txtNombre = txtNombre;
	}

	public ComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(ComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public TextField getTxtNumeroDeDocumento() {
		return txtNumeroDeDocumento;
	}

	public void setTxtNumeroDeDocumento(TextFieldNumero txtNumeroDeDocumento) {
		this.txtNumeroDeDocumento = txtNumeroDeDocumento;
	}

	public ComboBox getCmbTipoTelefono() {
		return cmbTipoTelefono;
	}

	public void setCmbTipoTelefono(ComboBox cmbTipoTelefono) {
		this.cmbTipoTelefono = cmbTipoTelefono;
	}

	public TextField getTxtNumeroDeTelefono() {
		return txtNumeroDeTelefono;
	}

	public void setTxtNumeroDeTelefono(TextField txtNumeroDeTelefono) {
		this.txtNumeroDeTelefono = txtNumeroDeTelefono;
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



	public TextField getTxtCaracteristica() {
		return txtCaracteristica;
	}



	public void setTxtCaracteristica(TextField txtCaracteristica) {
		this.txtCaracteristica = txtCaracteristica;
	}

	public TextFieldMail getTxtEmailReingreso() {
		return txtEmailReingreso;
	}

	public void setTxtEmailReingreso(TextFieldMail txtEmailReingreso) {
		this.txtEmailReingreso = txtEmailReingreso;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getCmbTipoDocumento()){
			this.getTxtNumeroDeDocumento().focus();
		}
		
	}

	public Button getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}



}
