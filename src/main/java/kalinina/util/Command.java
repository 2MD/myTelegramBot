package kalinina.util;

import java.util.Arrays;

public enum Command {
    START("/start"),
    SUBSCRIBE("/subscribe"),
    CANCEL("cancel"),
    UNSUBSCRIBE("/unsubscribe"),
    SUGGEST("/suggest"),
    HELP("/help"),
    UNKNOWN("unknown");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command findCommand(String inputMessage) {
        try {
            return Arrays.stream(Command.values())
                    .filter(x -> inputMessage.equals(x.getName()))
                    .findFirst()
                    .orElse(Command.UNKNOWN);
        } catch (Exception e) {
            return Command.UNKNOWN;
        }
    }

    public String getName() {
        return name;
    }
}
