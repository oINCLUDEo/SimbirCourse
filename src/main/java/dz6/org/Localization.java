package dz6.org;
import com.codeborne.selenide.WebDriverRunner;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {

    private static ResourceBundle resourceBundle;

    // Метод для инициализации ResourceBundle в зависимости от языка
    public static void initialize(String language) {
        ResourceBundle.clearCache();

        Locale currentLocale;
        if ("en".equals(language) || "En".equals(language)) {
            currentLocale = new Locale("en", "EN");
            // Инициализация для английского языка
            resourceBundle = ResourceBundle.getBundle("en_US", Locale.ENGLISH);
        } else {
            currentLocale = new Locale("ru", "RU");
            // Инициализация для русского языка по умолчанию
            resourceBundle = ResourceBundle.getBundle("ru_RU", Locale.getDefault());
        }

        System.out.println("Initialized locale: " + currentLocale);
    }

    public static String getCurrentLanguage() {
        String currentUrl = WebDriverRunner.url();
        if (currentUrl.contains("/en/")) {
            return "en";
        } else {
            return "ru";  // По умолчанию считаем, что язык русский
        }
    }


    public static String getString(String key) {
        return resourceBundle.getString(key);
    }
}
