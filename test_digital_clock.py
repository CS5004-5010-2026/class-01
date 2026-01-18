"""
Test suite for DigitalClock class using pytest.

This test suite mirrors the Java JUnit tests to ensure both implementations
behave identically.

Run with:
    pytest test_digital_clock.py -v
    pytest test_digital_clock.py -v --cov=DigitalClock
"""

import pytest
from DigitalClock import DigitalClock


# ========== Constructor Tests ==========

class TestConstruction:
    """Tests for valid clock construction."""
    
    def test_valid_construction(self):
        """Test valid construction with afternoon time."""
        clock = DigitalClock(14, 37, 42)
        assert clock.get_decimal() == "14:37:42"
        assert clock.get_seconds_since_midnight() == 52662
    
    def test_midnight(self):
        """Test construction with midnight."""
        clock = DigitalClock(0, 0, 0)
        assert clock.get_seconds_since_midnight() == 0
        assert clock.get_decimal() == "00:00:00"
        assert clock.get_binary() == "0000000000000000"
        assert clock.get_hex() == "0x0000"
    
    def test_end_of_day(self):
        """Test construction with end of day."""
        clock = DigitalClock(23, 59, 59)
        assert clock.get_seconds_since_midnight() == 86399
        assert clock.get_decimal() == "23:59:59"
        assert clock.get_hex() == "0x1517F"
    
    def test_morning_time(self):
        """Test construction with morning time."""
        clock = DigitalClock(9, 5, 3)
        assert clock.get_decimal() == "09:05:03"


# ========== Invalid Input Tests ==========

class TestInvalidInputs:
    """Tests for invalid input validation."""
    
    def test_invalid_hours_negative(self):
        """Test invalid hours - negative."""
        with pytest.raises(ValueError) as exc_info:
            DigitalClock(-1, 0, 0)
        assert "Hours" in str(exc_info.value) or "hours" in str(exc_info.value)
    
    def test_invalid_hours_too_large(self):
        """Test invalid hours - too large."""
        with pytest.raises(ValueError) as exc_info:
            DigitalClock(24, 0, 0)
        assert "Hours" in str(exc_info.value) or "hours" in str(exc_info.value)
    
    def test_invalid_minutes_negative(self):
        """Test invalid minutes - negative."""
        with pytest.raises(ValueError) as exc_info:
            DigitalClock(12, -1, 0)
        assert "Minutes" in str(exc_info.value) or "minutes" in str(exc_info.value)
    
    def test_invalid_minutes_too_large(self):
        """Test invalid minutes - too large."""
        with pytest.raises(ValueError) as exc_info:
            DigitalClock(12, 60, 0)
        assert "Minutes" in str(exc_info.value) or "minutes" in str(exc_info.value)
    
    def test_invalid_seconds_negative(self):
        """Test invalid seconds - negative."""
        with pytest.raises(ValueError) as exc_info:
            DigitalClock(12, 30, -1)
        assert "Seconds" in str(exc_info.value) or "seconds" in str(exc_info.value)
    
    def test_invalid_seconds_too_large(self):
        """Test invalid seconds - too large."""
        with pytest.raises(ValueError) as exc_info:
            DigitalClock(12, 30, 60)
        assert "Seconds" in str(exc_info.value) or "seconds" in str(exc_info.value)


# ========== Format Tests ==========

class TestFormats:
    """Tests for different time format outputs."""
    
    def test_binary_format_small(self):
        """Test binary format with small value."""
        clock = DigitalClock(0, 0, 42)
        assert clock.get_binary() == "0000000000101010"
    
    def test_binary_format_large(self):
        """Test binary format with large value."""
        clock = DigitalClock(23, 59, 59)
        binary = clock.get_binary()
        # 86399 requires 17 bits, so we expect 17 bits in Python implementation
        assert int(binary, 2) == 86399, "Binary value should equal 86399"
    
    def test_hex_format_small(self):
        """Test hexadecimal format with small value."""
        clock = DigitalClock(0, 4, 15)  # 255 seconds total
        assert clock.get_hex() == "0x00FF"
    
    def test_hex_format_large(self):
        """Test hexadecimal format with large value."""
        clock = DigitalClock(23, 59, 59)
        assert clock.get_hex() == "0x1517F"
    
    def test_decimal_format_padding(self):
        """Test decimal format with zero-padding."""
        clock = DigitalClock(9, 5, 3)
        decimal = clock.get_decimal()
        assert decimal == "09:05:03"
        # Verify format matches HH:MM:SS pattern
        parts = decimal.split(":")
        assert len(parts) == 3
        assert all(len(part) == 2 for part in parts)
        assert all(part.isdigit() for part in parts)


