package com.muhammetkudur.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.muhammetkudur.ui.utils.indicator.IndicatorPresenter
import javax.inject.Inject

/**
 *    Created By Muhammet KÜDÜR
 *    17.07.2023
 */

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {

    @Inject
    lateinit var indicatorPresenter: IndicatorPresenter

    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectViewModel(viewModel)
    }

    private fun <VM : BaseViewModel> connectViewModel(vararg viewModels: VM) {
        viewModels.forEach { viewModel ->
            viewModel.indicator.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    indicatorPresenter.show()
                } else {
                    indicatorPresenter.hide()
                }
            }
        }
    }
}