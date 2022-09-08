package com.example.demo.payload.request;

/**
 * Checkbox Request
 * Arthur: kientt
 */
public class CheckboxRequest {
    private String content;

    private long checked;

    public CheckboxRequest(String content, long checked) {
        this.content = content;
        this.checked = checked;
    }

    public CheckboxRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getChecked() {
        return checked;
    }

    public void setChecked(long checked) {
        this.checked = checked;
    }
}
