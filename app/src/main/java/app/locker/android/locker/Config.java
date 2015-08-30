package app.locker.android.locker;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by john on 2015/8/18.
 * ���ܣ����������õ��ı�������Config�洢
 */
public class Config {

    public static final String APP_ID="app.locker.android.locker";
    public static final String CHARSET="utf-8";
    //�ӿ�ͨ�ŵ�ַ
//    public static final String SERVER_URL="http://www.baidu.com";
    public static final String SERVER_URL="http://120.25.58.4/index.php";

    //���ݸ��������ļ�ֵ��
    //��
    public static final String ACTION_LOGIN="login";
    public static final String ACTION_REGISTER="register";
    public static final String ACTION_CONFIRM_OLD_LOCKER_PASSWORD="confirm_old_locker_password";
    public static final String ACTION_CHANGE_LOCKER_PASSWORD="change_locker_password";
    public static final String ACTION_CONFIRM_OLD_USER_PASSWORD="confirm_old_user_password";
    public static final String ACTION_CHANGE_USER_PASSWORD="change_user_password";

    //ֵ
    public static final String KEY_ACTION="action";
    public static final String KEY_STATUS="status";
    public static final String KEY_USER_NAME="user_name";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_OLD_PASSWORD="old_password";
    public static final String KEY_NEW_PASSWORD="new_password";
    public static final String KEY_PHONE_NUM="phone_num";
    public static final String KEY_USER_RANK="user_rank";
    public static final String KEY_LOCK_ID_OR_MASTER_NAME="lock_id_or_master_name";
//    public static final String KEY_USER="";
//    public static final String KEY_="";
//    public static final String KEY_="";

    //���������ؽ��
    public static final int RESULT_STATUS_SUCCESS=1;
    public static final int RESULT_STATUS_FAIL=0;
    public static final int RESULT_STATUS_EXISTENT_USER=2;
    public static final int RESULT_STATUS_NONEXISTENT_LOCK_ID=3;
    public static final int RESULT_STATUS_NONEXISTENT_MASTER=4;


    public static final String CHINESE_CONNECT="����";
    public static final String CHINESE_CONNECTING="�����У����Ժ�....";


    //��̬�ķ�����ȡ�û����USER_RANK
    public static String getCachedUserRank(Context context){
        //��ȡ
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_USER_RANK,null);
    }

    //�����û����USRE_RANK
    public static void cacheUserRank(Context context,String user_rank){

        //��ȡEditor
        SharedPreferences.Editor e=context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        //����һ��user_rank
        e.putString(KEY_USER_RANK,user_rank);
        //�ύuser_rank
        e.commit();
    }

    //��̬�ķ�����ȡ�û����USRE_NAME
    public static String getCachedUserName(Context context){
        //��ȡ
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_USER_NAME,null);
    }

    //�����û����USRE_RANK
    public static void cacheUserName(Context context,String user_name){

        //��ȡEditor
        SharedPreferences.Editor e=context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        //����һ��user_rank
        e.putString(KEY_USER_NAME,user_name);
        //�ύuser_rank
        e.commit();
    }
}
