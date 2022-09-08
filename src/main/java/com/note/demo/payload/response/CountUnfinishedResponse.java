package com.note.demo.payload.response;


/**
 * Note Response
 * Arthur: kientt
 */
public class CountUnfinishedResponse {

    private Long unfinishedNoteCount;

    public CountUnfinishedResponse(Long unfinishedNoteCount) {
        this.unfinishedNoteCount = unfinishedNoteCount;
    }
    public CountUnfinishedResponse(){}

    public Long getUnfinishedNoteCount() {
        return unfinishedNoteCount;
    }

    public void setUnfinishedNoteCount(Long unfinishedNoteCount) {
        this.unfinishedNoteCount = unfinishedNoteCount;
    }

}
