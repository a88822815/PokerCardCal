package com.example.pokercardcal.ui.home;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
//import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;

//import android.content.Intent;

import com.example.pokercardcal.R;

import java.util.Locale;

public class HomeFragment extends Fragment {

    private Toast mToast = null;
    private boolean isShowingToast = false;
    final Temp temp = new Temp();
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
    private TextToSpeech textToSpeech;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d("MyLog", "Fragment--onCreateView");
        super.onActivityCreated(savedInstanceState);


        final Button button2 = root.findViewById(R.id.button2);
        final Button button3 = root.findViewById(R.id.button3);
        final Button button4 = root.findViewById(R.id.button4);
        final EditText nums = root.findViewById(R.id.nums);
        final EditText editText1 = root.findViewById(R.id.editText1); //第一个人的人名
        final EditText editText2 = root.findViewById(R.id.editText2);
        final EditText editText3 = root.findViewById(R.id.editText3);
        final EditText editText4 = root.findViewById(R.id.editText4);
        final EditText editText11 = root.findViewById(R.id.editText11); //第一个人所剩金额
        final EditText editText12 = root.findViewById(R.id.editText12);
        final EditText editText13 = root.findViewById(R.id.editText13);
        final EditText editText14 = root.findViewById(R.id.editText14);
        final EditText editText21 = root.findViewById(R.id.editText21); //第一个人临时金额
        final EditText editText22 = root.findViewById(R.id.editText22);
        final EditText editText23 = root.findViewById(R.id.editText23);
        final EditText editText24 = root.findViewById(R.id.editText24);
        final ImageView plus1 = root.findViewById(R.id.plus1);
        final ImageView plus2 = root.findViewById(R.id.plus2);
        final ImageView plus3 = root.findViewById(R.id.plus3);
        final ImageView plus4 = root.findViewById(R.id.plus4);
        final ImageView minus1 = root.findViewById(R.id.minus1);
        final ImageView minus2 = root.findViewById(R.id.minus2);
        final ImageView minus3 = root.findViewById(R.id.minus3);
        final ImageView minus4 = root.findViewById(R.id.minus4);

        final Button submit = root.findViewById(R.id.submit);
        final Button clear = root.findViewById(R.id.clearpr);

        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) { //两个人玩，删除3、4
                editText3.setText("");
                editText3.setEnabled(false);
                editText3.setBackgroundColor(0x00ffffff);
                editText4.setText("");
                editText4.setEnabled(false);
                editText4.setBackgroundColor(0x00ffffff);
                editText13.setBackgroundColor(0x00ffffff);
                if(!editText13.getText().toString().trim().isEmpty()) temp.setTotal3(editText13.getText().toString());
                editText13.setText("");
                editText14.setBackgroundColor(0x00ffffff);
                if(!editText14.getText().toString().trim().isEmpty()) temp.setTotal4(editText14.getText().toString());
                editText14.setText("");
                editText23.setBackgroundColor(0x00ffffff);
                if(!editText23.getText().toString().trim().isEmpty()) temp.setTemp3(editText23.getText().toString());
                editText23.setText("");
                editText24.setBackgroundColor(0x00ffffff);
                if(!editText24.getText().toString().trim().isEmpty()) temp.setTemp4(editText24.getText().toString());
                editText24.setText("");
                plus3.setEnabled(false);
                plus3.setVisibility(View.GONE);
                minus3.setEnabled(false);
                minus3.setVisibility(View.GONE);
                plus4.setEnabled(false);
                plus4.setVisibility(View.GONE);
                minus4.setEnabled(false);
                minus4.setVisibility(View.GONE);
            }
        } );
        button3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {                         //3个人玩，显示3删除4
                editText3.setText("卢");
                editText3.setEnabled(true);
                editText3.setBackgroundColor(0xFFA469AE);
                editText4.setText("");
                editText4.setEnabled(false);
                editText4.setBackgroundColor(0x00ffffff);
                editText13.setBackgroundColor(0xFFEEEEEE);
                if(!editText13.getText().toString().trim().isEmpty()) temp.setTotal3(editText13.getText().toString());
                editText13.setText(temp.getTotal3());
                editText14.setBackgroundColor(0x00ffffff);
                if(!editText14.getText().toString().trim().isEmpty()) temp.setTotal4(editText14.getText().toString());
                editText14.setText("");
                editText23.setBackgroundColor(0xFFEEEEEE);
                if(!editText23.getText().toString().trim().isEmpty()) temp.setTemp3(editText23.getText().toString());
                editText23.setText(temp.getTemp3());
                editText24.setBackgroundColor(0x00ffffff);
                if(!editText24.getText().toString().trim().isEmpty()) temp.setTemp4(editText24.getText().toString());
                editText24.setText("");
                plus3.setEnabled(true);
                plus3.setVisibility(View.VISIBLE);
                minus3.setEnabled(true);
                minus3.setVisibility(View.VISIBLE);
                plus4.setEnabled(false);
                plus4.setVisibility(View.GONE);
                minus4.setEnabled(false);
                minus4.setVisibility(View.GONE);
            }
        } );
        button4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {                       //4个人玩，显示3、4
                editText3.setText("卢");
                editText3.setEnabled(true);
                editText3.setBackgroundColor(0xFFA469AE);
                editText4.setText("石");
                editText4.setEnabled(true);
                editText4.setBackgroundColor(0xFFC8EE9C);
                editText13.setBackgroundColor(0xFFEEEEEE);
                if(!editText13.getText().toString().trim().isEmpty()) temp.setTotal3(editText13.getText().toString());
                editText13.setText(temp.getTotal3());
                editText14.setBackgroundColor(0xFFEEEEEE);
                if(!editText14.getText().toString().trim().isEmpty()) temp.setTotal4(editText14.getText().toString());
                editText14.setText(temp.getTotal4());
                editText23.setBackgroundColor(0xFFEEEEEE);
                if(!editText23.getText().toString().trim().isEmpty()) temp.setTemp3(editText23.getText().toString());
                editText23.setText(temp.getTemp3());
                editText24.setBackgroundColor(0xFFEEEEEE);
                if(!editText24.getText().toString().trim().isEmpty()) temp.setTemp4(editText24.getText().toString());
                editText24.setText(temp.getTemp4());
                plus3.setEnabled(true);
                plus3.setVisibility(View.VISIBLE);
                minus3.setEnabled(true);
                minus3.setVisibility(View.VISIBLE);
                plus4.setEnabled(true);
                plus4.setVisibility(View.VISIBLE);
                minus4.setEnabled(true);
                minus4.setVisibility(View.VISIBLE);
            }
        } );


        plus1.setOnClickListener( new View.OnClickListener() { //给第一个人加钱
            @Override
            public void onClick(View v) {
                Plus(nums,editText21);
            }
        } );
        minus1.setOnClickListener( new View.OnClickListener() { //给第一个人减钱
            @Override
            public void onClick(View v) {
                Minus(nums,editText21);
            }
        } );

        plus2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plus(nums,editText22);
            }
        } );
        minus2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Minus(nums,editText22);
            }
        } );

        plus3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plus(nums,editText23);
            }
        } );
        minus3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Minus(nums,editText23);
            }
        } );

        plus4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plus(nums,editText24);
            }
        } );
        minus4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Minus(nums,editText24);
            }
        } );

        //一轮结算
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int e1 = StringToInt(editText21.getText().toString());
                int e2 = StringToInt(editText22.getText().toString());
                int e3 = StringToInt(editText23.getText().toString());
                int e4 = StringToInt(editText24.getText().toString());
