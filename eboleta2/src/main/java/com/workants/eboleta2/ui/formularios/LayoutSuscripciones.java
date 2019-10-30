package com.workants.eboleta2.ui.formularios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.tepi.filtertable.FilterTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.workants.eboleta2.model.PreSolicitudConfirmar;
import com.workants.eboleta2.tools.DemoFilterGenerator;

public class LayoutSuscripciones extends VerticalLayout{
	
	
	private static final long serialVersionUID = 1L;
	private DateField dtfFechaSuscripcionDesde;
	private DateField dtfFechaSuscripcionHasta;
	private Button btnFisicas;
	private Button btnJuridicas;
	private FilterTable tablaSuscripciones;
	private Button btnCancelar;
	

	
	
	
	public LayoutSuscripciones() {
	
		
		setSpacing(true);
		setMargin(true);
		addComponent(generarSeleccionFechas());
		addComponent(generarSeleccionTipo());
		//addComponent(generarDtfFechaSuscripcion());
		addComponent(generarTabla());
		addComponent(generarCancelacion());
		
		
	}
	
	private Component generarSeleccionTipo() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
				
		
		this.setBtnFisicas(new Button("Fisicas"));
		this.setBtnJuridicas(new Button("Juridicas"));		
		
		lay.addComponent(this.getBtnFisicas());
		lay.addComponent(this.getBtnJuridicas());
		lay.setComponentAlignment(this.getBtnFisicas(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(this.getBtnJuridicas(), Alignment.BOTTOM_RIGHT);
		return lay;
	}

	private Component generarCancelacion() {
		
		this.setBtnCancelar(new Button("Cancelar Presuscripción"));
		return this.getBtnCancelar();
	}






	private Component generarSeleccionFechas() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
		this.setDtfFechaSuscripcionDesde(new DateField("Fecha Desde:"));
		this.getDtfFechaSuscripcionDesde().setRangeEnd(new Date());
		this.getDtfFechaSuscripcionDesde().setRequired(true);
		
		this.setDtfFechaSuscripcionHasta(new DateField("Fecha Hasta:"));
		this.getDtfFechaSuscripcionHasta().setRangeStart(new Date());
		this.getDtfFechaSuscripcionHasta().setRangeEnd(new Date());
		this.getDtfFechaSuscripcionHasta().setRequired(true);
		
		
		lay.addComponent(this.getDtfFechaSuscripcionDesde());
		lay.addComponent(this.getDtfFechaSuscripcionHasta());
		
		/*VerticalLayout layV = new VerticalLayout();
		layV.setSpacing(true);
		
		lay.addComponent(this.getDtfFechaSuscripcionDesde());
		lay.addComponent(this.getDtfFechaSuscripcionHasta());
		
		
		lay.addComponent(this.getBtnFisicas());
		lay.addComponent(this.getBtnJuridicas());
		lay.setComponentAlignment(this.getBtnFisicas(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(this.getBtnJuridicas(), Alignment.BOTTOM_RIGHT);*/
		
		
		/*
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		
		this.setDtfFechaSuscripcionDesde(new DateField("Fecha Desde:"));
		this.getDtfFechaSuscripcionDesde().setRangeEnd(new Date());
		this.getDtfFechaSuscripcionDesde().setRequired(true);
		
		
		this.setBtnFisicas(new Button("Fisicas"));
		this.setBtnJuridicas(new Button("Juridicas"));
		
		lay.addComponent(this.getDtfFechaSuscripcionDesde());
		lay.addComponent(this.getBtnFisicas());
		lay.addComponent(this.getBtnJuridicas());
		lay.setComponentAlignment(this.getBtnFisicas(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(this.getBtnJuridicas(), Alignment.BOTTOM_RIGHT);
		return lay;
		*/
		return lay;
		
		
	}






//	private Component generarDtfFechaSuscripcion() {
//		
//		this.setDtfFechaSuscripcionDesde(new DateField("Fecha a consultar:"));
//		this.getDtfFechaSuscripcionDesde().setRangeEnd(new Date());
//		this.getDtfFechaSuscripcionDesde().setRequired(true);
//		return this.getDtfFechaSuscripcionDesde();
//	}






	private Component generarTabla() {
		
		this.setTablaSuscripciones(new FilterTable("Suscripciones pendientes"));
		this.getTablaSuscripciones().setCaptionAsHtml(true);
		this.getTablaSuscripciones().setCaption("<big><strong>Suscripciones pendientes");
		this.getTablaSuscripciones().setSelectable(true);
		this.getTablaSuscripciones().setImmediate(true);
		this.getTablaSuscripciones().setSizeFull();
		this.getTablaSuscripciones().setVisibleColumns(new Object[]{});
		this.getTablaSuscripciones().setColumnHeaders(new String[]{});		
		
		
		
		
		return this.getTablaSuscripciones();
	}






	public DateField getDtfFechaSuscripcionDesde() {
		return dtfFechaSuscripcionDesde;
	}






	public void setDtfFechaSuscripcionDesde(DateField dtfFechaSuscripcionDesde) {
		this.dtfFechaSuscripcionDesde = dtfFechaSuscripcionDesde;
	}






	public FilterTable getTablaSuscripciones() {
		return tablaSuscripciones;
	}






	public void setTablaSuscripciones(FilterTable tablaSuscripciones) {
		this.tablaSuscripciones = tablaSuscripciones;
	}






	public Button getBtnFisicas() {
		return btnFisicas;
	}






	public void setBtnFisicas(Button btnFisicas) {
		this.btnFisicas = btnFisicas;
	}






	public Button getBtnJuridicas() {
		return btnJuridicas;
	}






	public void setBtnJuridicas(Button btnJuridicas) {
		this.btnJuridicas = btnJuridicas;
	}






	public void setearTabla(BeanItemContainer<PreSolicitudConfirmar> preSolicitudesConfirmar) {
		
		this.getTablaSuscripciones().setContainerDataSource(preSolicitudesConfirmar);
		this.getTablaSuscripciones().setCaptionAsHtml(true);
		this.getTablaSuscripciones().setCaption("<big><strong>Cantidad de suscriptores sin confirmar: " + this.getTablaSuscripciones().getContainerDataSource().size());	
		
		this.getTablaSuscripciones().setFilterGenerator(new DemoFilterGenerator());
		this.getTablaSuscripciones().setFilterBarVisible(true);
		
		
		this.getTablaSuscripciones().setVisibleColumns(new Object[]{"nombre","correo","tipoImpuesto","cpe","fechaSuscripcion"});
		this.getTablaSuscripciones().setColumnHeaders(new String[]{"Nombre","Correo","Tipo Impuesto","CPE", "Fecha Suscripcion"});
		
		this.getTablaSuscripciones().setConverter("fechaSuscripcion", new StringToDateConverter(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public DateFormat getFormat(Locale locale){

				return new SimpleDateFormat("dd-MM-yyyy");
			}
		});
		
		
		
	}






	public Button getBtnCancelar() {
		return btnCancelar;
	}






	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}






	public DateField getDtfFechaSuscripcionHasta() {
		return dtfFechaSuscripcionHasta;
	}






	public void setDtfFechaSuscripcionHasta(DateField dtfFechaSuscripcionHasta) {
		this.dtfFechaSuscripcionHasta = dtfFechaSuscripcionHasta;
	}

}

