package br.com.lg.pomodoro.model;

import java.util.Date;

/**
 * Created by teste on 25/01/2018.
 */

public class Pomodoro
{
    private long time;
    private long start;
    private boolean finished;
    private Date created;

    public Pomodoro()
    {
        created = new Date();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getCreated()
    {
        return created;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
