package programm_calculator.src.main.java.ru.startandroid.programm_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.util.Log;
import java.math.BigInteger;
import java.util.LinkedList;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    EditText et;
    int arrayBIN[] = { R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix,
            R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnA, R.id.btnB,
            R.id.btnC, R.id.btnD, R.id.btnE, R.id.btnF};

    int arrayOCT[] = { R.id.btnEight, R.id.btnNine, R.id.btnA, R.id.btnB, R.id.btnC, R.id.btnD,
            R.id.btnE, R.id.btnF};

    int arrayDEC[] = {R.id.btnA, R.id.btnB, R.id.btnC, R.id.btnD, R.id.btnE, R.id.btnF};

    private int previousBase = 10;
    BigInteger resultInDec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);


        int buttons[] = {R.id.btnCancel, R.id.btnDelete, R.id.btnZero, R.id.btnOne, R.id.btnTwo,
                R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven,
                R.id.btnEight, R.id.btnNine, R.id.btnEqual, R.id.btnDivision, R.id.btnMultiplication,
                R.id.btnPlus, R.id.btnMinus, R.id.btnA, R.id.btnB, R.id.btnC, R.id.btnD, R.id.btnE,
                R.id.btnF};


        for (int i = 0; i < buttons.length; i++) {
            Button btn = (Button) findViewById(buttons[i]);
            btn.setOnClickListener(this);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et = (EditText) findViewById(R.id.editText1);
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.rGroup);
        if (rGroup != null) {
            rGroup.setOnCheckedChangeListener(this);
        }
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
            case R.id.btnPlus:
                if( isTextEmpty(inputText)){
                    return;
                }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    et.setText(inputText + "+");
                }
                break;
            case R.id.btnMinus:
                if( isTextEmpty(inputText)){
                    return;
                }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    et.setText(inputText + "-");
                }
                break;
            case R.id.btnMultiplication:
                if( isTextEmpty(inputText)){
                    return;
                }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    et.setText(inputText + "*");
                }
                break;
            case R.id.btnDivision:
                if( isTextEmpty(inputText)){
                    return;
                }
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    et.setText(inputText + "/");
                }
                break;
            case R.id.btnPoint:
                if (inputText.equals(""))
                    return;
                if (!isOperator(inputText.charAt(inputText.length() - 1))) {
                    et.setText(inputText + ".");
                }
                break;
            case R.id.btnEqual:
                if (inputText.equals(""))
                    return;
                if(isOperator(inputText.charAt(inputText.length() - 1))) {
                    return;
                }
                if (inputText.equals(""))
                    return;

                resultInDec = getResultInDec(inputText, previousBase);
                resultInDec.toString(previousBase);
                et.setText(resultInDec.toString(previousBase));
                break;
            case R.id.btnDelete:
                if (inputText.equals(""))
                    return;
                et.setText(inputText.substring(0, inputText.length() - 1));
                break;
            case R.id.btnCancel:
                et.setText("");
                break;
            case R.id.btnA:
                input("A");
                break;
            case R.id.btnB:
                input("B");
                break;
            case R.id.btnC:
                input("C");
                break;
            case R.id.btnD:
                input("D");
                break;
            case R.id.btnE:
                input("E");
                break;
            case R.id.btnF:
                input("F");
                break;
        }
    }
    public void input(String s){
        et.setText( et.getText().toString() + s);
    }
    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    boolean isTextEmpty(String s) {
        if (s.equals("")) {
            return true;
        }
        else
            return false;
    }


    int priority(char operation) {
        // Если символ * или / - приоритет 1
        if (operation == '*' || operation == '/') {
            return 1;
        }
        // Если символ + или - — приоритет 0
        else if (operation == '+' || operation == '-') {
            return 0;
        }
        // Если ни то, ни другое - приоритет -1
        else {
            return -1;
        }
    }


    boolean calculate(LinkedList<BigInteger> st, char operation) {
        // Инициализируем и объявляем две переменные
        // Первая берет последнее значение из переданного
        // связанного листа в параметре, запоминает и удаляет
        // его из списка
        BigInteger someOne = st.removeLast();

        BigInteger someTwo = st.removeLast();

        switch (operation) {
            case '+':
                st.add(someTwo.add(someOne));
                return true;
            case '-':
                st.add(someTwo.subtract(someOne));
                return true;
            case '*':
                st.add(someTwo.multiply(someOne));
                return true;
            case '/':
                try{
                    st.add(someTwo.divide(someOne));
                    return true;
                }catch(ArithmeticException ex){
                    Log.d("ERROR", "error");
                    return false;
                }
            default:
                System.out.println("Oops");
        }
        return true;
    }
    BigInteger getResultInDec(String s, int baseFrom) {

        // Создаем два контейнера типа LinkedList
        // Один для чисел, другой для символов
        LinkedList<BigInteger> someNumbers = new LinkedList<>();
        LinkedList<Character> someOpers = new LinkedList<>();

        // Пишем цикл, который бегает по нашей строке
        for (int i = 0; i < s.length(); i++) {

            // Создаем локальную переменную типа символ,
            // чтобы было с чем делать сравнения и работать.
            // Присваиваем ей текущее положение i в строке
            char c = s.charAt(i);

            // Если натыкаемся на открывающуюся скобку
            if (c == '(') {

                // Добавляем открывающуюся скобку в контейнер
                // символов
                someOpers.add('(');

            }

            // Если натыкаемся на закрывающуюся скобку
            else if (c == ')') {

                // Смотрим - пока последний символ контейнера
                // символов не открывающаяся скобка -
                // Выполняем метод, который учит считать
                // программу, передавая ему в параметрах
                // наш контейнер с числами и последний
                // символ в контейнере символов, причем
                // удаляя его опосля
                while (someOpers.getLast() != '(') {
                    boolean isGood = calculate(someNumbers, someOpers.removeLast());
                    if (!isGood) {
                        return new BigInteger("0");
                    }
                }

                // После while - удаляем последний символ
                // из Символьного Контейнера. Если смотреть
                // пример - это открывающаяся скобка
                someOpers.removeLast();
            }

            // Так же, во время цикла мы проверяем каждый символ
            // на предмет - а не оператор ли он часом?
            // Если же да, то
            // ПОКА массив символов непустой и приоритет
            // последнего символа в контейнере символов
            // больше или равен приоритету текущего -
            // "учим" программу считать, передавая в параметрах
            // контейнер с числами и последний символ из
            // контейнера символов, удаляя его опосля
            else if (isOperator(c)) {
                while (!someOpers.isEmpty() &&
                        priority(someOpers.getLast()) >= priority(c)) {

                    boolean isGood = calculate(someNumbers, someOpers.removeLast());
                    if (!isGood) {
                        return new BigInteger("0");
                    }
                }

                // Если while не выполняется - добавляем
                // символ в контейнер символов
                someOpers.add(c);
            }

            // Если же ничего из вышеперечисленного не произошло,
            // то мы ожидаем число
            else if (Character.isDigit(c) || Character.isLetter(s.charAt(i))) {
                String operand = "";

                // После чего, ПОКА
                // текущее i меньше размера строки и
                // позиция от i в строке - число, -
                // мы составляем строку числа из символов,
                // увеличивая i на 1 каждый раз, когда символ
                // записался, чтобы проверять строку дальше

                while ((i < s.length() && Character.isDigit(s.charAt(i))) || Character.isLetter(s.charAt(i))) {
                    operand += s.charAt(i++);
                    if (i == s.length()) {
                        break;
                    }
                }

                // Если while не выполнился или закончился -
                // отнимаем у i единицу (т.к. i++ отработала
                // лишний раз, и добавляем нашу
                // распарсенную в числовой манер строку,
                // которую мы составили из чисел в
                // Числовой Контейнер
                --i;
                Log.d("operand", "" + operand);
                someNumbers.add(new BigInteger(operand, baseFrom));
                Log.d("BigInteger10", "" + new BigInteger(operand, baseFrom));
            }
        }

        // После цикла,
        // ПОКА контейнер символов НЕ пустой, -
        // "учим" считать программу, передавая ей наш контейнер
        // чисел и контейнер символов.
        while (!someOpers.isEmpty()) {
            boolean isGood = calculate(someNumbers, someOpers.removeLast());
            if (!isGood) {
                return new BigInteger("0");
            }
        }

        return someNumbers.get(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String inputText = et.getText().toString();
        switch (checkedId){
            case R.id.rbBIN:
                for (int i = 0; i <arrayBIN.length ; i++) {
                    Button btn = (Button) findViewById(arrayBIN[i]);
                    btn.setEnabled(false);
                }
                if(inputText.equals("")){
                    return;
                }
                resultInDec = getResultInDec(inputText, previousBase);

                String resInBin = resultInDec.toString(2);
                et.setText(resInBin);
                previousBase = 2;
                break;
            case R.id.rbOCT:
                for (int i = 0; i <arrayBIN.length ; i++) {
                    Button btn = (Button) findViewById(arrayBIN[i]);
                    btn.setEnabled(true);}
                for (int j = 0; j <arrayOCT.length ; j++) {
                    Button btn1 = (Button) findViewById(arrayOCT[j]);
                    btn1.setEnabled(false);
                }
                if(inputText.equals("")){
                    return;
                }
                resultInDec = getResultInDec(inputText, previousBase);

                String resInOct = resultInDec.toString(8);
                et.setText(resInOct);
                previousBase = 8;
                break;
            case R.id.rbDEC:
                for (int i = 0; i <arrayBIN.length ; i++) {
                    Button btn = (Button) findViewById(arrayBIN[i]);
                    btn.setEnabled(true);}
                for (int j = 0; j <arrayOCT.length ; j++) {
                    Button btn1 = (Button) findViewById(arrayOCT[j]);
                    btn1.setEnabled(true);
                }
                for (int k = 0; k <arrayDEC.length ; k++) {
                    Button btn3 = (Button) findViewById(arrayDEC[k]);
                    btn3.setEnabled(false);
                }
                if(inputText.equals("")){
                    return;
                }
                resultInDec = getResultInDec(inputText, previousBase);

                String resultInDec1 = resultInDec.toString(10);
                et.setText(resultInDec1);
                previousBase = 10;

                break;
            case R.id.rbHEX:
                for (int i = 0; i <arrayBIN.length ; i++) {
                    Button btn1 = (Button) findViewById(arrayBIN[i]);
                    btn1.setEnabled(true);}
                for (int j = 0; j <arrayOCT.length ; j++) {
                    Button btn2 = (Button) findViewById(arrayOCT[j]);
                    btn2.setEnabled(true);
                }
                for (int k = 0; k <arrayDEC.length ; k++) {
                    Button btn3 = (Button) findViewById(arrayDEC[k]);
                    btn3.setEnabled(true);
                }
                if(inputText.equals("")){
                    return;
                }
                resultInDec = getResultInDec(inputText, previousBase);

                String resInHex = resultInDec.toString(16);
                et.setText(resInHex);
                previousBase = 16;
                break;

        }

    }

}