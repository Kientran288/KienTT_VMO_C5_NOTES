package com.note.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Checkbox entity
 * Arthur: kientt
 */
@Entity
@Table(name = "checkbox")
public class Checkbox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "checked")
    private long checked;

    @Column(name = "content")
    private String content;

    public Checkbox() {
    }

    public Checkbox(String content, long checked) {
        this.content = content;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public long getChecked() {
        return checked;
    }

    public void setChecked(long checked) {
        this.checked = checked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
