package gravaLogCampo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import json.JsonUtil;
import montaMapLog.MontaMapLogInterface;

public class InclusaoLogFactory implements MontaMapLogInterface {
	
	public List<Map<String, Object>> logar(Object atual) {
		Map<String, Object> inclusoesMap = JsonUtil.toMapAnotado(atual);
		List<Map<String, Object>> inclusoes = new LinkedList<>();

		for (Entry<String, Object> entry : inclusoesMap.entrySet()) {
			montaMap(entry.getKey(), entry.getValue(), inclusoes);
		}

		return inclusoes;
	}
}
