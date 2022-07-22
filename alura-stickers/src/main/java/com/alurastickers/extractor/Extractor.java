package com.alurastickers.extractor;

import com.alurastickers.Client;
import com.alurastickers.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Extractor {

    protected final String API_KEY_LABEl = "{API_KEY}";
    private final String apiKey;
    private final String idField;
    private final String titleField;
    private final String urlField;

    protected Extractor(String apiKey, String idField, String titleField, String urlField) {
        this.apiKey = apiKey;
        this.idField = idField;
        this.titleField = titleField;
        this.urlField = urlField;
    }

    public abstract List<Content> extractContent(String json, List<String> idFilter);
    public abstract List<Content> fetchAndExtractContent();
    protected List<Content> fetchAndExtractContent(String url, List<String> idFilter) {
        return this.defaultContentExtractor(this.fetch(url), idFilter);
    }

    protected String fetch(String url) {
        Client client = new Client();
        return client.getData(url.replace(API_KEY_LABEl, this.apiKey));
    }

    protected List<Content> defaultContentExtractor(String json, List<String> idFilter) {
        List<Content> result = new ArrayList<>();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(json);

        for (Map<String, String> movie : movieList) {
            if(idFilter == null || idFilter.contains(processId(movie.get(this.idField)))) {
                result.add(new Content(processTitle(movie.get(this.titleField)), processUrl(movie.get(this.urlField))));
            }
        }

        return result;
    }

    protected String processUrl(String url) {
        return url;
    }

    protected String processTitle(String title) {
        return title;
    }

    protected String processId(String id) {
        return id;
    }
}
