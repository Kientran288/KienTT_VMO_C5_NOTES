package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Note entity
 * Arthur: kientt
 */
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    private String note;

    @Column(name = "check_box_ids")
    private String checkBoxIds;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "created_by")
    private String createdBy;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private EType type;

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public Note() {

    }

    public Note(String title, String note, String checkBoxIds, String imageLink, EType type, EStatus status, String createdBy) {
        this.title = title;
        this.note = note;
        this.imageLink = imageLink;
        this.checkBoxIds = checkBoxIds;
        this.type = type;
        this.status = status;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCheckBoxIds() {
        return checkBoxIds;
    }

    public void setCheckBoxIds(String checkBoxIds) {
        this.checkBoxIds = checkBoxIds;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
