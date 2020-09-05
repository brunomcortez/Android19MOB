package br.com.psyapp.ui.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {

    private var mAuth = FirebaseAuth.getInstance()

    fun logout() {
        mAuth.signOut()
    }
}