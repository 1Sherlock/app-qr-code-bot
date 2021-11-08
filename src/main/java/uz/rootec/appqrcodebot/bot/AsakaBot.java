package uz.rootec.appqrcodebot.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import uz.rootec.appqrcodebot.entity.TelegramChat;
import uz.rootec.appqrcodebot.entity.enums.TelegramChatStatus;
import uz.rootec.appqrcodebot.repository.TelegramChatRepository;
import uz.rootec.appqrcodebot.service.BotService;

import java.util.List;

@Component
public class AsakaBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Autowired
    BotService botService;

    @Autowired
    TelegramChatRepository telegramChatRepository;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {

                String text = update.getMessage().getText();



            } else if (update.getMessage().hasContact()) {
                Contact contact = update.getMessage().getContact();
                Long chatId = update.getMessage().getChatId();
                TelegramChat byChatId = telegramChatRepository.findByChatId(chatId);


            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();

            try {
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
//                sendMessage.setText(update.getCallbackQuery().getMessage().get());
//                this.execute(sendMessage);

                if (data.startsWith("language#")) {
//                    execute(botService.deleteTopMessage(update));
//
//                    if (data.endsWith("uzbek")) {
//                        botService.setLang(update, "uz");
//                    } else {
//                        botService.setLang(update, "ru");
//                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValid(String date) {
        if (date.length() != 5) {
            return false;
        }

        return isNumeric(date.substring(0, 2)) && isNumeric(date.substring(3, 5)) && (date.charAt(2) == '/');
    }
}
