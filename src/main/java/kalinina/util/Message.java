package kalinina.util;

public interface Message {
    String helloMessage = "Здравствуйте! Вас преветствует бот плохих советов" +
            " Он умеет слать плохие советы на любое сообщение. Также можно подписаться на ежедневную" +
            " рассылку плохих советов. Если потом захотите отписаться, то просто пришлите /unsubscribe.";

    String byeMessage = "Сейчас расплачусь..А нет. Платочек забыл";

    String welcomeMessage = "Добро пожаловать! \uD83D\uDD90\uD83C\uDFFB";

    String errorMessage = "Упс, что-то пошло не так\uD83E\uDDD0...Кажется вы уже на нас подписаны!";

    String cancelMessage = "ОК";

    String suggestMessage = "Ух ты! Вы хотите предложить нам вредный совет? Все просто!" +
            " Нажмите на кнопку \"Предложить совет!\" после чего напишите сообщение длинной не более 2000 символов" +
            " в ответном сообщении! Мы рассмотрим его и если оно нампонравится "+
            " то мы пополним им нашу копилку советов!";
}
