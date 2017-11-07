
package com.reactlibrary;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Nullable;

public class RNPrivilegesCheckModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNPrivilegesCheckModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNPrivilegesCheck";
  }
  @ReactMethod
  public void checkCamera(final Promise promise) {
    AndPermission.with(this.reactContext)
            .requestCode(300)
            .permission(Permission.CAMERA)
            .callback(new PermissionListener() {
              @Override
              public void onSucceed(int requestCode, List<String> grantedPermissions) {
                // 权限申请成功回调。

                // 这里的requestCode就是申请时设置的requestCode。
                // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
                if(requestCode == 300) {
                  promise.resolve("1");
                }
              }

              @Override
              public void onFailed(int requestCode, List<String> deniedPermissions) {
                // 权限申请失败回调。
                if(requestCode == 300) {
                  promise.reject("0","没有获取权限",null);
                }
              }
            })
            .start();
  }
  @ReactMethod
  public void checkPhotos(final Promise promise) {
    AndPermission.with(this.reactContext)
            .requestCode(400)
            .permission(Permission.STORAGE)
            .callback(new PermissionListener() {
              @Override
              public void onSucceed(int requestCode, List<String> grantedPermissions) {
                // 权限申请成功回调。

                // 这里的requestCode就是申请时设置的requestCode。
                // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
                if(requestCode == 400) {
                  promise.resolve("1");
                }
              }

              @Override
              public void onFailed(int requestCode, List<String> deniedPermissions) {
                // 权限申请失败回调。
                if(requestCode == 400) {
                  promise.reject("0","没有获取权限",null);
                }
              }
            }
  )
            .start();
  }


    @Override
    public @Nullable
    Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<String, Object>();
        constants.put("appName", getApplicationName());
        return constants;
    }


    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getCurrentActivity().getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getCurrentActivity().getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }


}