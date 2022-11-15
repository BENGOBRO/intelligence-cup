package ru.bengobro.dto;

public enum TypeOfError {

    HTTP_MESSAGE_NOT_READABLE("Bad request parameters (VK access token or ID's)"),
    API_AUTH("Problems with authorization in VK"),
    API_ACCESS("No rights to access information"),
    API_GROUP_ACCESS("No rights to access group members"),
    API("Something went wrong in VK"),
    CLIENT("Something went wrong on client");

    private final String message;

    TypeOfError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
