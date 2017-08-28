package com.example.maja.myapplication.presentation.mvp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maja.myapplication.R;

// MVP - ideja je da sva logika u aktivitiju bude iskljucivo logika koje je vezana za UI a u presenteru sve ostalo
public class LoginActivity extends AppCompatActivity implements LoginContact.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginPresenter presenter;
    private EditText username;
    private EditText password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (username.getText() != null && !username.getText().toString().isEmpty() && password.getText() != null && !password.getText().toString().isEmpty()){
                    presenter.login(username.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    private void initUIComponents() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);
    }

    @Override
    public void loginSuccesfull() {
        // ako se uspesno ulogujes ti ovde napravis skakanje na sledeci aktiviti
    }
}
