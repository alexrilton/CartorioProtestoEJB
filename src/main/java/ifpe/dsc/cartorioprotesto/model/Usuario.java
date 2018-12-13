package ifpe.dsc.cartorioprotesto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Usuario extends Entidade implements Serializable {
    
    @NotBlank
    @Size(max = 100)
    @Column(name = "TXT_NOME", length = 100, nullable = false)
    private String nome;
    
    @NotBlank
    @CPF
    @Column(name = "TXT_CPF", nullable = false, unique = true)
    private String cpf;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "TXT_LOGIN")
    private String login;
    
    @NotBlank
    @Size(min = 6, max = 20)
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})", 
            message = "{JPA.Usuario.senha}")
    @Column(name = "TXT_SENHA")
    private String senha;
    
    @NotBlank
    @Column(name = "TXT_GRUPO")
    private String grupo;
    
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}