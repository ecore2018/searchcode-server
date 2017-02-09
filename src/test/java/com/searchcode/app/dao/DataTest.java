package com.searchcode.app.dao;

import com.searchcode.app.config.Values;
import com.searchcode.app.service.Singleton;
import junit.framework.TestCase;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DataTest extends TestCase {

    public DataTest() {
        // Tests need to bootstrap themselves
        Data data = Singleton.getData();
        data.createTableIfMissing();
    }

    public void testDataSaveUpdate() {
        Data data = Singleton.getData();

        String expected = Values.EMPTYSTRING + System.currentTimeMillis();
        String actual = data.getDataByName("test_case_data_ignore");
        assertThat(actual).as("Checking value before saving").isNotEqualTo(expected);

        boolean isNew = data.saveData("test_case_data_ignore", expected);
        actual = data.getDataByName("test_case_data_ignore");
        assertThat(actual).as("Checking value after saving isNew=%s", isNew).isEqualTo(expected);
    }

    public void testManyGetCacheOk() {
        Data data = Singleton.getData();

        String expected = "" + System.currentTimeMillis();
        data.saveData("test_case_data_ignore", expected);

        for(int i=0; i<10000; i++) {
            assertThat(expected).as("Get with no default").isEqualTo(data.getDataByName("test_case_data_ignore"));
            assertThat(expected).as("Get with default").isEqualTo(data.getDataByName("test_case_data_ignore", "default"));
        }
    }

    public void testCreateTable() {
        Data data = Singleton.getData();

        data.createTableIfMissing();
        data.createTableIfMissing();
        data.createTableIfMissing();
        data.createTableIfMissing();
    }
}
