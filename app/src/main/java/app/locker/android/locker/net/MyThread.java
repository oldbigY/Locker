package app.locker.android.locker.net;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import app.locker.android.locker.Config;

/**
 * Created by john on 2015/8/22.
 */
public class MyThread extends Thread {
    String url;
    String methed;
    String parmas;
    public MyThread(String url,HttpMethed methed,String parmas) {
        this.url=url;
        this.methed=methed.toString();
        this.parmas=parmas;
        System.out.println("---------------parmas:-----"+parmas);
    }

    public String  doGet()throws IOException{
        System.out.println("-----------doGet------------");
        //设置url与参数
        url=url;
        System.out.println("------url----"+url);
        //与网络建立连接
        HttpURLConnection uc= (HttpURLConnection) new URL(url).openConnection();
        //设置连接参数
        uc.setRequestMethod("GET");
        uc.setConnectTimeout(5000);
        //利用connect建立实际连接
        System.out.println("----------------Begin_Connect---------");
        uc.connect();
        System.out.println("----------------Success_Connect---------");
        //打开服务器的输入流
        BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
        String line=null;
        StringBuilder result=new StringBuilder();
        while ((line=br.readLine())!=null){
            result.append(line);
        }
        br.close();
        System.out.println("---------------result----------" + result.toString());
        return result.toString();

    }

    public String doPost() throws IOException {

        System.out.println("-----------doPost--------------");
        HttpURLConnection uc = (HttpURLConnection) new URL(url).openConnection();

        uc.setDoOutput(true);
        uc.setDoInput(true);
//        System.out.println("-----------star--------------");
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(),Config.CHARSET));
        bw.write(parmas);
        bw.close();
//        System.out.println("-----------degin--------------");
        BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
        System.out.println("----------success---------------");
        StringBuilder result=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            result.append(line);
        }
        System.out.println("----doPostend----------result--------------"+result.toString());
        return result.toString();
    }

    @Override
    public void run() {
        super.run();
    }
}
