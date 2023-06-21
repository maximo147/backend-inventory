package com.example.backendinventory.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionResponse {
    private ArrayList<Map<String, String>> metadata = new ArrayList<>();
    private String message;

    public ExceptionResponse(String type, String code, String date, String message){
        Map<String, String> data = new HashMap<>();
        data.put("type", type);
        data.put("code", code);
        data.put("date", date);
        this.metadata.add(data);
        this.message = message;
    }

    public ArrayList<Map<String, String>> getMetadata() {
        return metadata;
    }

    public String getMessage() {
        return message;
    }
}
