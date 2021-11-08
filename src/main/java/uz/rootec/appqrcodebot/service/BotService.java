package uz.rootec.appqrcodebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.rootec.appqrcodebot.bot.AsakaBot;
import uz.rootec.appqrcodebot.bot.BotConstant;
import uz.rootec.appqrcodebot.entity.*;
import uz.rootec.appqrcodebot.entity.enums.TelegramChatStatus;
import uz.rootec.appqrcodebot.payload.*;
import uz.rootec.appqrcodebot.repository.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.*;


@Service
public class BotService {

    @Autowired
    AsakaBot asakaBot;

    @Autowired
    ButtonService buttonService;

    @Autowired
    TelegramChatRepository telegramChatRepository;

    public void welcomeText(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(BotConstant.welcomeTextRu);

//        TelegramChat byChatId = telegramChatRepository.findByChatId(update.getMessage().getChatId());
//        byChatId.setStatus(TelegramChatStatus.OTHER);
//        telegramChatRepository.save(byChatId);

        sendMessage.setReplyMarkup(buttonService.languageButton());
        asakaBot.execute(sendMessage);
    }
}
