package com.example.kanthivelg.espresso;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
public class EspressoImageActivityTest extends BaseActivityTest {

    @Rule
    public ActivityTestRule<EspressoImageActivity> activityTestRule =
            new ActivityTestRule<>(EspressoImageActivity.class);

    @Before
    public void setUp() {
        customFailureHandler = new CustomFailureHandler(
                getInstrumentation().getTargetContext(),
                activityTestRule.getActivity().getLocalClassName(), activityTestRule.getActivity());
        Espresso.setFailureHandler(customFailureHandler);
    }

    @Test
    public void testCoffeeFlowActivityHeader() {
        //setting the method name so that on failure Test name will be displayed
        customFailureHandler.setMethodName(name.getMethodName());

        //Test to check static contents
        onView(withText("Espresso Image")).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.preference), withText("Which one do you prefer....??")))
                .check(matches(isDisplayed()));
        onView(withId(R.id.teaImage)).check(matches(isDisplayed()));
        onView(withId(R.id.coffeeImage)).check(matches(isDisplayed()));
    }


    @Test
    public void testCoffeeFlow() {
        customFailureHandler.setMethodName(name.getMethodName());

        //Take screenshot before selecting the image
        Spoon.screenshot(activityTestRule.getActivity(), "Before_image_click");

        //perform action
        onView(withId(R.id.coffeeImage)).perform(click());


        //Take screenshot after selecting the image
        Spoon.screenshot(activityTestRule.getActivity(), "After_coffee_Click");

        //Make assertions for text to be displayed (positive scenario)
        onView(allOf(withId(R.id.preference), withText("Oh!! You Prefer Coffee")))
                .check(matches(isDisplayed()));

        //Make assertions for image not to be displayed
        onView(withId(R.id.teaImage))
                .check(matches(not(isDisplayed())));
    }


    @Test
    public void testTeaFlow() {
        //setting the method name so that on failure Test name will be displayed
        customFailureHandler.setMethodName(name.getMethodName());

        //Take screenshot before selecting the image
        Spoon.screenshot(activityTestRule.getActivity(), "Before_image_click");

        //perform action
        onView(withId(R.id.teaImage)).perform(click());


        //Take screenshot after selecting the image
        Spoon.screenshot(activityTestRule.getActivity(), "After_tea_Click");

        //Make assertions for text to be displayed (positive scenario)
        onView(allOf(withId(R.id.preference), withText("Oh!! You Prefer Green Tea")))
                .check(matches(isDisplayed()));

        //Make assertions for image not to be displayed
        onView(withId(R.id.coffeeImage))
                .check(matches(not(isDisplayed())));
    }

}
