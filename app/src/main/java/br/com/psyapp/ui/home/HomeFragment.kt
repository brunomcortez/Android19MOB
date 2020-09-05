package br.com.psyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.psyapp.R
import br.com.psyapp.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

   private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)
}
