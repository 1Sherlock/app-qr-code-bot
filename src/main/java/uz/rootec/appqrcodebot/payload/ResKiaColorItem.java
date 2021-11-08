package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResKiaColorItem {
    private Integer id;
    private String name;
    private String colorFolder;
    private String icon;
    private Double price;
    private String metalic;
}
