package hub.programs.calcul8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static Button buttonDivision, buttonMinus, buttonPlus, buttonPlusMinus, buttonEquality,
            buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive,
            buttonSix, buttonSeven, buttonEight, buttonNine,
            buttonDecimal;
    private static EditText etResult;
    private static StringBuilder equation = new StringBuilder();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etResult = (EditText) findViewById(R.id.et_result);
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

    public void touched (View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_deci:
                break;
            case R.id.bt_minus:
                break;
            case R.id.bt_plus:
                break;
            case R.id.bt_multip:
                break;
            case R.id.bt_divi:
                break;
            case R.id.bt_plusMinus:
                equate("");
                break;
            case R.id.bt_zero:
                equate("0");
                break;
            case R.id.bt_one:
                equate("1");
                break;
            case R.id.bt_two:
                equate("2");
                break;
            case R.id.bt_three:
                equate("3");
                break;
            case R.id.bt_four:
                equate("4");
                break;
            case R.id.bt_five:
                equate("5");
                break;
            case R.id.bt_six:
                equate("6");
                break;
            case R.id.bt_seven:
                equate("7");
                break;
            case R.id.bt_eight:
                equate("8");
                break;
            case R.id.bt_nine:
                equate("9");
                break;
        }
    }

    private void equate (String btn) {
        switch (btn) {
            case "plusMinus":
                break;
            default:
                equation.append(btn);
        }
        etResult.append(btn);
    }
}