package ifpe.dsc.cartorioprotesto2.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
                    name = "Devedor.PorNome",
                    query = "SELECT d FROM Devedor d WHERE d.nome LIKE :nome ORDER BY d.id"
            )
        }
)
public class Devedor implements Serializable{
    @Id
    @Column(name = "ID_DEVEDOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Devedor)) {
            return false;
        }
        Devedor other = (Devedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Tag[ id=" + id + ":" + nome + " ]";
    }
}
