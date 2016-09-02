package helix.attendancehelix;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by HelixTech-Admin on 3/26/2016.
 */
public class PunchInAndOut extends AppCompatActivity implements View.OnClickListener {
    DatabaseDateTimeHandler dbdth;// = new DatabaseDateTimeHandler(this);
    //AttendanceRegister ar = new AttendanceRegister(this);
    SimpleDateFormat sdf,sdf1;
    Calendar c,cc;
    Button in_button,out_button,sync;
    String last_record, punch_date,out_punching_date;
    boolean out_allow=false,in_allow=false;
    private static String last_value="";
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.punch_in_out);
        in_button=(Button)findViewById(R.id.in_button);
        out_button=(Button)findViewById(R.id.out_button);
        sync=(Button)findViewById(R.id.sync);
        out_button.setOnClickListener(this);
        in_button.setOnClickListener(this);
        sync.setOnClickListener(this);

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c = Calendar.getInstance();
    }

    @Override
    protected void onStart(){
        dbdth = new DatabaseDateTimeHandler(this);
        super.onStart();
        if(checkAutoTimeEnablity()==0)
            SingleButtonAlert("Attendance", "You Disabled the auto time & auto time zone checkbox in Settings.", "Change", "auto_time");
        else
            punch_date = sdf.format(c.getTime());
        checkViewVisibility();
        changeButtonColor();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.in_button:
                if(checkAutoTimeEnablity()==0)
                    SingleButtonAlert("Attendance", "You Disabled the auto time & auto time zone checkbox in Settings.", "Change", "auto_time");
                else if(in_allow)
                    callAttendancePage("intime");
                else
                    SingleButtonAlert("Attendance","Attendance completed today.\nWait for next day.","Ok","");
                break;
            case R.id.out_button:
                if(out_allow){
                    if(checkAutoTimeEnablity()==0)
                        SingleButtonAlert("Attendance", "You Disabled the auto time & auto time zone checkbox in Settings.", "Change", "auto_time");
                    else if(compareDates()==1) {
                        callAttendancePage("outtime");
                    }else{
                        SingleButtonAlert("Attendance","Out punching time is expired\nNow punch your In Time","Ok","");
                        dbdth.updateAttendance(new GetSetAttandance("1999-12-31 00:00:00", null, "0", "0",
                                null, "N", last_record, "N"));
                        onStart();
                    }}
                else
                    callAttendancePage("outtime");
                break;
            case R.id.sync:
//                Intent in = new Intent(this,GeoPointDifferenceSample.class);
//                startActivity(in);
                List<GetSetAttandance> image_list = dbdth.getImageName();
                if(image_list.size()>0)
                    callAttendancePage("sync");
                else
                    SingleButtonAlert("Attendance","All data Synced.\nNo more data to Sync.", "Ok","");
                break;
        }
    }

    int checkAutoTimeEnablity(){
        int auto_time_check=android.provider.Settings.Global.getInt(getContentResolver(), android.provider.Settings.Global.AUTO_TIME, 0);
        int auto_time_zone_check=android.provider.Settings.Global.getInt(getContentResolver(), android.provider.Settings.Global.AUTO_TIME_ZONE, 0);
        if(auto_time_check==1 && auto_time_zone_check==1)
            return 1;
        else
            return 0;
        }

    private void checkViewVisibility(){
        if(dbdth.getCountOfRow()<1){
//            in_button.setVisibility(View.VISIBLE);
//            out_button.setVisibility(View.INVISIBLE);
            out_button.setEnabled(false);
            in_button.setEnabled(true);
            in_allow=true;
        }
        else{
            last_record = dbdth.getLastRecord();
            int outPunchCount=dbdth.checkOutTimePunchedOrNot(last_record);
            if(outPunchCount>0) {
                punch_date = sdf.format(c.getTime());
                if(punch_date.equals(last_record)) {
//                    in_button.setVisibility(View.INVISIBLE);
//                    out_button.setVisibility(View.VISIBLE);
                    in_button.setEnabled(false);
                    out_button.setEnabled(true);
                    out_punching_date=punch_date;
                    out_allow=false;
                    Log.d("Punch time & ", "last record r same");
                }else{
                    last_value = addOneDate(last_record)+" 11:59:59";
//                    in_button.setVisibility(View.INVISIBLE);
//                    out_button.setVisibility(View.VISIBLE);
                    in_button.setEnabled(false);
                    out_button.setEnabled(true);
                    out_allow=true;
                }
            }
            else if(last_record.equals(punch_date) && outPunchCount==0){
//                in_button.setVisibility(View.VISIBLE);
//                out_button.setVisibility(View.INVISIBLE);
                out_button.setEnabled(false);
                in_button.setEnabled(true);
                in_allow=false;
                Log.d("Wait for next day", "Ok");
            } else if (dbdth.checkOutTimePunchedOrNot(last_record)==0) {
//                in_button.setVisibility(View.VISIBLE);
//                out_button.setVisibility(View.INVISIBLE);
                out_button.setEnabled(false);
                in_button.setEnabled(true);
                in_allow=true;
                Log.d("Today Punch Attendance", "Ok");
            }
        }
    }

    private String addOneDate(String date){
        Calendar ccc = Calendar.getInstance();
        try {
            ccc.setTime(sdf.parse(date));
            ccc.add(Calendar.DATE, 1);
            return sdf.format(ccc.getTime());
        }catch (Exception e){
            e.toString();
        }
        return "";
    }

    private void callAttendancePage(String time){
        Intent intent = new Intent(this, AttendanceRegister.class);
        intent.putExtra("punch", time);
        if(time.equals("outtime"))
            intent.putExtra("punching_date", out_punching_date);

        startActivity(intent);
    }

    public void changeButtonColor(){
        if(dbdth.getImageName().size()>0) {
            sync.setBackgroundResource(R.drawable.press_effect_rect1);
            sync.setEnabled(true);
        }
        else {
            sync.setBackgroundResource(R.drawable.press_effect_rect);
            sync.setEnabled(false);
        }
    }

    private long compareDates(){
        sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cc= Calendar.getInstance();
        long frm_to_diff=0;
        Date border_val,nowdt;
        double return_value=0.0;
        try {
            border_val = sdf1.parse(last_value);
            nowdt = sdf1.parse(sdf1.format(cc.getTime()));
            border_val= sdf1.parse(sdf1.format(border_val));
            nowdt= sdf1.parse(sdf1.format(nowdt));
            frm_to_diff= nowdt.getTime() - border_val.getTime();

            //return_value = frm_to_diff / 86400000;
            if(frm_to_diff>0) {
                out_punching_date=punch_date;
                //out_button.setText(return_value+"");
                return 0;
            }
            else {
                out_punching_date=last_record;
                return 1;
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void openDateTimeSettings(){
        Intent callGPSSettingIntent = new Intent(Settings.ACTION_DATE_SETTINGS);
        startActivity(callGPSSettingIntent);
    }

    /**Alert with single button*/
    public void SingleButtonAlert(String title,String message,String but_txt, final String action){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(but_txt, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(action.equals("auto_time"))
                    openDateTimeSettings();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
