package programm_calculator.src.main.java.ru.startandroid.programm_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int buttons[] = {R.id.btnCancel, R.id.btnBracket1, R.id.btnBracket2, R.id.btnDelete, R.id.btnZero,
                R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven,
                R.id.btnEight, R.id.btnNine, R.id.btnPoint, R.id.btnEqual, R.id.btnDivision, R.id.btnMultiplication,
                R.id.btnPlus, R.id.btnMinus};


        for (int i = 0; i < buttons.length; i++) {
            Button btn = (Button) findViewById(buttons[i]);
            btn.setOnClickListener(this);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et = (EditText) findViewById(R.id.editText1);
    }

    @Override
    public void onClick(View v) {
        String inputText = et.getText().toString();
        switch (v.getId()) {
            case R.id.btnZero:
                input("0");
                break;
            case R.id.btnOne:
                input("1");
                break;
            case R.id.btnTwo:
                input("2");
                break;
            case R.id.btnThree:
                input("3");
                break;
            case R.id.btnFour:
                input("4");
                break;
            case R.id.btnFive:
                input("5");
                break;
            case R.id.btnSix:
                input("6");
                break;
            case R.id.btnSeven:
                input("7");
                break;
            case R.id.btnEight:
                input("8");
                break;
            case R.id.btnNine:
                input("9");
                break;
            case R.id.btnBracket1:
                input("(");
                break;
            case R.id.btnBracket2:
                input(")");
                break;
            case R.id.btnPlus:
               if( isTextEmpty(inputText)){
                   return;
               }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    input("+");
                }
                break;
            case R.id.btnMinus:

                if (isTextEmpty(inputText)|| !isOperator(inputText.charAt(inputText.length() - 1))) {
                    input("-");
                }
                break;
            case R.id.btnMultiplication:
                if( isTextEmpty(inputText)){
                    return;
                }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    input("*");
                }
                break;
            case R.id.btnDivision:
                if( isTextEmpty(inputText)){
                    return;
                }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    input("/");
                }
                break;
            case R.id.btnPoint:
                if (inputText.equals(""))
                    return;
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    input(".");
                }
                break;
            case R.id.btnEqual:
                if (inputText.equals(""))
                    return;
                if(isOperator(inputText.charAt(inputText.length() - 1))) {
                    return;
                }
                boolean isVisible = false;
                Expression expression = new Expression(inputText);
                String result = String.valueOf(expression.calculate());

                String stringAfterPoint = "";
                int positionPoint;
                for(positionPoint = 1; positionPoint < result.length(); positionPoint++){
                    if(result.charAt(positionPoint) == '.'){
                        stringAfterPoint = result.substring(positionPoint + 1);
                        break;
                    }
                }

                for(int positionNotZero = 0; positionNotZero < stringAfterPoint.length(); positionNotZero++){
                    if(stringAfterPoint.charAt(positionNotZero) != '0'){
                        et.setText(result);
                        isVisible = true;
                        break;
                    }
                }

                if (!isVisible) {
                    et.setText(result.substring(0, positionPoint));
                }


                break;
            case R.id.btnDelete:
                if (inputText.equals(""))
                    return;
                et.setText(inputText.substring(0, inputText.length() - 1));
                break;
            case R.id.btnCancel:
                et.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        return true;
    }
    public void input(String s){
        et.setText(et.getText().toString() + s);
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'|| c == '.';
    }

    boolean isTextEmpty(String s) {
        if (s.equals("")) {
            return true;
        }
        else
            return false;
    }


}
