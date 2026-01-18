"""
Digital Clock - Python Reference Implementation
Class 01: Data Definitions and Classes
January 19, 2026

This is a reference implementation that students will translate to Java.
"""


class DigitalClock:
    """
    A digital clock that displays time in decimal, binary, and hexadecimal formats.
    
    Time is internally stored as seconds since midnight (0-86399) to simplify
    arithmetic operations and maintain a single source of truth.
    
    Invariants:
        - _seconds_since_midnight is always in range [0, 86399]
        - This represents a valid time within a 24-hour day
    """
    
    def __init__(self, hours, minutes, seconds):
        """
        Create a digital clock with the given time.
        
        Args:
            hours (int): Hour of day, must be in range [0, 23]
            minutes (int): Minutes, must be in range [0, 59]
            seconds (int): Seconds, must be in range [0, 59]
            
        Raises:
            ValueError: If any parameter is out of its valid range
            
        Examples:
            >>> clock = DigitalClock(14, 37, 42)
            >>> clock.get_decimal()
            '14:37:42'
            
            >>> DigitalClock(-1, 0, 0)  # doctest: +IGNORE_EXCEPTION_DETAIL
            Traceback (most recent call last):
            ValueError: Hours must be in range [0, 23], got -1
        """
        # Validate hours
        if not isinstance(hours, int) or not (0 <= hours <= 23):
            raise ValueError(f"Hours must be in range [0, 23], got {hours}")
        
        # Validate minutes
        if not isinstance(minutes, int) or not (0 <= minutes <= 59):
            raise ValueError(f"Minutes must be in range [0, 59], got {minutes}")
        
        # Validate seconds
        if not isinstance(seconds, int) or not (0 <= seconds <= 59):
            raise ValueError(f"Seconds must be in range [0, 59], got {seconds}")
        
        # Store as seconds since midnight (single source of truth)
        # This makes arithmetic operations simpler and maintains invariants
        self._seconds_since_midnight = hours * 3600 + minutes * 60 + seconds
    
    def get_decimal(self):
        """
        Return time in standard decimal format (HH:MM:SS).
        
        Returns:
            str: Time formatted as "HH:MM:SS" with zero-padding
            
        Examples:
            >>> clock = DigitalClock(9, 5, 3)
            >>> clock.get_decimal()
            '09:05:03'
            
            >>> clock = DigitalClock(23, 59, 59)
            >>> clock.get_decimal()
            '23:59:59'
        """
        hours = self._seconds_since_midnight // 3600
        minutes = (self._seconds_since_midnight % 3600) // 60
        seconds = self._seconds_since_midnight % 60
        return f"{hours:02d}:{minutes:02d}:{seconds:02d}"
    
    def get_binary(self):
        """
        Return time in binary format (16-bit representation).
        
        Returns seconds since midnight as a 16-bit binary string.
        Maximum value is 86399 seconds, which fits in 16 bits (2^16 = 65536).
        
        Returns:
            str: 16-bit binary representation (e.g., "0000000000101010")
            
        Examples:
            >>> clock = DigitalClock(0, 0, 42)
            >>> clock.get_binary()
            '0000000000101010'
            
            >>> clock = DigitalClock(0, 0, 0)
            >>> clock.get_binary()
            '0000000000000000'
        """
        return f"{self._seconds_since_midnight:016b}"
    
    def get_hex(self):
        """
        Return time in hexadecimal format.
        
        Returns seconds since midnight as a hexadecimal string with 0x prefix.
        Uses uppercase letters for hex digits A-F.
        
        Returns:
            str: Hexadecimal representation (e.g., "0x1517F")
            
        Examples:
            >>> clock = DigitalClock(0, 0, 255)
            >>> clock.get_hex()
            '0x00FF'
            
            >>> clock = DigitalClock(23, 59, 59)
            >>> clock.get_hex()
            '0x1517F'
        """
        return f"0x{self._seconds_since_midnight:04X}"
    
    def get_seconds_since_midnight(self):
        """
        Return the raw internal representation: seconds since midnight.
        
        Returns:
            int: Number of seconds since midnight [0, 86399]
            
        Examples:
            >>> clock = DigitalClock(0, 0, 0)
            >>> clock.get_seconds_since_midnight()
            0
            
            >>> clock = DigitalClock(1, 0, 0)
            >>> clock.get_seconds_since_midnight()
            3600
            
            >>> clock = DigitalClock(23, 59, 59)
            >>> clock.get_seconds_since_midnight()
            86399
        """
        return self._seconds_since_midnight
    
    def __str__(self):
        """
        Return string representation of the clock.
        
        Returns:
            str: Multi-line string showing all formats
        """
        return (f"DigitalClock:\n"
                f"  Decimal: {self.get_decimal()}\n"
                f"  Binary:  {self.get_binary()}\n"
                f"  Hex:     {self.get_hex()}\n"
                f"  Seconds: {self.get_seconds_since_midnight()}")
    
    def __repr__(self):
        """
        Return unambiguous representation of the clock.
        
        Returns:
            str: Constructor call that would recreate this clock
        """
        hours = self._seconds_since_midnight // 3600
        minutes = (self._seconds_since_midnight % 3600) // 60
        seconds = self._seconds_since_midnight % 60
        return f"DigitalClock({hours}, {minutes}, {seconds})"


