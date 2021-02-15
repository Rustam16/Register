package space.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button login;
    private TextView eAttemptsInfo;
    private TextView btnSinUp;

    private int counter = 5;
    boolean isValid = false;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        login = findViewById(R.id.login);
        btnSinUp = findViewById(R.id.btnSinUp);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        if (sharedPreferences !=null){
            String savedUserEmail = sharedPreferences.getString("UserEmail","");
            String  savedPassword = sharedPreferences.getString("Password","");
        }

        btnSinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail, inputPassword;

                inputEmail = email.getText().toString();
                inputPassword = pass.getText().toString();

                if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast toast = Toast.makeText(MainActivity.this, "Пожалуйста, введите все данные правильно", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                } else {

                    isValid = validate(inputEmail, inputPassword);

                    if (!isValid) {
                        counter--;

                        eAttemptsInfo.setText("Осталось попыток :" + counter);
                        Toast.makeText(MainActivity.this, "Введены неверные учетные данные!", Toast.LENGTH_SHORT).show();

                        if (counter == 0) {
                            login.setEnabled(false);
                            Toast.makeText(MainActivity.this, "Вы использовали все свои попытки, попробуйте еще раз позже!", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean validate(String email, String password) {
        if (Register.credentials != null) {
            if (email.equals(Register.credentials.getUserName()) && password.equals(Register.credentials.getuPassword())) {
                return true;
            }
        }
        return false;
    }

}