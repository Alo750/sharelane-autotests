package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.ZipCodePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginWithValidCredentials() {
        // 1. Регистрируем нового пользователя
        ZipCodePage zipCodePage = new ZipCodePage(driver);
        zipCodePage.open();
        zipCodePage.enterZipCode("12345");
        zipCodePage.clickContinue();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register("Ivan", "test" + System.currentTimeMillis() + "@gmail.com", "password123");
        assertTrue(registrationPage.isRegistrationSuccessful(), "Регистрация не прошла");

        // 2. Сайт подменяет данные — берём реальные логин и пароль со страницы подтверждения
        String realEmail = registrationPage.getAssignedEmail();
        String realPassword = registrationPage.getAssignedPassword();

        // 3. Входим на главной странице с реальными данными
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(realEmail, realPassword);

        // После успешного входа в меню появляется ссылка Logout
        assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(),
                "После успешного входа должна быть ссылка Logout");
    }

    @Test
    public void loginWithWrongPasswordShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("nonexistent@gmail.com", "wrongpassword");
        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Должна появиться ошибка при неверном пароле");
    }
}