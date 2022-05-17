package com.netspace.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class MainActivity extends Activity {

    private DeviceManager deviceManager;
    public Button git;
    public Button active_admin;
    public Button inactive_admin;
    //password
    public Button lock_screen_policy;
    public Button reset_password;
    public Button set_delay_lock_screen;
    public Button setMaximumFailedPasswordsForWipe;
    public Button setPasswordMinimumLength;
    //system
    public Button wipe_data;
    public Button enableSystemApp;
    public Button setApplicationHide;
    public Button setApplicationUnHide;
    public Button setUninstallBlocked;
    public Button setUninstallUnBlocked;
    public Button setStorageEncryption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //跳转至项目地址
        git = findViewById(R.id.jump_to_git);
        git.setOnClickListener(view -> {
            Intent url = new Intent();
            url.setAction(Intent.ACTION_VIEW);
            url.setData(Uri.parse("https://github.com/HexoCustomPass/Myipad-Admin"));
            startActivity(url);
        });
        //配置设备管理器
        deviceManager = new DeviceManager(MainActivity.this);
        //激活设备管理器
        active_admin = findViewById(R.id.active_admin);
        active_admin.setOnClickListener(view -> deviceManager.enableDeviceManager());
        //取消激活设备管理器
        inactive_admin = findViewById(R.id.inactive_admin);
        inactive_admin.setOnClickListener(view -> deviceManager.disableDeviceManager());

        //配置解锁策略
        lock_screen_policy = findViewById(R.id.lock_screen_policy);
        lock_screen_policy.setOnClickListener(view -> deviceManager.setLockMethod());
        //重置密码
        reset_password = findViewById(R.id.reset_password);
        reset_password.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("重置密码");
            builder.setMessage("本设备若是睿易t定制平板，在未刷原rom时只能设置一次六位数密码，若想重置密码，请先点击清除锁屏密码");
            final EditText getPassword = new EditText(MainActivity.this);
            builder.setView(getPassword);
            builder.setPositiveButton("重置", (dialog, whichButton) -> deviceManager.resetPassword(getPassword.getText().toString()));
            builder.setCancelable(true);
            builder.show();
        });
        //延时锁屏
        set_delay_lock_screen = findViewById(R.id.set_delay_lock_screen);
        set_delay_lock_screen.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("延时锁屏");
            builder.setMessage("请输入你要预设的时间，单位为秒");
            final EditText getTime = new EditText(MainActivity.this);
            builder.setView(getTime);
            builder.setPositiveButton("设置", (dialog, whichButton) -> deviceManager.lockByTime(Long.parseLong(getTime.getText().toString()) * 100));
            builder.setCancelable(true);
            builder.show();
        });
        //设置尝试次数
        setMaximumFailedPasswordsForWipe = findViewById(R.id.setMaximumFailedPasswordsForWipe);
        setMaximumFailedPasswordsForWipe.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("设置尝试次数");
            builder.setMessage("设置最大错误尝试次数，尝试机会使用完后，可以进行恢复出厂设置");
            final EditText getTimes = new EditText(MainActivity.this);
            builder.setView(getTimes);
            builder.setPositiveButton("设置", (dialog, whichButton) -> deviceManager.setMaximumFailedPasswordsForWipe(Integer.parseInt(getTimes.getText().toString())));
            builder.setCancelable(true);
            builder.show();
        });
        //设置密码长度
        setPasswordMinimumLength = findViewById(R.id.setPasswordMinimumLength);
        setPasswordMinimumLength.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final NumberPicker numberPicker = new NumberPicker(MainActivity.this);
            numberPicker.setMinValue(5);
            numberPicker.setMaxValue(30);
            builder.setTitle("设置密码长度");
            builder.setMessage("设置最小的密码长度，修改密码时，应大于等于设定值");
            builder.setView(numberPicker);
            builder.setPositiveButton("设置", (dialogInterface, i) -> deviceManager.setPasswordMinimumLength(numberPicker.getValue()));
        });
        //清除本机数据
        wipe_data = findViewById(R.id.wipe_data);
        wipe_data.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("清除本机数据");
            builder.setMessage("该操作不可逆，请慎重操作！");
            builder.setPositiveButton("确定", (dialog, whichButton) -> deviceManager.wipeData());
            builder.setCancelable(true);
            builder.show();
        });
        //启用系统应用
        enableSystemApp = findViewById(R.id.enableSystemApp);
        enableSystemApp.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("启用系统应用");
            builder.setMessage("输入被禁用的系统应用包名，点击确定恢复应用");
            final EditText getPackageName = new EditText(MainActivity.this);
            builder.setView(getPackageName);
            builder.setPositiveButton("启用", (dialog, whichButton) -> deviceManager.enableSystemApp(getPackageName.getText().toString()));
            builder.setCancelable(true);
            builder.show();
        });
        //设置应用隐藏
        setApplicationHide = findViewById(R.id.setApplicationHide);
        setApplicationHide.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("设置应用隐藏");
            builder.setMessage("输入要隐藏的应用包名，点击确定隐藏应用");
            final EditText getPackageName = new EditText(MainActivity.this);
            builder.setView(getPackageName);
            builder.setPositiveButton("确定", (dialog, whichButton) -> deviceManager.setApplicationHide(getPackageName.getText().toString()));
            builder.setCancelable(true);
            builder.show();
        });
        //恢复应用可见
        setApplicationUnHide = findViewById(R.id.setApplicationUnHide);
        setApplicationUnHide.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("恢复应用可见");
            builder.setMessage("输入要恢复的应用包名，点击确定恢复应用");
            final EditText getPackageName = new EditText(MainActivity.this);
            builder.setView(getPackageName);
            builder.setPositiveButton("确定", (dialog, whichButton) -> deviceManager.setApplicationUnHide(getPackageName.getText().toString()));
            builder.setCancelable(true);
            builder.show();
        });
        //设置禁止卸载
        setUninstallBlocked = findViewById(R.id.setUninstallBlocked);
        setUninstallBlocked.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("设置禁止卸载");
            builder.setMessage("输入要禁止卸载的应用包名，点击确定即可设置");
            final EditText getPackageName = new EditText(MainActivity.this);
            builder.setView(getPackageName);
            builder.setPositiveButton("确定", (dialog, whichButton) -> deviceManager.setUninstallBlocked(getPackageName.getText().toString(), true));
            builder.setCancelable(true);
            builder.show();
        });
        //设置允许卸载
        setUninstallUnBlocked = findViewById(R.id.setUninstallUnBlocked);
        setUninstallUnBlocked.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("设置允许卸载");
            builder.setMessage("输入要允许卸载的应用包名，点击确定即可设置");
            final EditText getPackageName = new EditText(MainActivity.this);
            builder.setView(getPackageName);
            builder.setPositiveButton("确定", (dialog, whichButton) -> deviceManager.setUninstallBlocked(getPackageName.getText().toString(), false));
            builder.setCancelable(true);
            builder.show();
        });
        //设置存储加密
        setStorageEncryption = findViewById(R.id.setStorageEncryption);
        setStorageEncryption.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("设置存储加密");
            builder.setMessage("设置存储加密后，你的数据等内容都会被加密。");
            final EditText getPackageName = new EditText(MainActivity.this);
            builder.setView(getPackageName);
            builder.setPositiveButton("加密", (dialog, whichButton) -> deviceManager.setStorageEncryption(true));
            builder.setNegativeButton("取消加密", (dialogInterface, i) -> deviceManager.setStorageEncryption(false));
            builder.setCancelable(true);
            builder.show();
        });
    }
}