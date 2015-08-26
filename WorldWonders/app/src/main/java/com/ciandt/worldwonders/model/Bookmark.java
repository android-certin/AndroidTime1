package com.ciandt.worldwonders.model;

import java.io.Serializable;

/**
 * Created by jfranco on 8/26/15.
 */
public class Bookmark implements Serializable {
    private int id;
    private int idWonders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWonders() {
        return idWonders;
    }

    public void setIdWonders(int idWonders) {
        this.idWonders = idWonders;
    }
}
