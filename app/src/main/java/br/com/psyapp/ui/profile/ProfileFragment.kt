package br.com.psyapp.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.psyapp.R
import br.com.psyapp.RESULT_LOGIN
import br.com.psyapp.ui.base.BaseFragment
import br.com.psyapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : BaseFragment() {

    private val profileViewModel: ProfileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btLogout?.setOnClickListener { logoutPressed() }
        updateProfile()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun updateProfile() {
        tvName?.text = profileViewModel.getProfileName()
        tvId?.text = profileViewModel.getUid()
        tvEmail?.text = profileViewModel.getEmail()
    }

    private fun logoutPressed() {
        profileViewModel.logout()
        proceedToLoginScreen()
    }

    private fun proceedToLoginScreen() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivityForResult(intent, RESULT_LOGIN)

    }
}