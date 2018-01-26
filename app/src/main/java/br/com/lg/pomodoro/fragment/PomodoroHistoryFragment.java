package br.com.lg.pomodoro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.lg.pomodoro.view.PomodoroHistoryView;

/**
 * Created by teste on 25/01/2018.
 */

public class PomodoroHistoryFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new PomodoroHistoryView(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();

        ((PomodoroHistoryView)getView()).refresh();
    }
}
