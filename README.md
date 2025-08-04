# GoogleSearchUITestAutomation
Mini-Project : Test Automation for Google Search Page using Selenium, TestNG, and Maven with Excel-based Test Data via Apache POI.


This mini-project automates UI testing of the Google Search homepage using **Selenium**, **TestNG**, and **Maven**, with test steps defined in an **Excel file** using **Apache POI**.

---
## Table of Contents
- [Project Highlights](#project-highlights)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [How to Run the Project](#how-to-run-the-project)

##  Project Highlights

- Validates UI elements on Google's homepage.
- Uses Excel for test steps — no hardcoding!
- Takes screenshots automatically.
- Generates detailed test reports via Maven.

---

## Tech Stack

| Tool         | Purpose                      |
|--------------|-------------------------------|
| Java         | Programming language          |
| Selenium     | Browser automation            |
| TestNG       | Test execution & assertions   |
| Apache POI   | Read/write Excel test data    |
| Maven        | Build & dependency management |
| ChromeDriver | WebDriver for Google Chrome   |

---

## Project Structure

```plaintext
GoogleSearchUITestAutomation/
│
├── src/
│ ├── main/java/
│ │ ├── ExcelReader.java # Reads test steps from Excel
│ │ └── ScreenshotUtil.java # Captures screenshots
│ └── test/java/
│     └── GoogleUIValidationTest.java # Main test class
├── pom.xml # Maven config
├── testng.xml # TestNG config
└── README.md # Project description

## How to Run the Project

1. **Clone the repo**
   ```bash
   git clone https://github.com/spoorthimaheshbhat/GoogleSearchUITestAutomation.git

2.  **Run the test**
   cmd: mvn clean test site

3. **View reports**

   view folder - target/site/index.html

4. Screenshots

Screenshots are saved during test execution with timestamps.

Check your local folder (e.g., /screenshots/) for results.


