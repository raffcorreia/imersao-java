package com.alurastickers;

import com.alurastickers.extractor.Content;
import com.alurastickers.extractor.Extractor;
import com.alurastickers.extractor.ExtractorImDb;
import com.alurastickers.extractor.ExtractorNasa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_HEART = "\u2665";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    public static void main(String[] args) throws Exception {

        Client client = new Client();

        Extractor extractorImdb = new ExtractorImDb(args[0]);
        Extractor extractorNasa = new ExtractorNasa(args[1]);

        List<Content> contents = new ArrayList<>();

        contents.addAll(extractorImdb.fetchAndExtractContent(Arrays.asList(args[2].split("\\|"))));
        contents.addAll(extractorNasa.fetchAndExtractContent(null));

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Content content : contents) {
            stickerGenerator.create(content.getImgUrl(), content.getTitle(), "TOPZERA");

            System.out.printf("%s%sTITLE:%s %s%s %s\n",
                    ANSI_BLACK_BACKGROUND, ANSI_WHITE, ANSI_RESET, ANSI_BLUE, content.getTitle(),
                    ANSI_RED + ANSI_HEART + ANSI_RESET);
        }
    }
}