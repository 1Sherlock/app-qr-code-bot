package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBody {
    private ResCard card;
    private Integer code;
    private String message;
    private String requestId;
}
