package com.example.backendinventory.util;

import java.lang.reflect.Field;

public class ConverterString<T> {
    public ConverterString() {
    }

    public T ConverterLower(T object) throws Exception {
        Field[] campos = object.getClass().getDeclaredFields();
        for (Field field: campos){
            field.setAccessible(true);
            Object valor = field.get(object);
            if(valor != null){
                if(valor.getClass().equals(String.class)){
                    String nuevo_valor = ((String) valor).toLowerCase().trim();
                    field.set(object, nuevo_valor);
                }
            }
        }
        return object;
    }


    public T ConverterUpper(T value) throws Exception {
        Field[] campos = value.getClass().getDeclaredFields();
        for (Field val: campos){
            val.setAccessible(true);
            Object valor = val.get(value);
            if(valor != null){
                if(valor.getClass().equals(String.class)){
                    String nuevo_valor = ((String) valor).toUpperCase().trim();
                    val.set(value, nuevo_valor);
                }
            }
        }
        return value;
    }
}
