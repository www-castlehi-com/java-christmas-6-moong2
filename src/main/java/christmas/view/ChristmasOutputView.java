package christmas.view;

import static christmas.model.util.event.EventDetails.APPLY_DISCOUNT;
import static christmas.model.util.event.EventDetails.COUNT_OF_GIVE_MENU;
import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.util.instructions.Guidance.PREVIEW_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_EACH_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_EACH_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_BADGE;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_NOT_APPLY;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU_EXISTS;
import static christmas.util.instructions.Guidance.PREVIEW_MONEY;
import static christmas.util.instructions.Guidance.PREVIEW_ORDER_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_AMOUNT_AFTER_DISCOUNT;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_AMOUNT_BEFORE_DISCOUNT;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS_AMOUNT;
import static christmas.util.instructions.Report.HEADER_NUMBER_OF_CLIENTS;
import static christmas.util.instructions.Report.HEADER_NUMBER_OF_NEXT_EVENT_CLIENTS;
import static christmas.util.instructions.Report.HEADER_TOTAL_AMOUNT_OF_ORDERS;
import static christmas.util.instructions.Report.STATISTIC_NUMBER_OF_CLIENTS;
import static christmas.util.instructions.Report.STATISTIC_TOTAL_AMOUNT_OF_ORDERS;

import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.util.instructions.Precautions;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ChristmasOutputView는 OutputView 인터페이스의 구현부다. 12월 크리스마스 이벤트의 출력 역할을 담당한다.
 */
public class ChristmasOutputView implements OutputView {
    /**
     * 주의 사항을 출력한다.
     */
    @Override
    public void printPrecaution() {
        System.out.println(Precautions.getPrecautions());
    }

    /**
     * 안내 문구를 출력한다.
     *
     * @param message 안내 문구
     */
    @Override
    public void printGuidance(String message) {
        System.out.println(message);
    }

    /**
     * 입력 받은 방문 정보를 기반으로 이벤트 혜택 미리 보기를 출력한다.
     *
     * @param visitDate 방문 날짜
     */
    @Override
    public void printPreview(int visitDate) {
        this.printGuidance(String.format(PREVIEW_BENEFITS.getGuidance(), EVENT_MONTH.getDate(), visitDate));
        System.out.println();
    }

    /**
     * 주문 내역을 출력한다.
     *
     * @param orders 주문 내역
     */
    @Override
    public void printOrderMenus(Map<String, Integer> orders) {
        this.printGuidance(PREVIEW_ORDER_MENU.getGuidance());
        for (Entry<String, Integer> order : orders.entrySet()) {
            System.out.println(String.format(PREVIEW_EACH_MENU.getGuidance(), order.getKey(), order.getValue()));
        }
        System.out.println();
    }

