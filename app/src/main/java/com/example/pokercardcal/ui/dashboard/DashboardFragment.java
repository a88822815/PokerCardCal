package com.example.pokercardcal.ui.dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pokercardcal.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private Toast mToast = null;
    private boolean isShowingToast = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x001:
                    //更新isShowToast标志位
                    isShowingToast = false;
                    break;
                default:
                    break;
            }
        }
    };
    private ArrayList<HistoryRecord> hhh = new ArrayList<HistoryRecord>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final ListView list = root.findViewById(R.id.phlist);

        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        hhh.add(new HistoryRecord("aaa","123"));
        HistoryAdapter ad = new HistoryAdapter(getActivity(),R.layout.history_item,hhh);
        list.setAdapter(ad);

        return root;
    }


//    public int StringToInt(String s){
//        int i;
//        if (s.trim().isEmpty()) i=0;
//        else  i =Integer.valueOf(s);
//        return i;
//    }


    /**
     * 显示Toast
     *
     * @param msg
     */
    private void showToast(String msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     *
     * @param msg
     * @param duration
     */
    private void showToast(String msg, int duration) {
        //如果当前Toast正在显示，直接返回
        if (isShowingToast) {
            return;
        }
        //当Toast不存在时，通过makeText函数构造Toast对象
        //否则，重用存在的Toast，直接通过setText函数显示内容
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
        //重置标志位
        isShowingToast = true;
        //通过Handler根据Duration的值发送延时消息更新isShowingToast标志位
        //Toast.LENGTH_LONG:3500ms
        //Toast.LENGTH_SHORT:2000ms
        int delayTime = mToast.getDuration() == Toast.LENGTH_SHORT ? 2000 : 3500;
        mHandler.sendEmptyMessageDelayed(0x001, delayTime);
    }

    /**
     * 取消Toast显示
     */
    private void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}