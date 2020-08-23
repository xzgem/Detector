package com.ryoua.common.serialization;

import com.google.gson.Gson;
import com.ryoua.model.common.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
public class GsonUtil {
    private static Gson gson;

    public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }

    public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        Type type = new ParameterizedTypeImpl(Result.class, new Type[]{listType});
        return gson.fromJson(reader, type);
    }
}
