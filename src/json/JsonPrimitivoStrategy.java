package json;

import java.lang.reflect.Field;

public class JsonPrimitivoStrategy implements JsonCampoStrategy {
    @Override
	public boolean objetoValido(Object valor) {
		return valor != null && isTipoPrimitivo(valor);

	}

    @Override
    public Object converte(Field field, Object valor) {
        return valor;
    }
}