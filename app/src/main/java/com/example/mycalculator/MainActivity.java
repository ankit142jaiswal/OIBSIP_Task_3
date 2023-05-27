package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class MainActivity extends AppCompatActivity {
    TextView calculating;
    TextView result;
    String calculated ="";
    String formula="";
    String tempFormula="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intTextViews();

    }

    private void intTextViews() {
        calculating = findViewById(R.id.calculatingTextView);
        result   = findViewById(R.id.resultTextView);
    }private void setCalculated(String givenValue)
    {
        calculated= calculated + givenValue;
        calculating.setText(calculated);
    }
    public void equalsOnClick(View view)
    {
        Double results= null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();
        try{
            results=(double) engine.eval(formula);
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if (results != null){
            result.setText(String.valueOf(results.doubleValue()));
        }

    }
    private  void checkForPowerOf(){
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for ( int i =0;i<calculated.length();i++){
            if (calculated.charAt(i) == '^'){
indexOfPowers.add(i);
            }

        }
     formula = calculated;
    tempFormula = calculated;
    for ( Integer index: indexOfPowers){
    changeFormula(index);
    }
    formula=tempFormula;
    }
    private  void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";
        for (int i = index + 1; i < calculated.length(); i++) {
            if (isNumeric(calculated.charAt(i))) {
                numberRight = numberRight + calculated.charAt(i);
            } else {
                break;
            }
        }
        for (int i = index - 1; i >= 0 ; i--) {
            if (isNumeric(calculated.charAt(i))) {
                numberLeft = numberLeft + calculated.charAt(i);
            } else {
                break;
            }
        }
        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original,changed);
    }
    private boolean isNumeric(char c){
        if((c<= '9' &&  c >= '0') || c == '.'){
            return true;
        }
        else{
            return false;
        }
    }
    public void allclearOnClick(View view){
        calculating.setText("");
        calculated="";
        result.setText("");
        leftBracket= true;

    }
    boolean leftBracket = true;

    public void bracketOnClick(View view){
        if (leftBracket){
            setCalculated("(");
            leftBracket= false;
        }
        else{
            setCalculated(")");
            leftBracket=true;
        }


    } public void powerofOnClick(View view){
        setCalculated("^");

    } public void divisionOnClick(View view){
        setCalculated("/");


    } public void sevenOnClick(View view){
        setCalculated("7");


    } public void eightOnClick(View view){
        setCalculated("8");


    } public void nineOnClick(View view){
        setCalculated("9");

    } public void multiplyOnClick(View view){
        setCalculated("*");

    } public void fourOnClick(View view){
        setCalculated("4");

    } public void fiveOnClick(View view){
        setCalculated("5");

    } public void sixOnClick(View view){
        setCalculated("6");

    } public void subtractOnClick(View view){
        setCalculated("-");

    } public void oneOnClick(View view){
        setCalculated("1");

    } public void twoOnClick(View view){
        setCalculated("2");

    } public void threeOnClick(View view){
        setCalculated("3");

    } public void additionOnClick(View view){
        setCalculated("+");

    } public void modulusOnClick(View view){
        setCalculated("%");

    } public void zeroOnClick(View view){
        setCalculated("0");

    } public void decimalOnClick(View view){
        setCalculated(".");

    }

}