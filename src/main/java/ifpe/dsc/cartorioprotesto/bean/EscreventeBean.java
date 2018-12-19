package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Escrevente;
import ifpe.dsc.cartorioprotesto.service.EscreventeServico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "escreventeBean")
@javax.faces.bean.RequestScoped
public class EscreventeBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private EscreventeServico escreventeServico;

    private List<Escrevente> escreventes;

    private String buscarPorNome;
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private double salario;
    private int comissao;
    private int cargaHorariaSemanal;
    private Date dataAdmissao;

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getComissao() {
        return comissao;
    }

    public void setComissao(int comissao) {
        this.comissao = comissao;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Escrevente> getEscreventes() {
        return escreventes;
    }

    public void setEscreventes(List<Escrevente> escreventes) {
        this.escreventes = escreventes;
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
            escreventes = escreventeServico.getEscreventes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNome() {
        try {
            Escrevente escrevente = escreventeServico.getEscreventePorNome(buscarPorNome);
            escreventes.clear();
            escreventes.add(escrevente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void salvar() {
        try {
            Escrevente escrevente = new Escrevente();
            escrevente.setNome(this.nome);
            escrevente.setCpf(this.cpf);
            escrevente.setLogin(this.login);
            escrevente.setSenha(this.senha);
            escrevente.setSalario(this.salario);
            escrevente.setComissao(this.comissao);
            escrevente.setCargaHorariaSemanal(this.cargaHorariaSemanal);
            escrevente.setDataAdmissao(this.dataAdmissao);
            
            escreventeServico.salvar(escrevente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}