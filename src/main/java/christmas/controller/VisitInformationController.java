package christmas.controller;

import static christmas.util.instructions.Guidance.WELCOME;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.domain.VisitInformation;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.service.VisitInformationService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class VisitInformationController {
    private final InputView inputView;
    private final OutputView outputView;
    private final VisitInformationService visitInformationService = new VisitInformationService();

    public VisitInformationController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void sendWelcome() {
        outputView.printPrecaution();
        outputView.printGuidance(WELCOME.getGuidance());
    }

    public VisitInformation getVisitInformation() {
        int visitDate = visitInformationService.validateAndConvertVisitDate(inputView.getVisitDate());
        Map<String, Integer> orders = visitInformationService.validateAndConvertOrders(inputView.getOrders());

        return visitInformationService.createVisitInformation(visitDate, orders);
    }

    public void saveVisitInformation(VisitInformation visitInformation) {
        visitInformationService.saveUser(visitInformation);
    }

    public void guideVisitInformation(VisitInformation visitInformation) {
        outputView.printPreview(visitInformation.getVisitDate().getDayOfMonth());
        outputView.printOrderMenus(visitInformation.getOrders().getOrders());
    }

    public void guideTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        outputView.printTotalAmountBeforeDiscount(totalAmountBeforeDiscount);
    }

    public void guideGivenMenu(MenuList menuList) {
        outputView.printGivenMenu(menuList);
    }

    public void guideBenefitsDetails(Map<EventCategory, Integer> benefits) {
        outputView.printBenefits(benefits);
    }

    public void guideBenefitsAmount(int totalBenefitsAmount) {
        outputView.printTotalBenefitsAmount(totalBenefitsAmount);
    }

    public void guideTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        outputView.printTotalAmountAfterDiscount(totalAmountAfterDiscount);
    }

    public void guideBadge(BadgeCategory badge) {
        outputView.printBadge(badge);
    }

    public void guideEventNotApply(String guidance) {
        outputView.printEventNotApply(guidance);
    }

    public void closeProcess() {
        Console.close();
    }
}