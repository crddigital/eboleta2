package com.workants.eboleta2.IService;

import java.util.Date;
import com.vaadin.data.util.BeanItemContainer;
import com.workants.eboleta2.model.PreSolicitudConfirmar;
import com.workants.eboleta2.model.Procesados;
import com.workants.eboleta2.model.Usuario;

public interface IServiceEnvio {

	
	public int generarProceso();
	public String obtenerCuerpoMail();
	public int guardarCambios(String nuevoCuerpoMail);
	public BeanItemContainer<PreSolicitudConfirmar> presuscripcionesJuridicas(Date fechaDesde, Date fechaHasta);
	public BeanItemContainer<PreSolicitudConfirmar> presuscripcionesFisicas(Date fechaDesde, Date fechaHasta);
	public int cancelarPresuscripcion(PreSolicitudConfirmar preSolicitudConfirmar, Usuario usuario);
	
	public BeanItemContainer <Procesados> cargarProcesados(String tipoImpuesto);
	public int eliminarEmision(String tipoImpuestos);
	
	public int exportarAdheridos(Date fechaDesde, Date fechaHasta);
	public int importarAdheridos(Usuario usuario);
	
	
}
