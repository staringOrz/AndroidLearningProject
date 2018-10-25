package learnningdemo.arraylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  适配器Adapter学习（arrayAdapter、simpleAdapter）
 *  监听器
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener,OnScrollListener {

    private  ListView arrayListView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<HashMap<String,Object>> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayListView=findViewById(R.id.listview);
        /**
         * 1.新建适配器
         * 数组适配器参数（上下文，数据视图，数据源）
         * 简单适配器SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
         * 参数： （上下文，数据源（list里面map,代表一个item）,）
         * 2.绑定数据源
         * 3.视图加载适配器
         */
        dataList=new ArrayList<>();
        String arr[]={"数据1","数据2","数据3","数据4","数据5","数据6"};
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arr);
        simpleAdapter=new SimpleAdapter(this,getDate(),R.layout.item1,new String[]{"pic","text"},new int[]{R.id.pic,R.id.text});
        //arrayListView.setAdapter(arrayAdapter);
        arrayListView.setAdapter(simpleAdapter);
        arrayListView.setOnItemClickListener(this);
        arrayListView.setOnScrollListener(this);
    }

    public List<HashMap<String,Object>> getDate() {
        for(int i=0;i<20;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","demo"+i);
            dataList.add(map);
        }
        return dataList;
    }



    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

        switch(i){
            case SCROLL_STATE_FLING:
                Log.i("Main", "onScrollStateChanged: "+"用户用力划了一下屏幕，手指离开里屏幕，屏幕因为惯性仍在滑动");
                HashMap<String,Object> map=new HashMap<>();
                map.put("pic",R.mipmap.ic_launcher);
                map.put("text","新增项目");
                dataList.add(map);
                simpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Mian", "onScrollStateChanged: "+"用户滑动屏幕，手指始终没有离开屏幕");
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main", "onScrollStateChanged: "+"屏幕停止滑动");
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String text=arrayListView.getItemAtPosition(position)+"";
        Toast.makeText(this, "position= "+position + " text= "+text, Toast.LENGTH_SHORT).show();
    }
}
