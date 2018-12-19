package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "TB_ESCREVENTE")
@DiscriminatorValue(value = "E")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName = "ID")
public class Escrevente extends Entidade implements Serializable {
    
    public static final String ESCREVENTE_POR_NOME = "EscreventePorNome";
    
    @DecimalMin("0.1")
    @Column(name = "NUM_SALARIO", nullable = false)
    private double salario;
    
    @Column(name = "NUM_COMISSAO", nullable = false)
    private int comissao;
    
    @Column(name = "CARGA_HORARIA_SEMANAL", nullable = false)
    private int cargaHorariaSemanal;
    
    @Column(name = "DATA_ADMISSAO", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
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
    
}