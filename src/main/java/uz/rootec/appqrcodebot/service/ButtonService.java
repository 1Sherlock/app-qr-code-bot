package uz.rootec.appqrcodebot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.rootec.appqrcodebot.bot.BotConstant;

import java.util.ArrayList;
import java.util.List;

@Service
public class ButtonService {
    public InlineKeyboardMarkup languageButton() {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

//        KeyboardRow row = new KeyboardRow();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(BotConstant.UZBEK);
        button.setCallbackData("language#uzbek");
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(BotConstant.RUSSIAN);
        button1.setCallbackData("language#russian");

        rows.add(button);
        rows.add(button1);
        inlineKeyboardButtons.add(rows);

        return replyKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
    }


}

