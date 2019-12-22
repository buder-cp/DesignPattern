package com.example.libone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.libbase2.RouterPath;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = RouterPath.ROUTER_LOGIN)
public class LoginActivity extends AppCompatActivity {

    @BindView(R2.id.tv_login_code)
    TextView tvLoginCode;
    @BindView(R2.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R2.id.btn_login_next)
    Button btnLoginNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String getPhone = getIntent().getStringExtra("phone");
        etLoginPhone.setText(getPhone);
    }

    @OnClick(R2.id.btn_login_next)
    void onClick(View view) {
        if (view.getId() == R.id.btn_login_next) {
            Toast.makeText(getApplicationContext(), getString(R.string.login), Toast.LENGTH_SHORT).show();
        }
    }
}
