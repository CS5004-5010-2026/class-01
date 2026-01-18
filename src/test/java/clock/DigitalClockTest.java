package clock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for DigitalClock class.
 * 
 * <p>Tests cover:</p>
 * <ul>
 *   <li>Valid construction with various times</li>
 *   <li>Invalid inputs (defensive programming)</li>
 *   <li>Boundary cases (midnight, end of day)</li>
 *   <li>Format correctness (decimal, binary, hex)</li>
 *   <li>Edge cases (noon, one second before/after midnight)</li>
 *   <li>Integration tests (multi-format workflows)</li>
 *   <li>Parametrized tests for comprehensive coverage</li>
 * </ul>
 * 
 * @author Instructor
 * @version 2.0
 */
public class DigitalClockTest {
    
    // ========== Constructor Tests ==========
    
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        
        @Test
        @DisplayName("Test valid construction with afternoon time")
        public void testValidConstruction() {
            DigitalClock clock = new DigitalClock(14, 37, 42);
            assertEquals("14:37:42", clock.getDecimal());
            assertEquals(52662, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test construction with midnight")
        public void testMidnight() {
            DigitalClock clock = new DigitalClock(0, 0, 0);
            assertEquals(0, clock.getSecondsSinceMidnight());
            assertEquals("00:00:00", clock.getDecimal());
            assertEquals("0000000000000000", clock.getBinary());
            assertEquals("0x0000", clock.getHex());
        }
        
        @Test
        @DisplayName("Test construction with end of day")
        public void testEndOfDay() {
            DigitalClock clock = new DigitalClock(23, 59, 59);
            assertEquals(86399, clock.getSecondsSinceMidnight());
            assertEquals("23:59:59", clock.getDecimal());
            assertEquals("0x1517F", clock.getHex());
        }
        
        @Test
        @DisplayName("Test construction with morning time")
        public void testMorningTime() {
            DigitalClock clock = new DigitalClock(9, 5, 3);
            assertEquals("09:05:03", clock.getDecimal());
        }
    }
    
    // ========== Invalid Input Tests ==========
    
    @Nested
    @DisplayName("Invalid Input Tests")
    class InvalidInputTests {
        
        @Test
        @DisplayName("Test invalid hours - negative")
        public void testInvalidHoursNegative() {
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(-1, 0, 0));
            assertTrue(exception.getMessage().contains("Hours") || 
                       exception.getMessage().contains("hours"));
        }
        
        @Test
        @DisplayName("Test invalid hours - too large")
        public void testInvalidHoursTooLarge() {
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(24, 0, 0));
            assertTrue(exception.getMessage().contains("Hours") || 
                       exception.getMessage().contains("hours"));
        }
        
