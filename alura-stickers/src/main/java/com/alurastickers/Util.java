package com.alurastickers;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static String IMDB_API_KEY;
    public static String NASA_API_KEY;
    public static List<String> FAVORITE_MOVIE_IDS;
    public static String SINGLE_IMG_URL = "https://flxt.tmsimg.com/assets/p22804_p_v8_av.jpg";
    public static String SINGLE_IMG_TITLE = "The Matrix";

    public static void setFavoriteMovieListFromString(String value) {
        FAVORITE_MOVIE_IDS = Arrays.asList(value.split("\\|"));
    }
}
