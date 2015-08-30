package app.locker.android.locker.atys;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowAnimationFrameStats;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.locker.android.locker.Config;
import app.locker.android.locker.R;
import app.locker.android.locker.net.ChangePassword;

/**
 * Created by john on 2015/8/27.
 */
public class AtyChangeuserpassword extends Activity {
    //定义各个控件
    public EditText etOldPassword,etNewPassword,etPasswordAgain;
    public Button btnOldPassword,btnNewPassword,btnCancel,btnTitleCancel;
    public LinearLayout lnloOldPassword,lnloNewPassword,lnlobtnNewPassword;
    public RelativeLayout rtloPasswordAgain;
    public TextView tvTitle;
    //上个界面传过来的值
    public String user_name;
    public String user_rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //修改标题栏
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.aty_change_user_password);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_settings);

        //获取上个界面传回来的信息（用户名与用户身份）
        user_name=getIntent().getStringExtra(Config.KEY_USER_NAME);
        user_rank=getIntent().getStringExtra(Config.KEY_USER_RANK);

        //与控件链接
        etOldPassword= (EditText) findViewById(R.id.etOldPassword);
        etNewPassword= (EditText) findViewById(R.id.etNewPassword);
        etPasswordAgain= (EditText) findViewById(R.id.etPasswordAgain);

        btnOldPassword= (Button) findViewById(R.id.btnOldPassword);
        btnNewPassword= (Button) findViewById(R.id.btnNewPassword);
        btnCancel= (Button) findViewById(R.id.btnCancel);
        btnTitleCancel= (Button) findViewById(R.id.btnTitleCancel);

        lnloOldPassword= (LinearLayout) findViewById(R.id.lnloOldPassword);
        lnloNewPassword= (LinearLayout) findViewById(R.id.lnloNewPassword);
        lnlobtnNewPassword= (LinearLayout) findViewById(R.id.lnlobtnNewPassword);
        rtloPasswordAgain= (RelativeLayout) findViewById(R.id.rtloPasswordAgain);

        tvTitle= (TextView) findViewById(R.id.tvTitle);

        lnlobtnNewPassword.setVisibility(View.GONE);
        lnloNewPassword.setVisibility(View.GONE);
        rtloPasswordAgain.setVisibility(View.GONE);

        tvTitle.setText(R.string.changeuserpassword);

        //设置监听器
        btnOldPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etOldPassword.getText())) {
                    Toast.makeText(AtyChangeuserpassword.this, R.string.complete_the_information, Toast.LENGTH_SHORT).show();
                    return;
                }

                //设置传输参数
                StringBuffer parmasStr=new StringBuffer();
                parmasStr.append(Config.KEY_ACTION).append("=").append(Config.ACTION_CONFIRM_OLD_USER_PASSWORD).append("&")
                        .append(Config.KEY_USER_NAME).append("=").append(user_name).append("&")
                        .append(Config.KEY_OLD_PASSWORD).append("=").append(etOldPassword.getText().toString().trim());
                new ChangePassword(parmasStr.toString(), new ChangePassword.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        lnloOldPassword.setVisibility(View.GONE);
                        btnOldPassword.setVisibility(View.GONE);
                        lnloNewPassword.setVisibility(View.VISIBLE);
                        rtloPasswordAgain.setVisibility(View.VISIBLE);
                        lnlobtnNewPassword.setVisibility(View.VISIBLE);
                        etNewPassword.setText("");
                        etPasswordAgain.setText("");
                    }
                }, new ChangePassword.FailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(AtyChangeuserpassword.this,R.string.password_is_wrong,Toast.LENGTH_SHORT).show();
                        return;
                    }
                });


            }
        });

        btnNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNewPassword.getText())||TextUtils.isEmpty(etPasswordAgain.getText())) {
                    Toast.makeText(AtyChangeuserpassword.this,R.string.complete_the_information,Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!etNewPassword.getText().toString().trim().equals(etPasswordAgain.getText().toString().trim())){
                    Toast.makeText(AtyChangeuserpassword.this,R.string.password_not_the_same,Toast.LENGTH_SHORT).show();
                    return;
                }

                //设置传输参数
                StringBuffer parmasStr=new StringBuffer();
                parmasStr.append(Config.KEY_ACTION).append("=").append(Config.ACTION_CHANGE_USER_PASSWORD).append("&")
                        .append(Config.KEY_USER_NAME).append("=").append(user_name).append("&")
                        .append(Config.KEY_NEW_PASSWORD).append("=").append(etNewPassword.getText().toString().trim());
                System.out.println("----------------parmasStr--------" + parmasStr.toString());
                new ChangePassword(parmasStr.toString(), new ChangePassword.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(AtyChangeuserpassword.this,R.string.success_change_password,Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new ChangePassword.FailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(AtyChangeuserpassword.this,R.string.fail_change_password,Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnloOldPassword.setVisibility(View.VISIBLE);
                btnOldPassword.setVisibility(View.VISIBLE);
                lnloNewPassword.setVisibility(View.GONE);
                rtloPasswordAgain.setVisibility(View.GONE);
                lnlobtnNewPassword.setVisibility(View.GONE);
                etOldPassword.setText("");
            }
        });

        btnTitleCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
