package comparador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import json.JsonUtil;

public class Comparador {

	private static final List<ComparadorStrategy> strategies = new ArrayList<>(Arrays.asList(
			new ComparadorMapStrategy(), 
			new ComparadorListStrategy(), 
			new ComparadorDefaultStrategy())
	);

	public List<Map<String, Object>> comparar(Object antigoObj, Object novoObj) {
		return comparar(JsonUtil.toMapAnotado(antigoObj), JsonUtil.toMapAnotado(novoObj));
	}

	private List<Map<String, Object>> comparar(Map<String, Object> antigo, Map<String, Object> novo) {
		List<Map<String, Object>> resultado = new ArrayList<>();
		compararRecursivo("", antigo, novo, resultado);
		return resultado;
	}

	static void compararRecursivo(String path, Object antigo, Object novo, List<Map<String, Object>> resultado) {
		if (Objects.equals(antigo, novo))
			return;

		for (ComparadorStrategy strategy : strategies) {
			if (strategy.objetoValido(antigo, novo)) {
				strategy.comparar(path, antigo, novo, resultado);
				return;
			}
		}
	}
}