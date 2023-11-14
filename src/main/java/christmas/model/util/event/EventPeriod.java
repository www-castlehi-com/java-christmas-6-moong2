package christmas.model.util.event;

public enum EventPeriod {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),

    EVENT_DAY_START(1),
    EVENT_DAY_CHRISTMAS(25),
    EVENT_DAY_END(31);

    private final int date;

    EventPeriod(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public static boolean isBeforeChristmas(int day) {
        return day <= EVENT_DAY_CHRISTMAS.getDate();
    }
}