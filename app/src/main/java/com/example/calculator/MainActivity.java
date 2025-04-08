package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        tvInput = findViewById(R.id.tvInput);

        Button btn0 = findViewById(R.id.khong);
        Button btn1 = findViewById(R.id.mot);
        Button btn2 = findViewById(R.id.hai);
        Button btn3 = findViewById(R.id.ba);
        Button btn4 = findViewById(R.id.bon);
        Button btn5 = findViewById(R.id.nam);
        Button btn6 = findViewById(R.id.sau);
        Button btn7 = findViewById(R.id.bay);
        Button btn8 = findViewById(R.id.tam);
        Button btn9 = findViewById(R.id.chin);

        btn0.setOnClickListener(v -> appendNumber("0"));
        btn1.setOnClickListener(v -> appendNumber("1"));
        btn2.setOnClickListener(v -> appendNumber("2"));
        btn3.setOnClickListener(v -> appendNumber("3"));
        btn4.setOnClickListener(v -> appendNumber("4"));
        btn5.setOnClickListener(v -> appendNumber("5"));
        btn6.setOnClickListener(v -> appendNumber("6"));
        btn7.setOnClickListener(v -> appendNumber("7"));
        btn8.setOnClickListener(v -> appendNumber("8"));
        btn9.setOnClickListener(v -> appendNumber("9"));

        Button btnC = findViewById(R.id.C);
        Button btnDel = findViewById(R.id.del);
        Button btnPercent = findViewById(R.id.phamtram);
        Button btnDivide = findViewById(R.id.chia);
        Button btnMultiply = findViewById(R.id.nhan);
        Button btnSubtract = findViewById(R.id.tru);
        Button btnAdd = findViewById(R.id.cong);
        Button btnEqual = findViewById(R.id.bang);
        Button btnAm = findViewById(R.id.am);
        Button btnDot = findViewById(R.id.phay);

        btnDot.setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                if (currentInput.isEmpty()) {
                    currentInput = "0.";
                } else {
                    currentInput += ".";
                }
                updateDisplay();
            }
        });

        btnC.setOnClickListener(v -> {
            currentInput = "";
            previousInput = "";
            operator = "";
            tvInput.setText("");
        });

        btnDel.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                updateDisplay();
            }
        });

        btnPercent.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                currentInput = String.valueOf(value / 100);
                updateDisplay();
            }
        });

        btnAm.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                if (currentInput.startsWith("-")) {
                    currentInput = currentInput.substring(1);
                } else {
                    currentInput = "-" + currentInput;
                }
                updateDisplay();
            }
        });

        btnDivide.setOnClickListener(v -> setOperator("/"));
        btnMultiply.setOnClickListener(v -> setOperator("×"));
        btnSubtract.setOnClickListener(v -> setOperator("-"));
        btnAdd.setOnClickListener(v -> setOperator("+"));

        btnEqual.setOnClickListener(v -> {
            if (!previousInput.isEmpty() && !operator.isEmpty() && !currentInput.isEmpty()) {
                double result = calculateResult(Double.parseDouble(previousInput), Double.parseDouble(currentInput), operator);
                String expression = previousInput + " " + operator + " " + currentInput;
                currentInput = String.valueOf(result);
                tvInput.setText(expression + "\n= " + currentInput);
                previousInput = "";
                operator = "";
            }
        });
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            currentInput = "";
            operator = op;
            updateDisplay();
        }
    }

    private double calculateResult(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "×":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    tvInput.setText("Error: Division by zero");
                    return 0;
                }
            default:
                return 0;
        }
    }

    private void appendNumber(String number) {
        if (number.equals("0") && currentInput.isEmpty()) return;
        currentInput += number;
        updateDisplay();
    }

    private void updateDisplay() {
        if (!operator.isEmpty() && !previousInput.isEmpty()) {
            tvInput.setText(previousInput + " " + operator + " " + currentInput);
        } else {
            tvInput.setText(currentInput);
        }
    }
}
