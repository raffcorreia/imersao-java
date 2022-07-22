package com.alurastickers.extractor;

import java.util.List;

import static com.alurastickers.Util.FAVORITE_MOVIE_IDS;
import static com.alurastickers.Util.IMDB_API_KEY;


public class ExtractorImDb extends Extractor {

    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/{API_KEY}";
//    private static final String URL = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String URL_FIELD = "image";

    public ExtractorImDb() {
        super(IMDB_API_KEY, ID_FIELD, TITLE_FIELD, URL_FIELD);
    }

    public List<Content> extractContent(String json, List<String> idFilter) {
        return super.defaultContentExtractor(json, idFilter);
    }

    @Override
    public List<Content> fetchAndExtractContent() {
        return super.fetchAndExtractContent(URL, FAVORITE_MOVIE_IDS);
    }

    @Override
    protected String processUrl(String url) {
        int paramBeginIndex = url.lastIndexOf("@");
        int typeBeginIndex = url.lastIndexOf(".");
        return url.substring(0, paramBeginIndex + 1) + url.substring(typeBeginIndex, typeBeginIndex + 4);
    }
}
