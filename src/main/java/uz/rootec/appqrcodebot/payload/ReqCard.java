package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCard {
    private String requestId;
    private String cardNumber;
    private String expiryDate;

    public ReqCard(String cardNumber, String expiryDate){
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }
}
