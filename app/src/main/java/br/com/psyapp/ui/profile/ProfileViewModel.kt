package br.com.psyapp.ui.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ProfileViewModel: ViewModel() {

    private var mAuth = FirebaseAuth.getInstance()

    fun getProfileName() = mAuth.currentUser?.displayName ?: ""

    fun getEmail() = mAuth.currentUser?.email ?: ""

    fun getUid() = mAuth.currentUser?.uid ?: ""

    fun logout() {
        mAuth.signOut()
    }
}