def demo():
    """
    Demonstrate the DigitalClock class with various examples.
    """
    print("=" * 60)
    print("Digital Clock Demo")
    print("=" * 60)
    
    # Example 1: Afternoon time
    print("\nExample 1: Afternoon (2:37:42 PM)")
    clock1 = DigitalClock(14, 37, 42)
    print(clock1)
    
    # Example 2: Midnight
    print("\nExample 2: Midnight")
    clock2 = DigitalClock(0, 0, 0)
    print(clock2)
    
    # Example 3: End of day
    print("\nExample 3: End of Day (11:59:59 PM)")
    clock3 = DigitalClock(23, 59, 59)
    print(clock3)
    
    # Example 4: Morning
    print("\nExample 4: Morning (9:05:03 AM)")
    clock4 = DigitalClock(9, 5, 3)
    print(clock4)
    
    # Example 5: Error handling
    print("\nExample 5: Error Handling")
    try:
        invalid_clock = DigitalClock(25, 0, 0)
    except ValueError as e:
        print(f"  Caught error: {e}")
    
    try:
        invalid_clock = DigitalClock(12, 60, 0)
    except ValueError as e:
        print(f"  Caught error: {e}")
    
    try:
        invalid_clock = DigitalClock(12, 30, -5)
    except ValueError as e:
        print(f"  Caught error: {e}")
    
    print("\n" + "=" * 60)


def run_tests():
    """
    Run basic tests to verify correctness.
    """
    print("\nRunning Tests...")
    print("-" * 60)
    
    # Test 1: Valid construction
    clock = DigitalClock(14, 37, 42)
    assert clock.get_decimal() == "14:37:42", "Test 1 failed"
    print("✓ Test 1: Valid construction")
    
    # Test 2: Seconds calculation
    clock = DigitalClock(1, 0, 0)
    assert clock.get_seconds_since_midnight() == 3600, "Test 2 failed"
    print("✓ Test 2: Seconds calculation")
    
    # Test 3: Binary format
    clock = DigitalClock(0, 0, 42)
    assert clock.get_binary() == "0000000000101010", "Test 3 failed"
    print("✓ Test 3: Binary format")
    
    # Test 4: Hex format
    clock = DigitalClock(0, 0, 255)
    assert clock.get_hex() == "0x00FF", "Test 4 failed"
    print("✓ Test 4: Hex format")
    
    # Test 5: Midnight
    clock = DigitalClock(0, 0, 0)
    assert clock.get_seconds_since_midnight() == 0, "Test 5 failed"
    assert clock.get_decimal() == "00:00:00", "Test 5 failed"
    print("✓ Test 5: Midnight")
    
    # Test 6: End of day
    clock = DigitalClock(23, 59, 59)
    assert clock.get_seconds_since_midnight() == 86399, "Test 6 failed"
    assert clock.get_hex() == "0x1517F", "Test 6 failed"
    print("✓ Test 6: End of day")
    
    # Test 7: Invalid hours
    try:
        DigitalClock(-1, 0, 0)
        assert False, "Test 7 failed: should raise ValueError"
    except ValueError:
        print("✓ Test 7: Invalid hours (negative)")
    
    # Test 8: Invalid hours (too large)
    try:
        DigitalClock(24, 0, 0)
        assert False, "Test 8 failed: should raise ValueError"
    except ValueError:
        print("✓ Test 8: Invalid hours (too large)")
    
    # Test 9: Invalid minutes
    try:
        DigitalClock(12, 60, 0)
        assert False, "Test 9 failed: should raise ValueError"
    except ValueError:
        print("✓ Test 9: Invalid minutes")
    
    # Test 10: Invalid seconds
    try:
        DigitalClock(12, 30, 60)
        assert False, "Test 10 failed: should raise ValueError"
    except ValueError:
        print("✓ Test 10: Invalid seconds")
    
    print("-" * 60)
    print("All tests passed! ✓")


if __name__ == "__main__":
    # Run demonstration
    demo()
    
    # Run tests
    run_tests()
    
    # Interactive mode
    print("\n" + "=" * 60)
    print("Try it yourself!")
    print("Create a clock: clock = DigitalClock(hours, minutes, seconds)")
    print("=" * 60)
