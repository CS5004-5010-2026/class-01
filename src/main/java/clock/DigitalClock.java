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
    
    /** Seconds since midnight [0, 86399] */
    private final int secondsSinceMidnight;
    
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
        // Validate hours (0-23)
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23, got: " + hours);
        }
        
        // Validate minutes (0-59)
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59, got: " + minutes);
        }
        
        // Validate seconds (0-59)
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Seconds must be between 0 and 59, got: " + seconds);
        }
        
        // Store the time as seconds since midnight
        this.secondsSinceMidnight = (hours * 3600) + (minutes * 60) + seconds;
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
        // Convert seconds since midnight back to hours, minutes, seconds
        int hours = secondsSinceMidnight / 3600;
        int minutes = (secondsSinceMidnight % 3600) / 60;
        int seconds = secondsSinceMidnight % 60;
        
        // Format as "HH:MM:SS" with zero-padding
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
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
        // Convert seconds since midnight to binary string
        String binary = Integer.toBinaryString(secondsSinceMidnight);
        
        // Pad to 16 bits
        return String.format("%16s", binary).replace(' ', '0');
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
        // Convert seconds since midnight to hexadecimal string
        String hex = Integer.toHexString(secondsSinceMidnight).toUpperCase();
        
        // Add "0x" prefix and pad to 4 digits
        return String.format("0x%04X", secondsSinceMidnight);
    }
    
    /**
     * Returns the raw internal representation: seconds since midnight.
     * 
     * @return Number of seconds since midnight [0, 86399]
     */
    public int getSecondsSinceMidnight() {
        return secondsSinceMidnight;
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
    
    /**
     * Demonstrates the DigitalClock class with various examples.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("Digital Clock Demo");
        System.out.println("============================================================");
        
        // Example 1: Afternoon time
        System.out.println("\nExample 1: Afternoon (2:37:42 PM)");
        DigitalClock clock1 = new DigitalClock(14, 37, 42);
        System.out.println(clock1);
        
        // Example 2: Midnight
        System.out.println("\nExample 2: Midnight");
        DigitalClock clock2 = new DigitalClock(0, 0, 0);
        System.out.println(clock2);
        
        // Example 3: End of day
        System.out.println("\nExample 3: End of Day (11:59:59 PM)");
        DigitalClock clock3 = new DigitalClock(23, 59, 59);
        System.out.println(clock3);
        
        // Example 4: Morning
        System.out.println("\nExample 4: Morning (9:05:03 AM)");
        DigitalClock clock4 = new DigitalClock(9, 5, 3);
        System.out.println(clock4);
        
        // Example 5: Error handling
        System.out.println("\nExample 5: Error Handling");
        try {
            new DigitalClock(25, 0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught error: " + e.getMessage());
        }
        
        try {
            new DigitalClock(12, 60, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught error: " + e.getMessage());
        }
        
        try {
            new DigitalClock(12, 30, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught error: " + e.getMessage());
        }
        
        System.out.println("\n============================================================");
    }
}
