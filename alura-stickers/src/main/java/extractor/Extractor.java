package main.java.extractor;

import java.util.List;

public interface Extractor {
    List<Content> extractContent(String json, List<String> idFilter);
}
