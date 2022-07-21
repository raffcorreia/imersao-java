package main.java.extractor;

import main.java.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractorImDb implements Extractor{

    @Override
    public List<Content> extractContent(String json, List<String> idFilter) {
        List<Content> result = new ArrayList<>();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(json);

        for (Map<String, String> movie : movieList) {
            if(idFilter == null || idFilter.contains(movie.get("id"))) {
                result.add(new Content(movie.get("title"), movie.get("image")));
            }
        }

        return result;
    }
}
