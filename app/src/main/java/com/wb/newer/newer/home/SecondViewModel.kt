package com.wb.newer.newer.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;

class SecondViewModel : ViewModel() {
    private val _testUrl = MutableLiveData<String>()
    val test: LiveData<String>
        get() = _testUrl

    init {
        _testUrl.value = "http://fp-testvideo.ks3-cn-beijing.ksyun.com/tv/170720/admin/EF4EADB4FE402A21AF81C703B600106D_v1.mp4"
    }
}
