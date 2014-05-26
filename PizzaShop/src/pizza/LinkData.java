package pizza;

import java.net.URL;
import java.util.Date;
 
import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class LinkData {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
     
    @Persistent
    private String user;
     
    @Persistent
    private String password;
    
    @Persistent
    private String address;
    
    @Persistent
    private String mail;
     
    @Persistent
    private Date datetime;
 
    public LinkData(String user, String password, String address, String mail, Date datetime) {
        super();
        this.user = user;
        this.password = password;
        this.address = address;
        this.mail = mail;
        this.datetime = datetime;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getMail() {
        return mail;
    }
 
    public void setMail(String mail) {
        this.mail = mail;
    }
 
 
    public Date getDatetime() {
        return datetime;
    }
 
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
