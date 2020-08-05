package com.example.administrator.arounterdemo.testAcivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;
import com.example.administrator.arounterdemo.entity.Person;
import com.example.administrator.arounterdemo.entity.TestObj;
import com.example.administrator.arounterdemo.testService.HelloService;
import com.example.administrator.arounterdemo.testService.TestService;

import java.lang.ref.WeakReference;

/**
 * https://www.jianshu.com/p/a57dd8c8f10e
 */

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 2;
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PERSON = "person";
    public static final String TEST_OBJ = "testObj";
    private Handler mHandler;
    private AlertDialog mAlertDialog;

    private static final String TAG = "MainActivity";

    static WeakReference<Activity> weakReference ;

    public static WeakReference<Activity> getWeakReference() {
        return weakReference;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weakReference=new WeakReference(this);
        mHandler = new Handler();
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                ARouter.getInstance().build(ARouterConstants.COM_ACTIVITY1).navigation();
                break;
            case R.id.btn_2:
//                ARouterCallBack();//单个监听
                ARouterDownDelegate(); //全局监听
                break;
            case R.id.btn_3:
                Person person = new Person();
                person.age=20;
                person.name="xujun";
                person.sex=0;
                person.weight=60.5f;
                TestObj testObj = new TestObj();
                testObj.name="test";
                ARouter.getInstance().build(ARouterConstants.COM_PARSE_ACTIVITY).withString(NAME,"jun")
                        .withInt(AGE,1).withParcelable(PERSON,person).withObject(TEST_OBJ,testObj)
                .navigation();
                break;
            case R.id.btn_4:
                Postcard postcard = ARouter.getInstance().build(ARouterConstants.COM_ACTIVITY_RESULT);
                LogisticsCenter.completion(postcard);
                Class<?> destination = postcard.getDestination();
                Intent intent = new Intent(this, destination);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btn_5:
                testIProvider();
                break;
            case R.id.btn_6:
                testIProvider2();
                break;
            case R.id.btn_7:
                ARouter.getInstance().build(ARouterConstants.COM_URL).navigation();
                break;
            case R.id.btn_8:
                ARouter.getInstance().build(ARouterConstants.COM_ACTIVITY_INTERCEPTOR).navigation();
                break;

        }
    }

    private void testIProvider2() {
        ARouter.getInstance().navigation(TestService.class).sayHello("xujun2");
    }

    private void testIProvider() {
        HelloService helloService = (HelloService) ARouter.getInstance().build(ARouterConstants.SERVICE_HELLO).navigation();
        String result = helloService.sayHello("xujun");
        Log.i(TAG, "testIProvider: result=" +result);
    }

    /**
     * 测试局部降级监听
     */
    private void ARouterCallBack() {
        ARouter.getInstance()
                .build("/com/hq")
                .navigation(this, new NavCallback() {

                    @Override
                    public void onFound(Postcard postcard) {
                        Log.e(TAG, "onArrival: 找到了 ");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.e(TAG, "onArrival: 找不到了 ");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e(TAG, "onArrival: 跳转完了 ");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.e(TAG, "onArrival: 被拦截了 ");
                    }
                });
    }

    /**
     * 测试全局降级监听，看打印 PretreatmentServiceImpl
     */
    private void ARouterDownDelegate() {
        ARouter.getInstance().build("/com/hq").navigation();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                showDialog();
//
//            }
//        },3000);
    }

    private void showDialog() {
        if(mAlertDialog==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("测试后台");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dissmiss();
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dissmiss();
                }
            });
            mAlertDialog = builder.create();
//            mAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
/*            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                mAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else {
                mAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
            }*/
        }

        mAlertDialog.show();
    }

    private void dissmiss() {
        if(mAlertDialog!=null){
            mAlertDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            Log.i(TAG, "onActivityResult: requestCode=" +requestCode
                    +" resultCode="+resultCode+" data="+data);
            if(data!=null){
                Toast.makeText(MainActivity.this,data.getStringExtra(ActivityResult.RESULT),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(weakReference!=null){
            weakReference.clear();
        }
    }
}
