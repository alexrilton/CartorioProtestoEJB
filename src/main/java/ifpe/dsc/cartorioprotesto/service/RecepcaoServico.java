package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Recepcao;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class RecepcaoServico extends Servico<Recepcao> {

    public void salvar(Recepcao object) {
        entityManager.persist(object);
    }

    public void atualizar(Recepcao object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    public void remover(Recepcao object) {
        object = entityManager.merge(object);
        entityManager.remove(object);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Recepcao> getRecepcao() {
        return new ArrayList<Recepcao>();
        //return getEntidades(Recepcao.FIND_ALL_CREDOR);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Recepcao getClienteByCPF(String cpf) {
        return new Recepcao();
        //return super.getEntidade(Recepcao.FIND_BY_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Recepcao criar() {
        return new Recepcao();
    }

}