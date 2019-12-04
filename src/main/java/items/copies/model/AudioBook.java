package items.copies.model;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class AudioBook implements BookType {
    private long duration;

    public AudioBook(long durationInMilliseconds) {
        this.setDuration(durationInMilliseconds);
    }

    public long getDuration() {
        return duration;
    }

    public String getDurationString(){
        String formattedDuration = DurationFormatUtils.formatDuration(this.duration, "H:mm:ss");
        return formattedDuration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
