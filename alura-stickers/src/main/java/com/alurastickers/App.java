package com.alurastickers;

import com.alurastickers.extractor.Content;
import com.alurastickers.extractor.Extractors;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.alurastickers.Util.IMDB_API_KEY;
import static com.alurastickers.Util.NASA_API_KEY;

public class App {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_HEART = "\u2665";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    public static void main(String[] args) throws Exception {
        IMDB_API_KEY = args[0];
        NASA_API_KEY = args[1];
        Util.setFavoriteMovieListFromString(args[2]);

        List<Content> contents = Stream.of(Extractors.values())
                .map(Extractors::getExtractor)
                .map(p -> {
                    try {
                        return p.getDeclaredConstructor().newInstance().fetchAndExtractContent();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(Collection::stream)
                .toList();

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Content content : contents) {
            stickerGenerator.create(content.getImgUrl(), content.getTitle(), "TOPZERA");

            System.out.printf("%s%sTITLE:%s %s%s %s\n",
                    ANSI_BLACK_BACKGROUND, ANSI_WHITE, ANSI_RESET, ANSI_BLUE, content.getTitle(),
                    ANSI_RED + ANSI_HEART + ANSI_RESET);
        }
    }
}