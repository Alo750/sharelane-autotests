package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.ZipCodePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    // Вспомогательный метод: проходит первый шаг регистрации (ZIP-код)
    private RegistrationPage openRegistrationForm() {
        ZipCodePage zipCodePage = new ZipCodePage(driver);
        zipCodePage.open();
        zipCodePage.enterZipCode("12345");
        zipCodePage.clickContinue();
        return new RegistrationPage(driver);
    }

    @Test
    public void userCanRegisterWithValidData() {
        RegistrationPage registrationPage = openRegistrationForm();
        // email каждый раз новый, чтобы не было "email уже использован"
        String email = "test" + System.currentTimeMillis() + "@gmail.com";
        registrationPage.register("Ivan", email, "password123");
        assertTrue(registrationPage.isRegistrationSuccessful(),
                "Должно появиться сообщение 'Account is created!'");
    }

    @Test
    public void registrationShouldFailWithInvalidEmail() {
        RegistrationPage registrationPage = openRegistrationForm();
        // email без @ — некорректный формат
        registrationPage.register("Ivan", "invalid-email", "password123");
        assertTrue(registrationPage.isErrorMessageDisplayed(),
                "Должна появиться ошибка, т.к. email некорректный");
    }
}