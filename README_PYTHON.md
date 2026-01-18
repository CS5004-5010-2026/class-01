# Digital Clock - Python Implementation

Python reference implementation of a digital clock that displays time in multiple formats.

## Features

- Display time in standard decimal format (HH:MM:SS)
- Convert time to 16-bit binary representation
- Convert time to hexadecimal format
- Input validation for hours, minutes, and seconds
- Comprehensive test suite with pytest
- Type hints and docstrings
- Code coverage reporting

## Project Structure

```
.
├── DigitalClock.py           # Main implementation
├── test_digital_clock.py     # Pytest test suite
├── requirements.txt          # Python dependencies
├── pytest.ini               # Pytest configuration
└── README_PYTHON.md         # This file
```

## Requirements

- Python 3.8 or higher
- pip (Python package installer)

## Setup

### 1. Create a virtual environment (recommended)

```bash
# Create virtual environment
python3 -m venv venv

# Activate virtual environment
# On macOS/Linux:
source venv/bin/activate
# On Windows:
venv\Scripts\activate
```

### 2. Install dependencies

```bash
pip install -r requirements.txt
```

## Running the Code

### Run the demo

```bash
python DigitalClock.py
```

This will:
- Show example clock instances
- Demonstrate error handling
- Run basic tests

### Interactive mode

```bash
python -i DigitalClock.py
```

Then try:
```python
>>> clock = DigitalClock(14, 37, 42)
>>> clock.get_decimal()
'14:37:42'
>>> clock.get_hex()
'0xCE6A'
>>> print(clock)
```

## Running Tests

### Run all tests

```bash
pytest
```

### Run with verbose output

```bash
pytest -v
```

### Run with coverage report

```bash
pytest --cov=DigitalClock --cov-report=html
```

Then open `htmlcov/index.html` in your browser to see detailed coverage.

### Run specific test class

```bash
pytest test_digital_clock.py::TestConstruction -v
```

### Run specific test

```bash
pytest test_digital_clock.py::TestConstruction::test_midnight -v
```

### Run tests matching a pattern

```bash
pytest -k "boundary" -v
```

## Code Quality

### Run type checking with mypy

```bash
mypy DigitalClock.py
```

### Run linting with pylint

```bash
pylint DigitalClock.py
```

### Format code with black

```bash
black DigitalClock.py test_digital_clock.py
```

## Test Coverage

The test suite includes:

- **Constructor tests**: Valid and invalid inputs
- **Format tests**: Decimal, binary, and hexadecimal outputs
- **Boundary tests**: Edge cases (midnight, end of day, etc.)
- **Calculation tests**: Seconds since midnight conversion
- **Parametrized tests**: Multiple inputs tested systematically
- **Integration tests**: Complete workflows

Current coverage: ~100% of DigitalClock.py

## Common Commands

```bash
# Install dependencies
pip install -r requirements.txt

# Run tests
pytest

# Run tests with coverage
pytest --cov=DigitalClock

# Run specific test class
pytest test_digital_clock.py::TestConstruction

# Run tests in watch mode (requires pytest-watch)
ptw

# Format code
black *.py

# Type check
mypy DigitalClock.py

# Lint code
pylint DigitalClock.py
```

## Example Usage

```python
from DigitalClock import DigitalClock

# Create a clock
clock = DigitalClock(14, 37, 42)

# Get different formats
print(clock.get_decimal())  # "14:37:42"
print(clock.get_binary())   # "0000110011101010"
print(clock.get_hex())      # "0xCE6A"
print(clock.get_seconds_since_midnight())  # 52662

# Display all formats
print(clock)

# Error handling
try:
    invalid = DigitalClock(25, 0, 0)
except ValueError as e:
    print(f"Error: {e}")
```

## Implementation Notes

The `DigitalClock` class stores time internally as seconds since midnight (0-86399), which:
- Simplifies arithmetic operations
- Maintains a single source of truth
- Makes validation straightforward
- Ensures class invariants are preserved

All display methods derive their values from this internal representation.

## Troubleshooting

### Import errors
Make sure you're in the correct directory and have activated your virtual environment.

### Test failures
Ensure you have the latest dependencies:
```bash
pip install --upgrade -r requirements.txt
```

### Coverage not showing
Install pytest-cov:
```bash
pip install pytest-cov
```

## License

This project is provided for educational purposes.
