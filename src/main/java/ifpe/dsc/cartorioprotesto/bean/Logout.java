package ifpe.dsc.cartorioprotesto.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "logoutBean")
@javax.faces.bean.RequestScoped
public class Logout extends AbstractBean {

    private static final String REDIRECT_TRUE = "?faces-redirect=true";
    private static final String PAGINA_PRINCIPAL = "/paginas/index.xhtml" + REDIRECT_TRUE;
    private static final long serialVersionUID = -7437667367775973347L;

    public void efetuarLogout() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.invalidate();
        ec.redirect(ec.getApplicationContextPath() + PAGINA_PRINCIPAL);
    }

}
