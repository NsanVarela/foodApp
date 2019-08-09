package com.projet.nsv.nsvapp.model;

public class Receipe {

    private Long id;
    private String title;
    private String photo;
    private int note;

    public Receipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Receipe{" +
                "id=" + id +
                ", title=" + title + '\'' +
                ", photo=" + photo + '\'' +
                "; note=" + note +
                '}';
    }
}
