package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText display;
    Button[] buttons = new Button[16];
    Calculator calculator;

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            pushButton(button.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editDisplay);
        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);
        buttons[9] = findViewById(R.id.button9);
        buttons[10] = findViewById(R.id.buttonC);
        buttons[11] = findViewById(R.id.buttonPlus);
        buttons[12] = findViewById(R.id.buttonMinus);
        buttons[13] = findViewById(R.id.buttonMulty);
        buttons[15] = findViewById(R.id.buttonEnter);

        for (Button button: buttons){
            button.setOnClickListener(clickListener);
        }

        calculator = new Calculator();

    }

    private void pushButton (String sing) {
        if (sing.equals("C")) {
            display.setText("0");
            calculator.first = 0;
            calculator.second = 0;
            calculator.operation = "";
        } else if ("0123456789".contains(sing)) {
            if (calculator.second == Integer.parseInt(display.getText().toString())) {
                calculator.second = Integer.parseInt(display.getText().toString() + sing);
            } else {
                calculator.second = Integer.parseInt(sing);
            }
            display.setText(Integer.toString(calculator.second));
        } else {
            try {
                calculator.first = calculator.calculate();
                display.setText(Integer.toString(calculator.first));
                calculator.operation = sing;
                calculator.second = 0;
            } catch (IOException e) {
                display.setText("");
            }

        }
    }
}
