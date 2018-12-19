package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Devedor;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class DevedorServico extends Servico<Devedor> {

    public void salvar(Devedor object) {
        entityManager.persist(object);
    }

    public void atualizar(Devedor object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    public void remover(Devedor object) {
        object = entityManager.merge(object);
        entityManager.remove(object);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Devedor> getDevedores() {
        return getEntidades(Devedor.DEVEDOR_TODOS);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Devedor getDevedorPorNome(String nome) {
        return super.getEntidade(Devedor.DEVEDOR_POR_NOME, new Object[]{nome});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Devedor criar() {
        return new Devedor();
    }
}