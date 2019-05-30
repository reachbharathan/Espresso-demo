package com.example.kanthivelg.espresso;

import android.Manifest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EspressoRadioPickerActivityTest {

    @Rule
    public ActivityTestRule<EspressoRadioPickerActivity> activityTestRule =
            new ActivityTestRule<>(EspressoRadioPickerActivity.class);

    @Rule public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private CustomFailureHandler customFailureHandler;

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
