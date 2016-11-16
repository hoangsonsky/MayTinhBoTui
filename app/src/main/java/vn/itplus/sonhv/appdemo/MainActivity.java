package vn.itplus.sonhv.appdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    Operator mCurrOperator = Operator.NONE;
    MixedNumber mLastNumber;
    MixedNumber result;
    MixedNumber mCurrNumber = new MixedNumber();

    CustomView mDisplay_last, mDisplay, mDisplay_result;
    TextView tvOperator, tvOperatorEnd;
    HorizontalScrollView scrollView;
    LinearLayout linearLayou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (HorizontalScrollView) findViewById(R.id.hori);
        tvOperator = (TextView) findViewById(R.id.tv_operator);
        tvOperatorEnd = (TextView) findViewById(R.id.tv_operatorEnd);
        mDisplay_last = (CustomView) findViewById(R.id.display);
        mDisplay = (CustomView) findViewById(R.id.display_last);
        mDisplay_result = (CustomView) findViewById(R.id.display_result);
        linearLayou = (LinearLayout) findViewById(R.id.linear3);
        setSizeView();
    }

    public void setOnClick_C(View view) {
        deleteData();
    }

    public void setOnClickPositiveOrNegative(View view) {
        if (tvOperatorEnd.getText().toString().equals(" = "))
            deleteData();
        mCurrNumber.setIsPositive(!mCurrNumber.isPositive());
        mCurrNumber.displayToView(mDisplay);
    }

    public void setOnClickButtonNumber(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                createNumber(view);
                break;

            case R.id.btn_two:
                createNumber(view);
                break;

            case R.id.btn_three:
                createNumber(view);
                break;

            case R.id.btn_four:
                createNumber(view);
                break;

            case R.id.btn_five:
                createNumber(view);
                break;

            case R.id.btn_six:
                createNumber(view);
                break;

            case R.id.btn_seven:
                createNumber(view);
                break;

            case R.id.btn_eight:
                createNumber(view);
                break;

            case R.id.btn_nine:
                createNumber(view);
                break;

            case R.id.btn_zero:
                createNumber(view);
                break;
        }
    }

    public void setOnClickButonNumerator(View view) {
        switch (view.getId()) {
            case R.id.btn_1t:
                createNumerator(view);
                break;
            case R.id.btn_2t:
                createNumerator(view);
                break;
            case R.id.btn_3t:
                createNumerator(view);
                break;
            case R.id.btn_4t:
                createNumerator(view);
                break;
            case R.id.btn_t5:
                createNumerator(view);
                break;
            case R.id.btn_6t:
                createNumerator(view);
                break;
            case R.id.btn_7t:
                createNumerator(view);
                break;
            case R.id.btn_8t:
                createNumerator(view);
                break;
            case R.id.btn_9t:
                createNumerator(view);
                break;
            case R.id.btn_0t:
                createNumerator(view);
                break;
            case R.id.btn_xoa:
                if (tvOperatorEnd.getText().toString().compareTo(" = ") != 0) {
                    mCurrNumber.deleteNumertor();
                    mCurrNumber.displayToView(mDisplay);
                }
                break;
        }
    }

    public void setOnClickButonDenominator(View view) {
        switch (view.getId()) {
            case R.id.btn_1m:
                createDenominator(view);
                break;

            case R.id.btn_2m:
                createDenominator(view);
                break;

            case R.id.btn_3m:
                createDenominator(view);
                break;

            case R.id.btn_4m:
                createDenominator(view);
                break;

            case R.id.btn_5m:
                createDenominator(view);
                break;

            case R.id.btn_6m:
                createDenominator(view);
                break;

            case R.id.btn_7m:
                createDenominator(view);
                break;

            case R.id.btn_8m:
                createDenominator(view);
                break;

            case R.id.btn_9m:
                createDenominator(view);
                break;
            case R.id.btn_0m:
                createDenominator(view);
                break;

            case R.id.btn_xoam:
                if (tvOperatorEnd.getText().toString().compareTo(" = ") != 0) {
                    mCurrNumber.deleteDenominaotr();
                    mCurrNumber.displayToView(mDisplay);
                }
                break;
        }
    }

    public void setOnclickOperator(View view) {
        switch (view.getId()) {
            case R.id.btn_Sum:
                if (mCurrNumber.getNumber() != 0 ||
                        mCurrNumber.getNumerator() != 0 ||
                        mCurrOperator.compareTo(Operator.NONE) != 0) {
                    tvOperator.setText(" + ");
                    doCalculate();
                    mCurrOperator = Operator.ADD;
                }
                break;

            case R.id.btn_tru:
                if (mCurrNumber.getNumber() != 0 ||
                        mCurrNumber.getNumerator() != 0 ||
                        mCurrOperator.compareTo(Operator.NONE) != 0) {
                    doCalculate();
                    tvOperator.setText(" - ");
                    mCurrOperator = Operator.SUB;
                }
                break;

            case R.id.btn_nhan:
                if (mCurrNumber.getNumber() != 0 ||
                        mCurrNumber.getNumerator() != 0 ||
                        mCurrOperator.compareTo(Operator.NONE) != 0) {
                    doCalculate();
                    tvOperator.setText(" * ");
                    mCurrOperator = Operator.MUL;
                }
                break;

            case R.id.btn_chia:
                if (mCurrNumber.getNumber() != 0 ||
                        mCurrNumber.getNumerator() != 0 ||
                        mCurrOperator.compareTo(Operator.NONE) != 0) {
                    doCalculate();
                    tvOperator.setText(" / ");
                    mCurrOperator = Operator.DIV;
                }
                break;

            case R.id.btn_bang:
                if (mCurrOperator.equals(Operator.NONE)) {
                    if (mCurrNumber.getNumber()!=0||mCurrNumber.getNumerator()!=0){
                        if (mCurrNumber.getDenominator() == 0)
                            mCurrNumber.addCharToDenominator(1);
                        tvOperatorEnd.setText(" = ");
                        mCurrNumber.displayResult(mDisplay);
                        mCurrNumber.shortNumberExcepti();
                        result = mCurrNumber;
                        result.displayResult(mDisplay_result);
                    }

                } else if (mCurrOperator.compareTo(Operator.NONE) != 0 && mDisplay.testEmty())
                    resultCalculate();
                break;
        }
    }

    private void doCalculate() {
        if (mDisplay.testEmty()) {
            if (mCurrOperator.compareTo(Operator.DIV) != 0 ||
                    mCurrNumber.getNumber() != 0 ||
                    mCurrNumber.getNumerator() != 0) {

                if (mCurrNumber.getDenominator() == 0)
                    mCurrNumber.addCharToDenominator(1);

                if (tvOperatorEnd.getText().toString().compareTo(" = ") != 0) {
                    resultCalculate();
                }

                tvOperatorEnd.setText(" = ");
                mLastNumber = mCurrNumber;
                mLastNumber.displayResult(mDisplay_last);
                mCurrNumber = new MixedNumber();
                mDisplay.displayEmty();
                result = new MixedNumber();
                mDisplay_result.displayEmty();
                tvOperatorEnd.setText("");
                scrollToRight();
            }
        }

    }

    private void resultCalculate() {
        if (mDisplay.testEmty() && mCurrNumber.getDenominator() == 0)
            mCurrNumber.addCharToDenominator(1);

        if (mCurrOperator.equals(Operator.NONE)) {
            mLastNumber = mCurrNumber;
            mCurrNumber = new MixedNumber();
            mCurrNumber.addCharToDenominator(1);
            mCurrOperator = Operator.ADD;
        }

        tvOperatorEnd.setText(" = ");
        result = calculate(mCurrNumber, mLastNumber, mCurrOperator);
        mCurrNumber.displayResult(mDisplay);
        mCurrNumber = new MixedNumber();
        mCurrNumber = result;
        scrollToRight();

    }

    private MixedNumber calculate(MixedNumber a, MixedNumber b, Operator op) {
        MixedNumber result = null;
        switch (op) {
            case ADD: {
                result = a.summation(b);
                result.shortNumberExcepti();
                result.displayResult(mDisplay_result);
                break;
            }
            case SUB: {
                result = a.subtraction(b);
                result.shortNumberExcepti();
                result.displayResult(mDisplay_result);
                break;
            }
            case MUL: {
                result = a.multiplication(b);
                result.shortNumberExcepti();
                result.displayResult(mDisplay_result);
                break;
            }
            case DIV: {
                if (mCurrNumber.getNumber() == 0 && mCurrNumber.getNumerator() == 0 && mDisplay.testEmty()) {
                    mDisplay_result.tvNumber.setText("Error");
                } else {
                    result = a.division(b);
                    result.shortNumberExcepti();
                    result.displayResult(mDisplay_result);
                }
                break;
            }
        }
        return result;
    }

    public void createNumber(View view) {
        if (tvOperatorEnd.getText().toString().equals(" = "))
            deleteData();
        mCurrNumber.addCharToNumber(Long.parseLong((String) ((Button) view).getText()));
        mCurrNumber.displayToView(mDisplay);
        scrollToRight();
    }

    private void createNumerator(View view) {
        if (tvOperatorEnd.getText().toString().equals(" = "))
            deleteData();
        mCurrNumber.addCharToNumerator(Long.parseLong((String) ((Button) view).getText()));
        mCurrNumber.displayToView(mDisplay);
        scrollToRight();
    }

    private void createDenominator(View view) {
        if (tvOperatorEnd.getText().toString().equals(" = "))
            deleteData();
        long x = Long.parseLong((String) ((Button) view).getText());
        mCurrNumber.addCharToDenominator(x);
        mCurrNumber.displayToView(mDisplay);
        scrollToRight();
    }

    private void scrollToRight() {
        scrollView.postDelayed(new Runnable() {
            public void run() {
                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 1L);
    }

    //cài đặt kích thước theo màn hình.
    private void setSizeView() {
        DisplayMetrics display = this.getResources().getDisplayMetrics();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linearLayou.getLayoutParams();
        params.height = display.heightPixels / 8;
        params.width = display.widthPixels;
        linearLayou.setLayoutParams(params);
        tvOperator.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_Number));
        tvOperatorEnd.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_Number));
    }

    private void deleteData() {
        mCurrOperator = Operator.NONE;
        mLastNumber = new MixedNumber();
        result = new MixedNumber();
        mCurrNumber = new MixedNumber();

        mDisplay_last.displayEmty();
        mDisplay_result.displayEmty();
        mDisplay.displayEmty();

        tvOperator.setText("");
        tvOperatorEnd.setText("");
    }
}
