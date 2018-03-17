package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        String string1 = load1();
        String string2 = load2();
        if (!string1.equals(null)){
            editText.setText(string1);
        }
        if (!string2.equals(null)){
            textView.setText(string2);
        }

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
                    editText.setText(str+((Button)v).getText());
                    Result();

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
                        editText.setText(str);
                        textView.setText("");
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
                str1 = str.substring(0,str.indexOf(" "));
                str2 = str.substring(str.indexOf(" ")+3);
                str3 = String.valueOf(str.charAt(str.indexOf(" ")+1));
                double a = new Double(str1);
                double b = new Double(str2);
                double result;
                if (str3.equals("＋")){
                    result = a+b;
                    String str_res = String.valueOf(result);
                    if (String.valueOf(result).endsWith("0")){
                        textView.setText(str_res.substring(0,str_res.indexOf(".")));
                    }else {

                        textView.setText(String.valueOf(result));
                    }
                }else if (str3.equals("－")){
                    result = a-b;
                    String str_res = String.valueOf(result);
                    if (String.valueOf(result).endsWith("0")){
                        textView.setText(str_res.substring(0,str_res.indexOf(".")));
                    }else {

                        textView.setText(String.valueOf(result));
                    }
                }else if (str3.equals("×")){
                    result = a*b;
                    String str_res = String.valueOf(result);
                    if (String.valueOf(result).endsWith("0")){
                        textView.setText(str_res.substring(0,str_res.indexOf(".")));
                    }else {

                        textView.setText(String.valueOf(result));
                    }
                }else if (str3.equals("÷")){
                    if (Integer.parseInt(str2) == 0){
                        Toast.makeText(this,"除数不能为零",Toast.LENGTH_SHORT).show();
                    }else {
                        result = a/b;
                        String str_res = String.valueOf(result);
                        if (String.valueOf(result).endsWith("0")){
                            textView.setText(str_res.substring(0,str_res.indexOf(".")));
                        }else {

                            textView.setText(String.valueOf(result));
                        }
                    }
                }
            }else if (!str.equals(" ")){
                editText.setText(str);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        String string = textView.getText().toString();
        save(inputText);
        saves(string);
    }
    public void save(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data1", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                if (writer!=null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void saves(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data2", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                if (writer!=null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public String load1(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data1");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public String load2(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data2");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
