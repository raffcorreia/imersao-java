package main.java;

import main.java.extractor.Content;
import main.java.extractor.ExtractorImDb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

public class App {
    public static final String URL = "https://imdb-api.com/en/API/Top250Movies/";
    public static final String URL_MOCK = "";// "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_HEART = "\u2665";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    public static void main(String[] args) throws Exception {
        String url = URL.equals("") ? URL_MOCK : URL + args[0];
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        ExtractorImDb extractorImdb = new ExtractorImDb();
        List<Content> contents = extractorImdb.extractContent(body, Arrays.asList(args[2].split("\\|")));

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Content content : contents) {
            stickerGenerator.create(cleanImgURL(content.getImgUrl()), content.getTitle(), "TOPZERA");

            System.out.printf("%s%sTITLE:%s %s%s %s\n",
                    ANSI_BLACK_BACKGROUND, ANSI_WHITE, ANSI_RESET, ANSI_BLUE, content.getTitle(),
                    ANSI_RED + ANSI_HEART + ANSI_RESET);
        }
    }

    private static String cleanImgURL(String url) {
        int paramBeginIndex = url.lastIndexOf("@");
        int typeBeginIndex = url.lastIndexOf(".");
        return url.substring(0, paramBeginIndex + 1) + url.substring(typeBeginIndex, typeBeginIndex + 4);
    }
}