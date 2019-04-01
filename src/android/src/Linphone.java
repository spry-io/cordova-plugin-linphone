package com.sip.linphone;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.linphone.core.TransportType;
import org.linphone.core.Core;
import org.linphone.core.Address;
import org.linphone.core.Factory;
import com.sip.linphone.AccountBuilder;
import com.sip.linphone.LinphoneManager;

import java.util.Timer;

public class Linphone extends CordovaPlugin  {
    public static Context mContext;
    private static Address address;
    public static Timer mTimer;
    public CallbackContext mListenCallback;
    private static String TAG = "sipManager";
    public static LinphoneManager mLinphoneManager;
    public static Core mLinphoneCore;
    public static Linphone mInstance;


    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        mContext = cordova.getActivity().getApplicationContext();
        Factory.instance().setDebugMode(true, "Linphone");
        // You must provide the Android app context as createCore last param !
        Core core = Factory.instance().createCore(null, null, mContext);
        core.start();

        mLinphoneManager = LinphoneManager.createAndStart(mContext);
        mLinphoneCore = mLinphoneManager.getLc();
        mInstance = this;
        mLinphoneManager.useRandomPort(true);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {
        if (action.equals("login")) {
            LOG.d(TAG, "TESTE Logging");
            login(args.getString(0), args.getString(1), args.getString(2), callbackContext);
            return true;
        }else if (action.equals("logout")) {
            //logout(callbackContext);
            return true;
        }
        return false;
    }

    public static synchronized void login(final String username, final String password, final String domain, final CallbackContext callbackContext) {
        try{
            mLinphoneManager.login(username, password, domain, callbackContext);

        }catch (Exception e){
            LOG.d(TAG, e.getMessage());
            callbackContext.error(e.getMessage());
        }
    }
//
//    public static synchronized void logout(final CallbackContext callbackContext) {
//        try{
//            Log.d("logout");
//            LinphoneProxyConfig[] prxCfgs = mLinphoneManager.getLc().getProxyConfigList();
//            final LinphoneProxyConfig proxyCfg = prxCfgs[0];
//            mLinphoneManager.getLc().removeProxyConfig(proxyCfg);
//            Log.d("logout sukses");
//            callbackContext.success();
//        }catch (Exception e){
//            Log.d("Logout error", e.getMessage());
//            callbackContext.error(e.getMessage());
//        }
//    }


}
