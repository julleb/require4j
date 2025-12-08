package se.require4j;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void test_isEmpty() {
        assertTrue(Utils.isEmpty(""));
        assertTrue(Utils.isEmpty("   "));
        assertFalse(Utils.isEmpty("abc"));
    }
}
