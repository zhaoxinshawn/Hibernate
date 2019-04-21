package model;

public class User  {

    private int uid;
    private String name;
    private String password;

    public int getUid() {
        return uid;
    }

    public void setUid(int id) {
        this.uid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id="+getName()+" name= "+getName()+" password "+getPassword();
    }
}
