package com.a3xh1.jetpackex.view.list

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.a3xh1.basecore.base.viewmodel.BaseViewModel
import com.a3xh1.basecore.common.rx.handleResponse
import com.a3xh1.basecore.ext.paging.IntPageKeyedData
import com.a3xh1.basecore.ext.paging.IntPageKeyedDataSource
import com.a3xh1.basecore.ext.paging.Paging
import com.a3xh1.basecore.utils.logger.loge
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

    val p = LivePagedListBuilder<Int, Medicine>(
        object : DataSource.Factory<Int, Medicine>() {
            override fun create(): DataSource<Int, Medicine> {
                return object : PageKeyedDataSource<Int, Medicine>() {
                    @SuppressLint("CheckResult")
                    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Medicine>) {
                        requestList(params.key)
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
                            .subscribe({
                                val (list, pageIndex, hasAdjacentPageKey) = it
                                when (hasAdjacentPageKey) {
                                    true -> {
                                        callback.onResult(list, pageIndex + 1)
                                    }
                                    false -> {
                                        callback.onResult(list, null)
                                    }
                                }
                            }, {
                                loge { it.message ?: "" }
                            })
                    }

                    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Medicine>) {
                    }

                    @SuppressLint("CheckResult")
                    override fun loadInitial(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, Medicine>
                    ) {
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
                            .subscribe({
                                val (list, pageIndex, hasAdjacentPageKey) = it
                                when (hasAdjacentPageKey) {
                                    true -> {
                                        callback.onResult(list, pageIndex, pageIndex + 1)
                                    }
                                    false -> {
                                        callback.onResult(list, pageIndex, null)
                                    }
                                }
                            }, {
                                loge { it.message ?: "" }
                            })
                    }
                }
            }
        },
        PagedList.Config
            .Builder()
            .setInitialLoadSizeHint(30)
            .setPageSize(15)
            .setPrefetchDistance(15)
            .setEnablePlaceholders(false)
            .build()
    ).build()

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
//            .onErrorReturn { SimpleViewState.error(it) }

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