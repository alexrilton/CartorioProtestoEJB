package ifpe.dsc.cartorioprotesto2.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_TITULO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Titulo.PorRecepcao",
                    query = "SELECT t FROM Titulo t WHERE t.recepcao.id = :idRecepcao ORDER BY t.id"
            )
        }
)
public class Titulo implements Serializable{
    @Id
    @Column(name = "ID_TITULO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 100)
    @Column(name = "TXT_NATUREZA", length = 100, nullable = false, unique = false)
    private String natureza;
    
    @DecimalMin("0.1")
    @NotNull
    @Column(name = "NUM_VALOR", length = 14, nullable = false, unique = false)
    private double valor;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RECEPCAO", referencedColumnName = "ID_RECEPCAO")
    private Recepcao recepcao;

    public void setRecepcao(Recepcao recepcao) {
        this.recepcao = recepcao;
    }

    public Recepcao getRecepcao() {
        return recepcao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Titulo)) {
            return false;
        }
        Titulo other = (Titulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Tag[ id=" + id + ":" + natureza + " ]";
    }
}