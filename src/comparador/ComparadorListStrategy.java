package comparador;

import java.util.List;
import java.util.Map;

import montaMapLog.MontaMapLogInterface;

public class ComparadorListStrategy implements ComparadorStrategy {

	@Override
	public boolean objetoValido(Object antigo, Object novo) {
		return antigo instanceof List && novo instanceof List;
	}

	@Override
	public void comparar(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado) {
		List<?> listaAntiga = (List<?>) antigo;
		List<?> listaNova = (List<?>) novo;
		if (!listaAntiga.equals(listaNova)) {
			montaMap(campo, listaAntiga, listaNova, resultado);
		}
	}
}
