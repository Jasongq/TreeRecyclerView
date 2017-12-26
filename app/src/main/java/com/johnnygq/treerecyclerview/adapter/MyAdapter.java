package com.johnnygq.treerecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.johnnygq.treerecyclerview.R;
import com.johnnygq.treerecyclerview.tree.Node;

import java.util.List;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2017/12/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyAdapter<T> extends TreeRecyclerViewAdapter<T> {
    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public MyAdapter(RecyclerView mTree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree_view, parent, false);
        return new mViewHolder(view);
    }

    @Override
    public void getBindViewHolder(Node node, int position, RecyclerView.ViewHolder holder) {
        mViewHolder holder1 = (mViewHolder) holder;
        if (node.getIcon() == -1) {
            holder1.mItemRightImage.setVisibility(View.GONE);
        } else {
            holder1.mItemRightImage.setVisibility(View.VISIBLE);
            holder1.mItemRightImage.setImageResource(node.getIcon());
        }
        holder1.itemGroupNameTv.setText(node.getName());
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView itemGroupNameTv;
        AppCompatImageView mItemRightImage;

        public mViewHolder(View itemView) {
            super(itemView);
            itemGroupNameTv = (AppCompatTextView) itemView.findViewById(R.id.itemGroupNameTv);
            mItemRightImage = (AppCompatImageView) itemView.findViewById(R.id.itemRightImage);
        }
    }
}
