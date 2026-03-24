# 🧮 Scientific Calculator — Java

A fully functional scientific calculator built from scratch in Java, featuring both a **graphical user interface (GUI)** and a **console-based runner**. Developed independently as a personal project to apply and deepen object-oriented programming skills.

---

## Features

### Arithmetic
- Addition, subtraction, multiplication, division
- Support for chained operations with multiple operands
- Division-by-zero error handling

### Scientific Functions
- **Trigonometry:** sin, cos, tan, csc, sec, cot
- **Inverse trig:** arcsin, arccos, arctan
- **Exact trig answers** (e.g. returns `√2/2` instead of `0.7071...`)
- **Logarithms:** log base 10, natural log (ln), custom base log
- **Exponents & roots:** power (`^`), square root (`√`), nth root (`y√x`)
- **Factorials** and **combinatorics:** nPr (permutations), nCr (cQombinations)
- **GCD** (Greatest Common Divisor)
- Reciprocal (`1/x`), absolute value, `e^x`

### Algebra & Calculus
- Solve single-variable algebraic equations (e.g. `2x + 4 = 10`)
- Compute **polynomial derivatives** by degree and coefficients
- Symbolic derivative parsing from expressions
- Generate a **table of values** for expressions over a range

### Modes & Memory
- Toggle between **Degree and Radian** mode for trig calculations
- Toggle between **exact and decimal** answer formats
- **6 memory slots** (mapped to keys: q, w, e, r, t, y) — store and recall values
- Previous answer (`Ans`) recall

### Expression Evaluator
- Parses and evaluates full mathematical expressions as strings
- Handles operator precedence and parentheses

---

## Project Structure

| File | Description |
|---|---|
| `Calculator.java` | Base class — arithmetic operations, memory, and answer history |
| `SciCalculator.java` | Extends `Calculator` — all scientific, algebraic, and calculus functions |
| `CalculatorGUI.java` | Java Swing GUI — button layout, input/output display, advanced panels |
| `CalculatorRunner.java` | Console-based interface for interacting with the calculator via terminal |
| `FunctionalTests.java` | Unit tests for core calculator functions |
| `GUITest.java` | Basic GUI interaction tests |
| `GUITestAll.java` | Comprehensive GUI test suite |
| `TestEval.java` | Tests for the expression evaluator |

---

## OOP Concepts Used

- **Inheritance** — `SciCalculator` extends `Calculator`, building on core arithmetic with advanced functionality
- **Polymorphism** — Method overloading (e.g. `SaveMemory(double)` and `SaveMemory(int)`, `sqroot(double)` and `sqroot(int)`)
- **Encapsulation** — Private fields with controlled access through public methods
- **Abstraction** — Clean separation between logic (`SciCalculator`) and interface (`CalculatorGUI`, `CalculatorRunner`)

---

## How to Run

### Requirements
- Java JDK 8 or higher
- No external libraries required

### Run the GUI
```bash
javac CalculatorGUI.java
java CalculatorGUI
```

### Run the Console Version
```bash
javac CalculatorRunner.java
java CalculatorRunner
```

### Run Tests
```bash
javac FunctionalTests.java
java FunctionalTests
```

---

## GUI Preview

The GUI is built with **Java Swing** and features:
- A 7×6 button grid with all standard and scientific operations
- An input field and scrollable output area
- A right-side panel for advanced features: algebra solver, derivative calculator, and table of values
- Degree/Radian toggle and memory store/recall buttons

---

## Author

**Japheth Roxon**  
First-year Software Engineering student at Carleton University  
📧 jzroxon@gmail.com
