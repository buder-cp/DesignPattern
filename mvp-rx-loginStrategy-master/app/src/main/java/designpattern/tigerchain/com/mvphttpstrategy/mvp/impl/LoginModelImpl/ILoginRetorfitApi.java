package designpattern.tigerchain.com.mvphttpstrategy.mvp.impl.LoginModelImpl;


import designpattern.tigerchain.com.mvphttpstrategy.mvp.domain.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * @Description Retorfit API
 */
public interface ILoginRetorfitApi {

    @GET("/login")
    Observable<User> login( @Field("userName") String userName,
                            @Field("passWord")String passWord) ;
}
