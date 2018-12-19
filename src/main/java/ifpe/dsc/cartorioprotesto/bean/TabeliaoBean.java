package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Tabeliao;
import ifpe.dsc.cartorioprotesto.service.TabeliaoServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "tabeliaoBean")
@javax.faces.bean.RequestScoped
public class TabeliaoBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private TabeliaoServico tabeliaoServico;

    private List<Tabeliao> tabelioes;

    private String buscarPorNome;

    public List<Tabeliao> getTabelioes() {
        return tabelioes;
    }

    public void setTabelioes(List<Tabeliao> tabelioes) {
        this.tabelioes = tabelioes;
    }

    public String getBuscarPorNome() {
        return buscarPorNome;
    }

    public void setBuscarPorNome(String buscarPorNome) {
        this.buscarPorNome = buscarPorNome;
    }

    @PostConstruct
    public void init() {
        listar();
    } 

    public void listar() {
        try {
            tabelioes = tabeliaoServico.getTabelioes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNome() {
        try {
            Tabeliao tabeliao = tabeliaoServico.getTabeliaoPorNome(buscarPorNome);
            tabelioes.clear();
            tabelioes.add(tabeliao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}