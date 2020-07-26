package hub.programs.calcul8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static Button buttonDivision, buttonMinus, buttonPlus, buttonPlusMinus, buttonEquality,
            buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive,
            buttonSix, buttonSeven, buttonEight, buttonNine,
            buttonDecimal;
    private static TextView tv_feedback;
    private static StringBuilder equation = new StringBuilder();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_feedback = (TextView) findViewById(R.id.et_feedback);
        findTheButtons();
    }

    protected void findTheButtons () {
        buttonDivision = (Button) findViewById(R.id.bt_divi);
        buttonMinus = (Button) findViewById(R.id.bt_minus);
        buttonPlus = (Button) findViewById(R.id.bt_plus);
        buttonPlusMinus = (Button) findViewById(R.id.bt_plusMinus);
        buttonEquality = (Button) findViewById(R.id.bt_equal);

        buttonZero = (Button) findViewById(R.id.bt_zero);
        buttonOne = (Button) findViewById(R.id.bt_one);
        buttonTwo = (Button) findViewById(R.id.bt_two);
        buttonThree = (Button) findViewById(R.id.bt_three);
        buttonFour = (Button) findViewById(R.id.bt_four);
        buttonFive = (Button) findViewById(R.id.bt_five);
        buttonSix = (Button) findViewById(R.id.bt_six);
        buttonSeven = (Button) findViewById(R.id.bt_seven);
        buttonEight = (Button) findViewById(R.id.bt_eight);
        buttonNine = (Button) findViewById(R.id.bt_nine);

        buttonDecimal = (Button) findViewById(R.id.bt_deci);
    }

    /**
     * Ascertain the {@link @param view} item clicked, and trigger the appropriate response.
     *
     * @param view item clicked
     */
    public void touched (View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_deci:
                appendChar("deciPoint");
                break;
            case R.id.bt_minus:
                appendChar("minus");
                break;
            case R.id.bt_plus:
                appendChar("plus");
                break;
            case R.id.bt_multip:
                appendChar("multi");
                break;
            case R.id.bt_divi:
                appendChar("divi");
                break;
            case R.id.bt_plusMinus:
                appendChar("plusMinus");
                break;
            case R.id.bt_equal:
                appendChar("equals?");
                break;
            case R.id.bt_zero:
                appendChar("0");
                break;
            case R.id.bt_one:
                appendChar("1");
                break;
            case R.id.bt_two:
                appendChar("2");
                break;
            case R.id.bt_three:
                appendChar("3");
                break;
            case R.id.bt_four:
                appendChar("4");
                break;
            case R.id.bt_five:
                appendChar("5");
                break;
            case R.id.bt_six:
                appendChar("6");
                break;
            case R.id.bt_seven:
                appendChar("7");
                break;
            case R.id.bt_eight:
                appendChar("8");
                break;
            case R.id.bt_nine:
                appendChar("9");
                break;
        }
    }

    /**
     * Checks the expression to determine what arithmetic symbols are at the beginning or end;
     * check it there's a decimal point anywhere in the expression or otherwise;
     * and decide behavior of the recent button tapped/clicked.
     *
     * @param btn identifier for numeric/symbol button clicked.
     */
    private void appendChar (String btn) {
        char firstChar = 'a', lastChar = 'z';
        int length = 0;
        if (equation.length() > 1) {
            length = equation.length() - 1;
            firstChar = equation.charAt(0);
            lastChar = equation.charAt(length);
        }
        String eq = String.valueOf(equation);
        switch (btn) {
            case "deciPoint":
                // check if a decimal point is contained in the equation
                // If there's one, replace it at the end; otherwise, append '.' to end of equation
                if (eq.contains(".")) {

                } else {
                    equation.append('.');
                }
                break;
            case "divi":
                if (eq.endsWith("/"))
                    break;
                else if (lastChar == '+' || lastChar == '-' || lastChar == '*')
                    equation.replace(length, equation.length(), "/");
                else
                    equation.append("/");
                break;
            case "minus":
                if (eq.endsWith("-"))
                    break;
                else if (lastChar == '+' || lastChar == '*' || lastChar == '/')
                    equation.replace(length, equation.length(), "-");
                else
                    equation.append('-');
                break;
            case "multi":
                if (eq.endsWith("*"))
                    break;
                else if (lastChar == '+' || lastChar == '-' || lastChar == '/')
                    equation.replace(length, equation.length(), "*");
                else
                    equation.append("*");
                break;
            case "plus":
                if (eq.endsWith("+"))
                    break;
                else if (lastChar == '-' || lastChar == '*' || lastChar == '/') {
                    equation.replace(length, equation.length(), "+");
                } else {
                    equation.append("+");
                }
                break;
            case "plusMinus":
                if (firstChar == '-')
                    appendChar(eq);
                else {
                    equation = new StringBuilder("-").append(eq);
                }
                break;
            case "equals?":
                if (eq.endsWith("*") || eq.endsWith("+") || eq.endsWith("-") || eq.endsWith("/")) {
                    eq = String.valueOf(equation.deleteCharAt(length));
                    displayResult(eq);
                } else
                    displayResult(eq);
                break;
            default:
                equation.append(btn);
        }
        tv_feedback.setText(equation);
    }

    /**
     * This method checks the spinner handler {@link} and uses the spinner item currently selected
     * to determine what result is to be parsed; if none is selected, it will turn back/forward to
     * int by default.
     * /* @param typeOption the numeric data type being dealt with
     */

    private void displayResult (/*char typeOption, */String theEquation) {
        String obResult = null;

        // JavaScript
//        try {
//            ScriptEngine mExp = new ScriptEngineManager().getEngineByName("JavaScript");
//            obResult = String.valueOf(mExp.eval(theEquation));
//        } catch (ScriptException se) {
//        }

        // Mozilla Rhino
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            obResult = String.valueOf(engine.eval(theEquation));
            // TODO: Casting to java.lang.String doesn't work; calling .toString() and wrapping in String.valueOf() each work. FIND OUT WHY.
        } catch (ScriptException se) {

        }

