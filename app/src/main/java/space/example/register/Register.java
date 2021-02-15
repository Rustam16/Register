package space.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class Register extends AppCompatActivity {

    EditText reg_email, reg_pass;
    Button btn_reg;
    TextView login_btn;

    public static Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        reg_email = findViewById(R.id.reg_email);
        reg_pass = findViewById(R.id.reg_pass);

        btn_reg = findViewById(R.id.btn_reg);
        login_btn = findViewById(R.id.login_btn);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String registerEmail = reg_email.getText().toString();
                String registerPassword = reg_pass.getText().toString();

                if (validate(registerEmail, registerPassword)) {
                    credentials = new Credentials(registerEmail, registerPassword);
                    Toast.makeText(Register.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();

                    sharedPreferencesEditor.putString("UserEmil", registerEmail);
                    sharedPreferencesEditor.putString("RegisterPassword", registerPassword);

                    startActivity(new Intent(Register.this, MainActivity.class));
                }
            }
        });
    }

    boolean validate(String email, String password) {
        if (email.isEmpty() || (password.length() < 8)) {
            Toast.makeText(this, "Пожалуйста, введите все данные, пароль должен содержать не менее 8 символов!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login_btn(View view) {
        startActivity( new Intent(Register.this, MainActivity.class));
    }
}