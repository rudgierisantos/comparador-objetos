package comparador;

import java.util.List;
import java.util.Map;

public class ComparadorMapStrategy implements ComparadorStrategy {
	
	@Override
	public boolean objetoValido(Object antigo, Object novo) {
		return antigo instanceof Map && novo instanceof Map;
	}

	@Override
	public void comparar(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado) {
		Map<String, Object> mapAntigo = (Map<String, Object>) antigo;
		Map<String, Object> mapNovo = (Map<String, Object>) novo;
		for (String key : mapAntigo.keySet()) {
			Comparador.compararRecursivo(campo.isEmpty() ? key : campo + "." + key, mapAntigo.get(key), mapNovo.get(key), resultado);
		}
		
	}

}
