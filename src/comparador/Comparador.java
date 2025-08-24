package comparador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import json.JsonUtil;

public class Comparador {
	
	private static final List<ComparadorStrategy> strategies = Arrays.asList(
			new ComparadorMapStrategy(),
			new ComparadorListStrategy(), 
			new ComparadorDefaultStrategy()
	);
	
	public List<Map<String, Object>> comparar(Object antigoObj, Object novoObj) {
		Map<String, Object> antigo = JsonUtil.toMapAnotado(antigoObj);
		Map<String, Object> novo = JsonUtil.toMapAnotado(novoObj);

		List<Map<String, Object>> resultado = new ArrayList<>();
		compararRecursivo("", antigo, novo, resultado);
		return resultado;
	}

	static void compararRecursivo(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado) {
		if (Objects.equals(antigo, novo)) return;

		for (ComparadorStrategy strategy : strategies) {
			if(strategy.objetoValido(antigo, novo)) {
				strategy.comparar(campo, antigo, novo, resultado);
				 break;
			}
		}
	}
}