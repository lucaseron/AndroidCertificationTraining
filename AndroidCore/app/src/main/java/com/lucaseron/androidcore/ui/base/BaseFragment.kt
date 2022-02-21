package com.lucaseron.androidcore.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.lucaseron.androidcore.BR

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    open val viewModel: ViewModel? = null

    private var _binding: T? = null
    val binding: T
        get() = _binding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(inflater)
        applyBinding(_binding)
        return binding.root
    }

    abstract fun createBinding(inflater: LayoutInflater): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun applyBinding(bind: T?) {
        bind?.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
    }
}