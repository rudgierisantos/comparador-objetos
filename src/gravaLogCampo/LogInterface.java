package gravaLogCampo;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface LogInterface {

    String getLog(Object anterior, Object atual) throws JsonProcessingException;

    default String getLog(Object atual) throws JsonProcessingException {
        return getLog(null, atual);
    }
    
    default String getLog(List<?> objetos) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < objetos.size(); i++) {
            Object obj = objetos.get(i);
            sb.append(getLog(obj));
            if (i < objetos.size() - 1) sb.append(",\n");
        }

        sb.append("\n]");
        return sb.toString();
    }
    
    default String getLog(List<?> anteriores, List<?> atuais) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        int size = Math.max(anteriores.size(), atuais.size());
        boolean hasChanges = false;

        for (int i = 0; i < size; i++) {
            Object antigo = i < anteriores.size() ? anteriores.get(i) : null;
            Object atual = i < atuais.size() ? atuais.get(i) : null;

            String itemLog = getLog(antigo, atual);
            if (!itemLog.equals("[]")) {
                if (hasChanges) sb.append(",\n");
                sb.append(itemLog);
                hasChanges = true;
            }
        }

        sb.append("\n]");
        return hasChanges ? sb.toString() : "[]";
    }
}
