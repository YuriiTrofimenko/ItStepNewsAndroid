/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.notebookandroid.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Администратор
 */

public class News implements Serializable {

    public Long id;
    public String title;
    public String content;

    public News() {}

    public News(String title, String content) {

        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "org.tyaa.notebook.entity.News[ id=" + id + " ]";
    }
    
}
