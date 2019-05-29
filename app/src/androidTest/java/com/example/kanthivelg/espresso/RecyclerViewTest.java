package com.example.kanthivelg.espresso;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<EspressoRecyclerViewActivity> activityTestRule =
            new ActivityTestRule<>(EspressoRecyclerViewActivity.class);


    @Test
    public void testEventList() {

    }
}
