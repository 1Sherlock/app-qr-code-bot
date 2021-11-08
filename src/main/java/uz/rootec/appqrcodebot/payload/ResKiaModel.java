package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResKiaModel {
    private String name;
    private String url;
    private String configurator_url;
    private String options_url;
    private String image;
    private String minPrice;
    private String pdf;
    private String techPdf;
    private Integer soon;
}
