package com.workants.eboleta2.servicio;

import java.io.Serializable;
import java.util.Date;
import com.vaadin.data.util.BeanItemContainer;
import com.workants.eboleta2.IService.IServiceEnvio;
import com.workants.eboleta2.dao.DaoEnvio;
import com.workants.eboleta2.model.PreSolicitudConfirmar;
import com.workants.eboleta2.model.Procesados;
import com.workants.eboleta2.model.Usuario;

public class ServicioEnvio implements IServiceEnvio, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioEnvio instance;
	
	
	private ServicioEnvio(){
		
	}
	
	
	public static ServicioEnvio getIntance(){
		
		if(instance == null){
			instance = new ServicioEnvio();
		}
		return instance;
	}

	@Override
	public int generarProceso() {
		
		return DaoEnvio.getIntance().generarProceso();
	}


	@Override
	public String obtenerCuerpoMail() {
		return DaoEnvio.getIntance().obtenerCuerpoMail();
	}


	@Override
	public int guardarCambios(String nuevoCuerpoMail) {
		
		return DaoEnvio.getIntance().guardarCambios(nuevoCuerpoMail);
	}


	@Override
	public BeanItemContainer<PreSolicitudConfirmar> presuscripcionesJuridicas(Date fechaDesde, Date fechaHasta) {
		
		BeanItemContainer<PreSolicitudConfirmar> objects = new BeanItemContainer<PreSolicitudConfirmar>(PreSolicitudConfirmar.class,
				DaoEnvio.getIntance().presuscripcionesJuridicas(fechaDesde, fechaHasta));		
		return objects;
	}


	@Override
	public BeanItemContainer<PreSolicitudConfirmar> presuscripcionesFisicas(Date fechaDesde, Date fechaHasta) {
		
		BeanItemContainer<PreSolicitudConfirmar> objects = new BeanItemContainer<PreSolicitudConfirmar>(PreSolicitudConfirmar.class,
				DaoEnvio.getIntance().presuscripcionesFisicas(fechaDesde,fechaHasta));		
		return objects;
	}


	@Override
	public int cancelarPresuscripcion(PreSolicitudConfirmar preSolicitudConfirmar, Usuario usuario) {
		
		return DaoEnvio.getIntance().cancelarPresuscripcion(preSolicitudConfirmar, usuario);
	}


	@Override
	public BeanItemContainer<Procesados> cargarProcesados(String tipoImpuesto) {
	
		BeanItemContainer<Procesados> container = new BeanItemContainer<Procesados>(Procesados.class,
				DaoEnvio.getIntance().cargarProcesados(tipoImpuesto));
		
		return container;
	}


	@Override
	public int eliminarEmision(String tipoImpuestos) {
		
		return DaoEnvio.getIntance().eliminarEmision(tipoImpuestos);
	}


	@Override
	public int exportarAdheridos(Date fechaDesde, Date fechaHasta) {
		
		return  DaoEnvio.getIntance().exportarAdheridos(fechaDesde,fechaHasta);
	}


	@Override
	public int importarAdheridos(Usuario usuario) {
	
		return DaoEnvio.getIntance().importarAdheridos(usuario);
	}

}
