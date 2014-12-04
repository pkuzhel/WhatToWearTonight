package annapaul.whattowearnav;

/**
 * Created by Anna on 2014-12-03.
 */
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileUpload {
    public FTPClient mFTPClient = null;
    public boolean ftpUpload1(String srcFilePath,String desFileName,String desDirectory)
    {
        String host="ftp.scrapperia.com";
        String username="android@scrapperia.com";
        String password="android123";
        int port=21;
        mFTPClient = new FTPClient();
        boolean status = false;
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}