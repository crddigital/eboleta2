package com.workants.eboleta2.presenter;

import java.io.Serializable;

import com.vaadin.ui.UI;
import com.workants.eboleta2.IService.IServiceValidar;
import com.workants.eboleta2.handler.IViewPrincipalHandler;
import com.workants.eboleta2.model.PerfilAdministrativo;
import com.workants.eboleta2.model.PerfilAdministrador;
import com.workants.eboleta2.model.PerfilCuit;
import com.workants.eboleta2.model.Usuario;
import com.workants.eboleta2.ui.view.ViewDesuscripcion.ViewDesuscripcion;
import com.workants.eboleta2.ui.view.ViewPrincipal.ViewPrincipal;
import com.workants.eboleta2.ui.view.responsive.ViewPrincipal.ViewPrincipalResponsive;

public class PresenterPrincipal implements IViewPrincipalHandler, Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewPrincipal viewPrincipal;	
	private ViewPrincipalResponsive viewPrincipalR;
	private IServiceValidar service;

	public PresenterPrincipal(ViewPrincipal viewPrincipal, IServiceValidar service){
		
		this.viewPrincipal = viewPrincipal;
		this.service = service;		
	}
	
	public PresenterPrincipal(ViewPrincipalResponsive viewPrincipalR, IServiceValidar service){
		
		this.viewPrincipalR = viewPrincipalR;
		this.service = service;		
	}

	
	@Override
	public void validar() {
	
		String codigo = "";
		String tipoImpuesto = ""; 
		int rta = 0;
		if(viewPrincipal != null){
			
			codigo = viewPrincipal.getViewPrincipalCuerpo().getFormularioCredencial().getTxtNumeroDePartida().getValue();
			tipoImpuesto = viewPrincipal.getViewPrincipalCuerpo().getFormularioCredencial().getSeleccionadorImpuesto().getValue().toString();
		
		    rta = service.validar(codigo, tipoImpuesto);
			if (rta == -1){
				viewPrincipal.accesoError(tipoImpuesto, codigo, rta);
			}
			if (rta == 0){
				viewPrincipal.accesoValidoPendiente(tipoImpuesto, codigo, rta);
			}
			if (rta == 1){
				viewPrincipal.accesoValidoProcesado(tipoImpuesto, codigo, rta);
			}
			if (rta == 2){
				viewPrincipal.accesoValido(tipoImpuesto, codigo, rta);
			}
			if (rta == 3){
				viewPrincipal.accesoInexistente(tipoImpuesto, codigo, rta);
			}
			
			}
		else{
			
			codigo = viewPrincipalR.getViewPrincipalCuerpo().getFormularioCredencialR().getTxtNumeroDePartida().getValue();
			tipoImpuesto = viewPrincipalR.getViewPrincipalCuerpo().getFormularioCredencialR().getSeleccionadorImpuesto().getValue().toString();
			
			 rta = service.validar(codigo, tipoImpuesto);
				if (rta == -1){
					viewPrincipalR.accesoError(tipoImpuesto, codigo, rta);
				}
				if (rta == 0){
					viewPrincipalR.accesoValidoPendiente(tipoImpuesto, codigo, rta);
				}
				if (rta == 1){
					viewPrincipalR.accesoValidoProcesado(tipoImpuesto, codigo, rta);
				}
				if (rta == 2){
					viewPrincipalR.accesoValido(tipoImpuesto, codigo, rta);
				}
				if (rta == 3){
					viewPrincipalR.accesoInexistente(tipoImpuesto, codigo, rta);
				}
		}
		
		
		
		
		
//		if (rta == 4){ // respuesta solo para automotor empresa siempre y cuando el cpe sea correcto
//			viewPrincipal.accesoValidoAutomotorEmpresa(tipoImpuesto, codigo, rta);
//		}
		
		
	}

	@Override
	public void login() {
		
		String nombreDeUsuario = viewPrincipal.getViewPrincipalCuerpo().getFomularioIngreso().getTxtNombreDeUsuario().getValue();
		String contrasenia = viewPrincipal.getViewPrincipalCuerpo().getFomularioIngreso().getTxtContrasenia().getValue();
		Usuario usuario = service.login(nombreDeUsuario, contrasenia);
		if (usuario != null){			
			if(usuario.getPerfil() instanceof PerfilAdministrativo){
				viewPrincipal.accesoAdministrativo(usuario);
			}
			if(usuario.getPerfil() instanceof PerfilAdministrador){
				viewPrincipal.accesoAdministrador(usuario);
			}
			if(usuario.getPerfil() instanceof PerfilCuit){
				viewPrincipal.accesoAdministrador(usuario);
			}
		} else viewPrincipal.accesoIncorrecto();
		
		
	}

	@Override
	public void desuscribir() {
		
		UI.getCurrent().getNavigator().navigateTo(ViewDesuscripcion.NAME);
		
	}

}
