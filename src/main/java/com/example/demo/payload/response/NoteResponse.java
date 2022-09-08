package com.example.demo.payload.response;

import com.example.demo.models.EStatus;
import com.example.demo.models.EType;
import com.example.demo.payload.request.CheckboxRequest;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * Note Response
 * Arthur: kientt
 */
public class NoteResponse {
    private String title;

    private String note;

    private String filepath;

    private List<CheckboxRequest> checkboxList;

    @Enumerated(EnumType.ORDINAL)
    private EStatus status;

    @Enumerated(EnumType.ORDINAL)
    private EType type;

    private String createdBy;

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

    public String getFile() {
        return filepath;
    }

    public void setFile(String filepath) {
        this.filepath = filepath;
    }

    public List<CheckboxRequest> getCheckboxList() {
        return checkboxList;
    }

    public void setCheckboxList(List<CheckboxRequest> checkboxList) {
        this.checkboxList = checkboxList;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

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
}