//                System.out.println("e1:"+e1+";e2:"+e2+";e3:"+e3+";e4:"+e4);
                if(e1 == 0 && e2 == 0 && e3 == 0 && e4 == 0){
                    showToast("请输入金额");
                }else if (e1+e2+e3+e4 != 0){
                    showToast("错误！重新计算");
                }else {
                    Plus(editText21,editText11);
                    Plus(editText22,editText12);
                    if (!editText3.getText().toString().trim().isEmpty()) {
                        Plus(editText23,editText13);
                        temp.setTemp3("");
                    }
                    if (!editText4.getText().toString().trim().isEmpty()) {
                        Plus(editText24,editText14);
                        temp.setTemp4("");
                    }
                    insertPreviewThis(editText1.getText().toString(), editText2.getText().toString(),
                            editText3.getText().toString(), editText4.getText().toString(),
                            editText21.getText().toString(), editText22.getText().toString(),
                            editText23.getText().toString(), editText24.getText().toString());
                    editText21.setText("");
                    editText22.setText("");
                    editText23.setText("");
                    editText24.setText("");
                    //实例并初始化TTS对象

                    String tts = "卢陈越";
                    showToast("123");
                    textToSpeech =new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {

                            if (status==TextToSpeech.SUCCESS) {
                                //设置朗读语言
                                //这里要要注意一下初始化的步骤，这里是一个异步操作
                                int supported =textToSpeech.setLanguage(Locale.US);
                                if ((supported!=TextToSpeech.LANG_AVAILABLE)&&(supported!=TextToSpeech.LANG_COUNTRY_AVAILABLE)) {
//                                    Toast.makeText(getActivity().this, "不支持当前语言！", Toast.LENGTH_SHORT).show();
                                    showToast("不支持");
                                }
                            }
                        }
                    });
                    //直接这样写会因为初始化没初始好导致这个播报不出来，因为上面的是异步的操作
                    //可以在这个地方打印一个log然后在上面那边打印一个log看谁先执行就知道了。
                    textToSpeech.speak(tts, TextToSpeech.QUEUE_FLUSH, null);
                }

            }
        } );

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder d = new AlertDialog.Builder(getActivity());
                d.setTitle("确定要重新记录吗");
                d.setPositiveButton("是", null);
                //设置正面按钮
                d.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int uri = getActivity().getContentResolver().delete(PreviewThis.CONTENT_URI,null,null);
                    }
                });
                d.setNegativeButton("否", null);
                d.show();
            }
        });


        return root;
    }

    //插入数据库-本局回顾
    private void insertPreviewThis(String name1, String name2, String name3, String name4, String ed21, String ed22, String ed23, String ed24 ) {
        ContentValues values = new ContentValues();
        values.put(PreviewThis.NAME1, name1);
        values.put(PreviewThis.NAME2, name2);
        values.put(PreviewThis.NAME3, name3);
        values.put(PreviewThis.NAME4, name4);
        values.put(PreviewThis.score1, ed21);
        values.put(PreviewThis.score2, ed22);
        values.put(PreviewThis.score3, ed23);
        values.put(PreviewThis.score4, ed24);
        Uri uri = getActivity().getContentResolver().insert(PreviewThis.CONTENT_URI, values);
//        showToast(uri.toString());
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("MyLog", "Fragment--onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyLog", "Fragment--onCreate");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("MyLog", "Fragment--onSaveInstanceState");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("MyLog", "Fragment--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MyLog", "Fragment--onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MyLog", "Fragment--onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MyLog", "Fragment--onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MyLog", "Fragment--onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MyLog", "Fragment--onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyLog", "Fragment--onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MyLog", "Fragment--onDetach");
    }

    //页面跳转，未实现
//    public void Jump(View view){
//        Intent intent = new Intent();
//        intent.setClass(HomeFragment.this, cal.class);
//        startActivity(intent);
//    }/*按钮函数响应*/
}