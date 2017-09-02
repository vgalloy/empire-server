package com.vgalloy.empire.webservice.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.webservice.dto.UserIdDto;

/**
 * Created by Vincent Galloy on 01/09/17.
 *
 * @author Vincent Galloy
 */
@Component
public class UserIdSerializer extends StdSerializer<UserIdDto> implements Converter<String, UserIdDto> {

    private static final long serialVersionUID = 4422601073432043944L;

    /**
     * Constructor.
     * For correct invocation of super constructor.
     */
    public UserIdSerializer() {
        super((Class<UserIdDto>) null);
    }

    @Override
    public void serialize(UserIdDto userIdDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(userIdDto.getUserId());
    }

    @Override
    public UserIdDto convert(String s) {
        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setUserId(s);
        return userIdDto;
    }
}
