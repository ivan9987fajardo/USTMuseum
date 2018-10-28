package fromscratch.start.com.ustmuseum;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    String name;
    String info;
    String year;
    String pic;
    String audio;
    @Override
    protected String doInBackground(String... params) {
        String login_url = "http://ivan9987fajardo.000webhostapp.com/USTMuseumQRSearch.php";
            try {
                String id = params[0];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

               // String result="";

                String result[];
                result = new String[5];
                String line="";
                Integer counter=0;
                while((line = bufferedReader.readLine())!= null) {
                    result[counter]= line;
                    //result=line;
                    counter++;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                setNameDB(result[0]);
                setYearDB(result[2]);
                setInfoDB(result[1]);
                setPicDB(result[3]);
                setAudioDB(result[4]);
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    /*@Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }*/

     public void setNameDB(String nameDB){
        this.name = nameDB;
    }
    public void setInfoDB(String infoDB){
        this.info = infoDB;
    }
    public void setYearDB(String yearDB){
        this.year = yearDB;
    }
    public void setPicDB(String picDB){
        this.pic = picDB;
    }
    public void setAudioDB(String audioDB){
        this.audio = audioDB;
    }

    public String getNameDB(){
        return this.name;
    }

    public String getInfoDB(){
        return this.info;
    }

    public String getYearDB(){
        return this.year;
    }

    public String getPicDB(){
        return this.pic;
    }

    public String getAudioDB(){
        return this.audio;
    }
}
