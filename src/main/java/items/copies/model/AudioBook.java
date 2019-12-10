package items.copies.model;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class AudioBook implements BookType {
    private long duration;

    public AudioBook(long durationInMilliseconds) {
        this.setDuration(durationInMilliseconds);
    }

    public AudioBook(String time)
    {
        this.duration = parseString(time);
    }

    public long getDuration() {
        return duration;
    }

    public String getDurationString(){


        long millis = duration % 1000;
        long second = (duration / 1000) % 60;
        long minute = (duration / (1000 * 60)) % 60;
        long hour = (duration / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d:%02d", hour, minute, second);

    return time;
    }

    public void setDuration(String time)
    {

        this.duration = parseString(time);
    }

    private long parseString(String time)
    {
        //ToDo: weryfikacja
        String[] data = time.split(":");

        long hours  = Integer.parseInt(data[0]);
        long minutes = Integer.parseInt(data[1]);
        long seconds = Integer.parseInt(data[2]);

        return  (seconds + 60 * minutes + 3600 * hours) * 1000 ;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
