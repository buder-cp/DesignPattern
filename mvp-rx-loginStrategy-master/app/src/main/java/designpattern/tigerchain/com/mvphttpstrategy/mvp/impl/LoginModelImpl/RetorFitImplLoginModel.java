package designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginModelImpl;

import designpattern.tigerchain.com.mvphttpstrategy.mvp.ILoginContact;
import designpattern.tigerchain.com.mvphttpstrategy.mvp.domain.User;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @Description 具体策略 使用 RetorFit 实现登录功能性
 */
public class RetorFitImplLoginModel implements ILoginContact.ILoginModel {


    @Override
    public Observable<User> login(final String uName, final String uPass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://")
                .build();
        ILoginRetorfitApi loginService = retrofit.create(ILoginRetorfitApi.class) ;
        return loginService.login(uName,uPass) ;
    }
}
