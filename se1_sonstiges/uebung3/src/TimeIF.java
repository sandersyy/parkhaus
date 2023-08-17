public interface TimeIF {
    int getHours();
    int getMinutes();
    void setHours(int hours);
    void setMinutes(int minutes);
    void addTime(TimeImpl time);
}
