package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Escrevente;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class EscreventeServico extends Servico<Escrevente> {

    public void salvar(Escrevente object) {
        entityManager.persist(object);
    }

    public void atualizar(Escrevente object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    public void remover(Escrevente object) {
        object = entityManager.merge(object);
        entityManager.remove(object);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Escrevente> getEscrevente() {
        return new ArrayList<Escrevente>();
        //return getEntidades(Escrevente.FIND_ALL_CREDOR);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Escrevente getClienteByCPF(String cpf) {
        return new Escrevente();
        //return super.getEntidade(Escrevente.FIND_BY_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Escrevente criar() {
        return new Escrevente();
    }
}