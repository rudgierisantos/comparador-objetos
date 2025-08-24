package gravaLogCampo;

import java.util.List;
import java.util.Map;

import comparador.Comparador;

public class AlteracaoLogFactory {
	public List<Map<String, Object>> logar(Object antigo, Object anterior){
		return new Comparador().comparar(antigo, anterior);
	}
}
