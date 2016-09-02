package helix.attendancehelix;

/**
 * Created by HelixTech-Admin on 3/12/2016.
 */
public class GetSetAttandance {
    int id,auto_update_time;
    String device_id,in_time,out_time,in_pic_location,out_pic_location,in_gps_lat,out_gps_lat,in_gps_lon,out_gps_lon,in_gps_address,out_gps_address,
            upload_state,mobile_time,photo_in_upload_state,photo_out_upload_state,user_id,email,mobile;
    public GetSetAttandance(){}
    public GetSetAttandance(String u_id, String eml, String mob,String d_id, String in_time,String out_time,String in_pic_loc,String out_pic_loc,
                            String ingps_lat,String ingps_lon,String outgps_lat,String outgps_lon,String ingps_address,String outgps_address,String upload_state,String atend_date,
                            String phot_in_up_state,String phot_out_up_state){
        this.user_id = u_id;
        this.email = eml;
        this.mobile = mob;
        this.device_id=d_id;
        this.in_time=in_time;
        this.out_time=out_time;
        this.in_pic_location=in_pic_loc;
        this.out_pic_location=out_pic_loc;
        this.in_gps_lat=ingps_lat;
        this.in_gps_lon=ingps_lon;
        this.out_gps_lat=outgps_lat;
        this.out_gps_lon=outgps_lon;
        this.in_gps_address=ingps_address;
        this.out_gps_address=outgps_address;
        this.upload_state=upload_state;
        this.mobile_time=atend_date;
        this.photo_in_upload_state=phot_in_up_state;
        this.photo_out_upload_state=phot_out_up_state;
    }
    public GetSetAttandance(String u_id, String eml, String mob,String d_id,String in_time,String in_pic_loc,String gps_lat,String gps_lon,
                            String gps_address,String upload_state,String atend_date,String phot_in_up_state){
        this.user_id = u_id;
        this.email = eml;
        this.mobile = mob;
        this.device_id=d_id;
        this.in_time=in_time;
        this.in_pic_location=in_pic_loc;
        this.in_gps_lat=gps_lat;
        this.in_gps_lon=gps_lon;
        this.in_gps_address=gps_address;
        this.upload_state=upload_state;
        this.mobile_time=atend_date;
        this.photo_in_upload_state=phot_in_up_state;
    }
    public GetSetAttandance(String out_time,String out_pic_loc,String gps_lat,String gps_lon,
                            String gps_address,String upload_state,String atend_date,String phot_out_up_state){
        //this.username=uname;
        this.out_time=out_time;
        this.out_pic_location=out_pic_loc;
        this.out_gps_lat=gps_lat;
        this.out_gps_lon=gps_lon;
        this.out_gps_address=gps_address;
        this.upload_state=upload_state;
        this.mobile_time=atend_date;
        this.photo_out_upload_state=phot_out_up_state;
    }

    /** getting ID*/
    public int getID(){
        return this.id;
    }
    /** getting ID*/
    public String getUserId(){
        return this.user_id;
    }
    /** getting ID*/
    public String getEmail(){
        return this.email;
    }
    /** getting ID*/
    public String getMobile(){
        return this.mobile;
    }
    /** getting ID*/
    public String getDeviceId(){
        return this.device_id;
    }
    /** getting ID*/
    public String getInTime(){
        return this.in_time;
    }
    /** getting ID*/
    public String getOutTime(){
        return this.out_time;
    }
    /** getting ID*/
    public String getInPicLocation(){
        return this.in_pic_location;
    }/** getting ID*/
    /** getting ID*/
    public String getOutPicLocation(){
        return this.out_pic_location;
    }/** getting ID*/
    public String getInGpsLat(){
        return this.in_gps_lat;
    }
    public String getOutGpsLat(){
        return this.out_gps_lat;
    }
    /** getting ID*/
    public String getInGpsLon(){
        return this.in_gps_lon;
    }
    /** getting ID*/
    public String getOutGpsLon(){
        return this.out_gps_lon;
    }
    /** getting ID*/
    public String getInAddress(){
        return this.in_gps_address;
    }
    /** getting ID*/
    public String getOutAddress(){
        return this.out_gps_address;
    }
    /** getting ID*/
    public String getUpState(){
        return this.upload_state;
    }
    /** getting ID*/
    public String getAttandenceDate(){
        return this.mobile_time;
    }
    /** getting ID*/
    public String getInPhotoUpState(){
        return this.photo_in_upload_state;
    }
    /** getting ID*/
    public String getOutPhotoUpState(){
        return this.photo_out_upload_state;
    }


    /** setting id*/
    public void setID(int id){
        this.id = id;
    }
    /** setting id*/
    public void setUserId(String u_id){
        this.user_id = u_id;
    }
    /** setting id*/
    public void setEmail(String eml){
        this.email = eml;
    }
    /** setting id*/
    public void setMobile(String mob){
        this.mobile = mob;
    }
    /** setting id*/
    public void setDeviceId(String id){
        this.device_id = id;
    }
    /** setting id*/
    public void setInTime(String in){
        this.in_time = in;
    }
    /** setting id*/
    public void setOutTime(String out){
        this.out_time = out;
    }
    /** setting id*/
    public void setInPicLocation(String pic_loc){
        this.in_pic_location = pic_loc;
    }
    /** setting id*/
    public void setOutPicLocation(String pic_loc){
        this.out_pic_location = pic_loc;
    }
    /** setting id*/
    public void setInGpsLat(String lat){
        this.in_gps_lat = lat;
    }
    /** setting id*/
    public void setInGpsLon(String lon){
        this.in_gps_lon = lon;
    }
    /** setting id*/
    public void setOutGpsLat(String lat){
        this.out_gps_lat = lat;
    }
    /** setting id*/
    public void setOutGpsLon(String lon){
        this.out_gps_lon = lon;
    }
    /** setting id*/
    public void setInAddress(String address){
        this.in_gps_address = address;
    }
    /** setting id*/
    public void setOutAddress(String address){
        this.out_gps_address = address;
    }
    /** setting id*/
    public void setUpState(String state){
        this.upload_state = state;
    }
    /** setting id*/
    public void setAttandenceDate(String mob_time){
        this.mobile_time = mob_time;
    }
    /** setting id*/
    public void setInPhotoUpState(String ste){
        this.photo_in_upload_state = ste;
    }
    /** setting id*/
    public void setOutPhotoUpState(String ste){
        this.photo_out_upload_state = ste;
    }
}
