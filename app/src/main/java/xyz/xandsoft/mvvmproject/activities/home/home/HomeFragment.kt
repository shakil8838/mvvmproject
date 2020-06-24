package xyz.xandsoft.mvvmproject.activities.home.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import xyz.xandsoft.mvvmproject.R
import xyz.xandsoft.mvvmproject.databinding.HomeFragmentBinding

class HomeFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val homeViewModelFactory: HomeViewModelFactory by instance<HomeViewModelFactory>()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val homeFragmentDataBinding: HomeFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment, container, false
        )

        viewModel = ViewModelProviders.of(this, homeViewModelFactory)
            .get(HomeViewModel::class.java)

        homeFragmentDataBinding.homeFragmentViewModel = viewModel
        homeFragmentDataBinding.lifecycleOwner = this

        return homeFragmentDataBinding.root
    }

}