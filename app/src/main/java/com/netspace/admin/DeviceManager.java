package com.netspace.admin;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class DeviceManager {

    private final DevicePolicyManager devicePolicyManager;
    private final Context mContext;
    private final ComponentName componentName;

    public DeviceManager(Context context) {
        mContext = context;

        //获取服务
        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(context, DeviceReceiver.class);
    }

    // 激活设备管理器
    public void enableDeviceManager() {
        //判断设备管理器是否激活，未激活则跳转激活
        if (!devicePolicyManager.isAdminActive(componentName)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    mContext.getString(com.netspace.admin.R.string.dm_extra_add_explanation));
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext.getApplicationContext(), "设备管理已激活，请勿重复激活!", Toast.LENGTH_SHORT).show();
        }
    }

    // 取消激活设备管理器
    public void disableDeviceManager() {
        devicePolicyManager.removeActiveAdmin(componentName);
                Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

    }

    // 配置策略
    public void setLockMethod() {
        if (devicePolicyManager.isAdminActive(componentName)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
            devicePolicyManager.setPasswordQuality(componentName,
                    DevicePolicyManager.PASSWORD_QUALITY_NUMERIC);
            mContext.startActivity(intent);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }

    // 设置延时锁屏
    public void lockByTime(long time) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setMaximumTimeToLock(componentName, time);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }

    //恢复出厂设置
    public void wipeData() {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }

    //重置密码
    public void resetPassword(String password) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.resetPassword(password,
                    DevicePolicyManager.RESET_PASSWORD_REQUIRE_ENTRY);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }
    //最大尝试次数
    public void setMaximumFailedPasswordsForWipe(int times) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setMaximumFailedPasswordsForWipe(componentName,times);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }
    public void enableSystemApp(String PackageName) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.enableSystemApp(componentName, PackageName);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }

    public void setApplicationHide(String PackageName) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setApplicationHidden(componentName, PackageName, true);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }

    public void setApplicationUnHide(String PackageName) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setApplicationHidden(componentName, PackageName, false);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }
    public void setPasswordMinimumLength(int length) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setPasswordMinimumLength(componentName, length);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }
    public void setStorageEncryption(boolean isEncrypt) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setStorageEncryption(componentName,isEncrypt);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }
    public void setUninstallBlocked(String packageName , boolean isBlocked) {
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.setUninstallBlocked(componentName,packageName,isBlocked);
        } else {
                    Toast.makeText(mContext.getApplicationContext(), "请先激活设备!", Toast.LENGTH_SHORT).show();

        }
    }






}