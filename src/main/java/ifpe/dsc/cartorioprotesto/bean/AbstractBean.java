package ifpe.dsc.cartorioprotesto.bean;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public abstract class AbstractBean implements Serializable {	
	
	public boolean getTipoUsuario(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}
	
	public String getLoginUsuario() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal usuario = externalContext.getUserPrincipal();
		if (usuario == null) {
			return "";
		}
		
		return usuario.getName();
	}

}
