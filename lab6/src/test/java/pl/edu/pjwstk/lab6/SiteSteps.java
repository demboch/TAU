package pl.edu.pjwstk.lab6;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on main page")
    public void userOnGunShopPage(){
        pages.gunshop().openPage();
    }

    @When("user write his $idEmail and $idPassword and click login")
    public void userWriteEmailAndPassword(String idEmail, String idPassword) {
        pages.gunshop().login(idEmail, idPassword);
    }

    @Then("the element named $linkText should appear on page")
    public void clickLinkTextAndLogout(String linkText) {
        assertTrue( pages.gunshop().logout(linkText).equals("Wyloguj"));
    }


//    @Given("user is on helpdesk page")
//    public void userOnHelpdeskPage(){
//        pages.gunshop().open();
//    }
//
//    @When("user clicks the $linkText tab")
//    public void userClicksTabLink(String linkText) {
//        pages.gunshop().click(linkText);
//    }
//
//    @Then("the tab with text $linkText should have class $classInside")
//    public void tabWithTextAndClass(String linkText, String classInside) {
//        assertTrue( pages.gunshop().getClassesForLink(linkText).contains(classInside));
//    }
//
//    @Then("the tab with text $linkText should not have class $classInside")
//    public void tabWithTextAndNotClass(String linkText, String classInside) {
//        assertTrue( !pages.gunshop().getClassesForLink(linkText).contains(classInside));
//    }
}
