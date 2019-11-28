package designpattern.tigerchain.com.mvphttpstrategy.mvp;

import designpattern.tigerchain.com.mvphttpstrategy.mvp.domain.User;
import io.reactivex.Observable;

/**
 * @Description MVP 的关联类「也可以单独创建 MVP 就是有点乱」
 */
public interface ILoginContact {

    interface LoginView extends IView{
        //显示进度条
        void showProgress() ;
        //隐藏进度条
        void hideProgress() ;
        //登录成功
        void loadSuccess(String str) ;
        //登录失败
        void loadFailed(String str) ;
        //取得用户名
        String getUserName() ;
        //取得用户密码
        String getUserPass() ;
        //清除输入框
        void clearEditText() ;
        //用户名和密码不能为空
        void editnotNull() ;
    }

    interface LoginPresenter extends IPresenter{
        /**
         * 登录功能
         */
        void login() ;

        /**
         * 清除输入框架内容
         */
        void clear() ;
    }

    interface ILoginModel extends IModel{
        /***
         * 登录的方法，其实这里就是一个抽象策略，至于你使用 retrofit 还是 asynchttpClient 还是 Volley 那是自己的事情
         * @param uName
         * @param uPass
         * @return
         */
        Observable<User> login(String uName, String uPass) ;
    }
}
