package com.example.backendinventory.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericoResponse<T> {
    private ArrayList<Map<String, String>> metadata = new ArrayList<>();
    private List<T> data;

    public GenericoResponse(String type, String code, String date, List<T> list){
        Map<String, String> data = new HashMap<>();
        data.put("type", type);
        data.put("code", code);
        data.put("date", date);
        this.metadata.add(data);
        this.data = list;
    }

    public ArrayList<Map<String, String>> getMetadata() {
        return metadata;
    }

    public List<T> getData() {
        return data;
    }
}
