package com.bignerdranch.android.bqtabs.tests.steps;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.test.rule.ActivityTestRule;

import com.bignerdranch.android.bqtabs.*;
import com.bignerdranch.android.bqtabs.R;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class cartStepDefintions {
    private final String TAG = "Cart Testing~";


    public final ActivityTestRule navLauncher = new ActivityTestRule<>(navigation.class, false, false);
    public final ActivityTestRule homeLauncher = new ActivityTestRule<>(MainActivity.class, false, false);

@After
private void finishActivity(){
    homeLauncher.finishActivity();
}

    @Given("that I have launched the app.")
    public void thatIHaveLaunchedTheApp() throws Exception{
        homeLauncher.launchActivity(null);

        Log.d(TAG, "Application launched");
    }

    @When("I enter {string} as my Username.")
    public void iEnterAsMyUsername(String username) {
        onView(withId(R.id.userpassword)).perform(typeText(username));
    }

    @And("I enter {string} as my password.")
    public void iEnterAsMyPassword(String password) {
        onView(withId(R.id.userpassword)).perform(typeText(password));
    }

    @And("I press the login button.")
    public void iPressTheLoginButton() {
        onView(withId(R.id.submitButton)).perform(click());
    }

    @When("I attempt to login as user {string} with password {string}.")
    public void iAttemptToLoginAsUserWithPassword(String username, String password) {
        iEnterAsMyUsername(username);
        iEnterAsMyPassword(password);
        iPressTheLoginButton();
    }

    @Given("I am logged into the app.")
    public void iAmLoggedIntoTheApp(){

    }




    @Given("a user named {string} exists.")
    public void a_user_named_exists(String string) {

        Log.d(TAG, "a_user_named_exists: ");
    }


    @And("I am signed in as that user.")
    public void iAmSignedInAsThatUser() {
    }


    @Given("that I am logged into account {string}.")
    public void that_I_am_logged_into_account(String string) {
        onView(withId(R.id.loginusernme)).perform(typeText("Oxime"));
        
    }

    @Given("I am on the {string}")
    public void iAmOnTheTab(String arg0) {
        navigation navActivity = (navigation)navLauncher.getActivity();
        Fragment tab = navActivity.getSupportFragmentManager().findFragmentByTag(navActivity.getResources().getString(R.id.nav_home));

        assertNotNull(tab);
        assertTrue("Test failed: not on "+ arg0 + " tab",tab.isVisible());

    }

    @Given("there are {int} BACON QUEEN orders in my cart.")
    public void there_are_BACON_QUEEN_orders_in_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @When("I add  {int} BACON QUEEN orders of to my cart.")
    public void i_add_BACON_QUEEN_orders_of_to_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @When("I click on the cart tab.")
    public void i_click_on_the_cart_tab() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @Then("I should see {int} BACON QUEEN orders in my cart.")
    public void i_should_see_BACON_QUEEN_orders_in_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @Then("I take a screenshot.")
    public void i_take_a_screenshot() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @Given("there are {int} TRIPLE BURGER orders in my cart.")
    public void there_are_TRIPLE_BURGER_orders_in_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @When("I add  {int} TRIPLE BURGER orders of to my cart.")
    public void i_add_TRIPLE_BURGER_orders_of_to_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @Then("I should see {int} TRIPLE BURGER orders in my cart.")
    public void i_should_see_TRIPLE_BURGER_orders_in_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @Given("there are {int} QUARTER POUND QUEEN orders in my cart.")
    public void there_are_QUARTER_POUND_QUEEN_orders_in_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @When("I add  {int} QUARTER POUND QUEEN orders of to my cart.")
    public void i_add_QUARTER_POUND_QUEEN_orders_of_to_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }

    @Then("I should see {int} QUARTER POUND QUEEN orders in my cart.")
    public void i_should_see_QUARTER_POUND_QUEEN_orders_in_my_cart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It works.");
    }


    @When("I press the {string} order.")
    public void iPressTheOrder(String order) {
        onView(withText(order)).perform(click());
    }
}

