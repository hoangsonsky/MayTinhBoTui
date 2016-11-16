package vn.itplus.sonhv.appdemo;

import android.util.Log;

/**
 * Created by Administrator on 17/03/2016.
 */
public class MixedNumber {
    private long mNumber = 0;
    private long mNumerator = 0;
    private long mDenominator = 0;
    private boolean mIsPositive = true;
    private int count = 0;

    public void addCharToNumber(long c) {
        if (this.mIsPositive) {
            this.mNumber = this.mNumber * 10 + c;
        } else {
            this.mNumber = this.mNumber * 10 - c;
        }
    }

    public void addCharToNumerator(long c) {
        if (this.mIsPositive) {
            this.mNumerator = this.mNumerator * 10 + c;
        } else {
            this.mNumerator = this.mNumerator * 10 - c;
        }
    }

    public void addCharToDenominator(long c) {
        this.mDenominator = this.mDenominator * 10 + c;
    }

    public void setIsPositive(boolean isPositive) {
        this.mIsPositive = isPositive;
    }

    public boolean isPositive() {
        return mIsPositive;
    }

    public void shortNumberExcepti() {
        if (mNumerator == 0) {
            mDenominator = 1;
        } else {
            long ucln = getUCLN(mNumerator, mDenominator);
            mNumerator = mNumerator / ucln;
            mDenominator = mDenominator / ucln;

            if (mDenominator < 0) {
                mDenominator = -mDenominator;
                mNumerator = -mNumerator;
            }

            long divide = 0;
            if (mNumerator > mDenominator && mDenominator > 0) {
                divide = mNumerator / mDenominator;
                mNumerator = mNumerator - divide * mDenominator;
                mNumber = (mNumber + divide);

            } else if (-mNumerator > mDenominator) {
                divide = mNumerator / mDenominator;
                mNumerator = mNumerator - divide * mDenominator;
                mNumber = (mNumber + divide);
            }

            if (mNumerator < 0 && mNumber > 0) {
                mNumber = mNumber - 1;
                mNumerator = mDenominator + mNumerator;

            }
            if (mNumerator == mDenominator) {
                mNumber = mNumber + 1;
                mNumerator = 0;
                mDenominator = 1;
            } else if (-mNumerator == mDenominator) {
                mNumber = mNumber - 1;
                mNumerator = 0;
                mDenominator = 1;
            }

        }

    }

    public long getNumber() {
        return mNumber;
    }

    public long getNumerator() {
        return mNumerator;
    }

    public long getDenominator() {
        return mDenominator;
    }

    public long getUCLN(long numerator, long denominator) {
        long positiveNumerator = 0;
        long positiveDenominator = 0;
        if ((numerator < 0) && denominator > 0) {
            positiveNumerator = -numerator;
            positiveDenominator = denominator;
        } else if ((numerator < 0) && (denominator < 0)) {
            positiveNumerator = -numerator;
            positiveDenominator = -denominator;
        } else if (numerator > 0 && denominator < 0) {
            positiveNumerator = numerator;
            positiveDenominator = -denominator;
        } else {
            positiveNumerator = numerator;
            positiveDenominator = denominator;
        }
        while (positiveDenominator != 0) {
            long tmp = positiveDenominator;
            positiveDenominator = positiveNumerator % positiveDenominator;
            positiveNumerator = tmp;
        }
        return positiveNumerator;
    }

    public MixedNumber summation(MixedNumber number) {
        MixedNumber result = new MixedNumber();
        result.mDenominator = number.mDenominator * this.mDenominator;
        result.mNumber = 0;
        result.mNumerator = (number.mNumber * number.mDenominator + number.mNumerator) * this.mDenominator
                + (this.mNumber * this.mDenominator + this.mNumerator) * number.mDenominator;
        return result;
    }

