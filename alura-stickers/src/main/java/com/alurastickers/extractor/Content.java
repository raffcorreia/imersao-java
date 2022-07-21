package com.alurastickers.extractor;

public class Content {
    private final String title;
    private final String imgUrl;

    public Content(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
