package com.anachronda.www.thecompletedndcompanion;

import com.activeandroid.serializer.TypeSerializer;

import java.lang.Class;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringListSerializer extends TypeSerializer {
    @Override
    public java.lang.Class<?> getDeserializedType(){
        return List.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public String serialize(Object o) {
        if (o == null) {
            return null;
        }

        String str = "";
        for (String s : (List<String>) o) {
            str = str + s + "|";
        }

        return str;
    }

    @Override
    public List<String> deserialize(Object o){
        if (o == null) {
            return null;
        }

        List<String> strings = new ArrayList<>();
        String str = (String) o;
        Collections.addAll(strings, str.split("|"));

        return strings;
    }
}
