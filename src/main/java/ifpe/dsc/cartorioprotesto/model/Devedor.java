package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name = "TB_DEVEDOR")
@NamedQueries(
        {
            @NamedQuery(
                    name = Devedor.DEVEDOR_TODOS,
                    query = "SELECT d FROM Devedor d ORDER BY d.nome"
            ),
            @NamedQuery(
                    name = Devedor.DEVEDOR_POR_NOME,
                    query = "SELECT d FROM Devedor d WHERE d.nome LIKE ?1 ORDER BY d.nome"
            )
        }
)
public class Devedor extends Entidade implements Serializable{
    
    public static final String DEVEDOR_TODOS = "DevedorTodos";
    public static final String DEVEDOR_POR_NOME = "DevedorPorNome";
    
    @NotBlank(message = "não pode estar vazio")
    @Size(max = 100, message = "deve conter no maximo 100 caracteres")
    @Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçÁÀÂÃÉÈÍÏÓÔÕÖÚÇ0-9 ]*", message = "caracteres invalidos")
    @Column(name = "TXT_NOME", length = 100, nullable = false, unique = true)
    private String nome;
    
    @NotBlank(message = "não pode estar vazio")
    @CPF(message = "CPF Invalido")
    @Column(name = "TXT_CPF", nullable = false, unique = true)
    private String cpf;
    
    @ManyToMany(mappedBy = "devedores")
    private List<Recepcao> recepcoes;

    public List<Recepcao> getRecepcoes() {
        return recepcoes;
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
}
