package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    //Variables
    double value1 = 0, value2 = 0, clicks = 0, calcSum;
    String operator, text;
    char lastChar = ' ';
    boolean equalsPressed = false;

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,
            btnPlus,btnMinus,btnDivide,btnMultiply,btnEquals,
            btnClear,btnBack,btnDec,btnNeg;
    TextView histView, displayView;

    CalcFunctions functions = new CalcFunctions();

    void operatorClicked(String operatorSymbol, String operatorText){

        text = histView.getText().toString();
        clicks = 0;
        if (displayView.getText().toString().charAt(displayView.getText().toString().length() -1) != '.') {

            if (displayView.getText().toString() != "") {
                if (text == "") {
                    value1 = Double.parseDouble(displayView.getText().toString());
                    histView.setText(displayView.getText().toString() + " " + operatorSymbol + " ");
                    displayView.setText("");
                    operator = operatorText;
                } else if (Character.isDigit(text.charAt(text.length() - 1))) {
                    value1 = Double.parseDouble(displayView.getText().toString());
                    histView.setText(displayView.getText().toString() + " " + operatorSymbol + " ");
                    displayView.setText("");
                } else {
                    value2 = Double.parseDouble(displayView.getText().toString());
                    if (value2 == 0 && operator == "divide"){
                        histView.setText("");
                        displayView.setText("NaN");
                    }
                    else {
                        calcSum = functions.calculate(displayView.getText().toString(), operator, value1, value2);
                        displayView.setText("");
                        if (functions.isDecimal(calcSum)) {
                            histView.setText(Double.toString(calcSum) + " " + operatorSymbol + " ");
                        } else {
                            histView.setText(Integer.toString((int) calcSum) + " " + operatorSymbol + " ");
                        }
                        value1 = calcSum;
                        operator = operatorText;
                    }
                }
            }
        }
    }

    void equalsPressed(){
        clicks = 0;
        if (equalsPressed || displayView.getText().toString() == "NaN"){
            displayView.setText("");
            equalsPressed = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btnPlus = (Button) findViewById(R.id.btn_Plus);
        btnMinus = (Button) findViewById(R.id.btn_Minus);
        btnMultiply = (Button) findViewById(R.id.btn_Multiply);
        btnDivide = (Button) findViewById(R.id.btn_Divide);
        btnEquals = (Button) findViewById(R.id.btn_Equals);
        btnClear = (Button) findViewById(R.id.btn_Clear);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnDec = (Button) findViewById(R.id.btn_dec);
        btnNeg = (Button) findViewById(R.id.btn_negative);
        displayView = (TextView)findViewById(R.id.displayTextView);
        histView = (TextView) findViewById(R.id.histTextView);

        //Number 1
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "1");
            }
        });

        //Number 2
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "2");
            }
        });

        //Number 3
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "3");
            }
        });

        //Number 4
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "4");
            }
        });

        //Number 5
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "5");
            }
        });

        //Number 6
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "6");
            }
        });

        //Number 7
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "7");
            }
        });

        //Number 8
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "8");
            }
        });

        //Number 9
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "9");
            }
        });

        //Number 0
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsPressed();
                displayView.setText(displayView.getText() + "0");
            }
        });

        //Operator +
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = histView.getText().toString();
                clicks = 0;
                operatorClicked("+", "plus");
            }
        });

        //Operator -
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = histView.getText().toString();
                clicks = 0;
                operatorClicked("-", "minus");
            }
        });

        //Operator /
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = histView.getText().toString();
                clicks = 0;
                operatorClicked("/", "divide");
            }
        });

        //Operator *
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = histView.getText().toString();
                clicks = 0;
                operatorClicked("*", "multiply");
            }
        });

        //Operator =
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value1 != 0) {
                    if(!equalsPressed) {
                        equalsPressed = true;
                        if (displayView.getText() != "") {
                            clicks = 0;
                            value2 = Double.parseDouble(displayView.getText().toString());
                            histView.setText("");
                            value1 = functions.calculate(displayView.getText().toString(), operator, value1, value2);
                            if (value2 == 0 && operator == "divide"){
                                displayView.setText("NaN");
                            }
                            else {
                                if (functions.isDecimal(Double.parseDouble(displayView.getText().toString()))) {
                                    displayView.setText(Double.toString(value1));
                                } else {
                                    displayView.setText(Integer.toString((int) value1));
                                }
                            }
                        }
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clicks == 0) {
                    displayView.setText("");
                    clicks = 1;
                    value2 = 0;
                }
                else{
                    clicks = 0;
                    displayView.setText("");
                    histView.setText("");
                    value1 = 0;
                    operator = "";
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(displayView.getText().toString() != ""){
                    if (displayView.getText().toString().contains(".")){
                        if (displayView.getText().toString().charAt(displayView.getText().length() -2) == '.'){
                            displayView.setText(Integer.toString((int) Double.parseDouble(displayView.getText().toString())));
                        }
                        else if (displayView.getText().toString().charAt(displayView.getText().length() -1) == '.'){
                            displayView.setText(displayView.getText().toString().substring(0, displayView.getText().length() - 1));
                        }
                    }
                    else{
                        if (Double.parseDouble(displayView.getText().toString()) >= 0){
                            if (displayView.getText().length() > 1) {
                                displayView.setText(displayView.getText().toString().substring(0, displayView.getText().length() - 1));
                            }
                            else{
                                displayView.setText("");
                            }
                        }
                        else {
                            if (displayView.getText().length() > 2){
                                displayView.setText(displayView.getText().toString().substring(0, displayView.getText().length() - 1));
                            }
                            else {
                                displayView.setText("");
                            }
                        }
                    }
                }
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (displayView.getText().toString() == "") {
                    displayView.setText(displayView.getText() + "0.");
                }
                else {
                    if (!displayView.getText().toString().contains(".")) {
                        displayView.setText(displayView.getText() + ".");
                    }
                }
            }
        });

        btnNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (displayView.getText().toString() != "" && displayView.getText().toString().charAt(displayView.getText().toString().length() - 1) != '.') {
                    double disValue = Double.parseDouble(displayView.getText().toString());
                    if (functions.isDecimal(Double.parseDouble(displayView.getText().toString()))) {
                        displayView.setText(Double.toString(disValue * -1));
                    } else {
                        displayView.setText(Integer.toString((int) disValue * -1));
                    }
                }
            }
        });


    }
}
