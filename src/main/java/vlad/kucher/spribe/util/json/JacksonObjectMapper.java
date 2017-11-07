package vlad.kucher.spribe.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Vlad on 07.11.2017.
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final ObjectMapper MAPPER = new JacksonObjectMapper();

    private JacksonObjectMapper() {
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