//        switch (typeOption) {
//            case 'b':
//                byte byteResult = Byte.parseByte(String.valueOf(equation));
//                break;
//            case 'd':
//                double doubleResult = Double.parseDouble(String.valueOf(equation));
//                break;
//            case 'f':
//                float floatResult = Float.parseFloat(String.valueOf(equation));
//                break;
//            case 's':
//                short shortResult = Short.parseShort(String.valueOf(equation));
//                break;
//            case 'i':
//                int intResult = Integer.parseInt(String.valueOf(equation));
//                break;
//            case 'l':
//                long longResult = Long.parseLong(String.valueOf(equation));
//                break;
//
//            case 'D': // for BigDecimal ty
//                BigDecimal bD = new BigDecimal(equation);
//                break;
//            case 'I': // for BigInteger
//                BigInteger bI = new BigInteger(equation);
//                break;
//            default:
//        }


        if (obResult != null) {
            Log.i(LOG_TAG, "Result in obResult: " + obResult);
            tv_feedback.append("\n");
            tv_feedback.append(String.valueOf(R.string.result));
            tv_feedback.append(obResult);
        } else {
            throw new AssertionError();
        }
    }

    // TODO; designate a handler for the data type spinner and use
    //     byteResult = Byte.parseByte((String) engine.eval(String.valueOf(equation)));
    //     shortResult = Short.parseShort((String) engine.eval(String.valueOf(equation)));
    //     intResult = (int) engine.eval(String.valueOf(equation));
    //     longResult = (long) engine.eval(String.valueOf(equation));
    //     floatResult = (float) engine.eval(String.valueOf(equation));
    //     doubleResult = (double) engine.eval(String.valueOf(equation));
    //  to parse result depending on Spinner item selected.

    //    private void checkType () { }
}