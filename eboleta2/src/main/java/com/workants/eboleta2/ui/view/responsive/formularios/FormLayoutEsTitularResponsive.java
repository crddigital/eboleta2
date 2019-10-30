package com.workants.eboleta2.ui.view.responsive.formularios;





import com.vaadin.data.Validator;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.componentes.TextFieldMail;
import com.workants.eboleta2.componentes.TextFieldNumero;
import com.workants.eboleta2.componentes.TextFieldString;
import com.workants.eboleta2.model.Titular;

public class FormLayoutEsTitularResponsive extends VerticalLayout implements ValueChangeListener{

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
	private ComboBox cmbTipoTelefonoAlternativo;
	private TextField txtCaracteristicaAlternativo;
	private TextField txtNumeroDeTelefonoAlternativo;
	private TextFieldMail txtEmail;
	private TextFieldMail txtEmailReingreso;
	
	private Button btnAceptar;	
	private Button btnVolver;
	
	
	
	
	

	public FormLayoutEsTitularResponsive(){
		
		
		setCaption("Contribuyente Titular");
		//setMargin(true);
		setSpacing(true);
		addComponent(generarTxtApellido());
		addComponent(generarTxtNombre());
		addComponent(generarCmbTipoDocumento());
		addComponent(generarTxtNumeroDeDocumento());
		addComponent(generarCmbTipoDeTelefono());
		addComponent(generarTxtCaracteristica());
		addComponent(generarTxtNumeroDeTelefono());
		addComponent(generarCmbTipoDeTelefonoAleternativo());
		addComponent(generarTxtCaracteristicaAlternativo());
		addComponent(generarTxtNumeroDeTelefonoAlternativo());
		addComponent(generarTxtEmail());	
		addComponent(generarTxtEmailReingreso());
		addComponent(generarBotonera());
		
		this.getTxtApellido().focus();		
	}
		
	public FormLayoutEsTitularResponsive(Titular titular) {
		
		//setCaption("Contribuyente Titular - Editar");
		//setMargin(true);
		//setSpacing(true);
		//setCaptionAsHtml(true);
		//setCaption("<big><strong>Titular");
		//setSizeFull();
		//setWidth("600px");
		//setHeight("400px");		
	
		GridLayout grid = new GridLayout();		
		grid.setSpacing(true);
		//grid.setMargin(true);
		grid.setColumns(3);
		//grid.setSizeFull();
		grid.addComponent(generarTxtApellido(titular));
		grid.addComponent(generarTxtNombre(titular));
		grid.addComponent(generarCmbTipoDocumento(titular));
		grid.addComponent(generarTxtNumeroDeDocumento(titular));
		grid.addComponent(generarCmbTipoDeTelefono(titular));
		grid.addComponent(generarTxtCaracteristica(titular));
		grid.addComponent(generarTxtNumeroDeTelefono(titular));
		grid.addComponent(generarCmbTipoDeTelefonoAleternativo(titular));
		grid.addComponent(generarTxtCaracteristicaAlternativo(titular));
		grid.addComponent(generarTxtNumeroDeTelefonoAlternativo(titular));
		grid.addComponent(generarTxtEmail(titular));	
		grid.addComponent(generarTxtEmailReingreso(titular));
		//grid.addComponent(generarBotoneraEdicion());
		
		addComponent(grid);		
		//addComponent(generarBotoneraEdicion());
	
		this.getTxtApellido().focus();
	}


	private Component generarTxtEmailReingreso() {
		
		this.setTxtEmailReingreso(new TextFieldMail("Reingresar correo electronico:"));
		return this.getTxtEmailReingreso();
	}


	private Component generarTxtCaracteristicaAlternativo() {
		
		this.setTxtCaracteristicaAlternativo(new TextField("Caracteristica alternativo:"));
		this.getTxtCaracteristicaAlternativo().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristicaAlternativo().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().setEnabled(false);
		return this.getTxtCaracteristicaAlternativo();
	}


	
	private Component generarTxtNumeroDeTelefonoAlternativo() {
		
		this.setTxtNumeroDeTelefonoAlternativo(new TextField("Número de teléfono alternativo:"));		
		this.getTxtNumeroDeTelefonoAlternativo().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefonoAlternativo().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
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
		//this.getTxtEmail().addValidator(new EmailValidator("Direcci�n mal compuesta"));
		return this.getTxtEmail();
	}

	

