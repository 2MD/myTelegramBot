package kalinina;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotMain {

    public static void main(String[] args) {
       /*
        System.getProperties().put("proxySet", "true");

        System.getProperties().put("socksProxyHost", "128.0.0.2");

        System.getProperties().put("socksProxyPort", "9150");
       */

        ApiContextInitializer.init();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi();

            // Set up Http proxy
          /*  DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(HOST_PROXY);
            botOptions.setProxyPort(PORT_PROXY);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
          */

            botsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}