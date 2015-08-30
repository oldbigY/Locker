package app.locker.android.locker.atys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import app.locker.android.locker.Config;
import app.locker.android.locker.R;
import app.locker.android.locker.net.Register;

/**
 * Created by Administrator on 2015/8/18.
 * 功能：连接注册界面，实现用户注册
 */
public class AtyRegister extends Activity {

    public EditText etUsername,etPassword,etPasswordAgain,etPhonenumber,etIDCode;
    public RadioGroup rgChoiceRank;
    public Button btnRegister,btnCancel;
    public RadioButton rbtnMaster,rbtnFamily;
    public String user_rank=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_registration);
        //控件链接
        etIDCode= (EditText) findViewById(R.id.etIDCode);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etPasswordAgain= (EditText) findViewById(R.id.etPasswordAgain);
        etPhonenumber= (EditText) findViewById(R.id.etPhonenumber);
        etUsername= (EditText) findViewById(R.id.etUsername);

        rgChoiceRank= (RadioGroup) findViewById(R.id.rgChoiceRank);

        btnRegister= (Button) findViewById(R.id.btnRegister);
        btnCancel= (Button) findViewById(R.id.btnCancel);

        rbtnFamily= (RadioButton) findViewById(R.id.rbtnFamily);
        rbtnMaster= (RadioButton) findViewById(R.id.rbtnMaster);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //信息必须填写完整
                if (TextUtils.isEmpty(etUsername.getText())||TextUtils.isEmpty(etPassword.getText())
                        ||TextUtils.isEmpty(etPasswordAgain.getText())||TextUtils.isEmpty(etIDCode.getText())
                        ||TextUtils.isEmpty(etPhonenumber.getText())||user_rank==null){
                    Toast.makeText(AtyRegister.this,R.string.complete_the_information,Toast.LENGTH_SHORT).show();
                    return;
                }
                //两次输入的密码必须一致
                if (!etPassword.getText().toString().equals(etPasswordAgain.getText().toString())){
                    Toast.makeText(AtyRegister.this,R.string.password_not_the_same,Toast.LENGTH_LONG).show();
                    return;
                }

                //设置要传输的参数
                StringBuffer parmasStr=new StringBuffer();
                parmasStr.append(Config.KEY_ACTION).append("=").append(Config.ACTION_REGISTER).append("&")
                        .append(Config.KEY_USER_NAME).append("=").append(etUsername.getText().toString().trim()).append("&")
                        .append(Config.KEY_PASSWORD).append("=").append(etPassword.getText().toString().trim()).append("&")
                        .append(Config.KEY_PHONE_NUM).append("=").append(etPhonenumber.getText().toString().trim()).append("&")
                        .append(Config.KEY_LOCK_ID_OR_MASTER_NAME).append("=").append(etIDCode.getText().toString().trim()).append("&")
                        .append(Config.KEY_USER_RANK).append("=").append(user_rank);
                System.out.println("------------paprmasStr--------"+parmasStr.toString());

                final ProgressDialog pd=ProgressDialog.show(AtyRegister.this,getText(R.string.register),getText(R.string.registering));
                new Register(parmasStr.toString(), new Register.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        Toast.makeText(AtyRegister.this,R.string.success_register, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AtyRegister.this,AtyLogin.class);
                        startActivity(intent);

                        finish();
                    }
                }, new Register.FailCallback() {
                    @Override
                    public void onFail(int errorCode) {
                        pd.dismiss();
                        switch (errorCode){
                            case Config.RESULT_STATUS_EXISTENT_USER:
                                Toast.makeText(AtyRegister.this,R.string.fail_register_the_user_name_has_be_used, Toast.LENGTH_LONG).show();
                                break;
                            case Config.RESULT_STATUS_NONEXISTENT_LOCK_ID:
                                Toast.makeText(AtyRegister.this, R.string.fail_register_the_lock_id_is_wrong, Toast.LENGTH_LONG).show();
                                break;
                            case Config.RESULT_STATUS_NONEXISTENT_MASTER:
                                Toast.makeText(AtyRegister.this,R.string.fail_register_has_not_the_master, Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(AtyRegister.this, R.string.fail_register, Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
            }
        });
        //按取消键，返回登录界面
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AtyRegister.this,AtyLogin.class);
                startActivity(intent);

                finish();
            }
        });

        rgChoiceRank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtnMaster) {
                    user_rank = "master";
                    AlertDialog.Builder builder = new AlertDialog.Builder(AtyRegister.this);
                    builder.setIcon(android.R.drawable.btn_star_big_on).setTitle(getText(R.string.attention)).setMessage(R.string.user_rank_is_master)
                            .setPositiveButton(R.string.confirm, new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //事件响应
                                }
                            }).create().show();
                }
                if (checkedId == R.id.rbtnFamily) {
                    user_rank = "family";
                    AlertDialog.Builder builder = new AlertDialog.Builder(AtyRegister.this);
                    builder.setIcon(android.R.drawable.btn_star_big_on)
                            .setTitle(R.string.attention).setMessage(R.string.user_rank_is_family)
                            .setPositiveButton(R.string.confirm, new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //事件响应
                                }
                            }).create().show();
                }
            }
        });


    }
}
