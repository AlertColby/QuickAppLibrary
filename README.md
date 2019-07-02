# QuickAppLibrary
个人应用开发代码库

### 一.接入相机、相册，多选、单选图片功能：
  具体源码查看photo目录。
  步骤：
  1. 导入[com.zhihu.android:matisse:0.5.2-beta4](https://github.com/zhihu/Matisse)库；
  2. 检查申请相应权限;
  3. 发起请求，发起请求时注意如果要打开相机，即capture(true)，需要在AndroidManifest.xml中注册FileProvider；
  4. 处理结果；
  5. 如果要混淆，要处理matisse的混淆。
  
  
