package se.require4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RequireTest {

    @Test
    void test_nonNull_ShouldNotThrowException() {
        Require.nonNull("abc", () -> new IllegalArgumentException("shouldNotSee"));
        Require.nonNull(123, () -> new IllegalArgumentException("shouldNotSee"));
        Require.nonNull(new ArrayList<>(), () -> new IllegalArgumentException("shouldNotSee"));
    }

    @Test
    void test_nonNull_ShouldThrowException() {
        IllegalArgumentException expectedException = new IllegalArgumentException("error");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> Require.nonNull(null, () -> expectedException));

        assertEquals(expectedException, exception);

        assertThrows(IOException.class,
            () -> Require.nonNull(null, () -> new IOException("IO error")));
    }

    @Test
    void test_nonEmpty_ShouldNotThrowException() {
        Require.nonEmpty("abc", () -> new IllegalArgumentException("shouldNotSee"));
        Require.nonEmpty(123, () -> new IllegalArgumentException("shouldNotSee"));
        Require.nonEmpty(Arrays.asList(1,2,3), () -> new IllegalArgumentException("shouldNotSee"));
        Require.nonEmpty(new byte[] {1,2}, () -> new IllegalArgumentException("shouldNotSee"));
        Require.nonEmpty(new int[] {1,2}, () -> new IllegalArgumentException("shouldNotSee"));
    }

    @Test
    void test_nonEmpty_ShouldThrowException() {
        IllegalArgumentException expectedException = new IllegalArgumentException("error");

        // Test for empty String
        IllegalArgumentException stringException = assertThrows(IllegalArgumentException.class,
            () -> Require.nonEmpty("   ", () -> expectedException));
        assertEquals(expectedException, stringException);

        // Test for empty Collection
        IllegalArgumentException collectionException = assertThrows(IllegalArgumentException.class,
            () -> Require.nonEmpty(new ArrayList<>(), () -> expectedException));
        assertEquals(expectedException, collectionException);

        // Test for empty Map
        IllegalArgumentException mapException = assertThrows(IllegalArgumentException.class,
            () -> Require.nonEmpty(Map.of(), () -> expectedException));
        assertEquals(expectedException, mapException);

        // Test for empty Array
        IllegalArgumentException arrayException = assertThrows(IllegalArgumentException.class,
            () -> Require.nonEmpty(new int[] {}, () -> expectedException));
        assertEquals(expectedException, arrayException);

        //Test for Map of null
        Map<String, String> nullMap = null;
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class,
            () -> Require.nonEmpty(nullMap, () -> expectedException));

        assertThrows(IOException.class,
            () -> Require.nonEmpty(null, () -> new IOException("IO error")));
    }


}
