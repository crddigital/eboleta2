package com.workants.eboleta2.servicio;

import java.io.Serializable;

import com.workants.eboleta2.IService.IServiceRegistro;
import com.workants.eboleta2.dao.DaoRegistro;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;

public class ServicioRegistro implements IServiceRegistro, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioRegistro instance;
	
	
	private ServicioRegistro(){
		
	}
	
	
	public static ServicioRegistro getIntance(){
		
		if(instance == null){
			instance = new ServicioRegistro();
		}
		return instance;
	}


	@Override
	public int subscribir(Titular titular, String nombreDeImpuesto, String codigo, String clave) {
		
		return DaoRegistro.getIntance().subscribir(titular, nombreDeImpuesto, codigo, clave);
		//return DaoRegistro.getIntance().emailPreSuscripcion(titular, nombreDeImpuesto, codigo);
	}


	@Override
	public int subscribirSolicitante(Titular titular, Solicitante solicitante, String nombreDeImpuesto, String codigo, String clave) {
	
		return DaoRegistro.getIntance().subscribirSolicitante(titular, solicitante, nombreDeImpuesto, codigo, clave);
	}


	@Override
	public int subscribirJuridica(Juridica juridica, String nombreDeImpuesto, String codigo, String clave) {
		
		return DaoRegistro.getIntance().subscribirJuridica(juridica, nombreDeImpuesto, codigo, clave);
	}

}
