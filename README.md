android-BootDemo
========

##android API 3.1的更新：
###1、从Android 3.1开始，系统的包管理器保持跟踪处于停止状态(stoppedstate)的应用程序，控制其从后台进程和其它应用程序启动。 有两种方式可以使app处于stopped state，：
 - 安装了但是从来没有启动过的apk   
 - 被用户在程序管理里面force stop了的apk  


###2、定义了两个新的Intent的Flag：

 - FLAG_INCLUDE_STOPPED_PACKAGES----包括停止的应用程序列表中的 
 - FLAG_EXCLUDE_STOPPED_PACKAGES ----排除停止的应用程序列表中的

 
请注意，  系统向所有的Intent的广播添加了FL​​AG_EXCLUDE_STOPPED_PACKAGES标志。它这样做是为了防止广播无意中的或不必要地开启组件的stoppped应用程序的后台服务。用户可以给自己的应用或者后台服务添加FLAG_INCLUDE_STOPPED_PACKAGES标志以唤醒停止状态的应用，但系统自带的广播intent，用户无法修改，只能接受；注意系统级应用都不是停止状态（/system/app下面的apk都是非 stopped package）。  

##总结
 - >=3.1，安装并运行一次，下次开机的时候才能实现接受广播
 - <3.1，不需要先运行一次，能直接获取并处理系统的开机广播
 - 系统app，系统级的应用程序是可以接收到开机启动广播的。

**注意：**被用户在程序管理里面force stop了的app，重启以后都收不到广播。
