package json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonListStrategy implements JsonCampoStrategy {
    @Override
    public boolean objetoValido(Object valor) {
        return valor instanceof List;
    }

    @Override
    public Object converte(Field field, Object valor) {
        List<?> lista = (List<?>) valor;
        List<Object> resultado = new ArrayList<>();
        for (Object item : lista) {
        	if (isTipoPrimitivo(item)) {
        		resultado.add(item);
			} else {
				resultado.add(JsonUtil.toMapAnotado(item));
			}
        }
        return resultado;
    }
}
