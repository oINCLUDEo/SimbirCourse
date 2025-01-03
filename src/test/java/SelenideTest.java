import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.WebDriverConditions.url;


public class SelenideTest {
    private static Logger logger = LoggerFactory.getLogger(LoggerExample.class);

    @BeforeClass
    void init(){
        Configuration.browserSize = "1280x720";
        Configuration.timeout = 15000;
        Configuration.pollingInterval = 500;
        Configuration.pageLoadTimeout = 30000;
    }

    @BeforeMethod(onlyForGroups = "headless")
    public void setUpHeadlessConfig() {
        Configuration.headless = true;
    }

    @BeforeMethod(onlyForGroups = "non-headless")
    public void setUpNonHeadlessConfig() {
        Configuration.headless = false;
    }

    @BeforeMethod(onlyForGroups = "edge")
    public void setUpEdgeConfig() { Configuration.browser = "edge"; }

    @BeforeMethod(onlyForGroups = "chrome")
    public void setUpChromeConfig() { Configuration.browser = "chrome"; }

    @Test(groups = {"headless"})
    public void ulstuTest(){
        logger.info("Запущен тест кнопки 'Все новости' на сайте УлГТУ");

        open("https://ulstu.ru/");
        $(By.id("all-main-news-link")).click();
        $(By.id("bx_breadcrumb_1")).shouldBe(visible);
    }

    @Test(groups = {"non-headless", "edge"})
    public void ulstuCalendarTest(){
        logger.info("Тест Календаря на сайте УлГТУ");

        open("https://ulstu.ru/");
        SelenideElement calendar = $(By.xpath("//*[contains(@class, calendar)]"));
        scrollToElement(calendar);

        $(By.xpath("//*[contains(@class, calendar_day)]//a[@data-date='30.01.2025']")).click();
        $(By.xpath("//*[contains(@class, event_header)]/p/b")).shouldHave(text("30 января 2025 года"));
        sleep(1000);
    }

    public static void scrollToElement(SelenideElement element) {
        WebDriver driver = Selenide.webdriver().driver().getWebDriver();
        Actions actions = new Actions(driver);
        actions.moveToElement((WebElement) element).perform();
    }

    @Test(groups = {"non-headless", "chrome"})
    public void steamTest(){
        logger.info("Запущен тест кнопки 'войти' на сайте Steam");

        open("https://store.steampowered.com/?l=russian");
        $(By.xpath("//*[contains(@id, 'global_action_menu')]/a[2]")).shouldBe(visible).click();
        $(By.id("loginModals")).shouldBe(visible);
    }

    @Test(groups = {"non-headless", "chrome"})
    public void steamTest2(){
        logger.info("Запущен тест ссылки 'О компании' на сайте Steam");

        open("https://store.steampowered.com/?l=russian");
        $(By.xpath("//*[contains(@class, valve_links)]//a[text()='О Valve']")).shouldBe(visible).click();
        Selenide.switchTo().window(1);
        webdriver().shouldHave(url("https://www.valvesoftware.com/ru/about"));
        sleep(2500);
    }
}
