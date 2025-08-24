package json;

import java.lang.reflect.Field;

public class JsonObjetoStrategy implements JsonCampoStrategy {
    @Override
    public boolean objetoValido(Object valor) {
        return valor != null;
    }

    @Override
    public Object converte(Field field, Object valor) {
        return JsonUtil.toMapAnotado(valor);
    }
}
