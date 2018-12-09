package teste.ejb;

import javax.ejb.Stateless;

@Stateless
public class TesteAlo {
    
    public String getMessagem(String texto) {
        return String.format("Guilherme gostoso %s", texto);
    }
    
}
