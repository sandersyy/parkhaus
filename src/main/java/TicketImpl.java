import java.util.Date;
import java.util.Random;

public class TicketImpl implements TicketIF {

    public Date time;
    private String id;


    public TicketImpl(Date time,String name){
        setTime(time);
        this.id = name;
    }

    public TicketImpl(){
        time = new Date();
    }

    public TicketImpl(char id){
        time = new Date();
        this.id = id + "";
    }
    @Override
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public float calcPrice() {


        return 1; //(time.getHours()) * 6.0f + time.getMinutes() * 2.0f ;

    }

    public Date getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
