package com.note.demo.payload.request;

import com.note.demo.models.EStatus;
import com.note.demo.models.EType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * Note Request
 * Arthur: kientt
 */
public class NoteRequest {

    private String title;

    private String note;

    private MultipartFile file;

    private List<CheckboxRequest> checkboxList;

    @Enumerated(EnumType.ORDINAL)
    private EStatus status;

    @Enumerated(EnumType.ORDINAL)
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
}
