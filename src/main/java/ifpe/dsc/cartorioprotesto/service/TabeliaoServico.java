package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Tabeliao;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@LocalBean
public class TabeliaoServico extends Servico<Tabeliao> {

    public void salvar(Tabeliao object) {
        entityManager.persist(object);
    }

    public void atualizar(Tabeliao object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    public void remover(Tabeliao object) {
        object = entityManager.merge(object);
        entityManager.remove(object);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Tabeliao> getTabeliao() {
        return new ArrayList<Tabeliao>();
        //return getEntidades(Tabeliao.FIND_ALL_CREDOR);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Tabeliao getClienteByCPF(String cpf) {
        return new Tabeliao();
        //return super.getEntidade(Tabeliao.FIND_BY_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Tabeliao criar() {
        return new Tabeliao();
    }

}