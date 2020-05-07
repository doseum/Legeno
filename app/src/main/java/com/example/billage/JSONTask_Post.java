package com.example.billage;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONTask_Post extends AsyncTask<String,String,String> {

    JSONObject jsonObject=new JSONObject();

    public JSONTask_Post(JSONObject postingdata){
        this.jsonObject=postingdata;
    }

    @Override
    protected String doInBackground(String... urls){
        try{
            HttpURLConnection con=null;
            BufferedReader reader=null;

            try{
                URL url=new URL(urls[0]);//연결url 설정
                con=(HttpURLConnection)url.openConnection();//연결 생성

                con.setRequestMethod("POST");//request method post로 설정
                con.setRequestProperty("Cache-Control","no-cache");//캐시설정
                con.setRequestProperty("User-Agent","Mozilla/5.0(compatible)");
                con.setRequestProperty("Content_type","application/json");//전송 데이터 json
                con.setRequestProperty("Accept","*/*");//response를 text로 설정
                con.setDoOutput(true);//outstream으로 post데이터 전송
                con.setDoInput(true);//inputstream으로 서버로부터 reponse받음
                con.connect();

                //서버로 보낼 스트림생성
                OutputStream os=con.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os));
                Log.d("posting",jsonObject.toString());
                writer.write(jsonObject.toString());
                writer.flush();
                writer.close();//buffer 종료

                int is=con.getResponseCode();

                return Integer.toString(is);//서버로 부터 받은값을 return
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(con!=null) con.disconnect();
                try{
                    if(reader!=null){
                        reader.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        //호출한 class textview에 result set
    }
}