@Override
public void onActivityCreate(Bundle savedInstanceState){
        activity.setContentView(R.layout.scn_activity_plugin_detail);
        ScnBackGroundManager.setWindowBackground(activity);
        //先初始化顶部栏
        actionBarView=new DetailActionBarView(activity,this);
        ActivityLifeCycleDispatcher.get().register(activity,actionBarView);
        initBlocksView();
        initData();
        detailEventBusManager=new DetailEventBusManager(context,this,detailViewAdapter,dataManager);
        EventBus.getDefault().register(detailEventBusManager);
        //动态添加阴影view，布局里面添加apk版本会运行错误
        if(BuildConfig.BUILD_PLUGIN){
        FocusView view=new FocusView(activity);
        ViewGroup rootView=activity.findViewById(android.R.id.content);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(0,0);
        rootView.addView(view,params);
        //获焦阴影
        ModuleManagerApi.getSccnPluginApi().initCardFocus(view);
        }
        }