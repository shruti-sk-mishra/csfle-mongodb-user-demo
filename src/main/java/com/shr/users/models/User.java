package com.shr.users.models;
import com.shr.fle.Encrypted;
import org.bson.BsonBinary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 *
 * @author shruti.mishra
 */
@Document(collection = "users")
public class User {

    @Id
    @Field("_id")
    private String id;

    @Field("user_id")
    private String userId;
    @Field("user_name")
    private String userName;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    @Field("creation_date_time")
    private Date creationDateTime;
    @Field("status")
    private String status;

    /**
     *  Use @Encrypted annotation for the
     *  field to be able to get encrypted
     */
    @Field("password")
    @Encrypted
    private BsonBinary password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return new String(password.getData());
    }

    public void setPassword(String password) {
        this.password = new BsonBinary(password.getBytes());
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

