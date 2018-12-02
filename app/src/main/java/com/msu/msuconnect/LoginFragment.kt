package com.msu.msuconnect


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mCurrentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mAuth = FirebaseAuth.getInstance()
        var logout = arguments?.getBoolean("logout")
        if(logout != null && logout) {
            mAuth.signOut()
        }
        mCurrentView = inflater.inflate(R.layout.fragment_login, container, false)
        // Buttons
        mCurrentView.emailSignInButton.setOnClickListener(this)
        mCurrentView.emailCreateAccountButton.setOnClickListener(this)
        mCurrentView.signOutButton.setOnClickListener(this)
        mCurrentView.verifyEmailButton.setOnClickListener(this)

        updateUI(mAuth.currentUser)
        return mCurrentView
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.activity!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this.context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.activity!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this.context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

                if (!task.isSuccessful) {
                    mCurrentView.status.setText(R.string.auth_failed)
                }
            }
    }

    private fun signOut() {
        mAuth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        // Disable button
        mCurrentView.verifyEmailButton.isEnabled = false

        // Send verification email
        // [START send_email_verification]
        val user = mAuth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this.activity!!) { task ->
                // [START_EXCLUDE]
                // Re-enable button
                mCurrentView.verifyEmailButton.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(
                        this.context,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this.context,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // [END_EXCLUDE]
            }
        // [END send_email_verification]
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = mCurrentView.fieldEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            mCurrentView.fieldEmail.error = "Required."
            valid = false
        } else {
            mCurrentView.fieldEmail.error = null
        }

        val password = mCurrentView.fieldPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            mCurrentView.fieldPassword.error = "Required."
            valid = false
        } else {
            mCurrentView.fieldPassword.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {

            mCurrentView.status.text = getString(
                R.string.emailpassword_status_fmt,
                user.email, user.isEmailVerified
            )
            mCurrentView.detail.text = getString(R.string.firebase_status_fmt, user.uid)

            mCurrentView.emailPasswordButtons.visibility = View.GONE
            mCurrentView.emailPasswordFields.visibility = View.GONE
            mCurrentView.signedInButtons.visibility = View.VISIBLE

            mCurrentView.verifyEmailButton.isEnabled = !user.isEmailVerified

            var act = this.activity as MainActivity
            act.changeFragment(R.id.maps)
        } else {
            mCurrentView.status.setText(R.string.signed_out)
            mCurrentView.detail.text = null

            mCurrentView.emailPasswordButtons.visibility = View.VISIBLE
            mCurrentView.emailPasswordFields.visibility = View.VISIBLE
            mCurrentView.signedInButtons.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.emailCreateAccountButton -> createAccount(mCurrentView.fieldEmail.text.toString(), mCurrentView.fieldPassword.text.toString())
            R.id.emailSignInButton -> signIn(mCurrentView.fieldEmail.text.toString(), mCurrentView.fieldPassword.text.toString())
            R.id.signOutButton -> signOut()
            R.id.verifyEmailButton -> sendEmailVerification()
        }
    }
}
