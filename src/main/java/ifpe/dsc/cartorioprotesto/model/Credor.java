package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "TB_CREDOR")
@SqlResultSetMapping(
        name = "CredorQtdRecepcao",
        classes = @ConstructorResult(
                targetClass = CredorQtdRecepcao.class,
                columns = {
                    @ColumnResult(name = "id", type = Long.class),
                    @ColumnResult(name = "nome"),
                    @ColumnResult(name = "cpf"),
                    @ColumnResult(name = "telefone"),
                    @ColumnResult(name = "email"),
                    @ColumnResult(name = "numRecepcoes", type = Long.class)}
        )
)
@NamedQueries(
        {
            @NamedQuery(
                    name = "Credor.PorNome",
                    query = "SELECT c FROM Credor c WHERE c.nome LIKE :nome ORDER BY c.id"
            )
        }
)
@NamedNativeQueries({
    @NamedNativeQuery(name = "quantidadeRecepcoes", query = "SELECT c.ID_CREDOR as id, c.TXT_NOME as nome, c.TXT_CPF as cpf, c.TXT_TELEFONE as telefone, c.TXT_EMAIL as email, count(c.ID_CREDOR) as numRecepcoes FROM tb_credor c inner join  tb_recepcoes_credores rc on c.ID_CREDOR = rc.ID_CREDOR group by rc.ID_CREDOR order by numRecepcoes desc, nome", resultSetMapping = "CredorQtdRecepcao")
})
public class Credor implements Serializable{
    @Id
    @Column(name = "ID_CREDOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 100, message = "deve conter no maximo 100 caracteres")
    @Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçÁÀÂÃÉÈÍÏÓÔÕÖÚÇ0-9 ]*", message = "caracteres invalidos")
    @Column(name = "TXT_NOME", length = 100, nullable = false, unique = true)
    private String nome;
    
    @NotBlank
    @CPF(message = "CPF Invalido")
    @Column(name = "TXT_CPF")
    private String cpf;
    
    @Column(name = "TXT_TELEFONE", length = 10, nullable = false, unique = true)
    private String telefone;
    
    @NotBlank
    @Email
    @Size(max = 30)
    @Column(name = "TXT_EMAIL", length = 40, nullable = false, unique = true)
    private String email;
    
    @ManyToMany(mappedBy = "credores")
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
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Credor)) {
            return false;
        }
        Credor other = (Credor) object;
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
