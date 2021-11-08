package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBodyHumo {
    private Integer code;
    private String message;
    private String requestId;
    private String primaryAccountNumber;
    private String institutionId;
    private String expiry;
    private Boolean isSmsOn;
    private String phone;
    private String nameOnCard;
    private String cardholderId;
    private ResStatus status;
    private Double availableAmount;

    private String type;
    private String title;
    private String traceId;

    public ResBodyHumo(Integer code, String message, String requestId, String primaryAccountNumber, String institutionId, String expiry, Boolean isSmsOn, String phone, String nameOnCard, String cardholderId, ResStatus status, Double availableAmount){
        this.code = code;
        this.message = message;
        this.requestId = requestId;
        this.primaryAccountNumber = primaryAccountNumber;
        this.institutionId = institutionId;
        this.expiry = expiry;
        this.isSmsOn = isSmsOn;
        this.phone = phone;
        this.nameOnCard = nameOnCard;
        this.cardholderId = cardholderId;
        this.status = status;
        this.availableAmount = availableAmount;
    }

    public ResBodyHumo(String type, String title, Integer status, String traceId){
        this.type = type;
        this.title = title;
        this.status.setActionCode(status.toString());
        this.traceId = traceId;
    }

}
