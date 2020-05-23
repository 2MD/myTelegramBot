package kalinina;

import kalinina.service.CommandService;
import kalinina.service.Dao;
import kalinina.util.Command;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (!update.hasCallbackQuery()) {
                var message = update.getMessage();
                long chatId = message.getChatId();
                execute(CommandService.commandExecute(Command.findCommand(message.getText()), chatId));
            } else {
                var message = update.getCallbackQuery();

                execute(
                        CommandService.commandCallBackExecute(
                                Command.findCommand(message.getData()),
                                message.getMessage().getChatId(),
                                message.getFrom()
                        )
                );
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MaryStatBot";
    }

    @Override
    public String getBotToken() {
        return "1095300584:AAFXBOY9y4raZpBzrjj6TWPOlXmXN-QqzTk";
    }
}
