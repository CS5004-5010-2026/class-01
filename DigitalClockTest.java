package clock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for DigitalClock class.
 * 
 * <p>Tests cover:</p>
 * <ul>
 *   <li>Valid construction with various times</li>
 *   <li>Invalid inputs (defensive programming)</li>
 *   <li>Boundary cases (midnight, end of day)</li>
 *   <li>Format correctness (decimal, binary, hex)</li>
 * </ul>
 * 
 * @author Instructor
 * @version 1.0
 */
public class DigitalClockTest {
    
    // ========== Constructor Tests ==========
    
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
    
    // ========== Invalid Input Tests ==========
    
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
    
    // ========== Format Tests ==========
    
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
        // 86399 in binary is 10101000101111111
        // Padded to 16 bits: 0001010100011111
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
    
    // ========== Seconds Calculation Tests ==========
    
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
    
    // ========== Boundary Tests ==========
    
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
    
    // ========== toString() Test ==========
    
    @Test
    @DisplayName("Test toString() includes all formats")
    public void testToString() {
        DigitalClock clock = new DigitalClock(14, 37, 42);
        String str = clock.toString();
        assertTrue(str.contains("14:37:42"), "toString should include decimal format");
        assertTrue(str.contains("0x"), "toString should include hex format");
        assertTrue(str.contains("52662"), "toString should include seconds");
    }
}
