package com.a3xh1.jetpackex.common.loadings

import androidx.lifecycle.MutableLiveData
import com.a3xh1.base.viewmodel.BaseViewModel

@Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
class CommonLoadingViewModel private constructor() : BaseViewModel(), ILoadingDelegate {

    private val state: MutableLiveData<CommonLoadingState> = MutableLiveData()

    override fun loadingState(): MutableLiveData<CommonLoadingState> = state

    override fun applyState(state: CommonLoadingState) {
        this.state.postValue(state)
    }

    companion object {

        fun instance() =
                CommonLoadingViewModel()
    }
}