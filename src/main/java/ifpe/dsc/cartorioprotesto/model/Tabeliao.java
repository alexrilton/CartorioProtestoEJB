package ifpe.dsc.cartorioprotesto.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TABELIAO")
@DiscriminatorValue(value = "T")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName = "ID")
@NamedQueries(
        {
            @NamedQuery(
                    name = Tabeliao.TABELIAO_POR_NOME,
                    query = "SELECT t FROM Tabeliao t WHERE t.nome LIKE ?1 ORDER BY t.nome"
            )
        }
)
public class Tabeliao extends Usuario {
    
    public static final String TABELIAO_POR_NOME = "TABELIAO_POR_NOME";
    
    @Column(name = "REGISTRO_TJ", nullable = false)
    private long registroTJ;

    public long getRegistroTJ() {
        return registroTJ;
    }

    public void setRegistroTJ(long registroTJ) {
        this.registroTJ = registroTJ;
    }
    
}