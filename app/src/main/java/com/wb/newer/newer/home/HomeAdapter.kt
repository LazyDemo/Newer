package com.wb.newer.newer.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wb.newer.newer.R
import com.wb.newer.newer.model.data.HomeResponse
import kotlinx.android.synthetic.main.item_home_list_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/28.
 * Description:
 */
class HomeAdapter(layoutResId: Int, data: List<HomeResponse.DatasBean>?) : BaseQuickAdapter<HomeResponse.DatasBean, BaseViewHolder>(layoutResId, data) {

    @SuppressLint("SimpleDateFormat")
    override fun convert(helper: BaseViewHolder, item: HomeResponse.DatasBean) {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        val authorString = SpannableString("作者:" + item.author)
        authorString.setSpan(ForegroundColorSpan(Color.parseColor("#38B1F4")), authorString.indexOf(item.author), authorString.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        helper.setText(R.id.author, authorString)
                .setText(R.id.title, item.title)
                .setText(R.id.chapter, item.chapterName)
                .setText(R.id.date, date.format(item.publishTime))


    }

}
