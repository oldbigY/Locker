package app.locker.android.locker.net;

import org.json.JSONException;
import org.json.JSONObject;

import app.locker.android.locker.Config;

/**
 * Created by john on 2015/8/18.
 */
public class Login {

    public Login(String parmas, final SuccessCallback successCallback, final FailCallback failCallback) {
        new NetConnection(parmas, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object=new JSONObject(result);
                    System.out.println("----login---resuit---------"+result);
                    System.out.println("---login------------result_status:"+object.getInt(Config.KEY_STATUS));
                    switch (object.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null){
                                System.out.println("-----login----------success-----------");
                                successCallback.onSuccess(object.getString(Config.KEY_USER_RANK));
                            }
                            break;
                        default:

                            if (failCallback!=null){
                                System.out.println("-----login----------fail-----------");
                                failCallback.onFail();
                            }
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback!=null){
                        System.out.println("-----login---error-------fail-----------");
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

    public static interface SuccessCallback{
        void onSuccess(String result);
    }
    public static interface FailCallback{
        void onFail();
    }
}
