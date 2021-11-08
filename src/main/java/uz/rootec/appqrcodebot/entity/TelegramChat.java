package uz.rootec.appqrcodebot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.rootec.appqrcodebot.entity.enums.TelegramChatStatus;
import uz.rootec.appqrcodebot.entity.template.AbstractEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TelegramChat extends AbstractEntity {
    private Long chatId;

    private String language;

    private TelegramChatStatus status;

    private String fullName;

    private String phoneNumber;

    private Integer attempt;
    private String specify;
}
