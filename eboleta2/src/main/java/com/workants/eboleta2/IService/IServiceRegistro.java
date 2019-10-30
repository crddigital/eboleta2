package com.workants.eboleta2.IService;

import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;

public interface IServiceRegistro {

	public int subscribir(Titular titular, String nombreDeImpuesto, String codigo, String clave);
	public int subscribirJuridica(Juridica juridica, String nombreDeImpuesto, String codigo, String clave);
	public int subscribirSolicitante(Titular titular, Solicitante solicitante, String nombreDeImpuesto, String codigo, String clave);
}
