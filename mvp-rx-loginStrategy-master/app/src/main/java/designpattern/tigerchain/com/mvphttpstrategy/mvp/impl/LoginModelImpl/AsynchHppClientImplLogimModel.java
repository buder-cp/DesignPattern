package designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginModelImpl;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import designpattern.tigerchain.com.mvphttpstrategy.mvp.ILoginContact;
import designpattern.tigerchain.com.mvphttpstrategy.mvp.domain.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Description 具体策略使用 AsyncHttpClient 来调用登录 API
 */
public class AsynchHppClientImplLogimModel implements ILoginContact.ILoginModel {



    @Override
    public Observable<User> login(final String uName, final String uPass) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(final ObservableEmitter<User> e) throws Exception {

                AsyncHttpClient client = new AsyncHttpClient() ;
                // 这里就是一个请求 没有真正的对接服务器，只是一个演示
                client.get("http://www.baidu.com", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        if(uName.equalsIgnoreCase("TigerChain") && uPass.equals("123")){
                            User user = new User() ;
                            user.setuName(uName);
                            user.setUpass(uPass);
                            e.onNext(user);
                            e.onComplete();
                        }else{
                            e.onNext(null);
                            e.onComplete();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        e.onError(error);
                    }
                }) ;


            }
        });
    }
}
