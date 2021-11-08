package uz.rootec.appqrcodebot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResAutoFilial{
    private String filial_id;
    private String name;
    private String state;
    private String logo_sha;
    private String note;
}