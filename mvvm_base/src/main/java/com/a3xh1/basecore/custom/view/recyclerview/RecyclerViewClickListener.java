package com.a3xh1.basecore.custom.view.recyclerview;

import android.view.View;

/**
 * Author: GIndoc on 2017/7/24 下午2:09
 * FOR   :
 */
public interface RecyclerViewClickListener {
    void onItemClickListener(View view, int position);

    void onItemLongClickListener(View view, int position);
}
