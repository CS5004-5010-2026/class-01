package clock;

/**
 * A digital clock that displays time in decimal, binary, and hexadecimal formats.
 * 
 * <p>Time is internally stored as seconds since midnight (0-86399) to simplify
 * arithmetic operations and maintain a single source of truth.</p>
 * 
 * <p><strong>Class Invariants:</strong></p>
 * <ul>
 *   <li>secondsSinceMidnight is always in range [0, 86399]</li>
 *   <li>This represents a valid time within a 24-hour day</li>
 * </ul>
 * 
 * <p><strong>Design Notes:</strong></p>
 * <ul>
 *   <li>Storing as seconds simplifies validation and arithmetic</li>
 *   <li>All display methods derive values from this single field</li>
 *   <li>Maximum value (86399) fits comfortably in a 32-bit int</li>
 * </ul>
 * 
 * @author Your Name
 * @version 1.0
 */
public class DigitalClock {
    
    // TODO: Add private field(s) to store the time
    // Hint: Consider storing as seconds since midnight
    
    /**
     * Creates a digital clock with the given time.
     * 
     * <p>Validates all parameters and throws IllegalArgumentException if any
     * parameter is out of range.</p>
     * 
     * @param hours   Hour of day, must be in range [0, 23]
     * @param minutes Minutes, must be in range [0, 59]
     * @param seconds Seconds, must be in range [0, 59]
     * @throws IllegalArgumentException if any parameter is out of its valid range
     */
    public DigitalClock(int hours, int minutes, int seconds) {
        // TODO: Validate hours (0-23)
        // TODO: Validate minutes (0-59)
        // TODO: Validate seconds (0-59)
        // TODO: Store the time (consider converting to seconds since midnight)
        
        throw new UnsupportedOperationException("Constructor not yet implemented");
    }
    
    /**
     * Returns time in standard decimal format (HH:MM:SS).
     * 
     * <p>Format uses zero-padding to ensure consistent width.
     * For example: "09:05:03" not "9:5:3"</p>
     * 
     * @return Time formatted as "HH:MM:SS" with zero-padding
     */
    public String getDecimal() {
        // TODO: Convert seconds since midnight back to hours, minutes, seconds
        // TODO: Format as "HH:MM:SS" with zero-padding
        // Hint: Use String.format() with %02d for zero-padding
        
        throw new UnsupportedOperationException("getDecimal() not yet implemented");
    }
    
    /**
     * Returns time in binary format (16-bit representation).
     * 
     * <p>Returns seconds since midnight as a 16-bit binary string.
     * Maximum value is 86399 seconds, which fits in 16 bits (2^16 = 65536).</p>
     * 
     * @return 16-bit binary representation (e.g., "0000000000101010")
     */
    public String getBinary() {
        // TODO: Convert seconds since midnight to binary string
        // TODO: Pad to 16 bits
        // Hint: Use Integer.toBinaryString() and String.format()
        
        throw new UnsupportedOperationException("getBinary() not yet implemented");
    }
    
    /**
     * Returns time in hexadecimal format.
     * 
     * <p>Returns seconds since midnight as a hexadecimal string with 0x prefix.
     * Uses uppercase letters for hex digits A-F.</p>
     * 
     * @return Hexadecimal representation (e.g., "0x1517F")
     */
    public String getHex() {
        // TODO: Convert seconds since midnight to hexadecimal string
        // TODO: Add "0x" prefix and use uppercase
        // Hint: Use Integer.toHexString() and String.format()
        
        throw new UnsupportedOperationException("getHex() not yet implemented");
    }
    
    /**
     * Returns the raw internal representation: seconds since midnight.
     * 
     * @return Number of seconds since midnight [0, 86399]
     */
    public int getSecondsSinceMidnight() {
        // TODO: Return the internal field value
        
        throw new UnsupportedOperationException("getSecondsSinceMidnight() not yet implemented");
    }
    
    /**
     * Returns a string representation of this clock.
     * 
     * <p>Format shows all representations for easy debugging.</p>
     * 
     * @return Multi-line string showing all formats
     */
    @Override
    public String toString() {
        return String.format("DigitalClock:\n  Decimal: %s\n  Binary:  %s\n  Hex:     %s\n  Seconds: %d",
                getDecimal(), getBinary(), getHex(), getSecondsSinceMidnight());
    }
}
