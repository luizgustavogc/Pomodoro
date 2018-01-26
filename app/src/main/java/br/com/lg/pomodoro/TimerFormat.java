package br.com.lg.pomodoro;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by teste on 25/01/2018.
 */

public class TimerFormat
{

    public static String format(long value)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        return simpleDateFormat.format(new Date(value));
    }
}
