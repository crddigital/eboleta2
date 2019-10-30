package com.workants.eboleta2.ui.formularios;

import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.IView.ILayoutABMUsuarios;
import com.workants.eboleta2.handler.ILayoutABMUsuariosHandler;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.servicio.ServicioUsuario;
import com.workants.eboleta2.tools.DemoFilterGenerator;

public class LayoutABMUsuarios extends VerticalLayout implements ILayoutABMUsuarios, ClickListener, ValueChangeListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
    //private Table tablaUsuarios;
    //private GridLayout formularioUsuarios;
    //private HorizontalLayout tablaUsuariosBotonera;
    //private HorizontalLayout formularioUsuariosBotonera;
	private FilterTable tablaUsuarios;    
    private TextField txtApellido;
    private TextField txtNombre;
    private TextField txtNombreDeUsuario;
    private PasswordField txtContrasenia;
    private ComboBox cmbPerfil;
    private ComboBox cmbTipoDocumento;
    private ComboBox cmbGenero; 
    private TextField txtNumeroDeDocumento;
    private TextField txtNumeroLegajo;
    private TextField txtNumeroInterno;
    
    private CheckBox chbModificarContrasenia;
    
    private Button btnGuardar;
    private Button btnEliminar;
    private Button btnLimpiar;
    
    private ILayoutABMUsuariosHandler handler;
    
    
    private FieldGroup fieldGroup = new FieldGroup();

	public LayoutABMUsuarios(){
		
		//setMargin(true);
		setSpacing(true);		
		setSizeFull();
		addComponent(generarTabla());	
		addComponent(generarFormulario());
	}
	
	

	private Component generarFormulario() {
		
		VerticalLayout vertical = new VerticalLayout();
		vertical.setSpacing(true);
		vertical.setSizeFull();
		//this.setFormularioUsuarios(new GridLayout(4,3));
		
		GridLayout grid = new GridLayout(3, 3);
		grid.setSizeFull();
		grid.setSpacing(true);
	//	grid.setMargin(true);
		
		//TextField txtApellido = new TextField("Apellido:");
		//TextField txtNombre = new TextField("Nombre:");		
	    //TextField txtNombreDeUsuario = new TextField("Nombre de Usuario:");	
		//PasswordField txtContrasenia = new PasswordField("Contrase�a:");
		this.setTxtApellido(new TextField("Apellido:"));
		this.getTxtApellido().setNullSettingAllowed(false);
		this.getTxtApellido().setNullRepresentation("");
		this.getTxtApellido().setRequired(true);
		
		
		this.setTxtNombre(new TextField("Nombre:"));
		this.getTxtNombre().setNullSettingAllowed(false);
		this.getTxtNombre().setNullRepresentation("");
		this.getTxtNombre().setRequired(true);
		
		this.setTxtContrasenia(new PasswordField("Contraseña:"));
		this.getTxtContrasenia().setNullSettingAllowed(false);
		this.getTxtContrasenia().setNullRepresentation("");
		this.getTxtContrasenia().setRequired(true);
		
		this.setTxtNombreDeUsuario(new TextField("Nombre de usuario:"));
		this.getTxtNombreDeUsuario().setNullRepresentation("");
		this.getTxtNombreDeUsuario().setNullSettingAllowed(false);
		this.getTxtNombreDeUsuario().setRequired(true);
		this.getTxtNombreDeUsuario().setImmediate(true);
		
		this.setTxtNumeroInterno(new TextField("Nº de Interno:"));
		this.getTxtNumeroInterno().setNullSettingAllowed(false);
		this.getTxtNumeroInterno().setNullRepresentation("");
		this.getTxtNumeroInterno().setRequired(true);
		
		this.setTxtNumeroLegajo(new TextField("Nº de Legajo:"));
		this.getTxtNumeroLegajo().setNullSettingAllowed(false);
		this.getTxtNumeroLegajo().setNullRepresentation("");
		this.getTxtNumeroLegajo().setRequired(true);
		
		
		this.setCmbTipoDocumento(new ComboBox("Tipo Documento:"));
		this.getCmbTipoDocumento().setNullSelectionAllowed(false);	
		this.getCmbTipoDocumento().addItem("DNI");
		this.getCmbTipoDocumento().addItem("DU");
		this.getCmbTipoDocumento().addItem("PASAPORTE");
		this.getCmbTipoDocumento().setRequired(true);
		
		this.setTxtNumeroDeDocumento(new TextField("Nº de Doc:"));
		this.getTxtNumeroDeDocumento().setNullRepresentation("");
		this.getTxtNumeroDeDocumento().setNullSettingAllowed(false);
		this.getTxtNumeroDeDocumento().setRequired(true);
		
		this.setCmbPerfil(new ComboBox("Perfil:"));
		this.getCmbPerfil().setNullSelectionAllowed(false);
		this.getCmbPerfil().addItem("ADMINISTRADOR");
		this.getCmbPerfil().addItem("ADMINISTRATIVO");
		this.getCmbPerfil().addItem("INMOBILIARIO");
		this.getCmbPerfil().addItem("AUTOMOTORES");
		this.getCmbPerfil().setRequired(true);
		
		/*ComboBox cmbPerfil = new ComboBox("Perfil:");
		cmbPerfil.setNullSelectionAllowed(false);
		cmbPerfil.addItem("ADMINISTRADOR");
		cmbPerfil.addItem("ADMINISTRATIVO");
		cmbPerfil.addItem("INMOBILIARIO");
		cmbPerfil.addItem("AUTOMOTORES");*/
		
		
		/*ComboBox cmbTipoDocumento = new ComboBox("Tipo Documento:");
		cmbTipoDocumento.setNullSelectionAllowed(false);
		cmbTipoDocumento.addItem("DNI");
		cmbTipoDocumento.addItem("DU");
		cmbTipoDocumento.addItem("PASAPORTE");*/
		
		this.setCmbGenero(new ComboBox("Genero:"));
		this.getCmbGenero().setNullSelectionAllowed(false);	
		this.getCmbGenero().addItem("1");
		this.getCmbGenero().addItem("0");
		this.getCmbGenero().setItemCaption("1", "MASCULINO");
		this.getCmbGenero().setItemCaption("0", "FEMENINO");
		
		this.setChbModificarContrasenia(new CheckBox("Modificar contraseña?"));
		this.getChbModificarContrasenia().setEnabled(false);
		this.getChbModificarContrasenia().setImmediate(true);
		this.getChbModificarContrasenia().addValueChangeListener(this);
		
		
		this.getTxtContrasenia().setEnabled(true);
		
		
	//	this.getCmbGenero().setitem
		
		/*ComboBox cmbGenero = new ComboBox("Genero:");
		cmbGenero.setNullSelectionAllowed(false);
		cmbGenero.addItem("FEMENINO");
		cmbGenero.addItem("MASCULINO");*/
				
		//TextField txtNumeroDeDocumento = new TextField("Nº de Doc:");
				
		this.setBtnGuardar(new Button("Guardar",this));
		this.setBtnEliminar(new Button("Eliminar",this));
		this.setBtnLimpiar(new Button("Limpiar",this));
		
		HorizontalLayout botonera = new HorizontalLayout();
		botonera.setSpacing(true);
		botonera.addComponent(this.getBtnGuardar());
		botonera.addComponent(this.getBtnEliminar());
		botonera.addComponent(this.getBtnLimpiar());
		
		
		
		/*this.setFormularioUsuariosBotonera(new HorizontalLayout());
		this.getFormularioUsuariosBotonera().setSpacing(true);
		this.getFormularioUsuariosBotonera().addComponent(new Button("Guardar"));
		this.getFormularioUsuariosBotonera().addComponent(new Button("Descartar"));
		*/
		fieldGroup.bind(txtApellido, "apellido");
		fieldGroup.bind(txtNombre, "nombre");
		fieldGroup.bind(txtNumeroDeDocumento, "numeroDeDocumento");
		fieldGroup.bind(cmbPerfil, "perfil.nombreDePerfil");
		fieldGroup.bind(cmbTipoDocumento, "tipoDocumento");
		fieldGroup.bind(this.getCmbGenero(), "genero");
		fieldGroup.bind(txtNumeroInterno,"numeroInterno");
		fieldGroup.bind(txtNumeroLegajo,"numeroLegajo");
		fieldGroup.bind(txtNombreDeUsuario,"nombreDeUsuario");
		
		//fieldGroup.bi�
//		fieldGroup.bind(txtApellido, "apellido");
//		fieldGroup.bind(txtApellido, "apellido");
		
		
		/*
		this.getFormularioUsuarios().addComponent(txtApellido);
		this.getFormularioUsuarios().addComponent(txtNombre);
		this.getFormularioUsuarios().addComponent(cmbTipoDocumento);
		this.getFormularioUsuarios().addComponent(txtNumeroDeDocumento);
		this.getFormularioUsuarios().addComponent(cmbPerfil);
		this.getFormularioUsuarios().addComponent(cmbGenero);
		this.getFormularioUsuarios().addComponent(txtContrasenia);
		this.getFormularioUsuarios().setSpacing(true);
		*/
		
		grid.addComponent(this.getTxtApellido());
		grid.addComponent(this.getTxtNombre());
		grid.addComponent(this.getCmbTipoDocumento());
		grid.addComponent(this.getTxtNumeroDeDocumento());
		grid.addComponent(this.getCmbPerfil());
		//grid.addComponent(this.getCmbGenero());
		grid.addComponent(this.getTxtNumeroInterno());
		grid.addComponent(this.getTxtNumeroLegajo());
		grid.addComponent(this.getTxtNombreDeUsuario());
		grid.addComponent(this.getTxtContrasenia());
		grid.addComponent(this.getChbModificarContrasenia());
		
		
		
		
		
		//vertical.addComponent(this.getFormularioUsuarios());
		//vertical.addComponent(this.getFormularioUsuariosBotonera());
		
		
		vertical.addComponent(grid);
		vertical.addComponent(this.getChbModificarContrasenia());
		vertical.addComponent(botonera);
		
		return vertical;
		
	}



	private Component generarTabla() {
		
		//VerticalLayout vertical = new VerticalLayout();
		//vertical.setSpacing(true);
		//this.setTablaUsuarios(new Table("Usuarios", ServicioUsuario.getIntance().getUsuarios()));
		this.setTablaUsuarios(new FilterTable("Usuarios"));
		this.getTablaUsuarios().setFilterGenerator(new DemoFilterGenerator());
		this.getTablaUsuarios().setContainerDataSource(ServicioUsuario.getIntance().getUsuarios());
		this.getTablaUsuarios().setVisibleColumns(new Object[]{"apellido","nombre","tipoDocumento","numeroDeDocumento","perfil.nombreDePerfil" });
		this.getTablaUsuarios().setColumnHeaders(new String[]{"Apellido", "Nombre", "Tipo Doc","Nº Doc","Perfil"});
		this.getTablaUsuarios().sort(new Object[]{ "apellido", "nombre" },new boolean[]{true,true});
		this.getTablaUsuarios().setPageLength(10);
		this.getTablaUsuarios().setSizeFull();
		this.getTablaUsuarios().setSelectable(true);
		this.getTablaUsuarios().setImmediate(true);
		this.getTablaUsuarios().setFilterBarVisible(true);
		this.getTablaUsuarios().addValueChangeListener(this);			
		return this.getTablaUsuarios();
	}



	protected void editarUsuario(Usuario usuario) {
		
		if (usuario == null) {
			usuario = new Usuario();
	    }
		BeanItem<Usuario> item = new BeanItem<Usuario>(usuario);
		item.addNestedProperty("perfil.nombreDePerfil");	  
		fieldGroup.setItemDataSource(item);
	}


	public FilterTable getTablaUsuarios() {
		return tablaUsuarios;
	}

	public void setTablaUsuarios(FilterTable tablaUsuarios) {
		this.tablaUsuarios = tablaUsuarios;
	}

	public TextField getTxtApellido() {
		return txtApellido;
	}



	public void setTxtApellido(TextField txtApellido) {
		this.txtApellido = txtApellido;
	}



	public TextField getTxtNombre() {
		return txtNombre;
	}



	public void setTxtNombre(TextField txtNombre) {
		this.txtNombre = txtNombre;
	}



	public PasswordField getTxtContrasenia() {
		return txtContrasenia;
	}



	public void setTxtContrasenia(PasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}



	public ComboBox getCmbPerfil() {
		return cmbPerfil;
	}



	public void setCmbPerfil(ComboBox cmbPerfil) {
		this.cmbPerfil = cmbPerfil;
	}



	public ComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}



	public void setCmbTipoDocumento(ComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}



	public ComboBox getCmbGenero() {
		return cmbGenero;
	}



	public void setCmbGenero(ComboBox cmbGenero) {
		this.cmbGenero = cmbGenero;
	}



	public TextField getTxtNumeroDeDocumento() {
		return txtNumeroDeDocumento;
	}



	public void setTxtNumeroDeDocumento(TextField txtNumeroDeDocumento) {
		this.txtNumeroDeDocumento = txtNumeroDeDocumento;
	}



	public FieldGroup getFieldGroup() {
		return fieldGroup;
	}



	public void setFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroup = fieldGroup;
	}



	public Button getBtnGuardar() {
		return btnGuardar;
	}



	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}



	public Button getBtnEliminar() {
		return btnEliminar;
	}



	public void setBtnEliminar(Button btnEliminar) {
		this.btnEliminar = btnEliminar;
	}



	public Button getBtnLimpiar() {
		return btnLimpiar;
	}



	public void setBtnLimpiar(Button btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}



	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setHandler(ILayoutABMUsuariosHandler handler) {
		
		this.handler = handler;
		
	}



	@Override
	public ILayoutABMUsuariosHandler getHandler() {
		
		return this.handler;
	}



	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource() == this.getBtnGuardar()){
			
			if(!this.getTxtApellido().isValid()||
			   !this.getTxtNombre().isValid()||
			   !this.getTxtNumeroDeDocumento().isValid() ||
			   !this.getCmbGenero().isValid()||
			   !this.getCmbPerfil().isValid()||
			   !this.getTxtNumeroInterno().isValid()||
			   !this.getTxtNumeroLegajo().isValid()||
			   !this.getTxtContrasenia().isValid()) {
				Notification.show("Error", "Campos inválidos y/o incompletos", Type.ERROR_MESSAGE);				
		}else{
			
			 ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea guardar los datos del usuario?", "SI", "NO", new ConfirmDialog.Listener() {			
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClose(ConfirmDialog rta) {
				
					if(rta.isConfirmed()){
						if(getTablaUsuarios().getValue() != null){
							handler.modificarUsuario();
						}else{
							handler.guardarUsuario();
						}
						
					}
				}
				});	
			
		}
		
	 }
     if(event.getSource() == this.getBtnLimpiar()){
			
    	 	handler.limpiar();
		}
     if(event.getSource() == this.getBtnEliminar()){
    	 
    	 if(this.getTablaUsuarios().getValue() != null){
    	 ConfirmDialog.show(UI.getCurrent(), "Atención", "Desea eliminar al usuario?", "SI", "NO", new ConfirmDialog.Listener() {			
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClose(ConfirmDialog rta) {
				
					if(rta.isConfirmed()){
						if(getTablaUsuarios().getValue() != null){
						
							handler.eliminarUsuario();
						}
						
					}
				}
				});	
        }
     }
	}



	public TextField getTxtNumeroLegajo() {
		return txtNumeroLegajo;
	}



	public void setTxtNumeroLegajo(TextField txtNumeroLegajo) {
		this.txtNumeroLegajo = txtNumeroLegajo;
	}



	public TextField getTxtNumeroInterno() {
		return txtNumeroInterno;
	}



	public void setTxtNumeroInterno(TextField txtNumeroInterno) {
		this.txtNumeroInterno = txtNumeroInterno;
	}



	public CheckBox getChbModificarContrasenia() {
		return chbModificarContrasenia;
	}



	public void setChbModificarContrasenia(CheckBox chbModificarContrasenia) {
		this.chbModificarContrasenia = chbModificarContrasenia;
	}



	@Override
	public void valueChange(ValueChangeEvent event) {
		
		if(event.getProperty() == this.getChbModificarContrasenia()){
			
			if(this.getChbModificarContrasenia().getValue()){
				this.getTxtContrasenia().setEnabled(true);
				this.getTxtContrasenia().setRequired(true);
				this.getTxtContrasenia().focus();
			}else {
				this.getTxtContrasenia().setEnabled(false);		
				this.getTxtContrasenia().setRequired(false);
			}
			
	}
		if(event.getProperty() == this.getTablaUsuarios()){
			
			editarUsuario((Usuario)getTablaUsuarios().getValue());
			this.getChbModificarContrasenia().setEnabled(true);
			this.getTxtContrasenia().setEnabled(false);
			this.getTxtContrasenia().setRequired(false);
			this.getTxtNombreDeUsuario().setEnabled(false);
		}
	}

	public TextField getTxtNombreDeUsuario() {
		return txtNombreDeUsuario;
	}



	public void setTxtNombreDeUsuario(TextField txtNombreDeUsuario) {
		this.txtNombreDeUsuario = txtNombreDeUsuario;
	}



	public void guardarUsuarioDniRepetido() {
		
		Notification.show("Atención", "Tipo y numero de documento ya se encuentran registrados", Type.ERROR_MESSAGE);
		
	}



	public void guardarUsuarioOK() {
		
		Notification notif = new Notification("Atención","Solicitud procesada exitosamente", Type.ASSISTIVE_NOTIFICATION);
		notif.setDelayMsec(2000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
	}



	public void guardarUsuarioUserRepeat() {
		
		Notification.show("Atención", "Nombre de Usuario existente", Type.ERROR_MESSAGE);
		
	}



	public void guardarUsuarioOtroError() {
		
		Notification.show("Atención", "Imposible procesar solicitud", Type.ERROR_MESSAGE);
		
	}



	public void modificarUsuarioOK() {
	
		Notification notif = new Notification("Atención","Solicitud procesada exitosamente", Type.ASSISTIVE_NOTIFICATION);
		notif.setDelayMsec(2000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
	}



	public void modificarUsuarioError() {
		 
		Notification.show("Atención", "Imposible procesar solicitud", Type.ERROR_MESSAGE);
	}



	public void eliminarUsuarioOK() {
		
		Notification notif = new Notification("Atención","Solicitud procesada exitosamente", Type.ASSISTIVE_NOTIFICATION);
		notif.setDelayMsec(2000);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.show(Page.getCurrent());
		
		
	}



	public void eliminarUsuarioError() {
		
		Notification.show("Atención", "Imposible procesar solicitud", Type.ERROR_MESSAGE);
		
	}	
}



