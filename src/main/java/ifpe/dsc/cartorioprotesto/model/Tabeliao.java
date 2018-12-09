package ifpe.dsc.cartorioprotesto.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TABELIAO")
@DiscriminatorValue(value = "T")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID_USUARIO")
public class Tabeliao extends Usuario {
    
    @Column(name = "REGISTRO_TJ", nullable = false)
    private long registroTJ;

    public long getRegistroTJ() {
        return registroTJ;
    }

    public void setRegistroTJ(long registroTJ) {
        this.registroTJ = registroTJ;
    }
    
}