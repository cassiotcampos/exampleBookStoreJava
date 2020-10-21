package com.cassio.example.bookstore;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.cassio.example.bookstore.mock.MockData;
import com.cassio.example.bookstore.ui.bookmaster.master.MasterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApiTest {


    private MockWebServer server;

    @Rule
    public ActivityTestRule<MasterActivity> mActivityRule = new ActivityTestRule<>(
            MasterActivity.class);



    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
        server.start(8080);
    }


    @After
    public void shutDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void apiTestErrorTryAgainAfterSuccess() throws InterruptedException {

        // first call should load Book1
        server.enqueue(new MockResponse().setResponseCode(200).setBody(MockData.GET_VOLUMES_SUCCESS_BOOK1));
        // simulates an API error. Forces the app to show the button "Try Again"
        server.enqueue(new MockResponse().setResponseCode(404));
        // after clicking Try Again, simulates another success with Book 2
        server.enqueue(new MockResponse().setResponseCode(200).setBody(MockData.GET_VOLUMES_SUCCESS_BOOK2));


        // start Activity with enqueued requests
        mActivityRule.launchActivity(new Intent());

        onView(withText("Book1"));

        // give a time to "Try Again" shows up
        Thread.sleep(2000); // TODO Its better to use other technique as CountingIddleResources

        onView(withId(R.id.item_book_row_footer_try_again)).perform(click());

        // check if Book2 was loaded
        onView(withText("Book2"));

        Thread.sleep(2000);
    }

    @Test
    public void apiTestErrorFirstCallTryAgain() throws InterruptedException {

        // simulates an API error at first call. Forces the app to show the button "Try Again"
        server.enqueue(new MockResponse().setResponseCode(404).setBody("Simulating an error"));
        // after clicking Try Again, simulates another success with Book 2
        server.enqueue(new MockResponse().setResponseCode(200).setBody(MockData.GET_VOLUMES_SUCCESS_BOOK2));


        // start Activity with enqueued requests
        mActivityRule.launchActivity(new Intent());

        onView(withId(R.id.recycler_view_books));

        // give a time to "Try Again" shows up
        Thread.sleep(2000); // TODO Its better to use other technique as CountingIddleResources

        onView(withId(R.id.item_book_row_footer_try_again)).perform(click());

        // check if Book2 was loaded
        onView(withText("Book2"));

        Thread.sleep(2000);
    }
}