    public MixedNumber subtraction(MixedNumber number) {
        MixedNumber result = new MixedNumber();
        result.mDenominator = number.mDenominator * this.mDenominator;
        result.mNumber = 0;
        result.mNumerator = (number.mNumber * number.mDenominator + number.mNumerator) * this.mDenominator
                - (this.mNumber * this.mDenominator + this.mNumerator) * number.mDenominator;
        return result;
    }

    public MixedNumber multiplication(MixedNumber number) {
        MixedNumber result = new MixedNumber();
        result.mDenominator = number.mDenominator * this.mDenominator;
        result.mNumber = 0;
        result.mNumerator = (number.mNumber * number.mDenominator + number.mNumerator)
                * (this.mNumber * this.mDenominator + this.mNumerator);
        return result;

    }

    public MixedNumber division(MixedNumber number) {
        MixedNumber result = new MixedNumber();
        result.mNumerator = (number.mNumber * number.mDenominator + number.mNumerator) * this.mDenominator;
        result.mNumber = 0;
        result.mDenominator = number.mDenominator * (this.mNumber * this.mDenominator + this.mNumerator);
        return result;
    }

    public void deleteNumber() {
        this.mNumber = this.mNumber / 10;
    }

    public void deleteNumertor() {
        if (this.mNumerator == 0) {
            count++;
        }
        this.mNumerator = this.mNumerator / 10;
        if (count >= 1) {
            deleteNumber();
        }
    }

    public void deleteDenominaotr() {
        if (this.mDenominator == 0) {
            count++;
        }
        this.mDenominator = this.mDenominator / 10;
        if (count >= 1) {
            deleteNumber();
        }

    }

    public void displayToView(CustomView customView) {
        count=0;
        long tmpNumerator;
        customView.tvNumber.setText("" + this.mNumber);
        customView.tvNumertor.setText("" + this.mNumerator);
        customView.tvDenominator.setText("" + this.mDenominator);

        if (mNumerator == 0) {
            customView.tvNumertor.setText("");
        }
        if (mDenominator == 0) {
            customView.tvDenominator.setText("");
        }
        if (this.mIsPositive == false) {
            if (mNumber > 0) {
                mNumber = -mNumber;
                customView.tvNumber.setText("" + this.mNumber);
            } else if (mNumber == 0) {
                customView.tvNumber.setText("-");
            }
            if (mNumerator > 0) {
                tmpNumerator = mNumerator;
                mNumerator = -mNumerator;
                customView.tvNumertor.setText("" + tmpNumerator);
            }
        } else {
            if (mNumber < 0) {
                mNumber = -mNumber;
                customView.tvNumber.setText("" + this.mNumber);
            } else if (mNumber == 0) {
                customView.tvNumber.setText("");
            }
            if (mNumerator < 0) {
                mNumerator = -mNumerator;
                customView.tvNumertor.setText("" + mNumerator);
            }
        }

        if (mNumerator < 0 && mNumber < 0) {
            tmpNumerator = -this.mNumerator;
            customView.tvNumertor.setText("" + tmpNumerator);
        } else if (mNumerator < 0 && mNumber == 0) {
            customView.tvNumber.setText("");
            customView.tvNumertor.setText("" + mNumerator);
        }

//        Log.e("abc", mNumber + "   " + mNumerator + "   " + mDenominator);

    }

    public void displayResult(CustomView customView) {
        count = 0;
        long tmpNumerator;
        customView.tvNumber.setText("" + this.mNumber);
        customView.tvNumertor.setText("" + this.mNumerator);
        customView.tvDenominator.setText("" + this.mDenominator);
        if (mNumerator == 0) {
            customView.tvNumertor.setText("");
            customView.tvDenominator.setText("");
        } else if (mNumerator < 0 && mNumber < 0) {
            tmpNumerator = -this.mNumerator;
            customView.tvNumertor.setText("" + tmpNumerator);
        }
        if (mNumber == 0 && mNumerator != 0) {
            customView.tvNumber.setText("");
        }
    }

}
