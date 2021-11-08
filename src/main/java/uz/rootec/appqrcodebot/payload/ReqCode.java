package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCode {
    private String messageId;
    private String phoneNumber;
    private String message;
    private String method;
    private String sourceSystem;
}
