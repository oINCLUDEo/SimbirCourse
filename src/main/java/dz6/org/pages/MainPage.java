package dz6.org.pages;

import com.codeborne.selenide.SelenideElement;
import dz6.org.Localization;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс главной страницы.
 */
public class MainPage {


    final String mainSlogan = "//h1";
    /**
     * Кнопка обратной связи.
     */
    @FindBy(xpath = "//a[@data-gclick='showModalFeedback']")
    private SelenideElement feedbackButton;

    @FindBy(xpath = "//*[contains(@class,'gh-nav-item--about')]")
    private SelenideElement navItemAbout;

    @FindBy(xpath = "//*[contains(@class, 'gh-nav-item')]//a[text()='Locations']")
    private SelenideElement locations;

    @FindBy(xpath = "//*[contains(@class,'gh-nav-item')]//a[text()='Вакансии']")
    private SelenideElement navItemVacancies;

    @FindBy(xpath = "//*[contains(@class, 'gh-tools-lang')]")
    private SelenideElement languageButton;

    /**
     * Проверяет, что кнопка Написать нам присутствует и кликабельна.
     * @return текущйи экземпляр класса
     */
    @Step("Проверить, что кнопка Написать нам присутствует и кликабельна")
    public FeedbackModal showFeedbackModal() {
        feedbackButton
                .should(exist)
                .shouldBe(visible)
                .shouldHave(text("Написать нам"))
                .click();
        return page(FeedbackModal.class);
    }

    @Step("Проверить, что при наведении на элемент меню О нас появляется ссылка Контакты")
    public ContactsPage goToContacts() {
        String language = Localization.getCurrentLanguage(); // метод для получения текущего языка

        if (language.equals("en")) {
            locations
                    .shouldHave(text(Localization.getString("contacts_page_title")))
                    .click();
        } else {
            navItemAbout
                    .shouldHave(text("О нас"))
                    .hover();
            $(By.linkText("Контакты"))
                    .shouldHave(text("Контакты"))
                    .click();
        }
        return page(ContactsPage.class);
    }

    @Step("Открыть страницу Вакансий")
    public VacanciesPage goToVacancies() {
        navItemVacancies
                .shouldHave(text("Вакансии"))
                .click();
        return page(VacanciesPage.class);
    }

    @Step("Перевод страницы")
    public MainPage switchLanguage(){
        String langToSwitch = Localization.getString("other_language");
        Localization.initialize(langToSwitch);

        languageButton
                .shouldHave(text(langToSwitch))
                .click();
        return page(MainPage.class);
    }

    @Step("Проверка смены языка")
    public MainPage checkLanguage() {
        String slogan = Localization.getString("slogan");

        $(By.xpath("//h1"))
                .shouldHave(text(slogan))
                .shouldBe(visible);
        return page(MainPage.class);
    }
}
