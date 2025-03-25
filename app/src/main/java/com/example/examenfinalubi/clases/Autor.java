package com.example.examenfinalubi.clases;

public class Autor {
    private String nombres;
    private String filiacion;

    public Autor(String nombres, String filiacion) {
        this.nombres = nombres;
        this.filiacion = filiacion;
    }
    public String getNombres() {
        return nombres;
    }

    public String getFiliacion() {
        return filiacion;
    }

}
