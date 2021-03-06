package com.wb.newer.newer.home

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wb.newer.newer.GlideApp

import com.wb.newer.newer.R
import com.wb.newer.newer.WebActivity
import com.wb.newer.newer.base.IntentKey
import com.wb.newer.newer.model.data.HomeResponse
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.content_layout.*
import kotlinx.android.synthetic.main.fragment_index.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IndexFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IndexFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
import kotlinx.android.synthetic.main.fragment_index.view.*

class IndexFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index, container, false)
    }

    private lateinit var viewModel: IndexViewModel
    private var adapter: HomeAdapter? = null

    private var banner: Banner? = null

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IndexViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = HomeAdapter(R.layout.item_home_list_layout, viewModel.pagerList)

        val header = LayoutInflater.from(activity).inflate(R.layout.home_header_layout, null)
        banner = header.findViewById(com.wb.newer.newer.R.id.banner)
        banner?.setOnBannerListener {
            if (!TextUtils.isEmpty(viewModel.banner.value!![it].url)) {
                val intent = Intent(activity, WebActivity::class.java)
                intent.putExtra(IntentKey.web_url, viewModel.banner.value!![it].url)
                startActivity(intent)
            }
        }
        adapter?.addHeaderView(header)
        adapter?.setOnItemClickListener { _, _, position ->
            if (!TextUtils.isEmpty(viewModel.pagerList[position].link)) {
                val intent = Intent(activity, WebActivity::class.java)
                intent.putExtra(IntentKey.web_url, viewModel.pagerList[position].link)
                startActivity(intent)
            }
        }
        recyclerView.adapter = adapter
        refreshLayout.setOnLoadMoreListener {
            viewModel.getPaper()
            adapter?.notifyDataSetChanged()
            refreshLayout.finishLoadMore(true)

        }
        refreshLayout.setOnRefreshListener {
            viewModel.getBanner()
            viewModel.resetPage()
            viewModel.getPaper()
        }
        refreshLayout.autoRefresh()
        viewModel.banner.observe(this, Observer {

            // Set the text exposed by the LiveData
            banner?.setImageLoader(GlideImageLoader())
            //设置图片集合
            banner?.setImages(it)
            //banner设置方法全部调用完毕时最后调用
            banner?.start()
        })

        viewModel.paper.observe(this, Observer {
            if (it != null) {
                adapter?.notifyDataSetChanged()
                refreshLayout.finishRefresh(true)
            }
        })

    }

    override fun onStop() {
        super.onStop()
        banner?.stopAutoPlay()
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onIndexEvent(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onIndexEvent(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IndexFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): IndexFragment {
            val fragment = IndexFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