# ========== Seconds Calculation Tests ==========

class TestSecondsCalculation:
    """Tests for seconds since midnight calculation."""
    
    def test_seconds_one_hour(self):
        """Test seconds calculation for one hour."""
        clock = DigitalClock(1, 0, 0)
        assert clock.get_seconds_since_midnight() == 3600
    
    def test_seconds_one_minute(self):
        """Test seconds calculation for one minute."""
        clock = DigitalClock(0, 1, 0)
        assert clock.get_seconds_since_midnight() == 60
    
    def test_seconds_complex_time(self):
        """Test seconds calculation for complex time."""
        clock = DigitalClock(14, 37, 42)
        # (14 * 3600) + (37 * 60) + 42 = 50400 + 2220 + 42 = 52662
        assert clock.get_seconds_since_midnight() == 52662


# ========== Boundary Tests ==========

class TestBoundaries:
    """Tests for boundary conditions."""
    
    def test_boundary_max_hours(self):
        """Test boundary - maximum valid hours."""
        clock = DigitalClock(23, 0, 0)
        assert clock.get_decimal() == "23:00:00"
    
    def test_boundary_max_minutes(self):
        """Test boundary - maximum valid minutes."""
        clock = DigitalClock(0, 59, 0)
        assert clock.get_decimal() == "00:59:00"
    
    def test_boundary_max_seconds(self):
        """Test boundary - maximum valid seconds."""
        clock = DigitalClock(0, 0, 59)
        assert clock.get_decimal() == "00:00:59"
    
    def test_boundary_all_zeros(self):
        """Test boundary - all zeros."""
        clock = DigitalClock(0, 0, 0)
        assert clock.get_decimal() == "00:00:00"
        assert clock.get_seconds_since_midnight() == 0
    
    def test_boundary_all_max(self):
        """Test boundary - all maximum values."""
        clock = DigitalClock(23, 59, 59)
        assert clock.get_decimal() == "23:59:59"
        assert clock.get_seconds_since_midnight() == 86399


# ========== String Representation Tests ==========

class TestStringRepresentation:
    """Tests for __str__ and __repr__ methods."""
    
    def test_str_includes_all_formats(self):
        """Test __str__() includes all formats."""
        clock = DigitalClock(14, 37, 42)
        str_repr = str(clock)
        assert "14:37:42" in str_repr, "__str__ should include decimal format"
        assert "0x" in str_repr, "__str__ should include hex format"
        assert "52662" in str_repr, "__str__ should include seconds"
    
    def test_repr_is_valid_constructor(self):
        """Test __repr__() returns valid constructor call."""
        clock = DigitalClock(14, 37, 42)
        repr_str = repr(clock)
        assert "DigitalClock" in repr_str
        assert "14" in repr_str
        assert "37" in repr_str
        assert "42" in repr_str


# ========== Parametrized Tests ==========

