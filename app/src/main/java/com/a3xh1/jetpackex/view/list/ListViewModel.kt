package com.a3xh1.jetpackex.view.list

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.a3xh1.basecore.base.viewmodel.BaseViewModel
import com.a3xh1.basecore.common.rx.handleResponse
import com.a3xh1.basecore.ext.paging.IntPageKeyedData
import com.a3xh1.basecore.ext.paging.IntPageKeyedDataSource
import com.a3xh1.basecore.ext.paging.Paging
import com.a3xh1.jetpackex.base.state.SimpleViewState
import com.a3xh1.jetpackex.common.loadings.CommonLoadingState
import com.a3xh1.jetpackex.pojo.Medicine
import com.uber.autodispose.autoDisposable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/9.
 * FOR   :
 */
class ListViewModel @Inject constructor(val repo: ListRepository) : BaseViewModel() {

    val list = MutableLiveData<List<Medicine>>()

    val error = MutableLiveData<Throwable>()

    val loadingState = MutableLiveData<CommonLoadingState>()

    val pagedList = MutableLiveData<PagedList<Medicine>>()

    init {
        initList()
    }

    fun initList() {
        Paging.buildReactiveStream(
            intPageKeyedDataSource = IntPageKeyedDataSource(
                loadInitial = {
                    requestList()
                        .flatMap {
                            when (it) {
                                is SimpleViewState.Result -> Flowable.just(
                                    IntPageKeyedData.build(
                                        data = it.result ?: arrayListOf(),
                                        pageIndex = 1,
                                        hasAdjacentPageKey = false//it.result?.isNotEmpty() ?: false
                                    )
                                )
                                else -> Flowable.empty()
                            }
                        }
                },
                loadAfter = {
                    requestList(it.key)
                        .flatMap {
                            when (it) {
                                is SimpleViewState.Result -> Flowable.just(
                                    IntPageKeyedData.build(
                                        data = it.result ?: arrayListOf(),
                                        pageIndex = 1,
                                        hasAdjacentPageKey = it.result?.isNotEmpty() ?: false
                                    )
                                )
                                else -> Flowable.empty()
                            }
                        }
                }
            )
        )
            .doOnNext { pagedList.postValue(it) }
            .autoDisposable(this)
            .subscribe()
    }

    fun requestList(): Flowable<SimpleViewState<List<Medicine>?>> =
        requestList(1)
            .startWith(SimpleViewState.loading())
            .onErrorReturn { SimpleViewState.error(it) }

    fun requestList(page: Int): Flowable<SimpleViewState<List<Medicine>?>> =
        repo.requestList(page)
            .compose(handleResponse())
            .map {
                SimpleViewState.result(it.data)
            }
            .onErrorReturn { SimpleViewState.error(it) }

    fun applyState(
        list: List<Medicine>? = null,
        error: Throwable? = null,
        loadingState: CommonLoadingState = CommonLoadingState.IDLE
    ) {
        error?.let { this.error.value = it }
        this.loadingState.value = loadingState

    }


}