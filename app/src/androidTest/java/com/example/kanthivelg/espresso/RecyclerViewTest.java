package com.example.kanthivelg.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.kanthivelg.espresso.RecyclerViewMatcher.withRecyclerView;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest extends BaseActivityTest{

    @Rule
    public ActivityTestRule<EspressoRecyclerViewActivity> activityTestRule =
            new ActivityTestRule<>(EspressoRecyclerViewActivity.class);

    @Before
    public void setUp() {
        customFailureHandler = new CustomFailureHandler(
                getInstrumentation().getTargetContext(),
                activityTestRule.getActivity().getLocalClassName(), activityTestRule.getActivity());
        Espresso.setFailureHandler(customFailureHandler);
    }

    // Convenience helper


    @Test
    public void testFirstItem() {
        onView(withRecyclerView()).perform(RecyclerViewActions.actionOnItem())
                ;

    }
}
