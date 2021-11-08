package uz.rootec.appqrcodebot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.rootec.appqrcodebot.entity.TelegramChat;

import java.util.UUID;

public interface TelegramChatRepository extends JpaRepository<TelegramChat, UUID> {
    Boolean existsByChatId(Long chatId);
    TelegramChat findByChatId(Long chatId);
}
