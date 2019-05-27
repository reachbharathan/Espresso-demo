package com.example.kanthivelg.espresso;

import android.Manifest;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EspressoBasicActivityTest {

    @Rule
    public ActivityTestRule<EspressoBasicActivity> activityTestRule =
            new ActivityTestRule<>(EspressoBasicActivity.class);

    @Rule public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Rule public TestName name = new TestName();

    private CustomFailureHandler customFailureHandler;

    @Before
    public void setUp() {
        customFailureHandler = new CustomFailureHandler(
                getInstrumentation().getTargetContext(),
                activityTestRule.getActivity().getLocalClassName(), activityTestRule.getActivity());
        Espresso.setFailureHandler(customFailureHandler);
    }

    @Test
    public void testCoffeeFlow() {
        customFailureHandler.setMethodName(name.getMethodName());
        onView(allOf(withId(R.id.preference), withText("Which one do you prefer....??")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.teaImage))
                .check(matches(isDisplayed()));

        Spoon.screenshot(activityTestRule.getActivity(), "Before_coffee_Click");

        onView(withId(R.id.coffeeImage))
                .check(matches(isDisplayed()))
                .perform(click());

        //Make assertions

        Spoon.screenshot(activityTestRule.getActivity(), "After_coffee_Click");

        onView(allOf(withId(R.id.preference), withText("Oh!! You Prefer Coffee")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.teaImage))
                .check(matches(not(isDisplayed())));
    }


    @Test
    public void testTeaFlow() {
        customFailureHandler.setMethodName(name.getMethodName());
        onView(allOf(withId(R.id.preference), withText("Which one do you prefer....??")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.coffeeImage))
                .check(matches(isDisplayed()));

        onView(withId(R.id.teaImage))
                .check(matches(isDisplayed()))
                .perform(click());

        //Make assertions

        onView(allOf(withId(R.id.preference), withText("Oh!! You Prefr Green Tea")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.coffeeImage))
                .check(matches(not(isDisplayed())));

    }

}
