package json;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final List<JsonCampoStrategy> strategies = Arrays.asList(
        new JsonPrimitivoStrategy(),
        new JsonListStrategy(),
        new JsonObjetoStrategy()
    );

    public static Map<String, Object> toMapAnotado(Object obj) {
        if (obj == null) return Collections.emptyMap();
        Map<String, Object> map = new LinkedHashMap<>();

        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(GravaLogCampo.class)) {
                field.setAccessible(true);
                try {
                    Object valor = field.get(obj);
                    String nome = field.getAnnotation(GravaLogCampo.class).nome();

                    if (valor == null) {
                        map.put(nome, null);
                        continue;
                    }

                    for (JsonCampoStrategy strategy : strategies) {
                        if (strategy.objetoValido(valor)) {
                            map.put(nome, strategy.converte(field, valor));
                            break;
                        }
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
