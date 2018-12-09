package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Devedor;
import java.util.ArrayList;

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
    public List<Devedor> getDevedor() {
        return new ArrayList<Devedor>();
        //return getEntidades(Devedor.FIND_ALL_CREDOR);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Devedor getClienteByCPF(String cpf) {
        return new Devedor();
        //return super.getEntidade(Devedor.FIND_BY_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Devedor criar() {
        return new Devedor();
    }
}