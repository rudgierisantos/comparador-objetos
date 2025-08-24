package montaMapLog;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface MontaMapLogInterface {
	default void montaMap(String campo, Object novo, List<Map<String, Object>> resultado) {
		Map<String, Object> alteracao = new LinkedHashMap<>();
		alteracao.put("campo", campo);
		alteracao.put("valorAtual", novo);
		resultado.add(alteracao);
	}

	default void montaMap(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado) {
		Map<String, Object> alteracao = new LinkedHashMap<>();
		alteracao.put("campo", campo);
		alteracao.put("valorAnterior", antigo);
		alteracao.put("valorAtual", novo);
		resultado.add(alteracao);
	}
}
