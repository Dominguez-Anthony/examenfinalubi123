package com.example.examenfinalubi.clases;

public class Galley {
    private String label;
    private String UrlViewGalley;

    public Galley(String label,String UrlViewGalley) {
        this.label = label;
        this.UrlViewGalley = UrlViewGalley;
    }

    public String getLabel() {
        return label;
    }

    public String getUrlViewGalley() {
        return UrlViewGalley;
    }
}
