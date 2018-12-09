package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Guia;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class GuiaServico extends Servico<Guia> {

    public void salvar(Guia object) {
        entityManager.persist(object);
    }

    public void atualizar(Guia object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    public void remover(Guia object) {
        object = entityManager.merge(object);
        entityManager.remove(object);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Guia> getGuia() {
        return new ArrayList<Guia>();
        //return getEntidades(Guia.FIND_ALL_CREDOR);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Guia getClienteByCPF(String cpf) {
        return new Guia();
        //return super.getEntidade(Guia.FIND_BY_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Guia criar() {
        return new Guia();
    }

}