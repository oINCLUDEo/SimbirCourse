package dz6.org.pages;

import io.qameta.allure.Step;
import dz6.org.Localization;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ContactsPage {
    String pageTitle = Localization.getString("contacts_page_title");

    @Step("Проверить, что страница контакты открылась")
    public ContactsPage contactsPageIsOpen() {
        $(By.xpath("//h1"))
                .shouldHave(text(pageTitle))
                .shouldBe(visible);
        return this;
    }
}
