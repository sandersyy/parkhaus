import java.sql.Time;

public class TimeImpl implements TimeIF {
    private int hours;
    private int minutes;

    public TimeImpl(int hours, int minutes){
        this.hours = hours;

        this.minutes = minutes;
    }
    public TimeImpl(){
        this.hours = 0;

        this.minutes = 0;
    }

    @Override
    public int getHours() {
        return this.hours;
    }

    @Override
    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

 /*
TimeImpl time = new TimeImpl(3, 150);
TimeImpl time2 = new TimeImpl(1, 1);
TimeImpl time3 = new TimeImpl(6, 31);
    */
    @Override
    public void addTime(TimeImpl time) {
        this.hours = this.hours +time.hours+(time.minutes+this.minutes)/60;
        this.minutes =(this.minutes+ time.minutes)%60;

    }
}
