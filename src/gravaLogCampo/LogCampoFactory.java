package gravaLogCampo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import comparador.Comparador;
import json.JsonUtil;
import montaMapLog.MontaMapLogInterface;

public class LogCampoFactory implements LogInterface, MontaMapLogInterface {
	
	private static final ObjectWriter MAPPER = new ObjectMapper().writerWithDefaultPrettyPrinter();

	@Override
	public String getLog(Object anterior, Object atual) throws JsonProcessingException {
	    List<Map<String, Object>> resultado;

	    if (anterior != null) {
	        resultado = new Comparador().comparar(anterior, atual);
	    } else {
	        Map<String, Object> inclusoesMap = JsonUtil.toMapAnotado(atual);
	        resultado = new LinkedList<>();
	        for (Map.Entry<String, Object> entry : inclusoesMap.entrySet()) {
	            montaMap(entry.getKey(), entry.getValue(), resultado);
	        }
	    }
	    
	    return MAPPER.writeValueAsString(resultado);
	}

}
