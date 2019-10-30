package com.workants.eboleta2.servicio;

import java.io.Serializable;

import com.workants.eboleta2.IService.IServiceValidar;
import com.workants.eboleta2.dao.DaoValidar;
import com.workants.eboleta2.model.Usuario;

public class ServicioValidar implements IServiceValidar, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioValidar instance;
	
	private ServicioValidar(){
		
	}
	
	
	public static ServicioValidar getIntance(){
		
		if(instance == null){
			instance = new ServicioValidar();
		}
		return instance;
	}
	
	@Override
	public int validar(String codigo, String tipoImpuesto) {
				
		return DaoValidar.getIntance().validar(codigo, tipoImpuesto);
	}


	@Override
	public Usuario login(String nombreDeUsuario, String contrasenia) {
		
		return DaoValidar.getIntance().login(nombreDeUsuario,contrasenia);
	}


//	@Override
//	public String buscarImpuestoBaja(String tipoDeDocumento,
//			String numeroDeDocumento, String codigoPagoElectronico) {
//		return DaoValidar.getIntance().buscarImpuestoBaja(tipoDeDocumento,numeroDeDocumento,codigoPagoElectronico);
//	}
// (tipoDeContribuyente, datosContribuyente, codigoPagoElectronico);	
	@Override
	public String buscarImpuestoBaja(String tipoDeContribuyente,
			String datosContribuyente, String codigoPagoElectronico) {
		return DaoValidar.getIntance().buscarImpuestoBaja(tipoDeContribuyente,datosContribuyente,codigoPagoElectronico);
	}
	

//	@Override
//	public int generarMailBaja(String codigoPagoElectronico,
//			String tipoDeDocumento, String numeroDeDocumento, String cuerpo) {
//		return DaoValidar.getIntance().generarMailBaja(codigoPagoElectronico,tipoDeDocumento,numeroDeDocumento,cuerpo);
//	}
	
	@Override
	public int generarMailBaja(String codigoPagoElectronico,
			String tipoDeContribuyente, String datosContribuyente, String cuerpo) {
		return DaoValidar.getIntance().generarMailBaja(codigoPagoElectronico,tipoDeContribuyente,datosContribuyente,cuerpo);
	}
	


	@Override
	public int verificarCpe(String cpe) {
		
		return DaoValidar.getIntance().verificarCpe(cpe);
	}


	@Override
	public int ejecutarBaja(String cpe, String codigoKeyBaja) {
		
		return DaoValidar.getIntance().ejecutarBaja(cpe, codigoKeyBaja);
	}


	@Override
	public int verificarCpeSuscripcion(String cpe) {
		
		return DaoValidar.getIntance().verificarCpeSuscripcion(cpe);
	}
	
	

}
