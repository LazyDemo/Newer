package com.wb.newer.newer.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import com.wb.newer.newer.http.HttpUtils
import com.wb.newer.newer.http.SingleBaseResponse
import com.wb.newer.newer.model.data.BannerResponse
import com.wb.newer.newer.model.data.HomeResponse
import com.wb.newer.newer.model.service.HomeService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class IndexViewModel : ViewModel() {
    private val _data = MutableLiveData<String>()
    private val _banner = MutableLiveData<ArrayList<BannerResponse>>()
    private val _paper = MutableLiveData<List<HomeResponse.DatasBean>>()
    private var currentPage = 0
    private var totalPage = 1
    var pagerList = ArrayList<HomeResponse.DatasBean>()
    val data: LiveData<String>
        get() = _data
    val banner: LiveData<ArrayList<BannerResponse>>
        get() = _banner
    val paper: LiveData<List<HomeResponse.DatasBean>>
        get() = _paper

    init {
        _data.value = "Hello, world!"
        _paper.value = ArrayList()
    }

    fun getBanner() {
        HttpUtils.retrofit.create(HomeService::class.java)
                .getBanner()
                .flatMap(SingleBaseResponse())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArrayList<BannerResponse>> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d("index", "type")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("index", "type")
                    }

                    override fun onSuccess(t: ArrayList<BannerResponse>) {
                        Log.d("index", "type")
                        _banner.value = t
                    }

                })
    }

    fun getPaper() {
        if (currentPage < totalPage) {
            HttpUtils.retrofit.create(HomeService::class.java)
                    .getPaperList(currentPage)
                    .flatMap(SingleBaseResponse())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : SingleObserver<HomeResponse> {
                        override fun onSuccess(t: HomeResponse) {
                            currentPage++
                            totalPage = t.pageCount
                            _paper.value = t.datas
                            pagerList.addAll(t.datas)
                        }

                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onError(e: Throwable) {

                        }
                    })
        } else {

        }

    }


    fun resetPage() {
        currentPage = 0
        pagerList.clear()
    }
}
