package ifpe.dsc.cartorioprotesto.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Guia;

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
    public List<Guia> getGuias() {
        return getEntidades(Guia.GUIA_TODAS);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Guia getGuiaPorNumero(Long numero) {
        return super.getEntidade(Guia.GUIA_POR_NUMERO, new Object[]{numero});
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public List<Guia> getGuiasPorRecepcao(String idGuia) {
        return super.getEntidades(Guia.GUIA_POR_RECEPCAO, new Object[]{idGuia});
    }


    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Guia criar() {
        return new Guia();
    }

}