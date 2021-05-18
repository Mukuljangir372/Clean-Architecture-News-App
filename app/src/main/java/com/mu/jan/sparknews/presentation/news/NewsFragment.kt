package com.mu.jan.sparknews.presentation.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mu.jan.sparknews.data.model.ArticleResponse
import com.mu.jan.sparknews.data.model.Status
import com.mu.jan.sparknews.databinding.FragNewsBinding
import com.mu.jan.sparknews.utils.Const
import com.mu.jan.sparknews.utils.showToast
import com.mu.jan.sparknews.utils.toNotVisible
import com.mu.jan.sparknews.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment: Fragment() {

    private var binding: FragNewsBinding? = null
    private val mViewModel by viewModels<NewsViewModel>()
    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragNewsBinding.inflate(inflater,container,false)
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments!=null){
            val category = requireArguments().getString(Const.NewsCategory)
            //load list by category
            if (category != null) {
                mViewModel.getNews(category)
                    .observe(viewLifecycleOwner){
                        when(it.status){
                            Status.LOADING -> { stateLoading() }
                            Status.SUCCESS -> {
                                it.data?.let { res ->
                                    stateSuccess(res.articles!!)
                                }
                            }
                            Status.ERROR -> {
                                stateError(it.message!!)
                            }
                        }
                    }
            }
        }

    }
    private fun stateLoading(){
        binding?.apply {
            rV.toNotVisible()
            progressBar.toVisible()
        }
    }
    private fun stateError(message: String){
        binding!!.progressBar.toNotVisible()
        mContext?.showToast(message)
    }
    private fun stateSuccess(data: List<ArticleResponse>){
        binding?.apply {
            var mAdapter = NewsAdapter()
            adapter = mAdapter
            mAdapter.submitList(data)

            progressBar.toNotVisible()
            rV.toVisible()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onDestroy() {
        binding = null
        mContext = null
        super.onDestroy()
    }
}