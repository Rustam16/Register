package space.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    //В этом счетчике хранится количество попыток
    private int counter = 5;

    //boolean переменная используемая для проверки
    boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Связываем представления XML с элементами кода Java
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        login = findViewById(R.id.login);
        btnSinUp = findViewById(R.id.btnSinUp);

        // Установил слушатель на текстовое представление SignUp
        btnSinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        // onClick для кнопки и логика на нажатие кнопки входа
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Получение пользовательского ввода
                String inputEmail = email.getText().toString();
                String inputPassword = pass.getText().toString();

                // Проверка  на пустоту логина
                if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast toast = Toast.makeText(MainActivity.this, "Пожалуйста, введите все данные правильно", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                } else {

                    //Проверяем вводимые пользователем данные
                    isValid = validate(inputEmail, inputPassword);

                    /* Проверяем вводимые пользователем данные
                    Если неверно */
                    if (!isValid) {
                        //Decrement the counter
                        counter--;
                        Toast.makeText(MainActivity.this, "Введены неверные учетные данные!", Toast.LENGTH_SHORT).show();
                        //показывает оставшиеся попыти
                        eAttemptsInfo.setText("Осталось попыток :" + counter);

                        //Отключить кнопку входа, если не осталось попыток
                        if (counter == 0) {
                            login.setEnabled(false);

                        } else {
                            // Показывает сообщение об ошибке
                            Toast.makeText(MainActivity.this, "Вы использовали все свои попытки, попробуйте еще раз позже!", Toast.LENGTH_LONG).show();
                        }
                        //если верно
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