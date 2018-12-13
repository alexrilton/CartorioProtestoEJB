package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
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
                    name = Credor.CREDOR_TODOS,
                    query = "SELECT c FROM Credor c ORDER BY c.nome"
            ),
            @NamedQuery(
                    name = Credor.CREDOR_POR_NOME,
                    query = "SELECT c FROM Credor c WHERE c.nome LIKE ?1 ORDER BY c.nome"
            )
        }
)
@NamedNativeQueries({
    @NamedNativeQuery(name = Credor.CREDOR_QTD_RECEPCOES, query = "SELECT c.ID as id, c.TXT_NOME as nome, c.TXT_CPF as cpf, c.TXT_TELEFONE as telefone, c.TXT_EMAIL as email, count(c.ID) as numRecepcoes FROM tb_credor c inner join  tb_recepcoes_credores rc on c.ID = rc.ID_CREDOR group by rc.ID_CREDOR order by numRecepcoes desc, nome", resultSetMapping = "CredorQtdRecepcao")
})
public class Credor extends Entidade implements Serializable{
    
    public static final String CREDOR_TODOS = "CredorTodos";
    public static final String CREDOR_POR_NOME = "CredorPorNome";
    public static final String CREDOR_QTD_RECEPCOES = "CredorQuantidadeRecepcoes";
            
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
}
