package kalinina.service;

import kalinina.model.CommandObj;
import kalinina.util.Command;
import kalinina.util.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class CommandService implements Message{

    private static InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    public static SendMessage commandCallBackExecute(Command command, Long chartId, User user) {
        String message;
        boolean isExistChart = Dao.isExistsChart(chartId);

        switch (command) {
            case SUBSCRIBE: {
                if (!isExistChart) {
                    Dao.saveNewChart(chartId, user);
                    System.out.println("Save new  " + chartId);
                    message = welcomeMessage;
                }
                else message = errorMessage;
                break;
            }
            case UNSUBSCRIBE: {
                if (isExistChart) Dao.deleteChart(chartId);
                message = byeMessage;
                break;
            }
            default: message = cancelMessage;
        }

        return new SendMessage()
                .setChatId(chartId)
                .setText(message);
    }

    private static List<InlineKeyboardButton> createButtonInRow(List<CommandObj> commandList) {
        return commandList
                .stream()
                .map(c -> new InlineKeyboardButton()
                                    .setText(c.getName())
                                    .setCallbackData(c.getCommandCallBackStr())
                ).collect(toList());
    }

    public static SendMessage commandExecute(Command command, Long chartId) {
        String message = "";
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        switch (command) {
            case UNKNOWN: {
                message = Dao.getAdvice();
                break;
            }
            case HELP:
            case START: {
                message = helloMessage;
                rowsInline.add(
                        createButtonInRow(
                                Arrays.asList(new CommandObj("Подписаться \uD83E\uDD73", Command.SUBSCRIBE),
                                        new CommandObj("Нет, спасибо", Command.CANCEL)
                                )
                        )
                );
                break;
            }
            case SUGGEST: {
                message = suggestMessage;
                rowsInline.add(
                        createButtonInRow(
                                Arrays.asList(new CommandObj("Предложить совет!", Command.SUGGEST),
                                        new CommandObj("Ой, что-то я не то нажал(а)", Command.CANCEL)
                                )
                        )
                );
                break;
            }
            case UNSUBSCRIBE: {
                if (Dao.isExistsChart(chartId)) Dao.deleteChart(chartId);
                message = byeMessage;
                break;
            }
        }

        if (!rowsInline.isEmpty()) {
            inlineKeyboardMarkup.setKeyboard(rowsInline);
            return new SendMessage()
                    .setChatId(chartId)
                    .setText(message)
                    .setReplyMarkup(inlineKeyboardMarkup);
        }
        else
            return new SendMessage()
                    .setChatId(chartId)
                    .setText(message);
    }
}
