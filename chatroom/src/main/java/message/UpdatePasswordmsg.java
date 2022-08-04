package message;

public class UpdatePasswordmsg extends Message{
    private int userid;
    private int phonenumber;
    private String password;

    public UpdatePasswordmsg(int userid,int phonenumber,String password){
        this.userid=userid;
        this.phonenumber=phonenumber;
        this.password=password;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UpdatePasswordmsg{" +
                "userid=" + userid +
                ", phonenumber=" + phonenumber +
                ", password='" + password + '\'' +
                '}';
    }
}
