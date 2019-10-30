package com.workants.eboleta2.ui.view.ViewAdministrativo;



import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Table.ColumnGenerator;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.servicio.ServicioSolicitudes;

public class ViewAdministrativoCuerpo extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnJuridico;
	private Button btnFisico;
	private Table tablaSolicitudes;
	private Table tablaJuridica;
	private Label lblCantidadPresolicitudesFisicas;
	private Label lblCantidadPresolicitudesJuridicas;
	
	private Button btnDetalleTitular;
	private Button btnAprobarPresolicitud;
	private Button btnActulizarPresolicitudes;
	private Button btnBajaPresolicitud;	
	private Button btnBuscarSuscriptor;
	private Button btnEditarSuscriptor;
	private static int CANTIDAD_FISICAS;
	private static int CANTIDAD_JURIDICAS;
	
	


	public ViewAdministrativoCuerpo(){
	
		setSpacing(true);
		//setMargin(new MarginInfo(false, true, false, true));
		
		addComponent(generarSeleccionContribuyente());
		addComponent(generarTablaFisico());
		
		generarTablaJuridico();
		generarLabelCantidadJuridica();
		addComponent(generarLabelCantidad());
		addComponent(generarBotonera());
		
		
		
	}																																			
	

	private Component generarSeleccionContribuyente() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		this.setBtnJuridico(new Button("Juridico"));
		this.setBtnFisico(new Button("Fisico"));
		lay.addComponent(this.getBtnFisico());
		lay.addComponent(this.getBtnJuridico());		
		return lay;
	}

	private Component generarLabelCantidad() {
		
		CANTIDAD_FISICAS = this.getTablaSolicitudes().size();
		this.setLblCantidadPresolicitudes(new Label("Cantidad de Presolicitudes a procesar Fisicas: " + String.valueOf(CANTIDAD_FISICAS)));
		return this.getLblCantidadPresolicitudes();
	}
	
	private Component generarLabelCantidadJuridica() {
		
		CANTIDAD_JURIDICAS = this.getTablaJuridica().size();
		this.setLblCantidadPresolicitudesJuridicas(new Label("Cantidad de Presolicitudes a procesar Juridicas : " + String.valueOf(CANTIDAD_JURIDICAS)));
		return this.getLblCantidadPresolicitudesJuridicas();
	}

	private Component generarBotonera() {
		
		HorizontalLayout botonera = new HorizontalLayout();
		botonera.setSpacing(true);
		this.setBtnDetalleTitular(new Button("Detalle Solicitud"));
		this.setBtnAprobarPresolicitud(new Button("Aprobar Presolicitud"));
		this.setBtnActulizarPresolicitudes(new Button("Actualizar Presolicitudes"));
		this.setBtnBajaPresolicitud(new Button("Cancelar Presolicutud"));
		this.setBtnBuscarSuscriptor(new Button("Buscar Suscriptor"));
		this.setBtnEditarSuscriptor(new Button("Editar Suscriptor"));
		//botonera.addComponent(this.getBtnDetalleTitular());
		botonera.addComponent(this.getBtnAprobarPresolicitud());
		botonera.addComponent(this.getBtnActulizarPresolicitudes());
		botonera.addComponent(this.getBtnBajaPresolicitud());
		botonera.addComponent(this.getBtnBuscarSuscriptor());
		botonera.addComponent(this.getBtnEditarSuscriptor());
		
		return botonera;
	}
	
	private Component generarTablaJuridico() {

		this.setTablaJuridica(new Table("Personas Juridicas"));
		this.getTablaJuridica().setSizeFull();
		this.getTablaJuridica().setSelectable(true);
		this.getTablaJuridica().setPageLength(10);
		//this.getTablaJuridica().setFilterGenerator(new DemoFilterGenerator());
		this.getTablaJuridica().setContainerDataSource(ServicioSolicitudes.getInstance().getPreSolicitudesBeanItemContainerJuridicas());
		this.getTablaJuridica().setVisibleColumns(new Object[]{"juridica.razonSocial","juridica.cuit",
																"juridica.correoElectronico","tipoImpuesto","codigoPagoElectronico"});
		this.getTablaJuridica().setColumnHeaders(new String[]{"Razon Social","CUIT","Email","Impuesto","CPE"});	
		return this.getTablaJuridica();
	}


	@SuppressWarnings("serial")
	private Component generarTablaFisico() {
		
		ColumnGenerator availabilityGenerator = new ColumnGenerator() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
			Property property = source.getItem(itemId).getItemProperty(columnId);
			if (property != null) {
			//Availability availability = (Availability) property.getValue();
			boolean availability = (Boolean) property.getValue();
			//Solicitante availability = (Solicitante) property.getValue();
			
		
			
			String color = "";
			if (availability) {
				color = "#2dd085";
			} else //if (availability != 0) {
				color = "#e70025";
//			} else if (!availability != 0) {
//				color = "#2dd085";
//			}
			String iconCode = "<span class=\"v-icon\" style=\"font-family: "+ FontAwesome.CIRCLE.getFontFamily()+ ";color:"	+ color	+ "\">&#x"+ Integer.toHexString(FontAwesome.CLOUD.getCodepoint())
			+ ";</span>";
			
			//property.getValue()
			
			Label label = new Label(iconCode + " " + ((property.getValue().equals(true) ? "Aprobado":"Pendiente")),ContentMode.HTML);
			label.setSizeUndefined();
			return label;
			}
			return null;
			}
			};
			
			ColumnGenerator availabilityGenerator2 = new ColumnGenerator() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, Object itemId, Object columnId) {
				@SuppressWarnings("rawtypes")
				Property property = source.getItem(itemId).getItemProperty(columnId);
				if (property != null) {
				//Availability availability = (Availability) property.getValue();
				//boolean availability = (Boolean) property.getValue();
				Solicitante availability = (Solicitante) property.getValue();
				
			
				
				String color = "";
				if (availability != null) {
					color = "#ffc66e";
				} else //if (availability != 0) {
					color = "#2dd085";
//				} else if (!availability != 0) {
//					color = "#2dd085";
//				}
				String iconCode = "<span class=\"v-icon\" style=\"font-family: "+ FontAwesome.CIRCLE.getFontFamily()+ ";color:"	+ color	+ "\">&#x"+ Integer.toHexString(FontAwesome.MALE.getCodepoint())
				+ ";</span>";
				
				
				Object objetito = property.getValue(); //Variable para poder usar el grafico
				boolean testing = false;
				if(objetito != null){
					testing = true;
				}
				
				Label label = new Label(iconCode + " " + ((testing ? "SI":"NO")),ContentMode.HTML);
				label.setSizeUndefined();
				return label;
				}
				return null;
				}
				};	
		
		this.setTablaSolicitudes(new Table("Persona Fisica",ServicioSolicitudes.getInstance().getPreSolicitudesBeanItemContainer()));
		//this.getTablaSolicitudes().setVisibleColumns(new Object[]{"titular.nombre", "tipoImpuesto","solicitante"});//, "apellido","tipoDocumentoTitular","numeroDeDocumentoTitular","tipoTelefonoTitular","numeroDeTelefonoTitular","tipoTelefonoAlternativoTitular","numeroDeTelefonoAlternativoTitular","correoElectronicoTitular","poseeSolicitante"});
		
		this.getTablaSolicitudes().setVisibleColumns(new Object[]{"titular.apellido", "titular.nombre", "titular.tipoDocumentoTitular",
																  "titular.numeroDeDocumentoTitular", //"titular.tipoTelefonoTitular",
																  //"titular.numeroDeTelefonoTitular", //"titular.tipoTelefonoAlternativoTitular",
																  //"titular.numeroDeTelefonoAlternativoTitular",
																  "titular.correoElectronicoTitular",
																  "tipoImpuesto","codigoPagoElectronico","estadoPresolicitud", "solicitante"});//, "Apellido","Tipo Documento", "N Documento", "Tipo Telefono","Numero","Tipo Tel Alternativo", "Numero",	"Email", "Solicitante"});
		this.getTablaSolicitudes().setColumnHeaders(new String[]{"Apellido","Nombre","Tipo Doc","N°",//"Tipo Tel",
																 /*"N° Tel 2"*/"Email",
																 "Impuesto","CPE", "Estado", "Solicitante"});
		
		this.getTablaSolicitudes().addGeneratedColumn("estadoPresolicitud", availabilityGenerator);
		this.getTablaSolicitudes().addGeneratedColumn("solicitante", availabilityGenerator2);
		this.getTablaSolicitudes().setSelectable(true);
		this.getTablaSolicitudes().setInvalidAllowed(false);
		this.getTablaSolicitudes().setPageLength(10);
		
	

		return this.getTablaSolicitudes();
	}
	
	

	public Button getBtnDetalleTitular() {
		return btnDetalleTitular;
	}

	public void setBtnDetalleTitular(Button btnDetalleTitular) {
		this.btnDetalleTitular = btnDetalleTitular;
	}

	public Table getTablaSolicitudes() {
		return tablaSolicitudes;
	}

	public void setTablaSolicitudes(Table tablaSolicitudes) {
		this.tablaSolicitudes = tablaSolicitudes;
	}
	public Button getBtnAprobarPresolicitud() {
		return btnAprobarPresolicitud;
	}

	public void setBtnAprobarPresolicitud(Button btnAprobarPresolicitud) {
		this.btnAprobarPresolicitud = btnAprobarPresolicitud;
	}

	public Button getBtnActulizarPresolicitudes() {
		return btnActulizarPresolicitudes;
	}

	public void setBtnActulizarPresolicitudes(Button btnActulizarPresolicitudes) {
		this.btnActulizarPresolicitudes = btnActulizarPresolicitudes;
	}

	public Label getLblCantidadPresolicitudes() {
		return lblCantidadPresolicitudesFisicas;
	}

	public void setLblCantidadPresolicitudes(Label lblCantidadPresolicitudes) {
		this.lblCantidadPresolicitudesFisicas = lblCantidadPresolicitudes;
	}

	public void actualizarLabel() {
		
		CANTIDAD_FISICAS = this.getTablaSolicitudes().size();
		this.getLblCantidadPresolicitudes().setValue("Cantidad de Presolicitudes a procesar : " + String.valueOf(CANTIDAD_FISICAS));
		System.out.println(CANTIDAD_FISICAS);
	}

	public Button getBtnBajaPresolicitud() {
		return btnBajaPresolicitud;
	}

	public void setBtnBajaPresolicitud(Button btnBajaPresolicitud) {
		this.btnBajaPresolicitud = btnBajaPresolicitud;
	}

	public Button getBtnJuridico() {
		return btnJuridico;
	}

	public void setBtnJuridico(Button btnJuridico) {
		this.btnJuridico = btnJuridico;
	}

	public Button getBtnFisico() {
		return btnFisico;
	}

	public void setBtnFisico(Button btnFisico) {
		this.btnFisico = btnFisico;
	}


	public Table getTablaJuridica() {
		return tablaJuridica;
	}


	public void setTablaJuridica(Table tablaJuridica) {
		this.tablaJuridica = tablaJuridica;
	}


	public Label getLblCantidadPresolicitudesJuridicas() {
		return lblCantidadPresolicitudesJuridicas;
	}


	public void setLblCantidadPresolicitudesJuridicas(
			Label lblCantidadPresolicitudesJuridicas) {
		this.lblCantidadPresolicitudesJuridicas = lblCantidadPresolicitudesJuridicas;
	}


	public Button getBtnBuscarSuscriptor() {
		return btnBuscarSuscriptor;
	}


	public void setBtnBuscarSuscriptor(Button btnBuscarSuscriptor) {
		this.btnBuscarSuscriptor = btnBuscarSuscriptor;
	}


	public Button getBtnEditarSuscriptor() {
		return btnEditarSuscriptor;
	}


	public void setBtnEditarSuscriptor(Button btnEditarSuscriptor) {
		this.btnEditarSuscriptor = btnEditarSuscriptor;
	}



}
