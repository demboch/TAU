package pl.edu.pjwstk.lab6;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on Login page")
    public void userOnLoginPage() {
        pages.gunshop().openPage();
    }

    @Given("user is on Home page")
    public void userOnHomePage() {
        pages.gunshop().openPage();
    }

    @When("user inserts login: $email in input with name: $idEmail")
    public void userInsertsLoginData(String idEmail, String email) {
        pages.gunshop().setUser(email, idEmail);
    }

    @Then("user inserts password: $password in input with name: $idPassword")
    public void userInsertsPasswordData(String idPassword, String password) { pages.gunshop().setPassword(password, idPassword); }

    @When("user clicks button with class name: $className")
    public void userClicksButton(String className) {
        pages.gunshop().submitClick(className);
    }

    @Then("user clicks on link with text: $homePageText")
    public void userClicksOnHomePageLinkText(String homePageText) {
        pages.gunshop().backHomePage(homePageText);
    }

    @Then("site displays failed login text in span with button: $linkText")
    public void siteDisplaysFailedLoginText(String linkText) {
        pages.gunshop().getFailed(linkText);
    }

    @Then("site visible button $loginText in right upper corner after trying to log")
    public void siteDisplaysSuccessLoginText(String loginText) { pages.gunshop().getSuccess(loginText); }

    @Then("site contains link with text: $logoutText")
    public void siteContainsLogoutLinkText(String logoutText) {
        pages.gunshop().getLogout(logoutText);
    }
}
