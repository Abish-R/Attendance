package helix.attendancehelix;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HelixTech-Admin on 3/15/2016.
 */
public class AsyncImageUpload extends AsyncTask<String, Void, String> {
    private static final String TAG = "Inside Thread HotelAdd";
    ProgressDialog progressDialog;
    Context context;
    File image_path;
    String attendance_dt, input_is;
    int count=0;

    public AsyncImageUpload(Context conx, File img_path) {
       context = conx;
       image_path=img_path;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage("Syncing image. Please Wait...");
        progressDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        String result = null,requestURL = null, charset=null;
        requestURL=arg0[0];
        charset= arg0[1];
        attendance_dt=arg0[2];
        input_is=arg0[3];
        count=Integer.parseInt(arg0[4]);
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.addFilePart("picture", image_path);
            List<String> response = multipart.finish();
            //result=multipart.finish();
            System.out.println("SERVER REPLIED:");
            result=response.get(0);
            for (String line : response) {
                System.out.println(line);
                //Toast.makeText(context, "txt " + line, Toast.LEN GTH_SHORT).show();
            }
        } catch (IOException ex) {
            Toast.makeText(context, "Server error while uploading image.!", Toast.LENGTH_LONG).show();
            //((AttendanceRegister)context).SingleButtonAlert("Attendance", "Server error while uploading image.", "Ok","");
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // stuff after posting data
        progressDialog.dismiss();
        //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        if(result.equals("Picture successfully uploaded<br />"))
            ((AttendanceRegister)context).deleteImageAndUploadData(image_path, attendance_dt, input_is,count);
        else
            ((AttendanceRegister)context).deleteImageAndUploadData(image_path, "", "",count);
    }

}
