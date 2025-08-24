package comparador;

import java.util.List;
import java.util.Map;

public class ComparadorDefaultStrategy implements ComparadorStrategy {

	@Override
	public boolean objetoValido(Object antigo, Object novo) {
		return true;
	}

	@Override
	public void comparar(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado) {
		montaMap(campo, antigo, novo, resultado);
	}
}
