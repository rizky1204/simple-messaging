package system.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "conversation")
public class Conversation extends Base {

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "RECIEVER")
    private String reciever;

    @Column(name = "MESSAGE")
    private String message;



}
