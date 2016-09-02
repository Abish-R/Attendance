package helix.attendancehelix;

/**
 * Created by HelixTech-Admin on 3/17/2016.
 */

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class AsyncSyncData extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    private Context context;

    public AsyncSyncData(Context conx){
        context=conx;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        // do stuff before posting data
        progressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage("Syncing data. Please Wait...");
        progressDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        String result=null;
        try{
            // url where the data will be posted
            String postReceiverUrl = "http://ridio.in/employeeattendance/SyncAttendance";
            Log.v("Login Url:", "postURL: " + postReceiverUrl);
            HttpClient httpClient = new DefaultHttpClient();   // HttpClient
            HttpPost httpPost = new HttpPost(postReceiverUrl);	// post header

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(15);
            nameValuePairs.add(new BasicNameValuePair("user_id", arg0[0]));
            nameValuePairs.add(new BasicNameValuePair("email_id", arg0[1]));
            nameValuePairs.add(new BasicNameValuePair("mobile_number", arg0[2]));
            nameValuePairs.add(new BasicNameValuePair("device_id", arg0[3]));
            nameValuePairs.add(new BasicNameValuePair("in_time", arg0[4]));
            nameValuePairs.add(new BasicNameValuePair("out_time", arg0[5]));
            nameValuePairs.add(new BasicNameValuePair("emp_picture_url_in", arg0[6]));
            nameValuePairs.add(new BasicNameValuePair("emp_picture_url_out", arg0[7]));
            nameValuePairs.add(new BasicNameValuePair("latitude_in", arg0[8]));
            nameValuePairs.add(new BasicNameValuePair("longitude_in", arg0[9]));
            nameValuePairs.add(new BasicNameValuePair("latitude_out", arg0[10]));
            nameValuePairs.add(new BasicNameValuePair("longitude_out", arg0[11]));
            nameValuePairs.add(new BasicNameValuePair("location_in", arg0[12]));
            nameValuePairs.add(new BasicNameValuePair("location_out", arg0[13]));
            nameValuePairs.add(new BasicNameValuePair("sync_status", arg0[14]));
            nameValuePairs.add(new BasicNameValuePair("attendance_date", arg0[15]));
            //nameValuePairs.add(new BasicNameValuePair("location_in", arg0[13]));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8);
            httpPost.setEntity(entity);

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(context, "Server error while uploading data.!", Toast.LENGTH_LONG).show();
            //((AttendanceRegister)context).SingleButtonAlert("Attendance", "Server error while uploading data.", "Ok","");
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // do stuff after posting data
        progressDialog.dismiss();
        int validation = 0;
        JSONObject root = null;
        String sync_state = null, message = null,attendance_dt=null;
        try {
            Log.d("Inside Post", "message");
            root = new JSONObject(result);
            validation = root.getInt("response");
            message = root.getString("message");
            sync_state = root.getString("SyncStatus");
            attendance_dt= root.getString("AttendanceDate");

            Log.d(result, sync_state);
            if (validation == 1 && sync_state.equals("U")) {
                ((AttendanceRegister) context).recordDeleteAfterUploadSuccess(attendance_dt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
