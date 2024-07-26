# Unit Test Kotlin Multiplatform Compose
Exmaple of Unit Test - Kotlin Multiplatform Compose

UI Unit test and Kotlin unit test

## Unit test setup guide for Kotlin Multiplatform Compose
![Kotlin Multiplatform Compose Unit test](https://raw.githubusercontent.com/TouhidApps/Unit-Test-Kotlin-Multiplatform-Compose/main/img/setup1.png)
![Kotlin Multiplatform Compose Unit test](https://raw.githubusercontent.com/TouhidApps/Unit-Test-Kotlin-Multiplatform-Compose/main/img/setup2.png)
![Kotlin Multiplatform Compose Unit test](https://raw.githubusercontent.com/TouhidApps/Unit-Test-Kotlin-Multiplatform-Compose/main/img/setup3.png)
![Kotlin Multiplatform Compose Unit test](https://raw.githubusercontent.com/TouhidApps/Unit-Test-Kotlin-Multiplatform-Compose/main/img/setup4.png)
![Kotlin Multiplatform Compose Unit test](https://raw.githubusercontent.com/TouhidApps/Unit-Test-Kotlin-Multiplatform-Compose/main/img/setup5.png)

## To set and see test coverage:

Add below plugin:

```
id("org.jetbrains.kotlinx.kover") version "0.8.0"
```

Set below rule:
If test meets the cover rule test will pass otherwise failed:
Ex:

```
kover {
    reports {
        verify {
            rule {
                /**
                 * If 10% code is executed in test it should pass, otherwise failed
                 * You can set it 80 if you can write that much unit test code
                 */
                minBound(10)
            }
        }
        filters {  // To get clean report we should filter unwanted packages/classes
            excludes {
                // Entry points
                classes("MainKt") // Desktop
                classes("*.MainActivity") // Android

                // Generated classes and resources
                packages("*.generated.*")
                packages("*.di*") // Dependency injection package

                // Compose related(If you don't do UI testing)
                classes("*ComposableSingletons*")
                annotatedBy("androidx.compose.runtime.Composable")
                annotatedBy("androidx.compose.ui.tooling.preview.Preview")

                classes("CounterKtTest") // For this project this is for UI testing
            }
        }
    }
}
```

Run kover from terminal:

```
./gradlew koverVerify -> Run all test and see error/exception in html page
./gradlew koverHtmlReport -> Get test report in html page to see how much code are covered by test
```

If failed it will show an html link in terminal about code coverage info.


## AAA Pattern: Arrange, Act, Assert

Test Class: CalculatorTest

Function Class: MyCalculator

![Kotlin Multiplatform Compose kover](https://raw.githubusercontent.com/TouhidApps/Unit-Test-Kotlin-Multiplatform-Compose/main/img/setup6.png)