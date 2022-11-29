package longvtph16016.poly.appquanlyphongtro;

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

                startActivity(new Intent(Login.this, SplashScreen.class));
                return;
            }
        });
    }

    private boolean validateUser(){
        String userName = textInputUser.getEditText().getText().toString().trim();
        if (userName.isEmpty()){
            textInputUser.setError("Field can't be empty");
            return false;
        }else if (userName.length() >15){
            textInputUser.setError("username too long");
            return false;
        }else {
            textInputUser.setError(null);
            return true;
        }
    }

    private boolean validatePassWord(){
        String passWordInPut = textInputPassWord.getEditText().getText().toString().trim();

        if (passWordInPut.isEmpty()){
            textInputPassWord.setError("Field can't be empty");
            return false;
        }else {
            textInputPassWord.setError(null);
            return true;
        }
    }
}