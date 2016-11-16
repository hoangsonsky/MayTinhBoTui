package vn.itplus.sonhv.appdemo;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 24/03/2016.
 */
public class CustomView extends LinearLayout {
    TextView tvNumber;
    TextView tvNumertor;
    TextView tvDenominator;


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.display_mixed_number, null);
        addView(view);

        int heightSCreen = getResources().getDisplayMetrics().heightPixels;

        this.tvNumber = (TextView) findViewById(R.id.tv_number);
        this.tvNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_Number));
        this.tvNumertor = (TextView) findViewById(R.id.tv_numerator);
        this.tvNumertor.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));
        this.tvDenominator = (TextView) findViewById(R.id.tv_denominator);
        this.tvDenominator.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));

        String charSequence = "";
        tvNumber.setText(charSequence);
        tvNumertor.setText(charSequence);
        tvDenominator.setText(charSequence);

    }

    public boolean testEmty() {
        String number = tvNumber.getText().toString();
        String numertor = tvNumertor.getText().toString();
        String denominator = tvDenominator.getText().toString();
        if (number.equals("") && numertor.equals("") && denominator.equals(""))
            return false;
        return true;
    }

    public void displayEmty() {
        tvNumber.setText("");
        tvNumertor.setText("");
        tvDenominator.setText("");
    }
}
