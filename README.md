# MakeMyTrip Automation Testing Framework

## Project Overview
This is a comprehensive automation testing framework for the MakeMyTrip website, focusing on train booking functionality. The framework is built using **Selenium WebDriver**, **Java**, **TestNG**, **Cucumber/BDD**, **Maven**, and the **Page Object Model (POM)** pattern.

## Tech Stack
- **Language**: Java 11+
- **Test Framework**: TestNG
- **BDD Framework**: Cucumber
- **Web Automation**: Selenium WebDriver 4.15.0
- **Build Tool**: Maven
- **Browser Driver Management**: WebDriver Manager
- **Logging**: Apache Log4j2
- **Design Pattern**: Page Object Model (POM)

## Project Structure

```
makemytrip-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/makemytrip/
│   │           ├── pages/              # Page Object Classes
│   │           │   ├── BasePage.java
│   │           │   ├── HomePage.java
│   │           │   └── TrainResultsPage.java
│   │           └── utils/              # Utility Classes
│   │               ├── DriverManager.java
│   │               ├── ConfigReader.java
│   │               └── TestUtils.java
│   └── test/
│       ├── java/
│       │   └── com/makemytrip/
│       │       ├── stepdefs/           # Step Definitions
│       │       │   └── TrainBookingStepDefinitions.java
│       │       └── runners/            # Test Runners
│       │           └── TestRunner.java
│       └── resources/
│           ├── features/               # Gherkin Feature Files
│           │   └── TrainBooking.feature
│           ├── config.properties       # Configuration File
│           ├── log4j2.xml             # Logging Configuration
│           └── testng.xml             # TestNG Configuration
├── pom.xml                            # Maven POM File
└── README.md                          # Project Documentation
```

## Key Features

### 1. **Page Object Model (POM)**
- Encapsulation of page elements and actions
- **BasePage**: Contains common methods for all pages
- **HomePage**: Handles train search form interactions
- **TrainResultsPage**: Handles train results and seat availability

### 2. **BDD with Cucumber**
- Gherkin syntax for readable test scenarios
- Feature files in `src/test/resources/features/`
- Step definitions map Gherkin steps to Java code

### 3. **Test Scenarios**
The framework includes scenarios for:
- Navigating to MakeMyTrip website
- Selecting trains from source to destination
- Searching for trains from Ballia to Sealdah
- Verifying train availability
- Checking seat availability
- Validating error messages if no trains available

### 4. **Utility Classes**
- **DriverManager**: Manages WebDriver initialization and cleanup (Chrome, Firefox, Edge)
- **ConfigReader**: Reads configuration from properties file
- **TestUtils**: Common utility methods for testing

### 5. **Logging**
- Log4j2 for comprehensive logging
- Console and file appenders
- Rolling file appenders for log management

## Prerequisites

### System Requirements
- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser

### Installation

1. **Install Java**
   ```bash
   # Verify Java installation
   java -version
   ```

2. **Install Maven**
   ```bash
   # Verify Maven installation
   mvn -version
   ```

3. **Clone/Create Project**
   ```bash
   # Navigate to your workspace directory
   # The project is already structured in c:\MakeMyTrip_QA
   ```

## Configuration

### config.properties
Edit `src/test/resources/config.properties` to customize:

```properties
# Browser to use (chrome, firefox, edge)
browser=chrome

# Wait times
implicitWait=10
explicitWait=20

# Application URL
url=https://www.makemytrip.com/

# Test Data
source.station=Ballia
destination.station=Sealdah

# Logging level
log.level=INFO
```

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Feature File
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

### Run with TestNG
```bash
mvn clean test -Dsuite=testng.xml
```

### Run Tests in Parallel
Modify pom.xml to enable parallel execution in Maven Surefire plugin.

## Test Scenarios

### Scenario 1: Search for Trains and Verify Availability
```gherkin
Scenario: Search for trains from Ballia to Sealdah and verify seat availability
  Given I navigate to MakeMyTrip website
  When I click on the Trains tab
  And I enter "Ballia" as the source station
  And I enter "Sealdah" as the destination station
  And I select the journey date as tomorrow
  And I click on the Search button
  Then I should see a list of available trains
  And If trains are available then I should mark the test as "good"
  And If no trains are available then I should throw an error message
```

## Page Objects

### HomePage
- `navigateToMakeMyTrip(url)` - Navigate to website
- `clickOnTrainsTab()` - Click trains tab
- `enterSourceStation(station)` - Enter source station
- `enterDestinationStation(station)` - Enter destination station
- `enterDepartureDate(date)` - Enter departure date
- `clickSearchButton()` - Click search button

### TrainResultsPage
- `areTrainsAvailable()` - Check if trains are available
- `getNumberOfAvailableTrains()` - Get count of available trains
- `getFirstTrainSeatAvailability()` - Get seat info of first train
- `getTrainDetails(index)` - Get details of specific train
- `isSeatAvailable()` - Check if seats are available
- `getErrorMessage()` - Get error message if no trains

## Logging

Logs are generated in `target/logs/` directory:
- **Console**: Logs displayed in console during execution
- **File**: `automation.log` - Main log file
- **Rolling**: Logs rotate based on date and size

### Log Levels
- INFO: General information messages
- DEBUG: Detailed debugging information
- WARN: Warning messages
- ERROR: Error messages

## Reports

### Cucumber Reports
Generated in `target/cucumber-reports/`:
- `cucumber.html` - HTML report
- `cucumber.json` - JSON report for CI/CD integration
- `cucumber.xml` - JUnit XML report

### Running Report Generation
```bash
mvn clean test
# Reports are automatically generated
```

## Common Issues and Solutions

### Issue: WebDriver not found
**Solution**: WebDriver Manager automatically downloads drivers. Ensure internet connectivity.

### Issue: Element not found
**Solution**: Check selectors in page objects and adjust wait times in config.properties

### Issue: Tests timeout
**Solution**: Increase `implicitWait` and `explicitWait` values in config.properties

## Best Practices Implemented

1. **Page Object Model**: Each page has its own class with locators and methods
2. **Separation of Concerns**: Step definitions, page objects, and utilities are separate
3. **Configuration Management**: All configurations in properties file
4. **Logging**: Comprehensive logging for debugging
5. **Reusability**: Common methods in BasePage class
6. **BDD Approach**: Readable test scenarios using Gherkin syntax
7. **Error Handling**: Try-catch blocks with proper logging

## Extending the Framework

### Adding New Page Objects
1. Create new class extending `BasePage`
2. Add page locators as `@FindBy` annotations
3. Implement page-specific methods

### Adding New Test Scenarios
1. Create new feature file in `src/test/resources/features/`
2. Write Gherkin scenarios
3. Implement step definitions in appropriate step definition class

### Adding New Utilities
1. Create new class in `src/main/java/com/makemytrip/utils/`
2. Add utility methods
3. Use in step definitions or page objects

## Continuous Integration

### GitHub Actions Example
```yaml
# .github/workflows/tests.yml
name: Automation Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
```

## Troubleshooting

### WebDriver Issues
- Clear browser cache
- Update WebDriver Manager: `mvn dependency:resolve`
- Check browser version compatibility

### Flaky Tests
- Increase wait times in config.properties
- Add explicit waits for specific elements
- Check internet connectivity

## Contact & Support

For issues or questions, please refer to the code comments and logging output for detailed error information.

## License

This project is for testing purposes only.

## Version History

- **v1.0.0** (Feb 2026): Initial release with Ballia to Sealdah train search automation
