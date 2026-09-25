package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    private By firstNameField = By.name("first_name");
    private By emailField = By.name("email");
    private By passwordField = By.name("password1");
    private By confirmPasswordField = By.name("password2");
    private By registerButton = By.cssSelector("input[value='Register']");
    private By confirmationMessage = By.xpath("//span[contains(text(),'Account is created')]");
    private By errorMessage = By.className("error_message");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(String firstName, String email, String password) {
        registerWithDifferentPasswords(firstName, email, password, password);
    }

    public void registerWithDifferentPasswords(String firstName, String email,
                                               String password, String confirmPassword) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
        driver.findElement(registerButton).click();
    }

    public boolean isRegistrationSuccessful() {
        return driver.findElement(confirmationMessage).isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}