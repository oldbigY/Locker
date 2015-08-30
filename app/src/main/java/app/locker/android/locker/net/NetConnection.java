//package app.locker.android.locker.net;
//
//import android.os.AsyncTask;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import app.locker.android.locker.Config;
//
///**
// * Created by john on 2015/8/18.
// * 功能：Http通信的基类——与服务器交换数据
// */
//public class NetConnection {
//    //需指明：通信的地址url，
//    // http通信的方法methed（创建一个枚举enum：POST和GET）
//    // 通信的具体参数（字符串的参数对）kvs
//    // （将相关的状态）通知给外界：SuccessCallback，FailCallback
//
//    //创建构造函数，在构造函数中完成网络通信
//
//    public NetConnection(final String url,final HttpMethed methed,final SuccessCallback successCallback,final FailCallback failCallback,final String ...kvs) {
//        System.out.println("HELLO_NET");
//        //采用异步任务（AsyncTack）将连接网络这个耗时的操作放入新进程
//        AsyncTask<Void,Void,String> asyncTask=new AsyncTask<Void, Void, String>() {
//            @Override
//            //在这个方法中执行那个耗时的操作
//            protected String doInBackground(Void... params) {
//                System.out.println("HELLO_BACK");
//                //网络传递的参数
//                StringBuffer paramsStr=new StringBuffer();
//                for (int i = 0; i <kvs.length ; i+=2) {
//                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
//                }
//                HttpURLConnection uc = null;
//                try {
//                    switch (methed){
//                        case POST:
//                            uc= (HttpURLConnection) new URL(Config.SERVER_URL).openConnection();
//                            uc.setDoOutput(true);
//                            uc.setReadTimeout(5000);
//                            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(),Config.CHARSET));
//                            bw.write(paramsStr.toString());
//                            bw.flush();
//                            break;
//                        default:
//                            uc= (HttpURLConnection) new URL(url+"?"+paramsStr.toString()).openConnection();
//                            uc.setReadTimeout(5000);
//                            break;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.print("fail net--1");
//                }
//
//                System.out.println("Requst url:" + uc.getURL());
//                System.out.println("Requst data:" + paramsStr);
//
//                try {
//                    BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
//                    String line=null;
//                    StringBuffer result=new StringBuffer();
//                    while ((line=br.readLine())!=null){
//                        result.append(line);
//                    }
//
//                    System.out.println("Result:" + result);
//                    return  result.toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.print("fail net--1");
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
////                super.onPostExecute(result);
//                if (result!=null){
//                    if (successCallback!=null){
//                        successCallback.onSuccess(result);
//                    }
//                }else{
//                    if (failCallback!=null){
//                        failCallback.onFail();
//                    }
//                }
//            }
//        };
//        asyncTask.execute();
//    }
//
//    // 通信完成后，要通知调用者，用interface实现
//    // SuccessCallback成功回调方法，onSuccess方法，服务器端传回的数据
//    //FailCallback失败回调方法（接口），onFail方法
//    public static interface SuccessCallback{
//        void onSuccess(String result);
//    }
//    public static interface FailCallback{
//        void onFail();
//    }
//}
//package  app.locker.android.locker.net;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//
//import android.os.AsyncTask;
//
//import app.locker.android.locker.Config;
//
//
//public class NetConnection {
//
//    public NetConnection(final String url,final HttpMethed method,final SuccessCallback successCallback,final FailCallback failCallback,final String ... kvs) {
//
//        new AsyncTask<Void, Void, String>() {
//
//            @Override
//            protected String doInBackground(Void... arg0) {
//
//                StringBuilder paramsStr = new StringBuilder();
//                for (int i = 0; i < kvs.length; i+=2) {
//                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
//                }
//
//
//                try {
//                    HttpURLConnection uc;
//
//                    switch (method) {
//                        case POST:
//                            uc = (HttpURLConnection) new URL(url).openConnection();
//                            uc.setDoOutput(true);
//                            uc.setReadTimeout(5000);
//                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
//                            bw.write(paramsStr.toString());
//                            bw.flush();
//                            break;
//                        default:
//                            uc = (HttpURLConnection) new URL(url+"?"+paramsStr.toString()).openConnection();
//                            uc.setReadTimeout(5000);
//                            break;
//                    }
//
//                    System.out.println("Request url:"+uc.getURL());
//                    System.out.println("Request data:"+paramsStr);
//
//                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
//                    String line;
//                    StringBuilder result = new StringBuilder();
//                    while((line=br.readLine())!=null){
//                        result.append(line);
//                    }
//
//                    System.out.println("Result:"+result);
//                    return result.toString();
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//
//                if (result!=null) {
//                    if (successCallback!=null) {
//                        successCallback.onSuccess(result);
//                    }
//                }else{
//                    if (failCallback!=null) {
//                        failCallback.onFail();
//                    }
//                }
//
//                super.onPostExecute(result);
//            }
//        }.execute();
//
//    }
//
//
//    public interface SuccessCallback{
//        void onSuccess(String result);
//    }
//
//    public interface FailCallback{
//        void onFail();
//    }
//}
package  app.locker.android.locker.net;


import java.io.IOException;
import android.os.Handler;
import android.os.Message;

import org.apache.http.client.HttpClient;

import app.locker.android.locker.Config;


public class NetConnection {



    public NetConnection(final String parmas,final SuccessCallback successCallback, final FailCallback failCallback) {


         final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==0x123)
                {
                    String result= (String) msg.obj;
                    if (result!=null){
                        if (successCallback!=null){
                            System.out.println("---------------4");
                            successCallback.onSuccess(result);
                            System.out.println("----handle---resuit---------"+result);
                        }
                    }else {
                        if (failCallback!=null){
                            failCallback.onFail();
                        }
                    }

                }
            }
        };

        new MyThread(Config.SERVER_URL,HttpMethed.POST,parmas){
            @Override
            public void run() {

                String result=null;

                switch (methed){
                    case "POST":
                        try {
                            result=doPost();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        try {
                            result=doGet();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                Message msg=new Message();
                msg.what=0x123;
                msg.obj=result;
                handler.sendMessage(msg);
            }
        }.start();
    }


    public interface SuccessCallback{
        void onSuccess(String result);
    }

    public interface FailCallback{
        void onFail();
    }
}