    /**
     * 할인 전 총주문 금액을 출력한다.
     *
     * @param totalAmountBeforeDiscount 할인 전 총주문 금액
     */
    @Override
    public void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        this.printGuidance(PREVIEW_TOTAL_AMOUNT_BEFORE_DISCOUNT.getGuidance());
        System.out.println(String.format(PREVIEW_MONEY.getGuidance(), formatCurrency(totalAmountBeforeDiscount)));
        System.out.println();
    }

    /**
     * 증정 메뉴를 출력한다.
     *
     * @param menuList 증정 메뉴
     */
    @Override
    public void printGivenMenu(MenuList menuList) {
        this.printGuidance(PREVIEW_GIVE_MENU.getGuidance());
        System.out.println(String.format(PREVIEW_GIVE_MENU_EXISTS.getGuidance(), menuList.getMenuName(),
                COUNT_OF_GIVE_MENU.getDetails()));
        System.out.println();
    }

    /**
     * 혜택 내역을 출력한다.
     *
     * @param benefits 혜택 내역
     */
    @Override
    public void printBenefits(Map<EventCategory, Integer> benefits) {
        this.printGuidance(PREVIEW_TOTAL_BENEFITS.getGuidance());
        for (Entry<EventCategory, Integer> benefit : benefits.entrySet()) {
            System.out.println(String.format(PREVIEW_EACH_BENEFITS.getGuidance(), benefit.getKey().getCategory(),
                    formatCurrency(benefit.getValue() * APPLY_DISCOUNT.getDetails())));
        }
        System.out.println();
    }

    /**
     * 총혜택금액을 출력한다.
     *
     * @param totalBenefitsAmount 총혜택금액
     */
    @Override
    public void printTotalBenefitsAmount(int totalBenefitsAmount) {
        this.printGuidance(PREVIEW_TOTAL_BENEFITS_AMOUNT.getGuidance());
        System.out.println(String.format(PREVIEW_MONEY.getGuidance(),
                formatCurrency(totalBenefitsAmount * APPLY_DISCOUNT.getDetails())));
        System.out.println();
    }

    /**
     * 할인 후 예상 결제 금액을 출력한다.
     *
     * @param totalAmountAfterDiscount 할인 후 예상 결제 금액
     */
    @Override
    public void printTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        this.printGuidance(PREVIEW_TOTAL_AMOUNT_AFTER_DISCOUNT.getGuidance());
        System.out.println(String.format(PREVIEW_MONEY.getGuidance(), formatCurrency(totalAmountAfterDiscount)));
        System.out.println();
    }

    /**
     * 수여받은 배지를 출력한다.
     *
     * @param badge 배지
     */
    @Override
    public void printBadge(BadgeCategory badge) {
        System.out.println(String.format(PREVIEW_EVENT_BADGE.getGuidance(), EVENT_MONTH.getDate()));
        System.out.println(badge.getCategory());
        System.out.println();
    }

    /**
     * 적용되지 않은 이벤트를 출력한다.
     *
     * @param guidance 이벤트 문구
     */
    @Override
    public void printEventNotApply(String guidance) {
        this.printGuidance(guidance);
        System.out.println(PREVIEW_EVENT_NOT_APPLY.getGuidance());
        System.out.println();
    }

    /**
     * admin이 분석한 통계 자료의 헤더를 출력한다.
     *
     * @param message 통계 자료의 헤더
     */
    @Override
    public void printHeader(String message) {
        System.out.println(message);
    }

    /**
     * 이벤트에 참여한 총 고객 수를 출력한다.
     *
     * @param totalNumberOfClients 이벤트에 참여한 총 고객 수
     */
    @Override
    public void printNumberOfClients(int totalNumberOfClients) {
        this.printHeader(HEADER_NUMBER_OF_CLIENTS.getReport());
        System.out.println(String.format(STATISTIC_NUMBER_OF_CLIENTS.getReport(), totalNumberOfClients));
    }

    /**
     * 다음 이벤트에 재참여할 것으로 예상되는 고객 수를 출력한다.
     *
     * @param totalNumberOfNextClients 다음 설날 이벤트 시, 예상 재참여 고객 수
     */
    @Override
    public void printNumberOfNextClients(double totalNumberOfNextClients) {
        this.printHeader(HEADER_NUMBER_OF_NEXT_EVENT_CLIENTS.getReport());
        System.out.println(String.format(STATISTIC_NUMBER_OF_CLIENTS.getReport(), totalNumberOfNextClients));
    }

    /**
     * 총 판매 금액을 출력한다.
     *
     * @param totalAmountOfOrders 총 판매 금액
     */
    @Override
    public void printTotalAmountOfOrders(long totalAmountOfOrders) {
        this.printHeader(HEADER_TOTAL_AMOUNT_OF_ORDERS.getReport());
        System.out.println(
                String.format(STATISTIC_TOTAL_AMOUNT_OF_ORDERS.getReport(), formatCurrency(totalAmountOfOrders)));
    }

    /**
     * 예외를 출력한다.
     *
     * @param message 예외 메시지
     */
    @Override
    public void printException(String message) {
        System.out.println(message);
    }

    private String formatCurrency(int amount) {
        NumberFormat currencyFormat = NumberFormat.getNumberInstance();
        currencyFormat.setGroupingUsed(true); // This is usually the default, but set explicitly for clarity
        return currencyFormat.format(amount);
    }

    private String formatCurrency(long amount) {
        NumberFormat currencyFormat = NumberFormat.getNumberInstance();
        currencyFormat.setGroupingUsed(true);
        return currencyFormat.format(amount);
    }
}
