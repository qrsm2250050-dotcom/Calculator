## 2-Way Fraction & Mixed Number Calculator

This Java-based desktop application is a specialized calculator designed to handle arithmetic operations using **fractions**, **mixed numbers**, and **decimals**. It features a graphical user interface (GUI) that renders mathematical notation using HTML for better readability.

The program is built on a custom mathematical engine consisting of a base `Fraction` class and a derived `MixedNumber` class to manage improper-to-mixed conversions automatically.

---

### Core Features

* **Multi-Format Input:** Supports the entry of whole numbers, simple fractions (e.g., `3/4`), mixed numbers (e.g., `1_1/2`), and decimal values (e.g., `0.75`).
* **Dynamic Conversion ($a \frac{b}{c} \iff \frac{d}{c}$):** A dedicated toggle button allows users to switch the current result between a mixed number format and an improper fraction format instantly.
* **Automatic Simplification:** All fractional results are automatically reduced to their lowest terms using Greatest Common Divisor (GCD) logic.
* **Decimal Synchronization:** Displays a real-time decimal equivalent (`DEC`) of the current fractional result for quick reference.
* **Mathematical Expression History:** A secondary display label tracks the current operation sequence (e.g., `1/2 + 3/4 =`) to provide context to the user.
* **Formatted GUI Rendering:** Uses HTML-based rendering to display fractions with vertical vinculums (division bars), mimicking textbook notation.
* **Error Handling:** Includes custom exception handling and visual "Math Error" or "Syntax Error" feedback for illegal operations like division by zero.

---

### System Architecture

The project is organized into the following components:

| Component | Responsibility |
| :--- | :--- |
| `Fraction.java` | Core logic for addition, subtraction, multiplication, division, and simplification. |
| `MixedNumber.java` | Handles the conversion between improper fractions and the `Whole_Numerator/Denominator` format. |
| `Calculator.java` | The Swing-based GUI that manages user input, button events, and display rendering. |
| `InvalidMixedNumberException.java` | A custom exception class for handling malformed numerical inputs. |

---

### Limitations

* **Single-Operation Sequence:** While it supports "chaining" (using the result of one calculation as the start of the next), it does not support complex expressions with parentheses or Order of Operations (PEMDAS) within a single input string.
* **Integer Overflow:** The calculator uses standard 32-bit `int` types for numerators and denominators, as required by the project guidelines. Extremely large products or denominators may result in integer overflow.
* **Decimal-to-Fraction Precision:** The `parseToFraction` logic converts decimals based on the number of trailing digits (e.g., `0.33` becomes `33/100`). It does not currently detect or convert repeating decimals (like $0.33\dots$ to $1/3$).
* **Fixed Window Size:** The GUI is set to a fixed size of $650 \times 600$ pixels and is not fully responsive to aggressive resizing.

GitHub repository link: https://github.com/qrsm2250050-dotcom/Calculator
