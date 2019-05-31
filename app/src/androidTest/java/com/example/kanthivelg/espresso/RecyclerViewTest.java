package com.example.kanthivelg.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kanthivelg.espresso.RecyclerViewMatcher.withRecyclerView;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest extends BaseActivityTest {

    @Rule
    public ActivityTestRule<EspressoRecyclerViewActivity> activityTestRule =
            new ActivityTestRule<>(EspressoRecyclerViewActivity.class);

    @Before
    public void setUp() {
        customFailureHandler = new CustomFailureHandler(
                getInstrumentation().getTargetContext(), activityTestRule.getActivity());
        Espresso.setFailureHandler(customFailureHandler);
    }

    @Test
    public void testFirstItemTitle() {
        //Clicking the third index recycleview element 10 times
        for (int i = 0; i < 10; i++) {
            onView(withRecyclerView(R.id.recyclerView).atPosition(3)).perform(click());
        }

        //Asserting first index recycleview element for Text 0
        onView(withRecyclerView(R.id.recyclerView).atPosition(0)).check(matches(hasDescendant(withText("Title 0"))));
    }

    @Test
    public void testScrollToLastItem() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
        for (int i = 0; i < 10; i++) {
            onView(withRecyclerView(R.id.recyclerView).atPosition(29)).perform(click());
        }

//        Uncomment to make the test fail
//        onView(withRecyclerView(R.id.recyclerView).atPosition(29)).check(matches(hasDescendant(withText("Title 0"))));
    }
}
