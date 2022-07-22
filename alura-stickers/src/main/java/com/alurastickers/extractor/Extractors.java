package com.alurastickers.extractor;

public enum Extractors {
    IMDB(ExtractorImDb.class),
    NASA(ExtractorNasa.class);

    private final Class<? extends Extractor> extractor;

    Extractors(Class<? extends Extractor> extractor) {
        this.extractor = extractor;
    }

    public Class<? extends Extractor> getExtractor() {
        return extractor;
    }
}