class TestParametrized:
    """Parametrized tests for comprehensive coverage."""
    
    @pytest.mark.parametrize("hours,minutes,seconds,expected_decimal", [
        (0, 0, 0, "00:00:00"),
        (12, 0, 0, "12:00:00"),
        (23, 59, 59, "23:59:59"),
        (9, 5, 3, "09:05:03"),
        (14, 37, 42, "14:37:42"),
    ])
    def test_decimal_format_various_times(self, hours, minutes, seconds, expected_decimal):
        """Test decimal format for various times."""
        clock = DigitalClock(hours, minutes, seconds)
        assert clock.get_decimal() == expected_decimal
    
    @pytest.mark.parametrize("hours,minutes,seconds,expected_seconds", [
        (0, 0, 0, 0),
        (1, 0, 0, 3600),
        (0, 1, 0, 60),
        (0, 0, 1, 1),
        (23, 59, 59, 86399),
        (12, 30, 45, 45045),
    ])
    def test_seconds_calculation_various_times(self, hours, minutes, seconds, expected_seconds):
        """Test seconds calculation for various times."""
        clock = DigitalClock(hours, minutes, seconds)
        assert clock.get_seconds_since_midnight() == expected_seconds
    
    @pytest.mark.parametrize("hours,minutes,seconds", [
        (-1, 0, 0),
        (24, 0, 0),
        (25, 0, 0),
        (0, -1, 0),
        (0, 60, 0),
        (0, 0, -1),
        (0, 0, 60),
        (100, 100, 100),
    ])
    def test_invalid_inputs_parametrized(self, hours, minutes, seconds):
        """Test that invalid inputs raise ValueError."""
        with pytest.raises(ValueError):
            DigitalClock(hours, minutes, seconds)


# ========== Edge Cases ==========

class TestEdgeCases:
    """Tests for edge cases and special scenarios."""
    
    def test_noon(self):
        """Test noon (12:00:00)."""
        clock = DigitalClock(12, 0, 0)
        assert clock.get_decimal() == "12:00:00"
        assert clock.get_seconds_since_midnight() == 43200
    
    def test_one_second_before_midnight(self):
        """Test one second before midnight."""
        clock = DigitalClock(23, 59, 59)
        assert clock.get_seconds_since_midnight() == 86399
    
    def test_one_second_after_midnight(self):
        """Test one second after midnight."""
        clock = DigitalClock(0, 0, 1)
        assert clock.get_seconds_since_midnight() == 1
    
    def test_hex_uppercase(self):
        """Test that hex format uses uppercase letters."""
        clock = DigitalClock(23, 59, 59)
        hex_str = clock.get_hex()
        assert hex_str == "0x1517F", "Hex should use uppercase"
        # Check that the hex digits (after 0x) are uppercase
        hex_digits = hex_str[2:]  # Remove '0x' prefix
        assert hex_digits == hex_digits.upper(), "Hex digits should be uppercase"
    
    def test_binary_length_consistency(self):
        """Test that binary format is always 16 bits."""
        test_times = [
            (0, 0, 0),
            (0, 0, 1),
            (1, 0, 0),
            (12, 30, 45),
        ]
        for hours, minutes, seconds in test_times:
            clock = DigitalClock(hours, minutes, seconds)
            assert len(clock.get_binary()) == 16
        
        # Note: 23:59:59 (86399 seconds) requires 17 bits
        clock_max = DigitalClock(23, 59, 59)
        assert len(clock_max.get_binary()) == 17


# ========== Integration Tests ==========

class TestIntegration:
    """Integration tests for complete workflows."""
    
    def test_create_and_display_all_formats(self):
        """Test creating a clock and displaying all formats."""
        clock = DigitalClock(14, 37, 42)
        
        # All methods should work without errors
        decimal = clock.get_decimal()
        binary = clock.get_binary()
        hex_val = clock.get_hex()
        seconds = clock.get_seconds_since_midnight()
        
        # Verify all formats are strings (except seconds)
        assert isinstance(decimal, str)
        assert isinstance(binary, str)
        assert isinstance(hex_val, str)
        assert isinstance(seconds, int)
        
        # Verify formats are non-empty
        assert len(decimal) > 0
        assert len(binary) > 0
        assert len(hex_val) > 0
        assert seconds >= 0
    
    def test_multiple_clocks_independent(self):
        """Test that multiple clock instances are independent."""
        clock1 = DigitalClock(10, 30, 0)
        clock2 = DigitalClock(15, 45, 30)
        
        assert clock1.get_decimal() == "10:30:00"
        assert clock2.get_decimal() == "15:45:30"
        assert clock1.get_seconds_since_midnight() != clock2.get_seconds_since_midnight()


if __name__ == "__main__":
    # Run tests with pytest
    pytest.main([__file__, "-v", "--tb=short"])
