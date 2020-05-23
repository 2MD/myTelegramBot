package kalinina.model;

import kalinina.util.Command;

public class CommandObj {
   private String name;
   private Command commandCallBack;

    public CommandObj(String name, Command commandCallBack) {
        this.name = name;
        this.commandCallBack = commandCallBack;
    }

    public String getName() {
        return name;
    }

    public String getCommandCallBackStr() {
        return commandCallBack.getName();
    }
}
