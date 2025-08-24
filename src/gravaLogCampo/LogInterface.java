package gravaLogCampo;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface LogInterface {

    String getLog(Object anterior, Object atual) throws JsonProcessingException;

    default String getLog(Object atual) throws JsonProcessingException {
        return getLog(null, atual);
    }
}
