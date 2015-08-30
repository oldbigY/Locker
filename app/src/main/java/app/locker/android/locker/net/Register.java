package app.locker.android.locker.net;

import org.json.JSONException;
import org.json.JSONObject;

import app.locker.android.locker.Config;

/**
 * Created by john on 2015/8/24.
 */
public class Register {
    public Register(String parmas, final SuccessCallback successCallback, final FailCallback failCallback) {

        new NetConnection(parmas, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject object=new JSONObject(result);
                    switch (object.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null){
                                //注册成功没有返回值
                                successCallback.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUS_EXISTENT_USER:
                            if (failCallback!=null){
                                //失败，用户名已存在
                                failCallback.onFail(Config.RESULT_STATUS_EXISTENT_USER);
                            }
                            break;
                        case Config.RESULT_STATUS_NONEXISTENT_LOCK_ID:
                            if (failCallback!=null){
                                //失败，锁ID错误
                                failCallback.onFail(Config.RESULT_STATUS_NONEXISTENT_LOCK_ID);
                            }
                            break;
                        case  Config.RESULT_STATUS_NONEXISTENT_MASTER:
                            //失败，主人不存在
                            if (failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_NONEXISTENT_MASTER);
                            }
                            break;
                        default:
                            if (failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback!=null){
                        failCallback.onFail(Config.RESULT_STATUS_FAIL);
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback!=null){
                    failCallback.onFail(Config.RESULT_STATUS_FAIL);
                }
            }
        });
    }

    public static interface SuccessCallback{
        void onSuccess();
    }
    public static interface FailCallback{
        void onFail(int errorCode);
    }
}

