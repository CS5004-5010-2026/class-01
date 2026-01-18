# Digital Clock

A Java implementation of a digital clock that displays time in multiple formats: decimal (HH:MM:SS), binary, and hexadecimal.

## Features

- Display time in standard decimal format (HH:MM:SS)
- Convert time to 16-bit binary representation
- Convert time to hexadecimal format
- Input validation for hours, minutes, and seconds
- Comprehensive test suite with JUnit 5

## Project Structure

```
digital-clock/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── clock/
│   │           └── DigitalClock.java
│   └── test/
│       └── java/
│           └── clock/
│               └── DigitalClockTest.java
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

## Requirements

- Java 11 or higher
- Gradle 7.0 or higher (or use the Gradle wrapper)

## Building the Project

### Using Gradle Wrapper (Recommended)

```bash
# On Unix/macOS
./gradlew build

# On Windows
gradlew.bat build
```

### Using Local Gradle Installation

```bash
gradle build
```

## Running Tests

```bash
# Run all tests
./gradlew test

# Run tests with detailed output
./gradlew test --info

# Run tests continuously (watch mode)
./gradlew test --continuous
```

## Compiling

```bash
./gradlew compileJava
```

## Cleaning Build Artifacts

```bash
./gradlew clean
```

## Common Gradle Tasks

- `./gradlew build` - Compile, test, and package the project
- `./gradlew test` - Run the test suite
- `./gradlew clean` - Remove build artifacts
- `./gradlew tasks` - List all available tasks
- `./gradlew dependencies` - Show project dependencies

## Implementation Notes

The `DigitalClock` class stores time internally as seconds since midnight (0-86399), which simplifies arithmetic operations and maintains a single source of truth. All display methods derive their values from this internal representation.

### Class Invariants

- `secondsSinceMidnight` is always in range [0, 86399]
- This represents a valid time within a 24-hour day

## License

This project is provided for educational purposes.