	private Component generarTxtCaracteristica() {
		
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		return this.getTxtCaracteristica();		
	}
	
	private Component generarTxtNumeroDeTelefono() {
		
		this.setTxtNumeroDeTelefono(new TextField("Número de teléfono:"));		
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
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
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtNumeroDeDocumento() {
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("Número de documento:"));
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 n�mero, m�ximo 8", 7, 8,false));
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
		
		this.setTxtNombre(new TextFieldString("Nombre:"));	
		return this.getTxtNombre();
	}

	private Component generarTxtApellido() {
			
		this.setTxtApellido(new TextFieldString("Apellido:"));
		this.getTxtApellido().focus();
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
	
	
	//Edicion de los datos del titular
	
	private Component generarTxtEmailReingreso(Titular titular) {
		
		this.setTxtEmailReingreso(new TextFieldMail("Reingresar correo electronico:"));
		this.getTxtEmailReingreso().setValue(titular.getCorreoElectronicoTitular());
		return this.getTxtEmailReingreso();
	}


	private Component generarTxtCaracteristicaAlternativo(Titular titular) {
		
		this.setTxtCaracteristicaAlternativo(new TextField("Caracteristica alternativo:"));
		this.getTxtCaracteristicaAlternativo().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristicaAlternativo().addValidator(caracteristicaValidator);
		this.getTxtCaracteristicaAlternativo().setEnabled(false);
		this.getTxtCaracteristicaAlternativo().setValue("297");
		return this.getTxtCaracteristicaAlternativo();
	}


	
	private Component generarTxtNumeroDeTelefonoAlternativo(Titular titular) {
		
		this.setTxtNumeroDeTelefonoAlternativo(new TextField("N�mero de tel�fono alternativo:"));		
		this.getTxtNumeroDeTelefonoAlternativo().setInputPrompt("Cel sin 0 y 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefonoAlternativo().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
		this.getTxtNumeroDeTelefonoAlternativo().setValue(titular.getNumeroDeTelefonoAlternativoTitular());
		return this.getTxtNumeroDeTelefonoAlternativo();
	}




	private Component generarCmbTipoDeTelefonoAleternativo(Titular titular) {
		
		this.setCmbTipoTelefonoAlternativo(new ComboBox("Tipo Telefono alternativo:"));
		this.getCmbTipoTelefonoAlternativo().setInvalidAllowed(false);
		this.getCmbTipoTelefonoAlternativo().addItem("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().addItem("FIJO");
		this.getCmbTipoTelefonoAlternativo().addItem("CELULAR");
		this.getCmbTipoTelefonoAlternativo().setValue("NO POSEE");
		this.getCmbTipoTelefonoAlternativo().setNullSelectionAllowed(false);
		this.getCmbTipoTelefonoAlternativo().setValue(titular.getTipoTelefonoAlternativoTitular().toString());
		this.getCmbTipoTelefonoAlternativo().addValueChangeListener(this);
		return this.getCmbTipoTelefonoAlternativo();
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

	private Component generarTxtEmail(Titular titular) {
		
		this.setTxtEmail(new TextFieldMail("Correo electronico:"));		
		//this.getTxtEmail().addValidator(new EmailValidator("Direcci�n mal compuesta"));
		this.getTxtEmail().setValue(titular.getCorreoElectronicoTitular());
		return this.getTxtEmail();
	}

	

	private Component generarTxtCaracteristica(Titular titular) {
		
		this.setTxtCaracteristica(new TextField("Caracteristica:"));
		this.getTxtCaracteristica().setInputPrompt("Prefijo sin el cero");		
		Validator caracteristicaValidator = new RegexpValidator("^[1-9][0-9]*$", "Ingrese prefijo sin el cero"); //no permite el ingreso del 0
		this.getTxtCaracteristica().addValidator(caracteristicaValidator);
		this.getTxtCaracteristica().setValue("297");
		return this.getTxtCaracteristica();		
	}
	
	private Component generarTxtNumeroDeTelefono(Titular titular) {
		
		this.setTxtNumeroDeTelefono(new TextField("N�mero de tel�fono:"));		
		this.getTxtNumeroDeTelefono().setInputPrompt("Cel sin 15");
		Validator telefonoValidator = new RegexpValidator("^[2-9][0-9]*$", "Ingrese sin el 15"); //no permite el ingreso del 15
		this.getTxtNumeroDeTelefono().addValidator(telefonoValidator);
		this.getTxtNumeroDeTelefono().setValue(titular.getNumeroDeTelefonoTitular());
		return this.getTxtNumeroDeTelefono();
	}

	private Component generarCmbTipoDeTelefono(Titular titular) {
		
		this.setCmbTipoTelefono(new ComboBox("Tipo Telefono:"));
		this.getCmbTipoTelefono().setInvalidAllowed(false);
		this.getCmbTipoTelefono().setRequired(true);
		this.getCmbTipoTelefono().addItem("NO POSEE");
		this.getCmbTipoTelefono().addItem("FIJO");
		this.getCmbTipoTelefono().addItem("CELULAR");		
		this.getCmbTipoTelefono().setNullSelectionAllowed(false);
		this.getCmbTipoTelefono().setValue(titular.getTipoTelefonoTitular().toString());
		this.getCmbTipoTelefono().addValueChangeListener(this);
		return this.getCmbTipoTelefono();
	}

	private Component generarTxtNumeroDeDocumento(Titular titular) {
		
		this.setTxtNumeroDeDocumento(new TextFieldNumero("N�mero de documento:"));
		this.getTxtNumeroDeDocumento().setValue(titular.getNumeroDeDocumentoTitular());
		//this.getTxtNumeroDeDocumento().addValidator(new StringLengthValidator("Minimo 6 n�mero, m�ximo 8", 7, 8,false));
		return this.getTxtNumeroDeDocumento();
	}

	private Component generarCmbTipoDocumento(Titular titular) {
	
		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
		this.getCmbTipoDocumento().setInvalidAllowed(false);
		this.getCmbTipoDocumento().setRequired(true);
		this.getCmbTipoDocumento().addItem("DNI");
		this.getCmbTipoDocumento().addItem("DU");
		this.getCmbTipoDocumento().addItem("LC");
		this.getCmbTipoDocumento().addItem("PASAPORTE");
		this.getCmbTipoDocumento().setNullSelectionAllowed(false);
		this.getCmbTipoDocumento().setValue(titular.getTipoDocumentoTitular().toString());
		this.getCmbTipoDocumento().addValueChangeListener(this);
		return this.getCmbTipoDocumento();
	}

	private Component generarTxtNombre(Titular titular) {
		
		this.setTxtNombre(new TextFieldString("Nombre:"));	
		this.getTxtNombre().setValue(titular.getNombre());
		return this.getTxtNombre();
	}

	private Component generarTxtApellido(Titular titular) {
			
		this.setTxtApellido(new TextFieldString("Apellido:"));
		this.getTxtApellido().setValue(titular.getApellido());
		this.getTxtApellido().focus();
		return this.getTxtApellido();
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

	public TextFieldNumero getTxtNumeroDeDocumento() {
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

	public TextFieldMail getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextFieldMail txtEmail) {
		this.txtEmail = txtEmail;
	}
	public Button getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(Button btnAceptar) {
		this.btnAceptar = btnAceptar;
	}




	public ComboBox getCmbTipoTelefonoAlternativo() {
		return cmbTipoTelefonoAlternativo;
	}




	public void setCmbTipoTelefonoAlternativo(ComboBox cmbTipoTelefonoAlternativo) {
		this.cmbTipoTelefonoAlternativo = cmbTipoTelefonoAlternativo;
	}




	public TextField getTxtNumeroDeTelefonoAlternativo() {
		return txtNumeroDeTelefonoAlternativo;
	}




	public void setTxtNumeroDeTelefonoAlternativo(
			TextField txtNumeroDeTelefonoAlternativo) {
		this.txtNumeroDeTelefonoAlternativo = txtNumeroDeTelefonoAlternativo;
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


	public TextField getTxtCaracteristicaAlternativo() {
		return txtCaracteristicaAlternativo;
	}


	public void setTxtCaracteristicaAlternativo(
			TextField txtCaracteristicaAlternativo) {
		this.txtCaracteristicaAlternativo = txtCaracteristicaAlternativo;
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
	
		if(event.getProperty() == this.getCmbTipoDocumento()){
			
			this.getTxtNumeroDeDocumento().focus();
			
		}	
		
		if(event.getProperty() == this.getCmbTipoTelefonoAlternativo()){
			
			if(this.getCmbTipoTelefonoAlternativo().getValue() != null){
			
			if(!this.getCmbTipoTelefonoAlternativo().getValue().toString().equalsIgnoreCase("no posee")){ //posee fijo o celular, por ende habilitamos
				this.getTxtCaracteristicaAlternativo().setEnabled(true);
				this.getTxtCaracteristicaAlternativo().focus();
				//this.getTxtCaracteristicaAlternativo().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
				this.getTxtCaracteristicaAlternativo().setRequired(true);
				
				this.getTxtNumeroDeTelefonoAlternativo().setEnabled(true);
				this.getTxtNumeroDeTelefonoAlternativo().setRequired(true);
				//this.getTxtNumeroDeTelefonoAlternativo().addValidator(new StringLengthValidator("Minimo 7 numeros", 7, 10, false));
				
			}else{
				this.getTxtCaracteristicaAlternativo().setEnabled(false);
				this.getTxtCaracteristicaAlternativo().removeValidator(null);
				this.getTxtCaracteristicaAlternativo().setRequired(false);
				
				this.getTxtNumeroDeTelefonoAlternativo().setEnabled(false);
				this.getTxtNumeroDeTelefonoAlternativo().removeValidator(null);
				this.getTxtNumeroDeTelefonoAlternativo().setRequired(false);
				
				this.getTxtEmail().focus();
			}
			}
		}
		
		if(event.getProperty() == this.getCmbTipoTelefono()){
			
			if(this.getCmbTipoTelefono().getValue() != null){
			if(!this.getCmbTipoTelefono().getValue().toString().equalsIgnoreCase("no posee")){ //posee fijo o celular, por ende habilitamos
				this.getTxtCaracteristica().setEnabled(true);
				this.getTxtCaracteristica().focus();				
				//this.getTxtCaracteristica().addValidator(new StringLengthValidator("Minimo 2 digitos, m�ximo 4 digitos", 2, 4, false));
				this.getTxtCaracteristica().setRequired(true);
				
				//this.getTxtNumeroDeTelefono().addValidator(new StringLengthValidator("Minimo 7 numeros", , 10, false));
				this.getTxtNumeroDeTelefono().setRequired(true);
				this.getTxtNumeroDeTelefono().setEnabled(true);
				
			}else{
				this.getTxtCaracteristica().setEnabled(false);
				this.getTxtCaracteristica().removeValidator(null);
				this.getTxtCaracteristica().setRequired(false);
				
				this.getTxtNumeroDeTelefono().setEnabled(false);
				this.getTxtNumeroDeTelefono().removeValidator(null);
				this.getTxtNumeroDeTelefono().setRequired(false);
				
				
				this.getCmbTipoTelefonoAlternativo().focus();
			}
		  }
		}
	}


	public TextFieldMail getTxtEmailReingreso() {
		return txtEmailReingreso;
	}


	public void setTxtEmailReingreso(TextFieldMail txtEmailReingreso) {
		this.txtEmailReingreso = txtEmailReingreso;
	}


//	public Button getBtnEditar() {
//		return btnEditar;
//	}
//
//
//	public void setBtnEditar(Button btnEditar) {
//		this.btnEditar = btnEditar;
//	}




}
