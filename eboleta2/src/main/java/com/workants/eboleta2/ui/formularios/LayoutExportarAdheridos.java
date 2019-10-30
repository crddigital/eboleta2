package com.workants.eboleta2.ui.formularios;

import java.io.File;
import java.util.Date;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;


public class LayoutExportarAdheridos extends VerticalLayout implements FocusListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateField dtfFechaDesde;
	private DateField dtfFechaHasta;	
	private Button btnGenerarExportacionAdheridos;
	private Button btnExportar;
	
	
	
	public LayoutExportarAdheridos() {
		
		setSpacing(true);
		setMargin(true);
		setSizeFull();
		
		this.setBtnGenerarExportacionAdheridos(new Button("Generar Archivo")); //Existe antes para poder usar el listener
		this.setBtnExportar(new Button("Exportar Archivo"));
		addComponent(generarComponentes());
		
		this.getBtnExportar().setEnabled(false); // 
		
		//FileResource fr = new FileResource(new File("C://Users/tbeloqui//Desktop//feeder//abc.csv")); // <-- local !
		FileResource fr = new FileResource(new File("//home//comodoro//public_html//eb/files//eboleta.csv"));  //<-- hosting !
		
		
		
		
		
		FileDownloader fileDownloader = new FileDownloader(fr);	
		fileDownloader.extend(this.getBtnExportar());		
		
	}


	private Component generarComponentes() {
		
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSpacing(true);
		lay.setMargin(true);
		
		this.setDtfFechaDesde(new DateField("Desde:"));
		this.getDtfFechaDesde().setRangeEnd(new Date());
		
		this.setDtfFechaHasta(new DateField("Hasta:"));
		this.getDtfFechaHasta().setRangeEnd(new Date());	
		
		lay.addComponent(this.getDtfFechaDesde());
		lay.addComponent(this.getDtfFechaHasta());
		lay.addComponent(this.getBtnGenerarExportacionAdheridos());
		lay.addComponent(this.getBtnExportar());
	
		
		lay.setComponentAlignment(this.getBtnGenerarExportacionAdheridos(), Alignment.BOTTOM_RIGHT);
		lay.setComponentAlignment(this.getBtnExportar(), Alignment.BOTTOM_RIGHT);
	

		
		this.getDtfFechaDesde().addFocusListener(this);
		this.getDtfFechaHasta().addFocusListener(this);
		
		
		
		return lay;
	}


	public DateField getDtfFechaDesde() {
		return dtfFechaDesde;
	}


	public void setDtfFechaDesde(DateField dtfFechaDesde) {
		this.dtfFechaDesde = dtfFechaDesde;
	}


	public DateField getDtfFechaHasta() {
		return dtfFechaHasta;
	}


	public void setDtfFechaHasta(DateField dtfFechaHasta) {
		this.dtfFechaHasta = dtfFechaHasta;
	}


	public Button getBtnGenerarExportacionAdheridos() {
		return btnGenerarExportacionAdheridos;
	}


	public void setBtnGenerarExportacionAdheridos(Button btnGenerarExportacionAdheridos) {
		this.btnGenerarExportacionAdheridos = btnGenerarExportacionAdheridos;
	}




	public Button getBtnExportar() {
		return btnExportar;
	}


	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}


	public void generarImportacionAdheridosVacio() {
		
		Notification.show("Atención", "No hay datos para exportar en las fechas seleccionadas", Type.ERROR_MESSAGE);
		
	}


	@Override
	public void focus(FocusEvent event) {
	
		if(event.getSource() == this.getDtfFechaDesde() || event.getSource() == this.getDtfFechaHasta()){
			
			if(this.getBtnExportar().isEnabled()){
				
				this.getDtfFechaDesde().clear();
				this.getDtfFechaHasta().clear();
				this.getBtnExportar().setEnabled(false);
				this.getBtnGenerarExportacionAdheridos().setEnabled(true);
			}
			
		}
		
	}



	
	

}
