package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCode {
    private Integer code;
    private String message;
    private String requestId;
}
