package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TB_GUIA")
@NamedQueries(
        {
            @NamedQuery(
                    name = Guia.GUIA_TODAS,
                    query = "SELECT g FROM Guia g ORDER BY g.numero"
            ),
            @NamedQuery(
                    name = Guia.GUIA_POR_NUMERO,
                    query = "SELECT g FROM Guia g WHERE g.numero LIKE ?1 ORDER BY g.id"
            ),
            @NamedQuery(
                    name = Guia.GUIA_POR_RECEPCAO,
                    query = "SELECT g FROM Guia g WHERE g.recepcao.id = ?1 ORDER BY g.id"
            )
        }
)
public class Guia extends Entidade implements Serializable{
    
    public static final String GUIA_TODAS = "GuiaTodas";
    public static final String GUIA_POR_NUMERO = "GuiaPorNumero";
    public static final String GUIA_POR_RECEPCAO = "GuiaPorRecepcao";
    
    @NotNull
    @Column(name = "NUM_NUMERO", nullable = false)
    private long numero;
    
    @DecimalMin("0.1")    
    @Column(name = "NUM_VALOR", nullable = false)
    private double valor;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_DATA", nullable = false)
    private Date data;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_RECEPCAO", referencedColumnName = "ID")
    private Recepcao recepcao;
    
    public long getNumero() {
        return numero;
    }

    public Recepcao getRecepcao() {
        return recepcao;
    }

    public void setRecepcao(Recepcao recepcao) {
        this.recepcao = recepcao;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    } 
   
}