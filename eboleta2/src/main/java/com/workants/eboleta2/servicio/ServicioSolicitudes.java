package com.workants.eboleta2.servicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.workants.eboleta2.IService.IServiceSolicitudes;
import com.workants.eboleta2.dao.DaoRegistro;
import com.workants.eboleta2.dao.DaoSolicitudes;
import com.workants.eboleta2.model.Juridica;
import com.workants.eboleta2.model.PreSolicitud;
import com.workants.eboleta2.model.Solicitante;
import com.workants.eboleta2.model.Titular;
import com.workants.eboleta2.model.Usuario;


public class ServicioSolicitudes implements IServiceSolicitudes, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServicioSolicitudes instance;
	
	public ServicioSolicitudes(){
		
	}
	
	public static ServicioSolicitudes getInstance(){
		
		if(instance == null){
			instance = new ServicioSolicitudes();
		}
		return instance;
	}

	

	public BeanItemContainer<PreSolicitud> getPreSolicitudesBeanItemContainer() {		
		
		ArrayList<PreSolicitud> presolicitudes;
		presolicitudes = DaoSolicitudes.getInstance().getPresolicitudes();
		List<PreSolicitud> listarPresolicitudes = presolicitudes;
		BeanItemContainer<PreSolicitud> objects = new BeanItemContainer<PreSolicitud>(PreSolicitud.class, listarPresolicitudes);
		objects.addNestedContainerProperty("titular.nombre");
		objects.addNestedContainerProperty("titular.apellido");
		objects.addNestedContainerProperty("titular.tipoDocumentoTitular");
		objects.addNestedContainerProperty("titular.numeroDeDocumentoTitular");	
		objects.addNestedContainerProperty("titular.numeroDeTelefonoTitular");
		objects.addNestedContainerProperty("titular.tipoTelefonoTitular");
		objects.addNestedContainerProperty("titular.numeroDeTelefonoAlternativoTitular");
		objects.addNestedContainerProperty("titular.tipoTelefonoAlternativoTitular");
		objects.addNestedContainerProperty("titular.correoElectronicoTitular");
		objects.addNestedContainerProperty("titular.poseeSolicitante");
		
		
		
		
		return objects;
	}
	
	public BeanItemContainer<PreSolicitud> getPreSolicitudesBeanItemContainerJuridicas() {		
		
		ArrayList<PreSolicitud> presolicitudes;
		presolicitudes = DaoSolicitudes.getInstance().getPresolicitudesJuridicas();
		List<PreSolicitud> listarPresolicitudes = presolicitudes;
		BeanItemContainer<PreSolicitud> objects = new BeanItemContainer<PreSolicitud>(PreSolicitud.class, listarPresolicitudes);
		objects.addNestedContainerProperty("juridica.razonSocial");
		objects.addNestedContainerProperty("juridica.cuit");
		objects.addNestedContainerProperty("juridica.tipo");
		objects.addNestedContainerProperty("juridica.numeroDeTelefono");	
		objects.addNestedContainerProperty("juridica.tipoTelefono");
		objects.addNestedContainerProperty("juridica.correoElectronico");		
		
		System.out.println(objects.size());
		return objects;
	}

	@Override
	public int aceptarSuscripcion(PreSolicitud presolicitud, Usuario usuario) {
		
		return DaoSolicitudes.getInstance().acpetarSuscripcion(presolicitud, usuario);
		
	}

	@Override
	public int cancelarSuscripcion(PreSolicitud presolicitud, Usuario usuario) {
		
		return DaoSolicitudes.getInstance().cancelarSuscripcion(presolicitud,usuario);
	}

	@Override
	public BeanItemContainer<PreSolicitud> buscarSuscriptro(String tipoDeDocumento,
			String numeroDeDocumento) {
		
		
		
		ArrayList<PreSolicitud> presolicitudes;
		presolicitudes = DaoSolicitudes.getInstance().buscarSuscriptro(tipoDeDocumento, numeroDeDocumento);
		List<PreSolicitud> listarPresolicitudes = presolicitudes;
		BeanItemContainer<PreSolicitud> objects = new BeanItemContainer<PreSolicitud>(PreSolicitud.class, listarPresolicitudes);
		
		if(!tipoDeDocumento.equalsIgnoreCase("cuit")){
			
			objects.addNestedContainerProperty("titular.nombre");
			objects.addNestedContainerProperty("titular.apellido");
			objects.addNestedContainerProperty("titular.tipoDocumentoTitular");
			objects.addNestedContainerProperty("titular.numeroDeDocumentoTitular");	
			objects.addNestedContainerProperty("titular.numeroDeTelefonoTitular");
			objects.addNestedContainerProperty("titular.tipoTelefonoTitular");
			objects.addNestedContainerProperty("titular.numeroDeTelefonoAlternativoTitular");
			objects.addNestedContainerProperty("titular.tipoTelefonoAlternativoTitular");
			objects.addNestedContainerProperty("titular.correoElectronicoTitular");
			objects.addNestedContainerProperty("titular.poseeSolicitante");
		}else{
			
			objects.addNestedContainerProperty("juridica.razonSocial");
			objects.addNestedContainerProperty("juridica.cuit");
			objects.addNestedContainerProperty("juridica.tipo");
			objects.addNestedContainerProperty("juridica.numeroDeTelefono");	
			objects.addNestedContainerProperty("juridica.tipoTelefono");
			objects.addNestedContainerProperty("juridica.correoElectronico");		
		}
		return objects;
	}

	@Override
	public int actualizarSuscriptor(Titular titular, String nombreImpuesto,	String codigo) {
		
	  return DaoRegistro.getIntance().actualizarSuscriptor(titular,nombreImpuesto,codigo); //actualizo sobre DaoRegistro para mantener orden
		
	}

	@Override
	public int actualizarSolicitante(Titular titular, Solicitante solicitante,
			String nombreImpuesto, String codigo) {
		
		return DaoRegistro.getIntance().actualizarSolicitante(titular,solicitante,nombreImpuesto,codigo); //actualizo sobre DaoRegistro para mantener orden;
	}

	@Override
	public int actualizarJuridica(Juridica juridica, String nombreImpuesto,String codigo) {
		
		return DaoRegistro.getIntance().actualizarJuridica(juridica,nombreImpuesto,codigo); //actualizo sobre DaoRegistro para mantener orden;;
	}

	
}