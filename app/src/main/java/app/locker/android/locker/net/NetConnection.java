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
// * ���ܣ�Httpͨ�ŵĻ��ࡪ�����������������
// */
//public class NetConnection {
//    //��ָ����ͨ�ŵĵ�ַurl��
//    // httpͨ�ŵķ���methed������һ��ö��enum��POST��GET��
//    // ͨ�ŵľ���������ַ����Ĳ����ԣ�kvs
//    // ������ص�״̬��֪ͨ����磺SuccessCallback��FailCallback
//
//    //�������캯�����ڹ��캯�����������ͨ��
//
//    public NetConnection(final String url,final HttpMethed methed,final SuccessCallback successCallback,final FailCallback failCallback,final String ...kvs) {
//        System.out.println("HELLO_NET");
//        //�����첽����AsyncTack�����������������ʱ�Ĳ��������½���
//        AsyncTask<Void,Void,String> asyncTask=new AsyncTask<Void, Void, String>() {
//            @Override
//            //�����������ִ���Ǹ���ʱ�Ĳ���
//            protected String doInBackground(Void... params) {
//                System.out.println("HELLO_BACK");
//                //���紫�ݵĲ���
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
//    // ͨ����ɺ�Ҫ֪ͨ�����ߣ���interfaceʵ��
//    // SuccessCallback�ɹ��ص�������onSuccess�������������˴��ص�����
//    //FailCallbackʧ�ܻص��������ӿڣ���onFail����
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