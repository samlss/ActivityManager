# ActivityManager
| Author        | Samlss           |
| ------------- |:-------------:|
| Email      | 729717222@qq.com |

<br>

用两种方法注册应用内Activity生命监听

主类为ActivityManager类

一：在Application的onCreate()中可调用：

```Java
@Override
public void onCreate() {
    super.onCreate();

    ActivityManager.init(false); //false为不开启打印
    //以下为可选注册形式
    //1为通过Activity Lifecycle监听形式
    //2为通过Instrumentation hook监听形式

    1.ActivityManager.get().registerActivityLifecycle(this);
    2.ActivityManager.get().hook();
}
```

二：其他地方可调用:
```Java
{
    ActivityManager.get().getActivityLifeCycle().getCurrentActivity();//获取当前应用最顶activity
    ActivityManager.get().getActivityLifeCycle();//获取ActivityLifeCycle对象
}
```

另：
//ViewHelper类为辅助类，可供获取当前activity的所有view、activity内模拟点击；
```Java
{

    List<View> list = ViewHelper.getActivityViews(ActivityManager.get().getActivityLifeCycle().getCurrentActivity());

    float x = 231;
    float y = 312;

    ViewHelper.onActivitySimulateTouch(ActivityManager.get().getActivityLifeCycle().getCurrentActivity(),
            x, y);
}
```    

<br><br>
[我的邮箱729717222@qq.com:cupid:](https://mail.qq.com/)
