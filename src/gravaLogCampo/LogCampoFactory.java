package gravaLogCampo;

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
				.writeValueAsString(getLog(atual));
	}
	
	public String getLogAsJson(Object anterior, Object atual) throws JsonProcessingException {
		return objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(getLog(anterior, atual));
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
}
