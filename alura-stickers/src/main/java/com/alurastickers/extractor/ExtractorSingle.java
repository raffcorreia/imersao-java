package com.alurastickers.extractor;

import java.util.Arrays;
import java.util.List;

import static com.alurastickers.Util.*;


public class ExtractorSingle extends Extractor {

    public ExtractorSingle() {
        super();
    }

    public List<Content> extractContent(String url, List<String> idFilter) {
        return List.of(new Content(SINGLE_IMG_TITLE, url));
    }

    @Override
    public List<Content> fetchAndExtractContent() {
        return List.of(new Content(SINGLE_IMG_TITLE, SINGLE_IMG_URL));
    }

    @Override
    protected String processUrl(String url) {
        int paramBeginIndex = url.lastIndexOf("@");
        int typeBeginIndex = url.lastIndexOf(".");
        return url.substring(0, paramBeginIndex + 1) + url.substring(typeBeginIndex, typeBeginIndex + 4);
    }
}
