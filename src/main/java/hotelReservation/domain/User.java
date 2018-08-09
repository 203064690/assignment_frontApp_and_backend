package hotelReservation.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Entity
public class User implements Serializable
{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String emailAddress;
    private String password;
    private String recoveryQuestion;
    private String recoveryAnswer;

    public Long getID()
    {
        return ID;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public String getPassword()
    {
        return password;
    }
    public String getRecoveryQuestion(){
        return recoveryQuestion;
    }
    public String getRecoveryAnswer(){
        return recoveryAnswer;
    }

    private User(){}

    public User( Builder builder )
    {
        ID = builder.ID;
        emailAddress = builder.emailAddress;
        password = builder.password;
        recoveryQuestion = builder.recoveryQuestion;
        recoveryAnswer = builder.recoveryAnswer;
    }


    public static class Builder
    {
        private Long ID;
        private String emailAddress;
        private String password;
        private String recoveryQuestion;
        private String recoveryAnswer;

        public Builder( String emailAddress )
        {
            this.emailAddress = emailAddress;
        }
        public Builder ID( Long value )
        {
            this.ID = value;
            return this;
        }
        public Builder password( String value )
        {
            this.password = value;
            return this;
        }
        public Builder recoveryQuestion(String value)
        {
            this.recoveryQuestion = value;
            return this;
        }
        public Builder recoveryAnswer(String value)
        {
            this.recoveryAnswer = value;
            return this;
        }

        public User build()
        {
            return new User( this );
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", recoveryQuestion='" + recoveryQuestion + '\'' +
                ", recoveryAnswer='" + recoveryAnswer + '\'' +
                '}';
    }

}