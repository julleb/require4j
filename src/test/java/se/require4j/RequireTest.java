package se.require4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RequireTest {

    @Test
    void test_NonNull_ShouldNotThrowException() {
        Require.NonNull("abc", () -> new IllegalArgumentException("shouldNotSee"));
        Require.NonNull(123, () -> new IllegalArgumentException("shouldNotSee"));
        Require.NonNull(new ArrayList<>(), () -> new IllegalArgumentException("shouldNotSee"));
    }

    @Test
    void test_NonNull_ShouldThrowException() {
        IllegalArgumentException expectedException = new IllegalArgumentException("error");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> Require.NonNull(null, () -> expectedException));

        assertEquals(expectedException, exception);
    }

    @Test
    void test_NonEmpty_ShouldNotThrowException() {
        Require.NonEmpty("abc", () -> new IllegalArgumentException("shouldNotSee"));
        Require.NonEmpty(123, () -> new IllegalArgumentException("shouldNotSee"));
        Require.NonEmpty(Arrays.asList(1,2,3), () -> new IllegalArgumentException("shouldNotSee"));
        Require.NonEmpty(new byte[] {1,2}, () -> new IllegalArgumentException("shouldNotSee"));
        Require.NonEmpty(new int[] {1,2}, () -> new IllegalArgumentException("shouldNotSee"));
    }

    @Test
    void test_NonEmpty_ShouldThrowException() {
        IllegalArgumentException expectedException = new IllegalArgumentException("error");

        // Test for empty String
        IllegalArgumentException stringException = assertThrows(IllegalArgumentException.class,
            () -> Require.NonEmpty("", () -> expectedException));
        assertEquals(expectedException, stringException);

        // Test for empty Collection
        IllegalArgumentException collectionException = assertThrows(IllegalArgumentException.class,
            () -> Require.NonEmpty(new ArrayList<>(), () -> expectedException));
        assertEquals(expectedException, collectionException);

        // Test for empty Map
        IllegalArgumentException mapException = assertThrows(IllegalArgumentException.class,
            () -> Require.NonEmpty(Map.of(), () -> expectedException));
        assertEquals(expectedException, mapException);

        // Test for empty Array
        IllegalArgumentException arrayException = assertThrows(IllegalArgumentException.class,
            () -> Require.NonEmpty(new int[] {}, () -> expectedException));
        assertEquals(expectedException, arrayException);

        //Test for Map of null
        Map<String, String> nullMap = null;
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class,
            () -> Require.NonEmpty(nullMap, () -> expectedException));
    }


}
