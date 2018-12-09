package ifpe.dsc.cartorioprotesto2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_RECEPCAO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Recepcao.PorNumero",
                    query = "SELECT r FROM Recepcao r WHERE r.numero = :numero ORDER BY r.id"
            ) 
                
        }
)
public class Recepcao implements Serializable{
    @Id
    @Column(name = "ID_RECEPCAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NUM_NUMERO", nullable = false)
    private long numero;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_DATA", nullable = false)
    private Date data;
    
    @OneToMany(mappedBy = "recepcao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RECEPCAO")
    private List<Titulo> titulos = new ArrayList<>();
    
    @OneToOne(mappedBy = "recepcao", cascade = CascadeType.ALL, optional = false)
    private Guia guia;  

    @ManyToMany
    @JoinTable(name="TB_RECEPCOES_CREDORES", joinColumns=
    {@JoinColumn(name="ID_RECEPCAO")}, inverseJoinColumns=
      {@JoinColumn(name="ID_CREDOR")})
    private List<Credor> credores = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="TB_RECEPCOES_DEVEDORES", joinColumns=
    {@JoinColumn(name="ID_RECEPCAO")}, inverseJoinColumns=
      {@JoinColumn(name="ID_DEVEDOR")})
    private List<Devedor> devedores = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }
    
    public void addTitulo(Titulo titulo) {
        if (!this.titulos.contains(titulo)){
            this.titulos.add(titulo);
        }
    }
    
    public List<Titulo> getTitulos() {
        return titulos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recepcao)) {
            return false;
        }
        Recepcao other = (Recepcao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public List<Credor> getCredores() {
        return this.credores;
    }
    
    public void addCredor(Credor credor) {
        if (!this.credores.contains(credor)) {
            this.credores.add(credor);
        }
    }
    
    public void removeCredor(Credor credor){
        if (!this.credores.contains(credor)){
            this.credores.remove(credor);
        }
    }
    
    public List<Devedor> getDevedores() {
        return this.devedores;
    }
    
    public void addDevedor(Devedor devedor) {
        if (!this.devedores.contains(devedor)) {
            this.devedores.add(devedor);
        }
    }
    
    public void removeDevedor(Devedor devedor){
        if (this.devedores.contains(devedor)){
            this.devedores.remove(devedor);
        }
    }

    @Override
    public String toString() {
        return "jpa.Tag[ id=" + id + ":" + numero + " ]";
    }
}