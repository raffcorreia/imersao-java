package com.alurastickers.extractor;

import java.util.List;

import static com.alurastickers.Util.NASA_API_KEY;

public class ExtractorNasa extends Extractor {

    private static final String URL = "https://api.nasa.gov/planetary/apod?api_key={API_KEY}&start_date=2022-06-12&end_date=2022-06-14";
    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String URL_FIELD = "url";

    public ExtractorNasa() {
        super(NASA_API_KEY, ID_FIELD, TITLE_FIELD, URL_FIELD);
    }

    @Override
    public List<Content> extractContent(String json, List<String> nameFilter) {
        return super.defaultContentExtractor(json, nameFilter);
    }

    @Override
    public List<Content> fetchAndExtractContent(List<String> idFilter) {
        return super.fetchAndExtractContent(URL, idFilter);
    }
}
