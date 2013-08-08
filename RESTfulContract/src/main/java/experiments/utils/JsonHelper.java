package experiments.utils;

import org.codehaus.jackson.map.ObjectMapper;

import experiments.exceptions.JsonException;

/**
 * @author zlatka
 * 
 */
public class JsonHelper<T> {

    private Class<T> type;

    public JsonHelper(Class<T> clazz) {
        this.type = clazz;
    }

    public String toJson(T object) throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonException();
        }
    }

    public T fromJson(String json) throws JsonException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T obj = (T) mapper.readValue(json, type);
            return obj;
        } catch (Exception e) {
            throw new JsonException();
        }
    }
}