        @Test
        @DisplayName("Test invalid minutes - negative")
        public void testInvalidMinutesNegative() {
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(12, -1, 0));
            assertTrue(exception.getMessage().contains("Minutes") || 
                       exception.getMessage().contains("minutes"));
        }
        
        @Test
        @DisplayName("Test invalid minutes - too large")
        public void testInvalidMinutesTooLarge() {
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(12, 60, 0));
            assertTrue(exception.getMessage().contains("Minutes") || 
                       exception.getMessage().contains("minutes"));
        }
        
        @Test
        @DisplayName("Test invalid seconds - negative")
        public void testInvalidSecondsNegative() {
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(12, 30, -1));
            assertTrue(exception.getMessage().contains("Seconds") || 
                       exception.getMessage().contains("seconds"));
        }
        
        @Test
        @DisplayName("Test invalid seconds - too large")
        public void testInvalidSecondsTooLarge() {
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(12, 30, 60));
            assertTrue(exception.getMessage().contains("Seconds") || 
                       exception.getMessage().contains("seconds"));
        }
        
        @ParameterizedTest
        @DisplayName("Test invalid hours - parametrized")
        @ValueSource(ints = {-1, -10, 24, 25, 100})
        public void testInvalidHoursParametrized(int hours) {
            assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(hours, 0, 0));
        }
        
        @ParameterizedTest
        @DisplayName("Test invalid minutes - parametrized")
        @ValueSource(ints = {-1, -10, 60, 61, 100})
        public void testInvalidMinutesParametrized(int minutes) {
            assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(12, minutes, 0));
        }
        
        @ParameterizedTest
        @DisplayName("Test invalid seconds - parametrized")
        @ValueSource(ints = {-1, -10, 60, 61, 100})
        public void testInvalidSecondsParametrized(int seconds) {
            assertThrows(IllegalArgumentException.class, 
                () -> new DigitalClock(12, 30, seconds));
        }
    }
    
    // ========== Format Tests ==========
    
    @Nested
    @DisplayName("Format Tests")
    class FormatTests {
        
        @Test
        @DisplayName("Test binary format with small value")
        public void testBinaryFormatSmall() {
            DigitalClock clock = new DigitalClock(0, 0, 42);
            assertEquals("0000000000101010", clock.getBinary());
        }
        
        @Test
        @DisplayName("Test binary format with large value")
        public void testBinaryFormatLarge() {
            DigitalClock clock = new DigitalClock(23, 59, 59);
            String binary = clock.getBinary();
            assertEquals(16, binary.length(), "Binary should be 16 bits");
            assertEquals(86399, Integer.parseInt(binary, 2), "Binary value should equal 86399");
        }
        
        @Test
        @DisplayName("Test hexadecimal format with small value")
        public void testHexFormatSmall() {
            DigitalClock clock = new DigitalClock(0, 0, 255);
            assertEquals("0x00FF", clock.getHex());
        }
        
        @Test
        @DisplayName("Test hexadecimal format with large value")
        public void testHexFormatLarge() {
            DigitalClock clock = new DigitalClock(23, 59, 59);
            assertEquals("0x1517F", clock.getHex());
        }
        
        @Test
        @DisplayName("Test decimal format with zero-padding")
        public void testDecimalFormatPadding() {
            DigitalClock clock = new DigitalClock(9, 5, 3);
            String decimal = clock.getDecimal();
            assertEquals("09:05:03", decimal);
            assertTrue(decimal.matches("\\d{2}:\\d{2}:\\d{2}"), 
                       "Decimal format should match HH:MM:SS pattern");
        }
        
        @ParameterizedTest
        @DisplayName("Test decimal format - various times")
        @CsvSource({
            "0, 0, 0, 00:00:00",
            "12, 0, 0, 12:00:00",
            "23, 59, 59, 23:59:59",
            "9, 5, 3, 09:05:03",
            "14, 37, 42, 14:37:42"
        })
        public void testDecimalFormatVariousTimes(int hours, int minutes, int seconds, String expected) {
            DigitalClock clock = new DigitalClock(hours, minutes, seconds);
            assertEquals(expected, clock.getDecimal());
        }
        
        @Test
        @DisplayName("Test hex format is uppercase")
        public void testHexFormatUppercase() {
            DigitalClock clock = new DigitalClock(10, 30, 45);
            String hex = clock.getHex();
            assertTrue(hex.matches("0x[0-9A-F]+"), "Hex should be uppercase");
        }
        
        @Test
        @DisplayName("Test binary format length consistency")
        public void testBinaryFormatLength() {
            DigitalClock clock1 = new DigitalClock(0, 0, 1);
            DigitalClock clock2 = new DigitalClock(23, 59, 59);
            assertEquals(16, clock1.getBinary().length());
            assertEquals(16, clock2.getBinary().length());
        }
    }
    
    // ========== Seconds Calculation Tests ==========
    
    @Nested
    @DisplayName("Seconds Calculation Tests")
    class SecondsCalculationTests {
        
        @Test
        @DisplayName("Test seconds calculation for one hour")
        public void testSecondsOneHour() {
            DigitalClock clock = new DigitalClock(1, 0, 0);
            assertEquals(3600, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test seconds calculation for one minute")
        public void testSecondsOneMinute() {
            DigitalClock clock = new DigitalClock(0, 1, 0);
            assertEquals(60, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test seconds calculation for complex time")
        public void testSecondsComplexTime() {
            DigitalClock clock = new DigitalClock(14, 37, 42);
            // (14 * 3600) + (37 * 60) + 42 = 50400 + 2220 + 42 = 52662
            assertEquals(52662, clock.getSecondsSinceMidnight());
        }
        
        @ParameterizedTest
        @DisplayName("Test seconds calculation - various times")
        @CsvSource({
            "0, 0, 0, 0",
            "1, 0, 0, 3600",
            "0, 1, 0, 60",
            "0, 0, 1, 1",
            "12, 30, 45, 45045",
            "23, 59, 59, 86399"
        })
        public void testSecondsCalculationVariousTimes(int hours, int minutes, int seconds, int expectedSeconds) {
            DigitalClock clock = new DigitalClock(hours, minutes, seconds);
            assertEquals(expectedSeconds, clock.getSecondsSinceMidnight());
        }
    }
    
    // ========== Boundary Tests ==========
    
    @Nested
    @DisplayName("Boundary Tests")
    class BoundaryTests {
        
        @Test
        @DisplayName("Test boundary - maximum valid hours")
        public void testBoundaryMaxHours() {
            DigitalClock clock = new DigitalClock(23, 0, 0);
            assertEquals("23:00:00", clock.getDecimal());
        }
        
        @Test
        @DisplayName("Test boundary - maximum valid minutes")
        public void testBoundaryMaxMinutes() {
            DigitalClock clock = new DigitalClock(0, 59, 0);
            assertEquals("00:59:00", clock.getDecimal());
        }
        
        @Test
        @DisplayName("Test boundary - maximum valid seconds")
        public void testBoundaryMaxSeconds() {
            DigitalClock clock = new DigitalClock(0, 0, 59);
            assertEquals("00:00:59", clock.getDecimal());
        }
        
        @Test
        @DisplayName("Test boundary - all zeros")
        public void testBoundaryAllZeros() {
            DigitalClock clock = new DigitalClock(0, 0, 0);
            assertEquals("00:00:00", clock.getDecimal());
            assertEquals(0, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test boundary - all maximum values")
        public void testBoundaryAllMax() {
            DigitalClock clock = new DigitalClock(23, 59, 59);
            assertEquals("23:59:59", clock.getDecimal());
            assertEquals(86399, clock.getSecondsSinceMidnight());
        }
    }
    
    // ========== Edge Case Tests ==========
    
    @Nested
    @DisplayName("Edge Case Tests")
    class EdgeCaseTests {
        
        @Test
        @DisplayName("Test noon")
        public void testNoon() {
            DigitalClock clock = new DigitalClock(12, 0, 0);
            assertEquals("12:00:00", clock.getDecimal());
            assertEquals(43200, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test one second before midnight")
        public void testOneSecondBeforeMidnight() {
            DigitalClock clock = new DigitalClock(23, 59, 59);
            assertEquals("23:59:59", clock.getDecimal());
            assertEquals(86399, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test one second after midnight")
        public void testOneSecondAfterMidnight() {
            DigitalClock clock = new DigitalClock(0, 0, 1);
            assertEquals("00:00:01", clock.getDecimal());
            assertEquals(1, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test one minute after midnight")
        public void testOneMinuteAfterMidnight() {
            DigitalClock clock = new DigitalClock(0, 1, 0);
            assertEquals("00:01:00", clock.getDecimal());
            assertEquals(60, clock.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test one hour after midnight")
        public void testOneHourAfterMidnight() {
            DigitalClock clock = new DigitalClock(1, 0, 0);
            assertEquals("01:00:00", clock.getDecimal());
            assertEquals(3600, clock.getSecondsSinceMidnight());
        }
    }
    
    // ========== Integration Tests ==========
    
    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        
        @Test
        @DisplayName("Test create and display all formats")
        public void testCreateAndDisplayAllFormats() {
            DigitalClock clock = new DigitalClock(14, 37, 42);
            
            // Verify all formats work together
            String decimal = clock.getDecimal();
            String hex = clock.getHex();
            String binary = clock.getBinary();
            int seconds = clock.getSecondsSinceMidnight();
            
            assertEquals("14:37:42", decimal);
            assertEquals("0xCDB6", hex);
            assertEquals(16, binary.length());
            assertEquals(52662, seconds);
            
            // Verify toString includes all information
            String str = clock.toString();
            assertTrue(str.contains(decimal));
            assertTrue(str.contains(hex));
        }
        
        @Test
        @DisplayName("Test multiple independent clock instances")
        public void testMultipleClockInstances() {
            DigitalClock clock1 = new DigitalClock(9, 30, 0);
            DigitalClock clock2 = new DigitalClock(14, 45, 30);
            DigitalClock clock3 = new DigitalClock(23, 59, 59);
            
            // Verify each clock maintains its own state
            assertEquals("09:30:00", clock1.getDecimal());
            assertEquals("14:45:30", clock2.getDecimal());
            assertEquals("23:59:59", clock3.getDecimal());
            
            assertEquals(34200, clock1.getSecondsSinceMidnight());
            assertEquals(53130, clock2.getSecondsSinceMidnight());
            assertEquals(86399, clock3.getSecondsSinceMidnight());
        }
        
        @Test
        @DisplayName("Test format consistency across all methods")
        public void testFormatConsistency() {
            DigitalClock clock = new DigitalClock(10, 20, 30);
            
            // Call methods multiple times to ensure consistency
            assertEquals(clock.getDecimal(), clock.getDecimal());
            assertEquals(clock.getHex(), clock.getHex());
            assertEquals(clock.getBinary(), clock.getBinary());
            assertEquals(clock.getSecondsSinceMidnight(), clock.getSecondsSinceMidnight());
        }
    }
    
    // ========== toString() Tests ==========
    
    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {
        
        @Test
        @DisplayName("Test toString() includes all formats")
        public void testToString() {
            DigitalClock clock = new DigitalClock(14, 37, 42);
            String str = clock.toString();
            assertTrue(str.contains("14:37:42"), "toString should include decimal format");
            assertTrue(str.contains("0x"), "toString should include hex format");
            assertTrue(str.contains("52662"), "toString should include seconds");
        }
        
        @Test
        @DisplayName("Test toString() for midnight")
        public void testToStringMidnight() {
            DigitalClock clock = new DigitalClock(0, 0, 0);
            String str = clock.toString();
            assertTrue(str.contains("00:00:00"));
            assertTrue(str.contains("0x0000") || str.contains("0x0"));
        }
        
        @Test
        @DisplayName("Test toString() for end of day")
        public void testToStringEndOfDay() {
            DigitalClock clock = new DigitalClock(23, 59, 59);
            String str = clock.toString();
            assertTrue(str.contains("23:59:59"));
            assertTrue(str.contains("86399"));
        }
    }
}
