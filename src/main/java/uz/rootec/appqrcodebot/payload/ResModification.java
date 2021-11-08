package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResModification {
    private String modification_id;
    private String name;
    private String price;
    private String producing;
    private String fuel_consumption;
    private String horsepower;
    private String acceleration;
    private String transmission;
    private String descriptions;
    private String min_price;
}
