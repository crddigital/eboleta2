package com.workants.eboleta2.IService;

import com.workants.eboleta2.model.Usuario;

public interface IServiceValidar {
	
	public int validar(String codigo, String tipoImpuesto);
	public Usuario login(String nombreDeUsuario, String contrasenia);
	public String buscarImpuestoBaja(String tipoDeDocumento, String numeroDeDocumento, String codigoPagoElectronico);
	public int generarMailBaja (String codigoPagoElectronico, String tipoDeDocumento, String numeroDeDocumento, String cuerpo);
	public int verificarCpe(String cpe);
	public int verificarCpeSuscripcion(String cpe);
	public int ejecutarBaja(String cpe, String codigoKeyBaja);
}
