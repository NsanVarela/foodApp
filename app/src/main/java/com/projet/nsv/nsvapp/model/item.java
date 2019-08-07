package com.projet.nsv.nsvapp.model;

import kotlin.reflect.KProperty;

public class item {

    String itemTitle;
    int background;
    int nbFollowers;

    public item (int background, String itemTitle, int nbFollowers) {
        this.itemTitle = itemTitle;
        this.background = background;
        this.nbFollowers = nbFollowers;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public int getBackground() {
        return background;
    }

    public int getNbFollowers() {
        return nbFollowers;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setNbFollowers(int nbFollowers) {
        this.nbFollowers = nbFollowers;
    }
}
