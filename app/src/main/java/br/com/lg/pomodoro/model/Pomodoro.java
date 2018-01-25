package br.com.lg.pomodoro.model;

/**
 * Created by teste on 25/01/2018.
 */

public class Pomodoro
{
    private long time;
    private boolean finished;

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
}
