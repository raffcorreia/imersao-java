package com.alurastickers.extractor;

import com.alurastickers.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractorNasa implements Extractor {

    @Override
    public List<Content> extractContent(String json, List<String> nameFilter) {
        List<Content> result = new ArrayList<>();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(json);

        for (Map<String, String> movie : movieList) {
            if(nameFilter == null || nameFilter.contains(movie.get("id"))) {
                result.add(new Content(movie.get("title"), movie.get("url")));
            }
        }

        return result;
    }
}
