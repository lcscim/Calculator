package com.example.calculator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText editText;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        findViewById(R.id.backspace).setOnClickListener(this);
        findViewById(R.id.clean).setOnClickListener(this);
        findViewById(R.id.baifen).setOnClickListener(this);
        findViewById(R.id.chu).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.cheng).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.jian).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.jia).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.dian).setOnClickListener(this);
        findViewById(R.id.deng).setOnClickListener(this);
        int orientation = getResources().getConfiguration().orientation;    //获取当前布局状态
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){        //如果是水平加载以下
            findViewById(R.id.pi_btn).setOnClickListener(this);
            findViewById(R.id.e_btn).setOnClickListener(this);
            findViewById(R.id.abs_btn).setOnClickListener(this);
            findViewById(R.id.asin_btn).setOnClickListener(this);
            findViewById(R.id.acos_btn).setOnClickListener(this);
            findViewById(R.id.atan_btn).setOnClickListener(this);
            findViewById(R.id.pf_btn).setOnClickListener(this);
            findViewById(R.id.lf_btn).setOnClickListener(this);
            findViewById(R.id.ncf_btn).setOnClickListener(this);
            findViewById(R.id.sin_btn).setOnClickListener(this);
            findViewById(R.id.cos_btn).setOnClickListener(this);
            findViewById(R.id.tan_btn).setOnClickListener(this);
            findViewById(R.id.pfg_btn).setOnClickListener(this);
            findViewById(R.id.lfg_btn).setOnClickListener(this);
            findViewById(R.id.ncfg_btn).setOnClickListener(this);
            findViewById(R.id.csc_btn).setOnClickListener(this);
            findViewById(R.id.sec_btn).setOnClickListener(this);
            findViewById(R.id.cot_btn).setOnClickListener(this);
            findViewById(R.id.sdn_btn).setOnClickListener(this);
            findViewById(R.id.ds_btn).setOnClickListener(this);
            findViewById(R.id.log10_btn).setOnClickListener(this);
            findViewById(R.id.ln_btn).setOnClickListener(this);
        }
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        editText.setText(preferences.getString("et",""));
        textView.setText(preferences.getString("tv",""));
    }
    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();
        String str_tv = textView.getText().toString();
        switch (v.getId()){
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.zero:
                if (str.equals("")){
                    editText.setText(str+((Button)v).getText());

                }else if (str.equals("0")){
                    editText.setText(((Button)v).getText());

                }else if (!str.equals("")){
                    if (str.contains("(")){
                        editText.setText(str.substring(0,str.length())+((Button)v).getText());
                        sjhs();
                    }else {
                        if (str.endsWith("π")||str.endsWith("e")){
                            editText.setText(str.substring(0,str.length()-1)+((Button)v).getText());
                        }else {
                            editText.setText(str+((Button)v).getText());
                            Result();
                        }
                    }
                }
                break;
            case R.id.e_btn:
            case R.id.pi_btn:
                if (str.equals("")){
                    editText.setText(str+((Button)v).getText());
                }else {
                    if (str.endsWith(" ")){
                        editText.setText(str+((Button)v).getText());
                        aboutpiore();
                    }else {
                        editText.setText(((Button)v).getText());
                    }

                }

                break;
            case R.id.dian:
                if (str.equals("")){
                    editText.setText("0.");
                }else if (!str.equals("")){
                    if (!str.contains(" ")){
                        if (str.contains(".")){
                            editText.setText(str);
                        }else {
                            editText.setText(str+((Button)v).getText());
                        }

                    }else if (str.contains(" ")){
                        if (String.valueOf(str.charAt(str.length()-1)).equals(" ")){
                            editText.setText(str+"0.");
                        }else if (!String.valueOf(str.charAt(str.length()-1)).equals(" ")){
                            editText.setText(str+".");
                        }
                    }
                }
                break;
            case R.id.baifen:
                if (str.equals("")){
                    editText.setText("");
                }else if (!str.equals("")){
                    if (!str_tv.equals("")){
                        editText.setText(str_tv+"%");
                        textView.setText(String.valueOf(new Double(str_tv)/100));
                    }else if (str_tv.equals("")){
                        if (!str.contains(" ")){
                            editText.setText(str+"%");
                            textView.setText(String.valueOf(new Double(str)/100));
                        }
                    }
                }
                break;
            case R.id.chu:
            case R.id.cheng:
            case R.id.jian:
            case R.id.jia:
                if (str.equals("")){
                    editText.setText("");
                }else if (!str.equals("")){
                    if (str_tv.equals("")){
                        if (!String.valueOf(str.charAt(str.length()-1)).equals(" ")){
                            editText.setText(str +" "+ ((Button)v).getText()+" ");
                        }else if (String.valueOf(str.charAt(str.length()-1)).equals(" ")) {
                            editText.setText(str.substring(0, str.length() - 3) + " " + ((Button) v).getText() + " ");
                        }else if (String.valueOf(str.charAt(str.length()-1)).equals(".")){
                            editText.setText(str);
                        }
                    } else if (!str_tv.equals("")){
                        editText.setText(str_tv+" "+ ((Button)v).getText()+" ");
                    }
                }
                break;
            case R.id.deng:
                if (str.equals("")){
                    editText.setText("");
                }else if (!str.equals("")){
                    if (!str_tv.equals("")){
                        editText.setText(str_tv);
                        textView.setText("");
                    }else if (str_tv.equals("")){
                        if (str.equals("π")){
                            editText.setText(String.valueOf(Math.PI));
                        }else if (str.equals("e")){
                            editText.setText(String.valueOf(Math.E));
                        }else {
                            editText.setText(str);
                            textView.setText("");
                        }
                    }
                }
                break;
            case R.id.backspace:
                if (str.equals("")){
                    editText.setText("");
                    textView.setText("");
                }else if (!String.valueOf(str.charAt(str.length()-1)).equals(" ")){
                    editText.setText(str.substring(0,str.length()-1));
                }else if (String.valueOf(str.charAt(str.length()-1)).equals(" ")){
                    editText.setText(str.substring(0,str.length()-3));
                }
                break;
            case R.id.clean:
                textView.setText("");
                editText.setText("");
                break;
            case R.id.abs_btn:
                if (!str_tv.equals("")){
                    String abs_et = String.valueOf(Math.abs(new Double(str_tv)));
                    if (abs_et.endsWith("0")){
                        editText.setText("|"+str_tv+"|");
                        textView.setText(abs_et.substring(0,abs_et.indexOf(".")));
                    }else {
                        editText.setText("|"+str_tv+"|");
                        textView.setText(abs_et);
                    }
                } else {
                    if (!str.contains(" ")){
                        String abs_et = String.valueOf(Math.abs(new Double(str)));
                        if (abs_et.endsWith("0")){
                            editText.setText("|"+str+"|");
                            textView.setText(abs_et.substring(0,abs_et.indexOf(".")));
                        }else {
                            editText.setText("|"+str+"|");
                            textView.setText(abs_et);
                        }
                    }
                }
                break;
            case R.id.asin_btn:
            case R.id.acos_btn:
            case R.id.atan_btn:
            case R.id.sin_btn:
            case R.id.cos_btn:
            case R.id.tan_btn:
            case R.id.csc_btn:
            case R.id.sec_btn:
            case R.id.cot_btn:
                if (!str_tv.equals("")){
                    editText.setText(str_tv+" "+"×"+" "+((Button)v).getText()+"(");
                    textView.setText("");
                }else {
                    if (str.equals("")){
                        editText.setText(((Button)v).getText()+"(");
                        textView.setText("");
                    }else {

                        if (!str.contains("(")){
                            editText.setText(str+" "+"×"+" "+((Button)v).getText()+"(");
                            textView.setText("");
                        }else {
                            editText.setText(((Button)v).getText()+"(");
                            textView.setText("");
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    public void Result(){
        String str = editText.getText().toString();
        String str1;
        String str2;
        String str3;
        if (!str.equals("")){
            if (str.contains(" ")){
                if (str.contains("π")||str.contains("e")){
                    aboutpiore();
                }else {

                    str1 = str.substring(0,str.indexOf(" "));
                    str2 = str.substring(str.indexOf(" ")+3);
                    str3 = String.valueOf(str.charAt(str.indexOf(" ")+1));
                    double a = new Double(str1);
                    double b = new Double(str2);
                    aandb(a,b,str3,str2);
                }
            }else if (!str.equals(" ")){
                editText.setText(str);
            }
        }
    }
    public void sjhs(){
        String str = editText.getText().toString();
        if (str.substring(0,str.indexOf("(")).contains(" ")){
            String str_sjhs = str.substring(str.indexOf(" ")+3,str.indexOf("("));
            if (str_sjhs.equals("asin")){
                textView.setText(String.valueOf((float)Math.asin(new Double(str.substring(str.indexOf("(")+1,str.length()-2))*Math.PI/180)));
            }
            if (str_sjhs.equals("acos")){
                textView.setText(String.valueOf((float)Math.acos(new Double(str.substring(str.indexOf("(")+1,str.length()-2))*Math.PI/180)));
            }
            if (str_sjhs.equals("atan")){
                textView.setText(String.valueOf((float)Math.atan(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("sin")){
                textView.setText(String.valueOf((float)Math.sin(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("cos")){
                textView.setText(String.valueOf((float)Math.cos(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("tan")){
                textView.setText(String.valueOf((float)Math.tan(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("csc")){
                textView.setText(String.valueOf((float)1/Math.sin(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("sec")){
                textView.setText(String.valueOf((float)1/Math.cos(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("cot")){
                textView.setText(String.valueOf((float)1/Math.tan(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }

        }else {
            String str_sjhs = str.substring(0,str.indexOf("("));

            if (str_sjhs.equals("asin")){
                textView.setText(String.valueOf((float)Math.asin(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("acos")){
                textView.setText(String.valueOf((float)Math.acos(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("atan")){
                textView.setText(String.valueOf((float)Math.atan(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("sin")){
                textView.setText(String.valueOf((float)Math.sin(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("cos")){
                textView.setText(String.valueOf((float)Math.cos(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("tan")){
                textView.setText(String.valueOf((float)Math.tan(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("csc")){
                textView.setText(String.valueOf((float)1/Math.sin(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("sec")){
                textView.setText(String.valueOf((float)1/Math.cos(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
            if (str_sjhs.equals("cot")){
                textView.setText(String.valueOf((float)1/Math.tan(new Double(str.substring(str.indexOf("(")+1))*Math.PI/180)));
            }
        }
    }
    @Override
    protected void onDestroy() {        //保存逻辑
        super.onDestroy();
        String inputText = editText.getText().toString();
        String string = textView.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("et",inputText);
        editor.putString("tv",string);
        editor.apply();
    }
    public void aandb(double a,double b,String str3,String str2){       //加减乘除计算逻辑
        double result;
        if (str3.equals("＋")){
            result = a+b;
            String str_res = String.valueOf(result);
            qcmw(result,str_res);
        }else if (str3.equals("－")){
            result = a-b;
            String str_res = String.valueOf(result);
            qcmw(result,str_res);
        }else if (str3.equals("×")){
            result = a*b;
            String str_res = String.valueOf(result);
            qcmw(result,str_res);
        }else if (str3.equals("÷")){
            if (Integer.parseInt(str2) == 0){
                Toast.makeText(this,"除数不能为零",Toast.LENGTH_SHORT).show();
            }else {
                result = a/b;
                String str_res = String.valueOf(result);
                qcmw(result,str_res);
            }
        }

    }
    public void qcmw(double result,String str_res){         //去除末尾0
        if (String.valueOf(result).endsWith("0")){
            textView.setText(str_res.substring(0,str_res.indexOf(".")));
        }else {

            textView.setText(String.valueOf(result));
        }
    }
    public void aboutpiore(){
        String str = editText.getText().toString();
        String str1;
        String str2;
        String str3;
        str1 = str.substring(0,str.indexOf(" "));
        str2 = str.substring(str.indexOf(" ")+3);
        str3 = String.valueOf(str.charAt(str.indexOf(" ")+1));
        if (str.contains(" ")){
            if (str1.equals("π")){
                double a = Math.PI;
                if (str2.equals("π")){
                    double b = Math.PI;
                    aandb(a,b,str3,str2);
                }else if (str2.equals("e")){
                    double b = Math.E;
                    aandb(a,b,str3,str2);
                }else {
                    double b = new Double(str2);
                    aandb(a,b,str3,str2);
                }
            }else if (str1.equals("e")){
                double a = Math.E;
                if (str2.equals("π")){
                    double b = Math.PI;
                    aandb(a,b,str3,str2);
                }else if (str2.equals("e")){
                    double b = Math.E;
                    aandb(a,b,str3,str2);
                }else {
                    double b = new Double(str2);
                    aandb(a,b,str3,str2);
                }
            }else{
                double a = new Double(str1);
                if (str2.equals("π")){
                    double b = Math.PI;
                    aandb(a,b,str3,str2);
                }else if (str2.equals("e")){
                    double b = Math.E;
                    aandb(a,b,str3,str2);
                }else {
                    double b = new Double(str2);
                    aandb(a,b,str3,str2);
                }
            }
        }
    }
}
