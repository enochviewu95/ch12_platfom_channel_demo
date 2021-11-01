package com.giantinc.ch12_platfom_channel_demo

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.plugin.common.MethodChannel
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant


class MainActivity: FlutterActivity() {

    private val DEVICE_INFO_CHANNEL = "com.giantinc.ch12_platfom_channel_demo/deviceinfo"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        GeneratedPluginRegistrant.registerWith(FlutterEngine(this))
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val messenger = flutterEngine.dartExecutor.binaryMessenger
        val deviceInfoChannel = MethodChannel(messenger,DEVICE_INFO_CHANNEL);
        deviceInfoChannel.setMethodCallHandler { call, result ->
            if(call.method == "getDeviceInfo"){
                val deviceInfo = getDeviceInfo()
                result.success(deviceInfo)
            }else{
                result.notImplemented()
            }
        }
    }

    private fun getDeviceInfo(): String {
        return ("\nDevice: "+Build.DEVICE
                +"\nManufacturer: "+Build.MANUFACTURER
                +"\nModel: "+Build.MODEL
                +"\nProduct: "+Build.PRODUCT
                +"\nVersion Release: "+Build.VERSION.RELEASE
                +"\nVersion SDK: "+Build.VERSION.SDK_INT
                +"\nFingerprint: "+Build.FINGERPRINT)
    }
}
