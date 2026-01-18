# Python Testing Commands Reference

## Quick Start

```bash
# Install dependencies
pip install -r requirements.txt

# Run all tests
pytest

# Run with coverage
pytest --cov=DigitalClock
```

## Test Execution

### Basic Testing

```bash
# Run all tests
pytest

# Run with verbose output
pytest -v

# Run with very verbose output
pytest -vv

# Run specific test file
pytest test_digital_clock.py

# Run specific test class
pytest test_digital_clock.py::TestConstruction

# Run specific test method
pytest test_digital_clock.py::TestConstruction::test_midnight

# Run tests matching a pattern
pytest -k "boundary"
pytest -k "invalid"
pytest -k "format"
```

### Test Output Control

```bash
# Show print statements
pytest -s

# Show local variables on failure
pytest -l

# Stop on first failure
pytest -x

# Stop after N failures
pytest --maxfail=3

# Show test durations
pytest --durations=10
```

### Coverage Reports

```bash
# Basic coverage
pytest --cov=DigitalClock

# Coverage with missing lines
pytest --cov=DigitalClock --cov-report=term-missing

# HTML coverage report
pytest --cov=DigitalClock --cov-report=html
# Then open: htmlcov/index.html

# XML coverage report (for CI)
pytest --cov=DigitalClock --cov-report=xml

# Multiple report formats
pytest --cov=DigitalClock --cov-report=term-missing --cov-report=html
```

### Test Selection

```bash
# Run only parametrized tests
pytest test_digital_clock.py::TestParametrized

# Run tests by marker (if defined)
pytest -m slow
pytest -m "not slow"

# Run failed tests from last run
pytest --lf

# Run failed tests first, then others
pytest --ff
```

## Using Makefile

If you have `make` installed:

```bash
# Run tests
make test

# Run with coverage
make coverage

# Run all quality checks
make quality

# Format code
make format

# Lint code
make lint

# Type check
make type-check

# Clean generated files
make clean
```

## Test Organization

The test suite is organized into classes:

- **TestConstruction**: Valid clock construction tests
- **TestInvalidInputs**: Input validation tests
- **TestFormats**: Output format tests (decimal, binary, hex)
- **TestSecondsCalculation**: Time calculation tests
- **TestBoundaries**: Boundary condition tests
- **TestStringRepresentation**: `__str__` and `__repr__` tests
- **TestParametrized**: Parametrized tests with multiple inputs
- **TestEdgeCases**: Edge case scenarios
- **TestIntegration**: Integration and workflow tests

## Test Statistics

Current test suite:
- **51 total tests**
- **100% pass rate**
- **~100% code coverage** of DigitalClock.py

Test breakdown:
- 4 construction tests
- 6 invalid input tests
- 5 format tests
- 3 seconds calculation tests
- 5 boundary tests
- 2 string representation tests
- 19 parametrized tests
- 5 edge case tests
- 2 integration tests

## Continuous Testing

### Watch Mode (requires pytest-watch)

```bash
# Install pytest-watch
pip install pytest-watch

# Run tests on file changes
ptw

# Run with coverage
ptw -- --cov=DigitalClock
```

### Manual Watch Loop

```bash
# Simple watch loop (Unix/macOS)
while true; do clear; pytest; sleep 2; done
```

## Debugging Tests

```bash
# Run with Python debugger
pytest --pdb

# Drop into debugger on failure
pytest --pdb -x

# Show full traceback
pytest --tb=long

# Show short traceback
pytest --tb=short

# Show only one line per failure
pytest --tb=line

# No traceback
pytest --tb=no
```

## Performance Testing

```bash
# Show slowest tests
pytest --durations=10

# Show all test durations
pytest --durations=0

# Profile tests (requires pytest-profiling)
pytest --profile
```

## Parallel Testing

```bash
# Install pytest-xdist
pip install pytest-xdist

# Run tests in parallel (auto-detect CPUs)
pytest -n auto

# Run tests on 4 CPUs
pytest -n 4
```

## Output Formats

```bash
# JUnit XML (for CI systems)
pytest --junit-xml=report.xml

# JSON report (requires pytest-json-report)
pytest --json-report

# HTML report (requires pytest-html)
pytest --html=report.html
```

## Configuration

Tests are configured in `pytest.ini`:
- Test discovery patterns
- Default options
- Coverage settings
- Markers

## Common Workflows

### Development Workflow
```bash
# 1. Make changes to DigitalClock.py
# 2. Run tests
pytest -v

# 3. Check coverage
pytest --cov=DigitalClock --cov-report=term-missing

# 4. If tests pass, commit
```

### Test-Driven Development
```bash
# 1. Write a failing test
# 2. Run the specific test
pytest test_digital_clock.py::TestConstruction::test_new_feature -v

# 3. Implement the feature
# 4. Run test again until it passes
# 5. Run full suite
pytest
```

### Pre-Commit Checks
```bash
# Run all quality checks
pytest && pylint DigitalClock.py && mypy DigitalClock.py
```

## Troubleshooting

### Tests not found
```bash
# Check test discovery
pytest --collect-only
```

### Import errors
```bash
# Ensure you're in the correct directory
pwd

# Check Python path
python -c "import sys; print(sys.path)"
```

### Coverage not working
```bash
# Reinstall pytest-cov
pip install --upgrade pytest-cov
```

### Slow tests
```bash
# Identify slow tests
pytest --durations=10
```

## CI/CD Integration

### GitHub Actions Example
```yaml
- name: Run tests
  run: |
    pip install -r requirements.txt
    pytest --cov=DigitalClock --cov-report=xml
```

### GitLab CI Example
```yaml
test:
  script:
    - pip install -r requirements.txt
    - pytest --cov=DigitalClock --cov-report=term
```

## Tips

1. **Use `-v` for better output**: Always run with `-v` to see test names
2. **Use `--cov-report=term-missing`**: See which lines aren't covered
3. **Use `-x` during development**: Stop on first failure to fix issues quickly
4. **Use `-k` for focused testing**: Test specific functionality while developing
5. **Check coverage regularly**: Aim for >90% coverage
6. **Use parametrized tests**: Test multiple inputs efficiently
7. **Keep tests fast**: Fast tests = more frequent testing
8. **Use descriptive test names**: Makes failures easier to understand
