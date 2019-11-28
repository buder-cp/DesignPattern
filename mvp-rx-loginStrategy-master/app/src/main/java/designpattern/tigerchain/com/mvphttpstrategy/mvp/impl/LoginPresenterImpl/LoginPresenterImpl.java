package designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginPresenterImpl;

import android.text.TextUtils;

import designpattern.tigerchain.com.mvphttpstrategy.mvp.ILoginContact;
import designpattern.tigerchain.com.mvphttpstrategy.mvp.domain.User;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @Description MVP 中的P ，就相当于策略中Context
 */
public class LoginPresenterImpl implements ILoginContact.LoginPresenter {

    private ILoginContact.ILoginModel iLoginModel ;
    private ILoginContact.LoginView loginView ;

    public LoginPresenterImpl(ILoginContact.LoginView loginView,ILoginContact.ILoginModel iLoginModel){
        this.iLoginModel = iLoginModel ;
        this.loginView = loginView ;
    }

    @Override
    public void login() {

        String uName = loginView.getUserName() ;
        String uPass = loginView.getUserPass() ;

        if(TextUtils.isEmpty(uName) || TextUtils.isEmpty(uPass)){
            loginView.editnotNull();
            return ;
        }
        loginView.showProgress();
        iLoginModel.login(uName,uPass)
//                subscribeOn(Schedulers.io()) 由于 AsyncHttpClient 本身就是在子线程去请求的，所以这里为了演示把这个去掉
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        loginView.loadSuccess("登录成功");

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.loadFailed("用户名或密码错误，登录失败");
                        loginView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        loginView.hideProgress();
                    }
                }) ;
    }

    @Override
    public void clear() {
        loginView.clearEditText();
    }
}
