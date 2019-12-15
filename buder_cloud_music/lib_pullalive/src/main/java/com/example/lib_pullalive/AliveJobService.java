package com.example.lib_pullalive;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * 一个轻量的后台job service,利用空闲时间执行一些小事情，提高进程不被回收的概率
 */
@TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
public class AliveJobService extends JobService {
    private static final String TAG = AliveJobService.class.getName();
    private static final int PULL_ALIVE = 0x01;

    private JobScheduler mJobScheduler;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PULL_ALIVE:
                    Log.d(TAG, "pull alive");
                    jobFinished((JobParameters) msg.obj, true);
                    break;
            }
        }
    };

    public static void start(Context context) {
        Intent intent = new Intent(context, AliveJobService.class);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JobInfo job = initJobInfo(startId);
        //提交自己的job到system process中
        if (mJobScheduler.schedule(job) <= 0) {
            Log.d(TAG, "AliveJobService falied.");
        } else {
            Log.d(TAG, "AliveJobService success.");
        }
        return START_STICKY;
    }

    //初始化我们的jobinfo
    private JobInfo initJobInfo(int startId) {
        JobInfo.Builder builder = new JobInfo.Builder(startId,
                new ComponentName(getPackageName(),
                        AliveJobService.class.getName()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setRequiresBatteryNotLow(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
            builder.setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
            builder.setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS,
                    JobInfo.BACKOFF_POLICY_LINEAR);//线性重试方案


        } else {
            builder.setPeriodic(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
        }
        builder.setPersisted(false); //是否持久化
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
        builder.setRequiresCharging(false);
        return builder.build();
    }

    //开始任务
    @Override
    public boolean onStartJob(JobParameters params) {
        mHandler.sendMessage(Message.obtain(mHandler, PULL_ALIVE, params));
        return true;
    }

    //结束任务
    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.removeCallbacksAndMessages(null);
        return false;
    }

}
