package app.locker.android.locker.atys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import app.locker.android.locker.Config;
import app.locker.android.locker.R;
import app.locker.android.locker.data.CustomData;

/**
 * Created by john on 2015/8/27.
 */
public class AtySettings extends Activity implements AdapterView.OnItemClickListener {

    public String user_name,user_rank;
    public ListView lvSettings;
    public Button btnTitleCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.aty_settings);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_settings);

        //获取上个界面传回来的信息（用户名与用户身份）
        user_name=getIntent().getStringExtra(Config.KEY_USER_NAME);
        user_rank=getIntent().getStringExtra(Config.KEY_USER_RANK);

        lvSettings= (ListView) findViewById(R.id.lvSettings);

        lvSettings.setAdapter(adapter);
        lvSettings.setOnItemClickListener(this);

        btnTitleCancel= (Button) findViewById(R.id.btnTitleCancel);
        btnTitleCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public BaseAdapter adapter=new BaseAdapter() {

        CustomData[] datas=new CustomData[]{
                new CustomData("change lock password", android.R.drawable.ic_lock_lock),
                new CustomData("change user information", android.R.drawable.ic_menu_set_as),
        };

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public CustomData getItem(int position) {

            return datas[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout=null;
            if (convertView!=null){
                linearLayout= (LinearLayout) convertView;
            }else {
                linearLayout= (LinearLayout) LayoutInflater.from(AtySettings.this).inflate(R.layout.list_cell,null);
            }

            CustomData data=getItem(position);

            ImageView icon= (ImageView) linearLayout.findViewById(R.id.ivIcon);
            TextView name= (TextView) linearLayout.findViewById(R.id.tvTitle);
            icon.setImageResource(data.iconId);
            name.setText(data.name);
            return linearLayout;
        }


    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CustomData data= (CustomData) adapter.getItem(position);
        Intent intent;
        System.out.println("--------------name--------"+data.getName());
        switch (data.getName()){
            case "change lock password":
                intent=new Intent(AtySettings.this,AtyChangelockpassword.class);
                intent.putExtra(Config.KEY_USER_NAME,user_name);
                intent.putExtra(Config.KEY_USER_RANK,user_rank);
                startActivity(intent);
                break;
            case "change user information":
                intent=new Intent(AtySettings.this,AtyChangeuserpassword.class);
                intent.putExtra(Config.KEY_USER_NAME,user_name);
                intent.putExtra(Config.KEY_USER_RANK,user_rank);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
