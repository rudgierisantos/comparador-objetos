package json;

import java.lang.reflect.Field;

public interface JsonCampoStrategy {
    boolean objetoValido(Object valor);
    Object converte(Field field, Object valor);
    
	default boolean isTipoPrimitivo(Object valor) {
		return (valor.getClass().isPrimitive()
				|| valor instanceof String 
				|| valor instanceof Number
				|| valor instanceof Boolean 
				|| valor instanceof Character
		);
	}
}