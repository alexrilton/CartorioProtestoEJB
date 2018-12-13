package ifpe.dsc.cartorioprotesto.service;

import javax.annotation.security.PermitAll;
import ifpe.dsc.cartorioprotesto.model.Tabeliao;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public Tabeliao getTabeliaoPorNome(String nome) {
        return getEntidade(Tabeliao.TABELIAO_POR_NOME, new Object[]{nome});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @PermitAll
    public Tabeliao criar() {
        return new Tabeliao();
    }
    
    public void criptografarSenha(Tabeliao tabeliao) throws Exception{
        tabeliao.setSenha(criptografarSenha(tabeliao.getSenha()));
    }
    
    private static String criptografarSenha(String senha) throws Exception {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(senha.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("Não foi possível criptografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new Exception("Não foi possível criptografar a senha!");
		}
	}

}