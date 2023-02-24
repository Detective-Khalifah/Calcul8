package hub.programs.calcul8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private static char type = 'd';
    private static final String LOG_TAG = MainActivity.class.getName();
    private static StringBuilder equation = new StringBuilder();
    private Spinner spTypes;
    private TextView tvFeedback, tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFeedback = (TextView) findViewById(R.id.tv_feedback);
        tvRes = (TextView) findViewById(R.id.tv_res);
        tvFeedback.setText(equation);
        spTypes = (Spinner) findViewById(R.id.types_spin);
        setTypeSpinner();
    }

    private void setTypeSpinner() {
        final ArrayAdapter typeSpin = ArrayAdapter.createFromResource(this, R.array.types,
                android.R.layout.simple_spinner_item);
        typeSpin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        /* android.R.layout.simple_spinner_dropdown_item */

        spTypes.setAdapter(typeSpin);

        spTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                switch (String.valueOf(adapterView.getItemAtPosition(pos)).charAt(0)) {
                    case 'b':
                    case 's':
                    case 'i':
                    case 'l':
                    case 'f':
                    case 'd':
                        // TODO: TAKE OUT -- make dynamic
//                        type = String.valueOf(adapterView.getItemAtPosition(pos)).charAt(0);
                        break;
                    default:
                        type = String.valueOf(adapterView.getItemAtPosition(pos)).charAt(3);
                }
                Log.i(LOG_TAG, "The equation: " + equation.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type = 'i';
            }
        });
    }

    /**
     * Ascertain the {@link @param view} item clicked, and trigger the appropriate response.
     *
     * @param view item clicked
     */
    public void touched(View view) {
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
            case R.id.bt_del:
                appendChar("del");
            case R.id.bt_lePar:
                appendChar("(");
                break;
            case R.id.bt_rePar:
                appendChar(")");
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
    private void appendChar(String btn) {
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
                if (eq.contains("."))
                    equation.deleteCharAt(equation.indexOf("."));
                equation.append('.');
                tvFeedback.setText(equation);
                break;
            case "divi":
                if (eq.endsWith("/"))
                    break;
                else if (lastChar == '+' || lastChar == '-' || lastChar == '*')
                    equation.replace(length, equation.length(), "/");
                else
                    equation.append("/");
                tvFeedback.setText(equation);
                break;
            case "minus":
                if (eq.endsWith("-"))
                    break;
                else if (lastChar == '+' || lastChar == '*' || lastChar == '/')
                    equation.replace(length, equation.length(), "-");
                else
                    equation.append('-');
                tvFeedback.setText(equation);
                break;
            case "multi":
                if (eq.endsWith("*"))
                    break;
                else if (lastChar == '+' || lastChar == '-' || lastChar == '/')
                    equation.replace(length, equation.length(), "*");
                else
                    equation.append("*");
                tvFeedback.setText(equation);
                break;
            case "plus":
                if (eq.endsWith("+")) {
                    break;
                } else if (lastChar == '-' || lastChar == '*' || lastChar == '/')
                    equation.replace(length, equation.length(), "+");
                else {
                    equation.append("+");
                }
                tvFeedback.setText(equation);
                break;
            case "plusMinus":
                if (firstChar == '-') {
                    eq = String.valueOf(equation.deleteCharAt(0));
                    equation = new StringBuilder(eq);
//                    displayResult(eq);
                } else {
                    equation = new StringBuilder("-").append(eq);
                }
                tvFeedback.setText(equation);
                break;
            case "equals?":
                if (eq.endsWith("*") || eq.endsWith("+") || eq.endsWith("-") || eq.endsWith("/"))
                    eq = String.valueOf(equation.deleteCharAt(length));
                displayResult(type, eq);
                break;
            case "(":
            case ")":
                break;
            case "del":
//                equation.append("\b");
//                equation = equation.delete(equation.length()-1, equation.length());
//                eq = String.valueOf(equation);
//                tvFeedback.setText(equation);
                break;
            default:
                equation.append(btn);
                tvFeedback.setText(equation);
        }
    }

    /**
     * This method checks the spinner handler {@link} and uses the spinner item currently selected
     * to determine what result is to be parsed; if none is selected, it will turn back/forward to
     * int by default.
     * /* @param typeOption the numeric data type being dealt with
     *
     * @param theEquation String formatted equation
     */

    private void displayResult(char numType, String theEquation) {
        String obResult = null, evalResult = "0.0"; // evaluated result, to be parsed into different types.
//        byte byteResult; short shortResult; int intResult; long longResult; float floatResult; double doubleResult;

        // Mozilla Rhino
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            evalResult = String.valueOf(engine.eval(theEquation));
        } catch (ScriptException se) {
            tvFeedback.setText(se.getLocalizedMessage());
        }

        // TODO: Make Spinner behave dynamically; change it's content when data falls into another type.
        type = numType;
        Log.i(LOG_TAG, "Type selected: " + type);
        try {
            switch (numType) {
                case 'b': // byte
                    // value within range of byte
                    if (evalResult.contains(".")) {
                        obResult = String.valueOf(Byte.parseByte(evalResult.substring(0, evalResult.indexOf("."))));
                    } else {
                        obResult = String.valueOf(Byte.parseByte(evalResult));
                    }
                    Log.i(LOG_TAG, "case b used.");
                    break;
                case 's': // short
                    if (evalResult.contains("."))
                        obResult = String.valueOf(Short.parseShort(evalResult.substring(0, evalResult.indexOf("."))));
                    else
                        obResult = String.valueOf((Short.parseShort(evalResult)));
                    Log.i(LOG_TAG, "case s used.");
                    break;
                case 'i': // int
                    if (evalResult.contains("."))
                        obResult = String.valueOf(Integer.parseInt(evalResult.substring(0, evalResult.indexOf("."))));
                    else
                        obResult = String.valueOf(Integer.parseInt(evalResult));
                    Log.i(LOG_TAG, "case i used.");
                    break;
                case 'l': // long
                    if (evalResult.contains("."))
                        obResult = String.valueOf(Long.parseLong(evalResult.substring(0, evalResult.indexOf("."))));
                    else
                        obResult = String.valueOf(Long.parseLong(evalResult));
                    Log.i(LOG_TAG, "case l used.");
                    break;
                case 'f': // float
                    obResult = String.valueOf(Float.parseFloat(evalResult));
                    Log.i(LOG_TAG, "case f used.");
                    break;
                case 'd': //double
                    obResult = String.valueOf(Double.parseDouble(evalResult));
                    final String dotZero = evalResult.substring(evalResult.length() - 2);
                    Log.i(LOG_TAG, dotZero);
                    if (dotZero.equals(".0"))
                        obResult = evalResult.substring(0, evalResult.length() - 2);
                    Log.i(LOG_TAG, "case d used.");
                    break;
                case 'D': // for BigDecimal ty
                    obResult = String.valueOf(new BigDecimal(evalResult));
                    Log.i(LOG_TAG, "case D used.");
                    break;
                case 'I': // for BigInteger
                    obResult = (evalResult.contains(".")
                            ?
                            String.valueOf(new BigInteger((evalResult.substring(0, evalResult.indexOf(".")))))
                            :
                            String.valueOf(new BigInteger(evalResult)));
                    Log.i(LOG_TAG, "case I used.");
                    break;
            }

//            if (obResult != null) {
//            tvFeedback.setText(R.string.result);
            Log.i(LOG_TAG, "obResult: " + obResult);
            tvFeedback.setText(equation);
//            tvFeedback.append("\n");
//            tvFeedback.append(obResult);
            tvRes.setText(obResult);

        } catch (NumberFormatException nfe) {
            try {
                if (obResult == null) {
                    throw new NullPointerException();
                } else {
                    tvFeedback.setText(nfe.getLocalizedMessage());
//                    numType = checkType(Double.parseDouble(evalResult));
//                    displayResult(numType, theEquation);
//                    Log.i(LOG_TAG, "numType: " + numType);

                    displayResult(checkType(Double.parseDouble(evalResult)), theEquation);
                    Log.i(LOG_TAG, "; numType returned: " + checkType(Double.parseDouble(evalResult)));
                }
            } catch (NullPointerException npe) { // No_Expression
                obResult = "0";
                tvFeedback.setText(obResult);
            }
        }

    }

    private char checkType(double doubleResult) {
        // TODO: Make conditions for BigInteger and BigDecimal
        // java.lang.NumberFormatException:
//        if (doubleResult > Long.MAX_VALUE || doubleResult < Long.MIN_VALUE) {
////            // TODO: Find out the range for other number types including this.
//            return 'd';
//        } else if (doubleResult > Integer.MAX_VALUE || doubleResult < Integer.MIN_VALUE) { // Value out of range for int
//            return 'l';
//        } else if (doubleResult > Short.MAX_VALUE || doubleResult < Short.MIN_VALUE) {// Value out of range for short
//            return 'i';
//        } else if (doubleResult > Byte.MAX_VALUE || doubleResult < Byte.MIN_VALUE) {
//            return 's';
//        } else
//            return 'b';

        if (doubleResult >= Byte.MIN_VALUE && doubleResult <= Byte.MAX_VALUE) // -128 through 127
            return 'b';
        else if (doubleResult >= Short.MIN_VALUE && doubleResult <= Short.MAX_VALUE) // - 32768 through 32767
            return 's';
        else if (doubleResult >= Integer.MIN_VALUE && doubleResult <= Integer.MAX_VALUE) // -2147483648 through 2147483647
            return 'i';
        else if (doubleResult >= Long.MIN_VALUE && doubleResult <= Long.MAX_VALUE) // -9223372036854775808.0 through 9223372036854775807.0
            return 'l';
        else if (doubleResult >= Float.MIN_VALUE && doubleResult <= Float.MAX_VALUE)
            return 'f';
        else if (doubleResult >= Double.MIN_VALUE && doubleResult <= Double.MAX_VALUE)
            return 'd';
        else { // when number falls into the Big_ class
            if (String.valueOf(doubleResult).contains("."))
                return 'I';
            else
                return 'D';
        }
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

// JavaScript
//        try {
//            ScriptEngine mExp = new ScriptEngineManager().getEngineByName("JavaScript");
//            obResult = String.valueOf(mExp.eval(theEquation));
//        } catch (ScriptException se) {
//        }
//                byteResult = (byte) (Byte.parseByte(evalResult.substring(0, evalResult.indexOf("."))));
//if (doubleResult > 127 || doubleResult < -128) { // java.lang.NumberFormatException:
//        // Value out of range for byte:
//        type = 's';
//        displayResult(type, theEquation);
//        }