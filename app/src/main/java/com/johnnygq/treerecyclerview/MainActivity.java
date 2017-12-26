package com.johnnygq.treerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.johnnygq.treerecyclerview.adapter.MyAdapter;
import com.johnnygq.treerecyclerview.adapter.TreeRecyclerViewAdapter;
import com.johnnygq.treerecyclerview.model.CountryModel;
import com.johnnygq.treerecyclerview.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private List<CountryModel> mData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=(RecyclerView)findViewById(R.id.mRecyclerView);
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.getItemAnimator().setChangeDuration(300);
        mRecyclerView.getItemAnimator().setMoveDuration(300);
        addDatas();
        try {
            adapter=new MyAdapter(mRecyclerView,this,mData,mData.size());
            mRecyclerView.setAdapter(adapter);
            adapter.setOnTreeNodeClickListener(mOnTreeNodeClickListener);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void addDatas() {
        //一级
        mData.add(new CountryModel(1,0,"北京"));
        mData.add(new CountryModel(2,0,"天津"));
        mData.add(new CountryModel(3,0,"上海"));
        mData.add(new CountryModel(4,0,"江苏省"));
        //二级-北京
        mData.add(new CountryModel(5,1,"东城区"));
        mData.add(new CountryModel(6,1,"西城区"));
        mData.add(new CountryModel(7,1,"朝阳区"));
        mData.add(new CountryModel(8,1,"丰台区"));
        mData.add(new CountryModel(9,1,"海淀区"));
        mData.add(new CountryModel(10,1,"......"));
        //二级-天津
        mData.add(new CountryModel(11,2,"和平区"));
        mData.add(new CountryModel(12,2,"河北区 "));
        mData.add(new CountryModel(13,2,"红桥区"));
        mData.add(new CountryModel(14,2,"塘沽区"));
        mData.add(new CountryModel(15,2,"......"));
        //二级-上海
        mData.add(new CountryModel(16,3,"黄浦区"));
        mData.add(new CountryModel(17,3,"徐汇区"));
        mData.add(new CountryModel(18,3,"长宁区"));
        mData.add(new CountryModel(19,3,"静安区"));
        mData.add(new CountryModel(20,3,"....."));
        //二级-江苏省
        mData.add(new CountryModel(21,4,"南京"));
        mData.add(new CountryModel(22,4,"无锡 "));
        mData.add(new CountryModel(23,4,"徐州"));
        mData.add(new CountryModel(24,4,"常州"));
        mData.add(new CountryModel(25,4,"苏州"));
        mData.add(new CountryModel(26,4,"盐城"));
        mData.add(new CountryModel(27,4,"......"));
        //三级-江苏省-南京
        mData.add(new CountryModel(28,21,"玄武区"));
        mData.add(new CountryModel(29,21,"秦淮区"));
        mData.add(new CountryModel(30,21,"建邺区"));
        mData.add(new CountryModel(31,21,"鼓楼区"));
        mData.add(new CountryModel(32,21,"......"));
        //三级-江苏省-徐州
        mData.add(new CountryModel(33,23,"云龙区"));
        mData.add(new CountryModel(34,23,"贾汪区"));
        mData.add(new CountryModel(35,23,"泉山区"));
        mData.add(new CountryModel(36,23,"铜山区"));
        //四级-江苏省-徐州-铜山区
        mData.add(new CountryModel(37,36,"利国街道"));
        mData.add(new CountryModel(38,36,"黄集镇"));
        mData.add(new CountryModel(39,36,"马坡镇"));
        mData.add(new CountryModel(40,36,"张集镇"));
        mData.add(new CountryModel(40,36,"单集镇"));
        mData.add(new CountryModel(40,36,"大许镇"));
    }

    private TreeRecyclerViewAdapter.OnTreeNodeClickListener mOnTreeNodeClickListener=new TreeRecyclerViewAdapter.OnTreeNodeClickListener() {
        @Override
        public void onClick(Node node, int position) {
            if (node.isLeaf()) {//最后一层
                Toast.makeText(MainActivity.this, node.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
