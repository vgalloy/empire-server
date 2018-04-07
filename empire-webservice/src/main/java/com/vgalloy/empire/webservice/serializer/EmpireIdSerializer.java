package com.vgalloy.empire.webservice.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.webservice.dto.EmpireIdDto;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
@Component
public class EmpireIdSerializer extends StdSerializer<EmpireIdDto> implements Converter<String, EmpireIdDto> {

    private static final long serialVersionUID = 4422601073432043944L;

    /**
     * Constructor.
     * For correct invocation of super constructor.
     */
    public EmpireIdSerializer() {
        super(EmpireIdDto.class);
    }

    @Override
    public void serialize(EmpireIdDto empireIdDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(empireIdDto.getEmpireId());
    }

    @Override
    public EmpireIdDto convert(String s) {
        EmpireIdDto empireIdDto = new EmpireIdDto();
        empireIdDto.setEmpireId(s);
        return empireIdDto;
    }
}
