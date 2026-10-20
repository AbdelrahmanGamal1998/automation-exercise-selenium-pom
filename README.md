# Automation Exercise: Selenium + TestNG (Page Object Model)

[![Build](https://github.com/AbdelrahmanGamal1998/automation-exercise-selenium-pom/actions/workflows/build.yml/badge.svg)](https://github.com/AbdelrahmanGamal1998/automation-exercise-selenium-pom/actions/workflows/build.yml)

A small web UI test framework in Java for the practice site [automationexercise.com](https://automationexercise.com/).
It automates the full sign-up flow (open the site, go to Signup / Login, create an account, check the "ACCOUNT
CREATED!" message and its green color) and shows a clean Page Object structure.

## Structure

```
src/main/java/framework/
  driver/DriverFactory.java     creates Chrome, Firefox or Edge (headless option)
  pages/                        page objects: HomePage, SignupLoginPage, AccountInformationPage
  utils/                        PropertiesReader (settings), JsonFileManager (test data), ElementActions (waits)
src/test/java/tests/SignupTest.java
src/test/resources/             configurations.properties (browser settings), testData.json (expected texts)
```

## Run it

You need JDK 17 or newer, Maven and a browser (Selenium 4 downloads the driver itself).

```bash
git clone https://github.com/AbdelrahmanGamal1998/automation-exercise-selenium-pom.git
cd automation-exercise-selenium-pom
mvn test                                              # headless Chrome
mvn test -DheadlessExecution=false                    # watch the browser
mvn test -DbrowserType=firefox                        # chrome, firefox or edge
```

Settings are in `src/test/resources/configurations.properties`. An option given with `-D` wins over the file.

## Notes

- GitHub Actions runs the test in headless Chrome on every push.
- The site accepts an e-mail address only once, so the test creates a new address on every run.
- Changes made when it was cleaned up: packages and class names follow Java conventions; the original had three
  copies of the same test at different stages of refactoring, and only the final page-object version is kept;
  fragile absolute XPaths were replaced with CSS selectors; settings are read from the classpath (the old code
  needed a library that was not declared); the `browserType == "edge"` string comparison and the Safari fallback
  were fixed; unused dependencies were removed.

## License

[MIT](LICENSE)