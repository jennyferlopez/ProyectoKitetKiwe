package com.fup.jennyferlopez.proyectokitetkiwe.models;

/**
 * Created by jlo-1 on 16/03/2018.
 */

public class ImagenR {
    String text_uno;
    String text_dos;
    int img_uno;
    int img_dos;

    public ImagenR(String text_uno, String text_dos, int img_uno, int img_dos) {
        this.text_uno = text_uno;
        this.text_dos = text_dos;
        this.img_uno = img_uno;
        this.img_dos = img_dos;
    }

    public ImagenR() {
    }

    public String getText_uno() {
        return text_uno;
    }

    public void setText_uno(String text_uno) {
        this.text_uno = text_uno;
    }

    public String getText_dos() {
        return text_dos;
    }

    public void setText_dos(String text_dos) {
        this.text_dos = text_dos;
    }

    public int getImg_uno() {
        return img_uno;
    }

    public void setImg_uno(int img_uno) {
        this.img_uno = img_uno;
    }

    public int getImg_dos() {
        return img_dos;
    }

    public void setImg_dos(int img_dos) {
        this.img_dos = img_dos;
    }
}
