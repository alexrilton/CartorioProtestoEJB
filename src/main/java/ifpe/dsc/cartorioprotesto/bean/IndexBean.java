package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.service.CredorServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "indexBean")
@javax.faces.bean.RequestScoped
public class IndexBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private CredorServico credorServico;


    @PostConstruct
    public void init() {
        
    } 
}
