package com.alurastickers;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static String IMDB_API_KEY;
    public static String NASA_API_KEY;
    public static List<String> FAVORITE_MOVIE_IDS;

    public static void setFavoriteMovieListFromString(String value) {
        FAVORITE_MOVIE_IDS = Arrays.asList(value.split("\\|"));
    }
}
