package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResModel {
    private String model_id;
    private String name;
    private String photo_sha;
    private List<ResColor> colors;
    private List<ResModification> modifications;
    private String dealer_exists;
    private List<String> dealer_ids;
    private String min_price;
}
