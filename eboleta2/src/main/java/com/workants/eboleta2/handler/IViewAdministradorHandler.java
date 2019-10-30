package com.workants.eboleta2.handler;

public interface IViewAdministradorHandler {

	
	public void generarProceso();
	public void obtenerCuerpoMail();
	
	public void presuscripcionesJuridicas();
	public void presuscripcionesFisicas();
	
	public void cancelarPresuscripcion();
	
	
	public void cargarProcesados();
	public void eliminarEmision();
	
	
	public void generarExportarAdheridos();
	public void generarImportarAdheridos();

}
