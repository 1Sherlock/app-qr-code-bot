package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCard {
    private String id;
    private String username;
    private String pan;
    private String expiry;
    private Integer status;
    private String phone;
    private String fullName;
    private Double balance;
    private Boolean sms;
    private Integer pincnt;
    private String aacct;
    private String cardtype;
    private Double holdAmount;
    private Double cashbackAmount;
}
