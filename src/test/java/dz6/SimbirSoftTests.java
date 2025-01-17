import dz6.org.dao.Person;
import dz6.org.pages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class SimbirSoftTests extends BaseTest {

    @BeforeMethod
    void initBeforeMethod() {
        open("/");
    }

    @Test(description = "Show feedback modal window test")
    public void feedBackModalOpenTest() {
        page(MainPage.class)
                .showFeedbackModal()
                .checkModalWindowVisible()
                .checkTextInput();
        sleep(5000);
    }

    @Test(description = "Open Contacts page in Russian")
    public void openContactsInRussian() {
        page(MainPage.class)
                .goToContacts()
                .contactsPageIsOpen();
        sleep(2000);
    }

    @Test(description = "Open Contacts page on English")
    public void openContactsInEnglish() {
        page(MainPage.class)
                .switchLanguage()
                .goToContacts()
                .contactsPageIsOpen();
        sleep(2000);
    }


    @Test(description = "Create Person test")
    public void createPersonTest() {
        Person person = new Person.Builder()
                .withName("Testoviy Test Testovich")
                .withAge(45)
                .withWeight(80)
                .build();
    }

    @Test(description = "Main page switch language")
    public void switchLanguageMainPageTest() {
        page(MainPage.class)
                .switchLanguage()
                .checkLanguage();
        sleep(2000);
    }

    @Test(description = "Open Vacancies")
    public void openVacanciesPageTest() {
        page(MainPage.class)
                .goToVacancies()
                .vacanciesPageIsOpen();
        sleep(2000);
    }

    /**
     * Присутствует два схожих теста один не проходит тест, другой проходит.
     * Это связано с тем, что на самом сайте Simbirsoft сломан плеер на странице DBA разработчика.
     * Думаю если html посмотрите сразу поймете в чем дело :)
     *
     * P.S.  Нужно сменить png затычку на другой источник как везде -
     * /upload/medialibrary/627/uzve8eb08tbljafjy9b2eyxor6e387sa/udalenkanew-_2_.mp4
     */

    @Test(description = "Test Video in DBA Vacancy")
    public void playVideoDBAVacancyTest() {
        page(MainPage.class)
                .goToVacancies()
                .vacanciesPageIsOpen()
                .goToBackendVacancies()
                .goToDBAVacancy()
                .startVideo()
                .videoIsPlay();
        sleep(2000);

    }

    @Test(description = "Test Video in DBA Vacancy")
    public void playVideoSDETJavaVacancyTest() {
        page(MainPage.class)
                .goToVacancies()
                .vacanciesPageIsOpen()
                .goToSDETJavaVacancy()
                .startVideo()
                .videoIsPlay();
        sleep(2000);

    }
}
