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
        SelenideElement calendar = $(By.id("comp_92505226034e58f28aba9a8ff60ad5c8"));
        scrollToElement(calendar);

        $(By.xpath("//*[@id=\"comp_92505226034e58f28aba9a8ff60ad5c8\"]/div/div[1]/div[2]/ul[4]/li[6]/a")).click();
        $(By.xpath("//*[@id=\"comp_92505226034e58f28aba9a8ff60ad5c8\"]/div/div[2]/div[1]/p/b")).shouldHave(text("21 декабря 2024 года"));
        sleep(1000);
    }

    public static void scrollToElement(SelenideElement element) {
        WebDriver driver = Selenide.webdriver().driver().getWebDriver();
        Actions actions = new Actions(driver);
        actions.moveToElement((WebElement) element).perform();
    }

    @Test(groups = "headless")
    public void steamTest(){
        logger.info("Запущен тест кнопки 'войти' на сайте Steam");

        open("https://store.steampowered.com/?l=russian");
        $(By.xpath("//*[@id=\"global_action_menu\"]/a[2]")).shouldBe(visible).click();
        $(By.id("loginModals")).shouldBe(visible);
    }

    @Test(groups = {"non-headless", "chrome"})
    public void steamTest2(){
        logger.info("Запущен тест ссылки 'О компании' на сайте Steam");

        open("https://store.steampowered.com/?l=russian");
        $(By.xpath("//*[@id=\"footer\"]/div/div[7]/a[1]")).shouldBe(visible).click();
        Selenide.switchTo().window(1);
        webdriver().shouldHave(url("https://www.valvesoftware.com/ru/about"));
        sleep(2500);
    }
}
