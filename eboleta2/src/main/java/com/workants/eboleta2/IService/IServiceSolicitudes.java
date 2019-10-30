package com.workants.eboleta2.IService;

import com.vaadin.data.util.BeanItemContainer;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.PreSolicitud;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.model.Usuario;

public interface IServiceSolicitudes {
	
	public int aceptarSuscripcion(PreSolicitud presolicitud, Usuario usuario);
	public int cancelarSuscripcion(PreSolicitud presolicitud, Usuario usuario);
	public BeanItemContainer<PreSolicitud> buscarSuscriptro(String tipoDeDocumento,String numeroDeDocumento);
	public int actualizarSuscriptor(Titular titular, String nombreImpuesto, String codigo);
	public int actualizarSolicitante(Titular titular, Solicitante solicitante, String nombreImpuesto, String codigo);
	public int actualizarJuridica(Juridica juridica, String nombreImpuesto, String codigo);

}
