package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResColor {
    private String color_id;
    private String name;
    private String hex_value;
    private String note;
    private String photo_sha;
}
