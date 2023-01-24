package com.ktxdevelopment.bailyapi.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Mapper extends ModelMapper {

    public <D> D mapComplex(Object source, Class<D> destination) {
        Type type = new TypeToken<D>(){}.getType();
        return map(source, type);
    }


    public <D> List<D> mapList(Object source, Class<D> destination) {
        Type type = new TypeToken<List<D>>(){}.getType();
        return map(source, type);
    }
}
