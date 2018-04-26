package aleksandar.tendjer.chatapplication;

/**
 * Created by user on 3/31/2018.
 */

 public class Message {

    private String text;
    private Integer idMessage;
    private Integer idSender;
    private Integer idReceiver;



    public Message(String txt,Integer idMsg, Integer idReceiver, Integer idSender) {
        this.text=txt;
        this.idMessage=idMsg;
        this.idReceiver=idReceiver;
        this.idSender=idSender;
    }
    public Message(String txt,long idReceiver,long idSender)
    {
        this.text=txt;
        this.idReceiver=(int)idReceiver;
        this.idSender=(int)idSender;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }
    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }
    public Integer getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Integer idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




}


