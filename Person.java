
import java.io.Serializable;

public class Person implements Serializable{
    private int id;
    private String username;
    private String password;

    Person (String username,String password){
        this.username=username;
        this.password=password;
    }
    
    @Override
    public String toString() {
        return "username= "+ this.username +" Password= "+this.password;
    }


    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    
}