package designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginModelImpl;

import designpattern.tigerchain.com.mvphttpstrategy.mvp.ILoginContact;
import designpattern.tigerchain.com.mvphttpstrategy.mvp.domain.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Description 具体策略使用 Volley 实现登录功能
 */
public class VolleyImplLoginModel implements ILoginContact.ILoginModel {

    @Override
    public Observable<User> login(final String uName, final String uPass) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(final ObservableEmitter<User> e) throws Exception {

                /***
                 * 这里调用和 Volley 相关的 API 即可
                 */
            }
        });
    }
}
