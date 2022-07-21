package com.alurastickers;

import com.alurastickers.extractor.Content;
import com.alurastickers.extractor.Extractor;
import com.alurastickers.extractor.ExtractorImDb;
import com.alurastickers.extractor.ExtractorNasa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static final String URL = "https://imdb-api.com/en/API/Top250Movies/";
    public static final String URL_MOCK = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
    private static final String URL_NASA = "https://api.nasa.gov/planetary/apod?api_key={DEMO_KEY}&start_date=2022-06-12&end_date=2022-06-14";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_HEART = "\u2665";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    public static void main(String[] args) throws Exception {
        String url = URL.equals("") ? URL_MOCK : URL + args[0];
        String urlNasa = URL_NASA.replace("{DEMO_KEY}", args[1]);

        Client client = new Client();

        Extractor extractorImdb = new ExtractorImDb();
        Extractor extractorNasa = new ExtractorNasa();

        List<Content> contents = new ArrayList<>();

        contents.addAll(extractorImdb.extractContent(client.buscaDados(url), Arrays.asList(args[2].split("\\|"))));
        contents.addAll(extractorNasa.extractContent(client.buscaDados(urlNasa), null));

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Content content : contents) {
            stickerGenerator.create(content.getImgUrl(), content.getTitle(), "TOPZERA");

            System.out.printf("%s%sTITLE:%s %s%s %s\n",
                    ANSI_BLACK_BACKGROUND, ANSI_WHITE, ANSI_RESET, ANSI_BLUE, content.getTitle(),
                    ANSI_RED + ANSI_HEART + ANSI_RESET);
        }
    }
}