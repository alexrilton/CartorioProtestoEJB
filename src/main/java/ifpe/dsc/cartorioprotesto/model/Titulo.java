package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
                    name = Titulo.TITULO_POR_RECEPCAO,
                    query = "SELECT t FROM Titulo t WHERE t.recepcao.id = :idRecepcao ORDER BY t.id"
            )
        }
)
public class Titulo extends Entidade implements Serializable{
    
    public static final String TITULO_POR_RECEPCAO = "TituloPorRecepcao";
    
    @Size(max = 100)
    @Column(name = "TXT_NATUREZA", length = 100, nullable = false, unique = false)
    private String natureza;
    
    @DecimalMin("0.1")
    @NotNull
    @Column(name = "NUM_VALOR", length = 14, nullable = false, unique = false)
    private double valor;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RECEPCAO", referencedColumnName = "ID")
    private Recepcao recepcao;

    public void setRecepcao(Recepcao recepcao) {
        this.recepcao = recepcao;
    }

    public Recepcao getRecepcao() {
        return recepcao;
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
}