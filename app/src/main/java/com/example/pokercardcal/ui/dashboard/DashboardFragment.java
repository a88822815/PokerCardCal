package com.example.pokercardcal.ui.dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pokercardcal.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final EditText nums = root.findViewById(R.id.nums);
        final ImageView plus1 = root.findViewById(R.id.plus1);
        final ImageView minus1 = root.findViewById(R.id.minus1);
        final EditText editText1 = root.findViewById(R.id.editText1); //第一个人的人名
        final EditText editText11 = root.findViewById(R.id.editText11); //第一个人所剩金额
        final EditText editText21 = root.findViewById(R.id.editText21); //第一个人临时金额


        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                nums.setText(s);
            }
        });
        return root;
    }

    //加法，参数nums加的数，editText被加的文本框
    public void Plus(EditText nums,EditText editText){
        if (nums.getText().toString().trim().isEmpty()){
            showToast("请输入金额");
//            Toast.makeText(getActivity(),"请输入金额", Toast.LENGTH_SHORT).show();
        }else {
            int num = Integer.valueOf(nums.getText().toString());
            if (editText.getText().toString().trim().isEmpty()){
                editText.setText(num+"");
            }else {
                String ed21 = editText.getText().toString();
                int t = Integer.valueOf(ed21) + num;
                editText.setText(t + "");
            }
        }
    }

    //减法
    public void Minus(EditText nums,EditText editText){
        if (nums.getText().toString().trim().isEmpty()){
            showToast("请输入金额");
//            Toast.makeText(getActivity(),"请输入金额", Toast.LENGTH_SHORT).show();
        }else {
            int num = Integer.valueOf(nums.getText().toString());
            if (editText.getText().toString().trim().isEmpty() ){
                editText.setText(0-num+"");
            }else {
                String ed21 = editText.getText().toString();
                int t = Integer.valueOf(ed21) - num;
                editText.setText(t + "");
            }
        }
    }

    public int StringToInt(String s){
        int i;
        if (s.trim().isEmpty()) i=0;
        else  i =Integer.valueOf(s);
        return i;
    }


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