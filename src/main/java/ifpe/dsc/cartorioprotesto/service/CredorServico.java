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
        return new ArrayList<Credor>();
        //return getEntidades(Credor.FIND_ALL_CREDOR);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Credor getClienteByCPF(String cpf) {
        return new Credor();
        //return super.getEntidade(Credor.FIND_BY_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Credor criar() {
        return new Credor();
    }

}