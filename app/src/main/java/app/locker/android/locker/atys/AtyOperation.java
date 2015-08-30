package app.locker.android.locker.atys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import app.locker.android.locker.Config;
import app.locker.android.locker.R;
import app.locker.android.locker.data.CustomData;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by john on 2015/8/19.
 */
public class AtyOperation extends Activity implements AdapterView.OnItemClickListener {

    public String user_name,user_rank;
    public ListView lvOperation;

    //数据列表项     public ArrayAdapter<String> adapter;
    //自定义列表项
    public BaseAdapter adapter=new BaseAdapter() {

        //数据
//        private String[] data=new String[]{"解锁","忘锁","拜访留言"};
        public CustomData[] data=new CustomData[]{
                new CustomData("openlock",R.drawable.openlock),
                new CustomData("warm",R.drawable.warm),

        };

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public CustomData getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            listView=findViewById(R.layout.list_cell);
//            tv.setText(getItem(position));
//            tv.setTextSize(20);
//            return tv;

            LinearLayout linearLayout=null;
            if (convertView!=null){
                linearLayout= (LinearLayout) convertView;
            }else {
                linearLayout= (LinearLayout) LayoutInflater.from(AtyOperation.this).inflate(R.layout.list_cell,null);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_operation);

        //获取上个界面传回来的信息（用户名与用户身份）
        user_name=getIntent().getStringExtra(Config.KEY_USER_NAME);
        user_rank=getIntent().getStringExtra(Config.KEY_USER_RANK);

//        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1);
//        adapter=new ArrayAdapter<String>(this,R.layout.list_cell);
        lvOperation= (ListView) findViewById(R.id.lvOperation);

        //配置列表项的适听器
        //数据适听器
        lvOperation.setAdapter(adapter);
        lvOperation.setOnItemClickListener(this);

    }


    //重写onOptionsItemSelected(MenuItem item)方法就可以做菜单响应的操作了
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //按下菜单给出反应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //按下设置键，转到设置界面
            case R.id.itemSettings:
                System.out.println("--------------tosetting-----------");
                Intent intent=new Intent(AtyOperation.this,AtySettings.class);
                intent.putExtra(Config.KEY_USER_RANK,user_rank);
                intent.putExtra(Config.KEY_USER_NAME,user_name);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //监听到键盘键被按下，给出反应
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //当回车键被按下时，询问是否退出
        if(keyCode == KEYCODE_BACK){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.stat_sys_warning)
                    .setTitle(R.string.app_name)
                    .setMessage(R.string.quit_desc)
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}})
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish(); }
                    }).show();

            return true;
        } else{
            return super.onKeyDown(keyCode, event);
        }
    }

    //列表项被按时，根据不同的列表项给出不同的反应
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
