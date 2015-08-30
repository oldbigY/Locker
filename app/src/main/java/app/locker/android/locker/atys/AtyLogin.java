package app.locker.android.locker.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.locker.android.locker.Config;
import app.locker.android.locker.R;
import app.locker.android.locker.net.Login;

/**
 * Created by Administrator on 2015/8/17.
 * ?????????????????????
 */
public class AtyLogin extends Activity {

    public Button btnLogin;
    public TextView tvToRegister;
    public EditText etUserName,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        btnLogin= (Button) findViewById(R.id.btnLogin);
        tvToRegister= (TextView) findViewById(R.id.btnToRegister);
        etUserName= (EditText) findViewById(R.id.etUserName);
        etPassword= (EditText) findViewById(R.id.etPassword);

        System.out.println("------------begin--------------------");

        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AtyLogin.this,AtyRegister.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户名和密码必须输入
                if (TextUtils.isEmpty(etUserName.getText())){
                    Toast.makeText(AtyLogin.this,R.string.user_name_can_not_be_empty,Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText())){
                    Toast.makeText(AtyLogin.this,R.string.password_can_not_be_empty,Toast.LENGTH_LONG).show();
                    return;
                }
                //设置要传输给服务器的参数
                StringBuffer parmasStr=new StringBuffer();
                parmasStr.append(Config.KEY_ACTION).append("=").append(Config.ACTION_LOGIN).append("&")
                        .append(Config.KEY_USER_NAME).append("=").append(etUserName.getText().toString().trim()).append("&")
                        .append(Config.KEY_PASSWORD).append("=").append(etPassword.getText().toString().trim());

                final ProgressDialog pd=ProgressDialog.show(AtyLogin.this,getText(R.string.connect),getText(R.string.connecting));

                new Login(parmasStr.toString(), new Login.SuccessCallback() {
                    @Override
                    public void onSuccess(String user_rank) {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.success_login, Toast.LENGTH_LONG).show();
                        System.out.println("---Atylogin------------result_user_rank:" + user_rank);
                        Config.cacheUserRank(AtyLogin.this, user_rank);
                        Config.cacheUserName(AtyLogin.this, etUserName.getText().toString());

                        Intent intent=new Intent(AtyLogin.this,AtyOperation.class);
                        intent.putExtra(Config.KEY_USER_RANK,user_rank);
                        intent.putExtra(Config.KEY_USER_NAME,etUserName.getText().toString());
                        startActivity(intent);

                        finish();


                    }
                }, new Login.FailCallback() {
                    @Override
                    public void onFail() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.fail_login, Toast.LENGTH_LONG).show();
                        System.out.println("-----Atylogin----------fail-----------");
                    }
                });
           }
        });


    }
}
