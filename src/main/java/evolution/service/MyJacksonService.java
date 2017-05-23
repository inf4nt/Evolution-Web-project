package evolution.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import evolution.model.message.Message;
import evolution.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 04.05.2017.
 */
@Service
public class MyJacksonService {

    public String objectToJson(Object obj) throws JsonProcessingException {
        if (obj == null)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = mapper.writeValueAsString(obj);
        return jsonObject;
    }

    public Object jsonToObject(String json, Class clazz) throws IOException {
        Object obj = mapper.readValue(json, clazz);
        return obj;
    }

    public List jsonToListUser(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
        List<User> jsonToPersonList = mapper.readValue(json, mapType);
        return jsonToPersonList;
    }

    public List jsonToListMessage(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Message>> mapType = new TypeReference<List<Message>>() {};
        List<Message> jsonToPersonList = mapper.readValue(json, mapType);
        return jsonToPersonList;
    }

    @Autowired
    private ObjectMapper mapper;
}
