package com.workants.eboleta2.ui.formularios;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Notification;
import com.workants.eboleta2.model.Procesados;
import com.vaadin.ui.VerticalLayout;

public class LayoutActualizarEmision extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox cmbTipoImpuesto;
	private Grid gridImpuestos;
	private Button btnEliminarEmision;
	private Label lblCantidadEmisiones;
	
	public LayoutActualizarEmision() {
		
		setSpacing(true);
		setMargin(true);
		addComponent(generarCmbTipoImpuesto());
		addComponent(generarGrid());
		addComponent(generarLabel());
		addComponent(generarComandos());
		
		
		
	}


	private Component generarLabel() {
		
		this.setLblCantidadEmisiones(new Label("<strong>Cantidad de Emisiones por impuesto: " + this.getGridImpuestos().
				getContainerDataSource().size(), ContentMode.HTML));
		return this.getLblCantidadEmisiones();
	}


	private Component generarComandos() {
		
		HorizontalLayout lay = new HorizontalLayout();
		this.setBtnEliminarEmision(new Button("Eliminar emision"));
		lay.addComponent(this.getBtnEliminarEmision());
		return lay;
	}


	private Component generarGrid() {

		this.setGridImpuestos(new Grid("Impuestos"));
		this.getGridImpuestos().setSizeFull();
		this.getGridImpuestos().setSelectionMode(SelectionMode.NONE);	
		
		
		
		

		
		
		return this.getGridImpuestos();
	}


	private Component generarCmbTipoImpuesto() {
		
		this.setCmbTipoImpuesto(new ComboBox("Tipo Impuesto:"));
		this.getCmbTipoImpuesto().setNewItemsAllowed(false);
		this.getCmbTipoImpuesto().setInvalidAllowed(false);
		this.getCmbTipoImpuesto().setImmediate(true);
		this.getCmbTipoImpuesto().setNullSelectionAllowed(false);
		this.getCmbTipoImpuesto().addItem("Impuesto Automotor");
		this.getCmbTipoImpuesto().addItem("Impuesto Inmobiliario");
		this.getCmbTipoImpuesto().addItem("Impuesto Tasa de Higiene Urbana");
		this.getCmbTipoImpuesto().addItem("Impuesto Derecho Ocupante");
		this.getCmbTipoImpuesto().setWidth("300px");
		
		
		return this.getCmbTipoImpuesto();
	}


	public ComboBox getCmbTipoImpuesto() {
		return cmbTipoImpuesto;
	}


	public void setCmbTipoImpuesto(ComboBox cmbTipoImpuesto) {
		this.cmbTipoImpuesto = cmbTipoImpuesto;
	}


	public Grid getGridImpuestos() {
		return gridImpuestos;
	}


	public void setGridImpuestos(Grid gridImpuestos) {
		this.gridImpuestos = gridImpuestos;
	}


	public void cargarProcesosOK(BeanItemContainer<Procesados> containter) {
		
		this.getGridImpuestos().setContainerDataSource(containter);
		this.getGridImpuestos().setColumnOrder("tipoImpuesto");
		this.getGridImpuestos().setColumnOrder("correoElectronico");
		this.getGridImpuestos().setColumnOrder("cpe");
		this.getGridImpuestos().setColumnOrder("fechaEnvio");
		
		
		
		this.getGridImpuestos().getColumn("tipoImpuesto").setHeaderCaption("Tipo Impuesto");
		this.getGridImpuestos().getColumn("correoElectronico").setHeaderCaption("Correo Electronico");
		this.getGridImpuestos().getColumn("cpe").setHeaderCaption("CPE");
		this.getGridImpuestos().getColumn("fechaEnvio").setHeaderCaption("Fecha de Envio");
		
		
		this.getGridImpuestos().getColumn("fechaEnvio").setConverter(new StringToDateConverter(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public DateFormat getFormat(Locale locale){

				return new SimpleDateFormat("dd-MM-yyyy");
			}
		});

		
//		this.getGridImpuestos().removeColumn("idProcesado");
		
		
		
//		if(containter.size() !=0){
//			this.getGridImpuestos().removeColumn("idProcesado");
//		}		
		
		this.getLblCantidadEmisiones().setValue("<strong>Cantidad de Emisiones por impuesto: " + this.getGridImpuestos().getContainerDataSource().size());		
    }


	public void cargarProcesosError() {

		Notification not = new Notification("Atención", "No se emitió el impuesto seleccionado", Type.ERROR_MESSAGE);
		not.setDelayMsec(3000);
		not.setPosition(Position.MIDDLE_CENTER);
		not.show(Page.getCurrent());	
		this.getGridImpuestos().getContainerDataSource().removeAllItems();
		this.getCmbTipoImpuesto().clear();
	}


	public Button getBtnEliminarEmision() {
		return btnEliminarEmision;
	}


	public void setBtnEliminarEmision(Button btnEliminarEmision) {
		this.btnEliminarEmision = btnEliminarEmision;
	}


	public Label getLblCantidadEmisiones() {
		return lblCantidadEmisiones;
	}


	public void setLblCantidadEmisiones(Label lblCantidadEmisiones) {
		this.lblCantidadEmisiones = lblCantidadEmisiones;
	}


	public void eliminarEmisionOK() {
		

		Notification not = new Notification("Atención", "Emision correctamente eliminada", Type.ASSISTIVE_NOTIFICATION);
		not.setDelayMsec(3000);
		not.setPosition(Position.MIDDLE_CENTER);
		not.show(Page.getCurrent());	
		this.getGridImpuestos().getContainerDataSource().removeAllItems();
		
	}


	public void eliminarEmisionError() {
		
		Notification not = new Notification("Atención", "Emision no ha sido eliminada", Type.ERROR_MESSAGE);
		not.setDelayMsec(3000);
		not.setPosition(Position.MIDDLE_CENTER);
		not.show(Page.getCurrent());	
		this.getGridImpuestos().getContainerDataSource().removeAllItems();
		
	}
	
	

}
