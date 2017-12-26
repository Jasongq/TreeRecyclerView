package com.johnnygq.treerecyclerview.model;

import com.johnnygq.treerecyclerview.tree.TreeNodeId;
import com.johnnygq.treerecyclerview.tree.TreeNodeLabel;
import com.johnnygq.treerecyclerview.tree.TreeNodePid;

import java.io.Serializable;

public class CountryModel implements Serializable {
    private static final long serialVersionUID = 4104738936409054526L;

    public CountryModel(int mId, int mParentId, String mName) {
        this.mId = mId;
        this.mParentId = mParentId;
        this.mName = mName;
    }

    @TreeNodeId
    private int mId;
    @TreeNodePid
    private int mParentId;
    @TreeNodeLabel
    private String mName;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmParentId() {
        return mParentId;
    }

    public void setmParentId(int mParentId) {
        this.mParentId = mParentId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
