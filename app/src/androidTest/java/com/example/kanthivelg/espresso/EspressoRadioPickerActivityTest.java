package com.example.kanthivelg.espresso;

import android.Manifest;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class EspressoRadioPickerActivityTest extends BaseActivityTest{

    @Rule
    public ActivityTestRule<EspressoRadioPickerActivity> activityTestRule =
            new ActivityTestRule<>(EspressoRadioPickerActivity.class);

    @Before
    public void setUp() {
        customFailureHandler = new CustomFailureHandler(
                getInstrumentation().getTargetContext(),activityTestRule.getActivity());
        Espresso.setFailureHandler(customFailureHandler);
    }

    @Test
    public void testRegisterFlow() {

        // Type Name

        onView(allOf(withId(R.id.nameLabel), withText("Name:")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.nameEditText), withHint("Enter your name")))
                .perform(typeText("VodQA"), closeSoftKeyboard());


        //Choose Gender

        onView(allOf(withId(R.id.genderLabel), withText("Gender:")))
                .check(matches(isDisplayed()));
        onView(withId(R.id.maleRadioButton)).check(matches(isChecked()));
        onView(withId(R.id.femaleRadioButton)).check(matches(not(isChecked())));
        onView(withId(R.id.otherRadioButton)).check(matches(not(isChecked())));
        onView(withId(R.id.otherRadioButton)).perform(click());
        onView(withId(R.id.otherRadioButton)).check(matches(isChecked()));


        //Select a date

        onView(allOf(withId(R.id.dobLabel), withText("DOB:")))
                .check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.dobDatePicker)).perform(scrollTo(), PickerActions.setDate(2019, 6, 1));

        //Click register

        scrollTo();
        onView(allOf(withId(R.id.registerButton), withText("Register")))
                .perform(scrollTo(), click());


        //Verify dialog

        onView(withText("Registered")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("You are a Other with name VodQA and DOB 1:6:2019")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button3), withText("OK"))).perform(click());

    }

}
