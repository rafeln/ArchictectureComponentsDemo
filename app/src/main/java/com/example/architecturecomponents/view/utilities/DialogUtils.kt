package com.example.architecturecomponents.view.utilities

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.architecturecomponents.R

class DialogUtils {
    companion object {
        fun showLoadingDialog(context: Context): AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setView(R.layout.dialog_progress)
                .setCancelable(false)

            val dialog = builder.create()

            val width = context.resources.getDimensionPixelSize(R.dimen.progress_dialog_width)
            val height = context.resources.getDimensionPixelSize(R.dimen.progress_dialog_height)

            // show dialog first
            dialog.show()
            // change width and height after
            dialog.window?.setLayout(width, height)
            return dialog
        }

        fun showDialog(context: Context,
                       message: String,
                       title: String? = null,
                       messageDetail: String? = null,
                       showConfirmCancelButtons: Boolean = false,
                       confirmText: String? = null,
                       cancelText: String? = null,
                       confirmListener: View.OnClickListener? = null,
                       cancelListener: View.OnClickListener? = null) : AlertDialog {
            val builder = AlertDialog.Builder(context)

            val view = LayoutInflater.from(context).inflate(R.layout.dialog_message, null, false) as LinearLayout

            var dialog : AlertDialog? = null

            val textViewTitle = view.findViewById<TextView>(R.id.titleTextView)
            if (title == null) {
                textViewTitle.visibility = View.GONE
            } else {
                textViewTitle.text = title
            }

            val messageTxt = view.findViewById<TextView>(R.id.messageTextView)
            messageTxt.text = message

            val textViewMessageDetail = view.findViewById<TextView>(R.id.messageDetailTextView)
            if (messageDetail == null) {
                textViewMessageDetail.visibility = View.GONE
            } else {
                textViewMessageDetail.text = messageDetail
            }

            val confirmBtn = view.findViewById<Button>(R.id.okButton)
            val cancelBtn = view.findViewById<Button>(R.id.cancelButton)

            if (!showConfirmCancelButtons) {
                cancelBtn.visibility = View.GONE
            }

            if (cancelText != null) {
                cancelBtn.text = cancelText
            }

            if (confirmText != null) {
                confirmBtn.text = confirmText
            }

            confirmBtn.setOnClickListener { v ->
                dialog?.hide()
                Handler(Looper.getMainLooper()).post {
                    confirmListener?.onClick(v)
                }
            }

            cancelBtn.setOnClickListener { v ->
                dialog?.hide()
                cancelListener?.onClick(v)
            }

            builder.setView(view).setCancelable(false)

            dialog = builder.create()

            dialog.show()
            return dialog
        }
    }
}