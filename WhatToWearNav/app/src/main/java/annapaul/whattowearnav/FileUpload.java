package annapaul.whattowearnav;

/**
 * Created by Anna on 2014-12-03.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FileUpload {
    public FTPClient mFTPClient = null;
    public String ftpUpload1(String srcFilePath,String desFileName,String desDirectory)
    {
        String host="ftp.scrapperia.com";
        String username="android@scrapperia.com";
        String password="android123";
        String image_str = "";
        int port=21;
        mFTPClient = new FTPClient();
        boolean status = false;
        try {

            Bitmap bitmap= BitmapFactory.decodeFile(desFileName);

            //ImageView im = (ImageView) findViewById(R.id.imgBox);
            //im.setImageBitmap(bitmap);

        /*
         * Convert the image to a string
         * */
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
            byte [] byte_arr = stream.toByteArray();
            image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);

        /*
         * Create a name value pair for the image string to be passed to the server
         * */
            ArrayList<NameValuePair> nameValuePairs = new  ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("image",image_str));

            return image_str;
/*
            JSONObject jsonString=new JSONObject();
            try {
                jsonString.put("img", image_str);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
*/
            //new uploadImageToPhp().execute(jsonString);
            /*
            mFTPClient.connect(host, port); // connecting to the host
            mFTPClient.login(username, password); //Authenticate using username and password
            mFTPClient.changeWorkingDirectory(desDirectory); //change directory to that directory where image will be uploaded
            mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
            BufferedInputStream buffIn = null;
            //File file=new File(srcFilePath+desFileName);
            File file=new File(desFileName);
            buffIn = new BufferedInputStream(new FileInputStream(file),8192);
            mFTPClient.enterLocalPassiveMode();
            status = mFTPClient.storeFile(desFileName, buffIn);
            buffIn.close();
            mFTPClient.logout();
            mFTPClient.disconnect();
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image_str;
    }
}