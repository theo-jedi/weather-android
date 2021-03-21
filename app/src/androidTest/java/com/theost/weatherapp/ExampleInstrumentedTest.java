package com.theost.weatherapp;

import android.content.Context;
import android.database.Cursor;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.theost.weatherapp.utils.DatabaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.theost.weatherapp", appContext.getPackageName());
    }

    @Test
    public void databaseTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // Database unit test
        DatabaseHelper databaseHelper = new DatabaseHelper(appContext);
        Cursor data = databaseHelper.getMonthData("Алмазный", 1);
        if (data != null) {
            if (data.moveToFirst()) {
                String dayTemp = data.getString(data.getColumnIndex("TEMP"));
                assertNotEquals(dayTemp, null);
            }
        }
    }
}