package app.locker.android.locker.net;

import org.json.JSONException;
import org.json.JSONObject;

import app.locker.android.locker.Config;

/**
 * Created by john on 2015/8/25.
 */
public class ChangePassword {

    public ChangePassword(String parmas, final SuccessCallback successCallback, final FailCallback failCallback) {
        new NetConnection(parmas, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object=new JSONObject(result);
                    switch (object.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null){
                                successCallback.onSuccess();
                            }
                            break;
                        default:
                            if (failCallback!=null){
                                failCallback.onFail();
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback!=null){
                        failCallback.onFail();
                    }
                }

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback!=null){
                    failCallback.onFail();
                }
            }
        });
    }

    public interface SuccessCallback{
        void onSuccess();
    }

    public interface FailCallback{
        void onFail();
    }
}
