# Gradle Commands Reference

## Essential Commands

### Build & Compile
```bash
./gradlew build          # Full build (compile + test + package)
./gradlew compileJava    # Compile main source code only
./gradlew compileTestJava # Compile test source code only
./gradlew clean          # Remove all build artifacts
./gradlew clean build    # Clean then build from scratch
```

### Testing
```bash
./gradlew test           # Run all tests
./gradlew test --info    # Run tests with detailed output
./gradlew test --debug   # Run tests with debug output
./gradlew test --rerun-tasks # Force re-run even if up-to-date
./gradlew test --tests DigitalClockTest # Run specific test class
./gradlew test --tests "*testMidnight*" # Run tests matching pattern
```

### Project Information
```bash
./gradlew tasks          # List all available tasks
./gradlew tasks --all    # List all tasks including dependencies
./gradlew dependencies   # Show dependency tree
./gradlew properties     # Show project properties
./gradlew projects       # List all projects in build
```

### Build Analysis
```bash
./gradlew build --scan   # Generate build scan (online analysis)
./gradlew build --profile # Generate local performance profile
```

### Continuous Build
```bash
./gradlew test --continuous # Auto-run tests on file changes
./gradlew build --continuous # Auto-build on file changes
```

### Cleaning
```bash
./gradlew clean          # Remove build directory
./gradlew cleanTest      # Remove test results (forces re-run)
```

## Gradle Wrapper

The Gradle wrapper (`gradlew`) ensures everyone uses the same Gradle version:

- **Unix/macOS**: `./gradlew <task>`
- **Windows**: `gradlew.bat <task>`

### Wrapper Management
```bash
gradle wrapper --gradle-version 8.5  # Update wrapper version
./gradlew wrapper --gradle-version 8.5 # Update using existing wrapper
```

## Common Workflows

### Development Workflow
```bash
# 1. Make code changes
# 2. Run tests
./gradlew test

# 3. If tests pass, build
./gradlew build
```

### Test-Driven Development
```bash
# Keep tests running in watch mode
./gradlew test --continuous
```

### Clean Build (when things go wrong)
```bash
./gradlew clean build --rerun-tasks
```

### Check Code Compiles Without Running Tests
```bash
./gradlew compileJava compileTestJava
```

## Build Output Locations

- **Compiled classes**: `build/classes/java/main/`
- **Test classes**: `build/classes/java/test/`
- **Test reports**: `build/reports/tests/test/index.html`
- **JAR file**: `build/libs/digital-clock-1.0.0.jar`

## Tips

1. **Use the wrapper**: Always use `./gradlew` instead of `gradle` to ensure version consistency
2. **Parallel builds**: Already enabled in `gradle.properties` for faster builds
3. **Build cache**: Already enabled in `gradle.properties` for faster rebuilds
4. **Daemon**: Already enabled in `gradle.properties` for faster startup
5. **View test reports**: Open `build/reports/tests/test/index.html` in a browser after running tests

## Troubleshooting

### Gradle daemon issues
```bash
./gradlew --stop         # Stop all Gradle daemons
```

### Permission issues (Unix/macOS)
```bash
chmod +x gradlew         # Make wrapper executable
```

### Force refresh dependencies
```bash
./gradlew build --refresh-dependencies
```

### Clear Gradle cache
```bash
rm -rf ~/.gradle/caches/
```
