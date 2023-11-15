package christmas.util.exceptions;

public enum Exceptions {
    DATE_OF_VISIT_INVALID("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDERING_INVALID("유효하지 않은 주문입니다. 다시 입력해 주세요."),

    AUTHENTICATION_INVALID("비정상적인 접근입니다."),

    EVENT_FULL("이벤트 참여자가 많아 참여할 수 없습니다.");

    private final String message;

    Exceptions(String message) {
        this.message = message;
    }

    public String getMessage() {
        StringBuilder message = new StringBuilder();
        String prefix = "[ERROR] ";

        message.append(prefix).append(this.message).append("\n");

        return message.toString();
    }
}
