package com.a3xh1.basecore.custom.view.recyclerview;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class DataBindingViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding dataBinding;
    private View mView;

    public DataBindingViewHolder(View itemView, ViewDataBinding dataBinding) {
        super(itemView);
        mView = itemView;
        this.dataBinding = dataBinding;
    }

    public DataBindingViewHolder(@NonNull ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        mView = dataBinding.getRoot();
        this.dataBinding = dataBinding;
    }

    public View getView() {
        return mView;
    }

    public ViewDataBinding getBinding() {
        return dataBinding;
    }


}