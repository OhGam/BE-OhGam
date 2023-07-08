package chbbo.BEOhGam.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private String message;
    private Object data;

    public MessageDTO() {
        this.data = null;
        this.message = null;
    }
}
