package com.huy.appquanlyphongtro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private Button btnLogin;
    TextInputLayout textInputUser;
    TextInputLayout textInputPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputUser = findViewById(R.id.text_in_put_user);
        textInputPassWord = findViewById(R.id.text_in_put_pass_word);
        btnLogin = findViewById(R.id.btn_log_in);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUser() || !validatePassWord()){
                    return;
                }
                String input = "UserName: " + textInputUser.getEditText().getText().toString();
                input += "\n";
                input = "PassWord: " + textInputPassWord.getEditText().getText().toString();

                startActivity(new Intent(Login.this, MainActivity.class));
                return;
            }
        });
    }

    private boolean validateUser(){
        String userName = textInputUser.getEditText().getText().toString().trim();
        if (userName.isEmpty()){
            textInputUser.setError("Field can't be empty");
            return false;
        }else if (userName.equals("admin")){
            textInputUser.setError(null);
            return true;
        }else {
            textInputUser.setError("sai tên tk");
            return false;
        }
    }

    private boolean validatePassWord(){
        String passWordInPut = textInputPassWord.getEditText().getText().toString().trim();

        if (passWordInPut.isEmpty()){
            textInputPassWord.setError("Field can't be empty");
            return false;
        }
        else if(passWordInPut.equals("123")){
            textInputPassWord.setError(null);
            return true;
        }
        else {
            textInputPassWord.setError("sai mật khẩu");
            return false;
        }
    }
}