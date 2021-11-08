package uz.rootec.appqrcodebot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.rootec.appqrcodebot.bot.BotConstant;

import java.util.ArrayList;
import java.util.List;

@Service
public class ButtonService {
    public InlineKeyboardButton generateButton(String textUz, String textRu, String callBackData, String language) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(language.equals("ru") ? textRu : textUz);
        button.setCallbackData(callBackData + (language.equals("ru") ? "ru" : "uz"));
        return button;
    }

    public InlineKeyboardButton generateButtonWithUrl(String textUz, String textRu, String language, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(language.equals("ru") ? textRu : textUz);
        button.setUrl(url);
        return button;
    }

    public InlineKeyboardMarkup languageButton() {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        rows.add(generateButton(BotConstant.UZBEK, BotConstant.UZBEK, "language#", "uz"));
        rows.add(generateButton(BotConstant.RUSSIAN, BotConstant.RUSSIAN, "language#", "ru"));
        inlineKeyboardButtons.add(rows);

        return replyKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
    }


    public ReplyKeyboard phoneNumberButton(String language) {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(new KeyboardButton(language.equals("ru") ? BotConstant.SHARENUMBERRU : BotConstant.SHARENUMBERUZ).setRequestContact(true));
        rows.add(row);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        return replyKeyboardMarkup.setKeyboard(rows);
    }
}

