package helix.attendancehelix;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by HelixTech-Admin on 3/19/2016.
 */
public class GetDeviceID {
    Context context;
    String deviceId;
    GetDeviceID(Context conx){
        context=conx;
    }
    public String getDeviceId(){
        deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID).toString();
        return deviceId;
    }

}
