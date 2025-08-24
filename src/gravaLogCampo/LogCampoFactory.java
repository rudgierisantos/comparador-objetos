package gravaLogCampo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import comparador.Comparador;
import json.JsonUtil;
import montaMapLog.MontaMapLogInterface;

public class LogCampoFactory implements MontaMapLogInterface {
	
	private Comparador comparador;
	private ObjectMapper objectMapper;
	
	public LogCampoFactory() {
		comparador = new Comparador();
		objectMapper = new ObjectMapper();
	}
	
	public String getLogAsJson(Object atual) throws JsonProcessingException {
		return objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(getLog(null, atual));
	}
	
	public String getLogAsJson(Object anterior, Object atual) throws JsonProcessingException {
		return objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(getLog(anterior, atual));
	}

	public String getLogAsJson(List<?> objetos) throws JsonProcessingException {
		return objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(getLog(objetos));
	}

	public String getLogAsJson(List<?> anteriores, List<?> atuais) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter()
				.writeValueAsString(getLog(anteriores, atuais));
	}

	private List<Map<String, Object>> getLog(Object anterior, Object atual) {
		List<Map<String, Object>> resultado;

		if (anterior != null) {
			resultado = comparador.comparar(anterior, atual);
		} else {
			Map<String, Object> inclusoesMap = JsonUtil.toMapAnotado(atual);
			resultado = new LinkedList<>();
			for (Map.Entry<String, Object> entry : inclusoesMap.entrySet()) {
				montaMap(entry.getKey(), entry.getValue(), resultado);
			}
		}
		return resultado;
	}

	private List<Map<String, Object>> getLog(Object atual) {
		return getLog(null, atual);
	}

	private List<Map<String, Object>> getLog(List<?> objetos) {
		List<Map<String, Object>> resultado = new ArrayList<>();
		for (Object obj : objetos) {
			List<Map<String, Object>> itemList = getLog(obj);
			if (!itemList.isEmpty()) {
				resultado.addAll(itemList);
			}
		}
		return resultado;
	}

	private List<Map<String, Object>> getLog(List<?> anteriores, List<?> atuais) {
		List<Map<String, Object>> resultado = new ArrayList<>();
		int size = Math.max(anteriores.size(), atuais.size());

		for (int i = 0; i < size; i++) {
			Object antigo = i < anteriores.size() ? anteriores.get(i) : null;
			Object atual = i < atuais.size() ? atuais.get(i) : null;

			List<Map<String, Object>> itemList = getLog(antigo, atual);
			if (!itemList.isEmpty()) {
				resultado.addAll(itemList);
			}
		}
		return resultado;
	}
}
