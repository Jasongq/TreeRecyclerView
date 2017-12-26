package com.johnnygq.treerecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnnygq.treerecyclerview.tree.Node;
import com.johnnygq.treerecyclerview.tree.TreeHelper;

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
public abstract class TreeRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    /**
     * 存储所有可见的Node
     */
    protected List<Node> mNodes;
    protected LayoutInflater mInflater;
    /**
     * 存储所有的Node
     */
    protected List<Node> mAllNodes;

    /**
     * 点击的回调接口
     */
    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public TreeRecyclerViewAdapter(RecyclerView mTree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        mContext = context;
        /**
         * 对所有的Node进行排序
         */
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        /**
         * 过滤出可见的Node
         */
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);

    }
    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        Node n = mNodes.get(position);
        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        Node node = mNodes.get(position);
        getBindViewHolder(node, position, holder);
        // 设置内边距
        holder.itemView.setPadding(node.getLevel() * 40, 3, 3, 3);
        /**
        * 设置节点点击时，可以展开以及关闭；并且将ItemClick事件继续往外公布
        */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(mNodes.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNodes.size();
    }


    public abstract RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType);
    public abstract void getBindViewHolder(Node node, int position, RecyclerView.ViewHolder holder);
}
