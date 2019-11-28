package designpattern.tigerchain.com.mvphttpstrategy;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import designpattern.tigerchain.com.mvphttpstrategy.mvp.ILoginContact;
import designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginModelImpl.AsynchHppClientImplLogimModel;

import designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginPresenterImpl.LoginPresenterImpl;

public class MainActivity extends AppCompatActivity implements ILoginContact.LoginView, View.OnClickListener{

    private LoginPresenterImpl loginPresenter ;

    private EditText et_uname,et_upass;
    private Button bt_login,bt_clear;

    private ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView() ;
        // 我是调用者，到时候经理说使用 Retorfit 实现请求，
        // 我只需要修改成 new RetorFitImplLoginModel() 即可，无非就是添加一个RetorFitImplLoginModel实现,
        //对现有代码根本不用改变「非常灵活」，小明的痛苦也就解决掉了

        loginPresenter = new LoginPresenterImpl(this, new AsynchHppClientImplLogimModel()) ;
//        loginPresenter = new LoginPresenterImpl(this, new RetorFitImplLoginModel()) ;
//        loginPresenter = new LoginPresenterImpl(this, new VolleyImplLoginModel()) ;

    }

    private void initView() {
        et_uname = this.findViewById(R.id.et_uname) ;
        et_upass = this.findViewById(R.id.et_upass) ;
        bt_login = this.findViewById(R.id.bt_login) ;
        bt_clear = this.findViewById(R.id.bt_clear) ;

        bt_login.setOnClickListener(this);
        bt_clear.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                loginPresenter.login() ;
                break ;
            case R.id.bt_clear:
                loginPresenter.clear();
                break ;
            default:
                break ;
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage("正在登录...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog = null ;
        }
    }

    @Override
    public void loadSuccess(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFailed(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserName() {
        return et_uname.getText().toString().trim();
    }

    @Override
    public String getUserPass() {
        return et_upass.getText().toString().trim();
    }

    @Override
    public void clearEditText() {
        et_uname.setText("");
        et_upass.setText("");
    }

    @Override
    public void editnotNull() {
        Toast.makeText(MainActivity.this,"用户名和密码不能为空!",Toast.LENGTH_SHORT).show();
    }


}
