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

import java.util.Map;
import java.util.jar.Attributes;

public class Register extends AppCompatActivity {

    /* Определяем элементы пользовательского интерфейса */
    EditText reg_email, reg_pass;
    Button btn_reg;
    TextView login_btn;

    /* Создаем объект класса Credentials */
    public static Credentials credentials;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        /* Связываем элементы пользовательского интерфейса с элементами макета XML */
        reg_email = findViewById(R.id.reg_email);
        reg_pass = findViewById(R.id.reg_pass);

        btn_reg = findViewById(R.id.btn_reg);
        login_btn = findViewById(R.id.login_btn);

        // A listener for click events on the Register Button
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Получаю данные из полей
                String registerEmail = reg_email.getText().toString();
                String registerPassword = reg_pass.getText().toString();

                // Проверяем правильность полей
                if (validate(registerEmail, registerPassword)) {
                    // Добавляем учетные данные в нашу базу данных
                    credentials = new Credentials(registerEmail, registerPassword);

                    startActivity(new Intent(Register.this, MainActivity.class));
                    Toast.makeText(Register.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //Функция для проверки вводимых учетных данных от пользователя
    boolean validate(String email, String password) {
        //Проверяем, пусто ли имя и длина поля пароля 8 символов
        if (email.isEmpty() || (password.length() < 8)) {
            Toast.makeText(this, "Пожалуйста, введите все данные, пароль должен содержать не менее 8 символов!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //onClick переход в MainActivity если у вас уже есть аккаунт
    public void login_btn(View view) {
        startActivity(new Intent(Register.this, MainActivity.class));
    }
}