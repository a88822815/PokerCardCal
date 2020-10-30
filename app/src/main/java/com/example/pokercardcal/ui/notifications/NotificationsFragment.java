package com.example.pokercardcal.ui.notifications;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pokercardcal.R;
import com.example.pokercardcal.ui.home.PreviewThis;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private List<String> stringList=new ArrayList<>();

    private NotificationsViewModel notificationsViewModel;
    static final String PROVIDER_NAME = "com.example.pokercardcal.PreviewThis";
    static final String URL = "content://" + PROVIDER_NAME ;
    static final Uri CONTENT_URI = Uri.parse(URL);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final TextView editext = root.findViewById(R.id.tptext1);
        final TextView total = root.findViewById(R.id.tptotal);
//        final TextView list = root.findViewById(R.id.tplist);
        final ListView list = root.findViewById(R.id.tplist);

        //输出人名
        String columns[] = new String[] {"_id", "name1", "name2","name3","name4", "score1","score2","score3","score4"};
        Uri myUri = CONTENT_URI;
        Cursor cur = getActivity().getContentResolver().query(myUri, columns,null, null, null );
        if (cur.moveToFirst()) {
            String id = null;
            String name1 = null;String name2 = null;String name3 = null;String name4 = null;
            name1 = cur.getString((cur.getColumnIndex("name1")));
            name2 = cur.getString((cur.getColumnIndex("name2")));
            name3 = cur.getString((cur.getColumnIndex("name3")));
            name4 = cur.getString((cur.getColumnIndex("name4")));
            String names = "              " + name1+ "        "+name2+"        "
                    + name3 + "        " + name4;
            editext.setText(names);
        }

        //输出合计
        String columns1[] = new String[] {"sum(score1)","sum(score2)","sum(score3)","sum(score4)"};
        Cursor cur2 = getActivity().getContentResolver().query(myUri, columns1,null, null, null );
        String sum1 = null; String sum2 = null; String sum3 = null; String sum4 = null;
        if (cur2.moveToFirst())  {
            sum1 = cur2.getString(cur2.getColumnIndex("sum(score1)"));
            sum2 = cur2.getString(cur2.getColumnIndex("sum(score2)"));
            sum3 = cur2.getString(cur2.getColumnIndex("sum(score3)"));
            sum4 = cur2.getString(cur2.getColumnIndex("sum(score4)"));
        }
        String t = "  总计：" + sum1 + stringmulti(sum1)+
                sum2+stringmulti(sum2)+sum3+stringmulti(sum3)+sum4;
        total.setText(t);

        //输出每一把记录
        String strs = "";
        if (cur.moveToFirst()) {
            String id = null;
            String score1 = null;String score2 = null;String score3 = null;String score4 = null;
            do {
                id = cur.getString(cur.getColumnIndex("_id"));
                score1 = cur.getString(cur.getColumnIndex("score1"));
                score2 = cur.getString(cur.getColumnIndex("score2"));
                score3 = cur.getString(cur.getColumnIndex("score3"));
                score4 = cur.getString(cur.getColumnIndex("score4"));
//                System.out.println("score1:" +score1);
                stringList.add("     " +id+stringmulti(id)+score1+stringmulti(score1)+
                        score2+stringmulti(score2)+score3+stringmulti(score3)+score4+"\n");
//                strs =  "     " +id+"        "+score1+stringmulti(" ",10-score1.length())+
//                        score2+stringmulti(" ",10-score2.length())+score3+stringmulti(" ",10-score3.length())+score4+"\n" + strs;
            } while (cur.moveToNext());
        }
        PreviewAdapter adapter=new PreviewAdapter(getActivity(),R.layout.list_item,stringList);
        list.setAdapter(adapter);

        return root;
    }

    //字符串乘法
    public String stringmulti(String x){
        String s  = " ";
        String str="";
        int i = 0;
        int num = 6;
        int y = 0;
        try {
            y = Integer.valueOf(x);

        }catch (Exception e){
            return "            " ;
        }
        if (y < 0){
            i = num -x.length() ;
        }else i = num -x.length();
        i = i*2;

        for(int j=0; j<i ;j++){
            str = str + s;
        }
        return str;
    }
}