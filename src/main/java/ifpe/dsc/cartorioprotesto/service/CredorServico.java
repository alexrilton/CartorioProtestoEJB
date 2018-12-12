package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Credor;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class CredorServico extends Servico<Credor> {

    public void salvar(Credor object) {
        entityManager.persist(object);
    }

    public void atualizar(Credor object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    public void remover(Credor object) {
        object = entityManager.merge(object);
        entityManager.remove(object);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Credor> getCredor() {
        return getEntidades(Credor.CREDOR_TODOS);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Credor getCredorPorNome(String nome) {
        return super.getEntidade(Credor.CREDOR_POR_NOME, new Object[]{nome});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Credor criar() {
        return new Credor();
    }

}