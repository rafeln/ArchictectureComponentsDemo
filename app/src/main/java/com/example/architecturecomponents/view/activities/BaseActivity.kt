package com.example.architecturecomponents.view.activities

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturecomponents.view.utilities.DialogUtils


abstract class BaseActivity: AppCompatActivity() {

    private var dialog: AlertDialog? = null

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissAlertDialog()
    }

    open fun showLoadingDialog() {
        dismissAlertDialog()
        dialog =
            DialogUtils.showLoadingDialog(
                this
            )
    }

    open fun showAlertDialog(message: String,
                        title: String? = null,
                        messageDetail: String? = null,
                        showConfirmCancelButtons: Boolean = false,
                        confirmText: String? = null,
                        cancelText: String? = null,
                        confirmListener: View.OnClickListener? = null,
                        cancelListener: View.OnClickListener? = null) {

        dismissAlertDialog()

        dialog =
            DialogUtils.showDialog(
                this,
                message,
                title,
                messageDetail,
                showConfirmCancelButtons,
                confirmText,
                cancelText,
                confirmListener,
                cancelListener
            )
    }

    fun dismissAlertDialog() {
        dialog?.dismiss()
        dialog = null
    }

    fun openBrowser(url: String?) {
        url?.let {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}