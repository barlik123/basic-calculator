# basic-calculator
## Purpose:
An application to run basic calculations.

## What does it include?
1. 10 number Buttons 0-9.
2. 4 arithmetic operations (division, multiplication, addition, and substraction)
3. sign convertion (between negative and positive numbers)
4. ability to write multi-digit numbers and fractions (with dot)
5. ability to output calculation result, stack calculations, and clear calculations.

## How does it work?
### Basic operation cell:
The user starts with writing a left operand with digits +/- and dot to constract a number.
Then the user can choose an arithmetic operation to perform. 
Finally the user writes the right operand number similarly to the left operand, and clicks on the '=' button to output the result.

### Series of operation cells:
The user can then continue adding operations to the calculations with the output of the previous operation cell as the left operand of the current operation cell.
That way the user can perform as long of a calculation sequence as he wish.
When the user is done with the currect calculation he can click on the 'C' Button to clear the calculation and start a new one.

## Necessary libraries:
To run the gui, JavaFX is needed. You can download the library from this link:
https://openjfx.io/openjfx-docs/
