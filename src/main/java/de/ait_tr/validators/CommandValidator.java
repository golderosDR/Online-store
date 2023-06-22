package de.ait_tr.validators;

public class CommandValidator {
    private CommandValidator() {}
    public static boolean validate(String command) {
        return command.matches("[+]?\\d+");
    }


}
