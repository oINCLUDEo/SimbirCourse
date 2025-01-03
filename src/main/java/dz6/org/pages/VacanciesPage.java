package dz6.org.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class VacanciesPage {

    private final SelenideElement vacanciesList = $(By.xpath("//a[contains(text(),'Вакансии')]"));
    @FindBy(xpath = "//*[contains(@class, 'list-tags-wrapper')]//a[@data-name='Backend-разработка']")
    private SelenideElement backendVacancies;

    @FindBy(xpath = "//*[contains(@class, 'l-item-name') and contains(text(), 'DBA')]")
    private SelenideElement DBAVacancy;

    @FindBy(xpath = "//*[contains(@class, 'l-item-name') and contains(text(), 'SDET (Java)')]")
    private SelenideElement SDETJavaVacancy;

    @Step("Проверить, что список вакансий отображается")
    public VacanciesPage vacanciesPageIsOpen() {
        $(By.xpath("//h1"))
                .shouldHave(text("Вакансии"))
                .shouldBe(visible);
        return this;
    }

    @Step("Проверить, что список вакансий в категории отображается")
    public VacanciesPage vacanciesCategoryPageIsOpen() {
        $(By.xpath("//h1"))
                .shouldHave(text("Вакансии"))
                .shouldBe(visible);
        return this;
    }

    @Step("Открыть категорию Backend Вакансий")
    public VacanciesPage goToBackendVacancies() {
        backendVacancies
                .shouldHave(text("Backend-разработка"))
                .click();
        return page(VacanciesPage.class);
    }

    @Step("Открыть вакансию DBA")
    public VacanciesPage goToDBAVacancy() {
        DBAVacancy
                .shouldHave(text("DBA"))
                .click();
        return page(VacanciesPage.class);
    }

    @Step("Открыть вакансию SDET (Java)")
    public VacanciesPage goToSDETJavaVacancy() {
        SDETJavaVacancy
                .shouldHave(text("SDET (Java)"))
                .click();
        return page(VacanciesPage.class);
    }

    @Step("")
    public VacanciesPage startVideo() {
        $(".is-button-play").click();
        return page(VacanciesPage.class);
    }

    @Step("Проверить, что видео воспроизводится")
    public VacanciesPage videoIsPlay() {
        $("video.vp-video").shouldNotHave(attribute("paused"));
        sleep(1000);

        Object currentTimeObject = executeJavaScript("return document.querySelector('video.vp-video').currentTime");

        double currentTime = 0.0;

        currentTime = ((Number) currentTimeObject).doubleValue();

        if (currentTime > 0) {
            System.out.println("Видео начало воспроизведение");
        } else {
            throw new AssertionError("Видео не воспроизводится. currentTime: " + currentTime);
        }
        return this;
    }
}
