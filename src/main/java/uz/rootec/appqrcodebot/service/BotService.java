package uz.rootec.appqrcodebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.rootec.appqrcodebot.bot.QrCodeBot;
import uz.rootec.appqrcodebot.bot.BotConstant;
import uz.rootec.appqrcodebot.entity.TelegramChat;
import uz.rootec.appqrcodebot.entity.enums.TelegramChatStatus;
import uz.rootec.appqrcodebot.repository.*;


@Service
public class BotService {

    @Autowired
    QrCodeBot qrCodeBot;

    @Autowired
    ButtonService buttonService;

    @Autowired
    TelegramChatRepository telegramChatRepository;

    @Value("${bot.chatid}")
    private String chatId;

    public DeleteMessage deleteTopMessage(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        if (update.hasMessage()) {
            deleteMessage.setChatId(update.getMessage().getChatId()).setMessageId(update.getMessage().getMessageId());
        } else if (update.hasCallbackQuery()) {
            deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId()).setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }
        return deleteMessage;
    }

    public void welcomeText(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(BotConstant.welcomeTextRu);

//        TelegramChat byChatId = telegramChatRepository.findByChatId(update.getMessage().getChatId());
//        byChatId.setStatus(TelegramChatStatus.OTHER);
//        telegramChatRepository.save(byChatId);

        sendMessage.setReplyMarkup(buttonService.languageButton());
        qrCodeBot.execute(sendMessage);
    }

    public void setLang(Update update, String language) throws TelegramApiException {
        if (telegramChatRepository.existsByChatId(update.getCallbackQuery().getMessage().getChatId())) {
            TelegramChat byChatId = telegramChatRepository.findByChatId(update.getCallbackQuery().getMessage().getChatId());
            byChatId.setLanguage(language);
            byChatId.setStatus(TelegramChatStatus.ENTER_PHONE);
            telegramChatRepository.save(byChatId);
        } else {
            TelegramChat telegramChat = new TelegramChat(update.getCallbackQuery().getMessage().getChatId(), language, TelegramChatStatus.ENTER_PHONE, "", "", 0, "");
            telegramChatRepository.save(telegramChat);
        }


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setText((language.equals("ru") ? BotConstant.LANGUAGECHANGEDRU + BotConstant.SENDPHONERU : BotConstant.LANGUAGECHANGEDUZ + BotConstant.SENDPHONEUZ));
        sendMessage.setReplyMarkup(buttonService.phoneNumberButton(language));
        qrCodeBot.execute(sendMessage);
    }

    public void enterPhone(Update update, String text) throws TelegramApiException {
        TelegramChat chat = telegramChatRepository.findByChatId(update.getMessage().getChatId());
        chat.setStatus(TelegramChatStatus.ENTER_SPECIFY);
        chat.setPhoneNumber(text);
        telegramChatRepository.save(chat);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getChatId());

        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setReplyMarkup(replyKeyboardRemove);

        sendMessage.setText((chat.getLanguage().equals("ru") ? BotConstant.SENDSPECIFYRU : BotConstant.SENDSPECIFYUZ));
        qrCodeBot.execute(sendMessage);
    }

    public void enterSpecify(Update update) throws TelegramApiException {
        TelegramChat chat = telegramChatRepository.findByChatId(update.getMessage().getChatId());
        chat.setStatus(TelegramChatStatus.ENTER_FIO);
        chat.setSpecify(update.getMessage().getText());
        telegramChatRepository.save(chat);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getChatId());

        sendMessage.setText((chat.getLanguage().equals("ru") ? BotConstant.SENDFIORU : BotConstant.SENDFIOUZ));
        qrCodeBot.execute(sendMessage);
    }

    public void enterFio(Update update) throws TelegramApiException {
        TelegramChat chat = telegramChatRepository.findByChatId(update.getMessage().getChatId());
        chat.setStatus(TelegramChatStatus.OTHER);
        chat.setFullName(update.getMessage().getText());
        telegramChatRepository.save(chat);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getChatId());

        sendMessage.setText((chat.getLanguage().equals("ru") ? BotConstant.SAVERU : BotConstant.SAVEUZ));
        qrCodeBot.execute(sendMessage);

        sendMessage.setParseMode("HTML");
        sendMessage.setText((chat.getLanguage().equals("ru") ? BotConstant.YOURDATARU + "\n\n" +
                BotConstant.PHONERU + "<b>" + chat.getPhoneNumber() + "</b>\n" +
                BotConstant.FIORU + "<b>" + chat.getFullName() + "</b>\n" +
                BotConstant.SPECIFYRU + "<b>" + chat.getSpecify() + "</b>\n"

                : BotConstant.YOURDATAUZ + "\n\n" +
                BotConstant.PHONEUZ + "<b>" + chat.getPhoneNumber() + "</b>\n" +
                BotConstant.FIOUZ + "<b>" + chat.getFullName() + "</b>\n" +
                BotConstant.SPECIFYUZ + "<b>" + chat.getSpecify() + "</b>\n"
        ));
        qrCodeBot.execute(sendMessage);
        sendMessage.setChatId(this.chatId);
        qrCodeBot.execute(sendMessage);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chat.getChatId());
        sendPhoto.setPhoto("https://api.qrserver.com/v1/create-qr-code/?size=320x320&data=http://orvilleservices.com");
        qrCodeBot.execute(sendPhoto);
    }
}
