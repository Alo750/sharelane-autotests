package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.ZipCodePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipCodeTest extends BaseTest {

    @Test
    public void zipCodeWith5DigitsShouldBeAccepted() {
        ZipCodePage zipCodePage = new ZipCodePage(driver);
        zipCodePage.open();
        zipCodePage.enterZipCode("12345");
        zipCodePage.clickContinue();
        // Если ZIP-код корректный, открывается форма регистрации (поле first_name)
        assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                "После корректного ZIP-кода должна открыться форма регистрации");
    }

    @Test
    public void zipCodeWith4DigitsShouldBeRejected() {
        ZipCodePage zipCodePage = new ZipCodePage(driver);
        zipCodePage.open();
        zipCodePage.enterZipCode("1234");
        zipCodePage.clickContinue();
        assertTrue(zipCodePage.isErrorMessageDisplayed(),
                "Должна появиться ошибка: ZIP-коду нужно 5 цифр");
    }
}