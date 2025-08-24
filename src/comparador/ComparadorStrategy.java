package comparador;

import java.util.List;
import java.util.Map;

import montaMapLog.MontaMapLogInterface;

public interface ComparadorStrategy extends MontaMapLogInterface {
    boolean objetoValido(Object antigo, Object novo);
    void comparar(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado);
}
