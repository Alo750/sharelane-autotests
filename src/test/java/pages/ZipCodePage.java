package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZipCodePage {
    private WebDriver driver;

    private By zipCodeField = By.name("zip_code");
    private By continueButton = By.cssSelector("input[value='Continue']");
    private By errorMessage = By.className("error_message");

    public ZipCodePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}