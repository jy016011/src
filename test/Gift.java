package test;

import java.util.Arrays;
import java.util.List;

public enum Gift implements EnumModel{
    CHAMPAGNE(25_000, "샴페인", "증정 이벤트");

    private final int price;
    private final String menu;
    private final String eventName;

    Gift(int price, String menu, String eventName) {
        this.price = price;
        this.menu = menu;
        this.eventName = eventName;
    }

    public static List<Gift> getGifts() {
        return Arrays.stream(Gift.values()).toList();
    }

    public static Gift getBy(String menu) {
        return Arrays.stream(Gift.values())
                .filter(gift -> gift.getMenu().equals(menu))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getItemBy(int count) {
        return menu + String.format(" %d개", count);
    }

    public int getBenefit(int count) {
        return price * count;
    }

    public String getMenu() {
        return menu;
    }

    public String getEventName() {
        return eventName;
    }
}
