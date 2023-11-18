package com.bengkelsampah.bengkelsampahapp.utils

import android.app.Activity

import android.os.Handler
import android.os.Looper
import cn.pedant.SweetAlert.SweetAlertDialog

/**
 * this object can be used to create SweetAlertDialog in Activity
 */
object SweetAlertDialogUtils {
    fun showSweetAlertDialog(
        context: Activity,
        title: String,
        sweetAlertDialogType: Int,
        hasConfirmationButton: Boolean,
        willFinishActivity: Boolean
    ) {
        val dialog = SweetAlertDialog(context, sweetAlertDialogType)
            .setTitleText(title)

        if (hasConfirmationButton) {
            dialog.setConfirmClickListener {
                dialog.dismissWithAnimation()
                if (willFinishActivity) run {
                    context.finish()
                }
            }
            dialog.show()
        } else {
            dialog.hideConfirmButton()
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismissWithAnimation()
                if (willFinishActivity) run {
                    context.finish()
                }
            }, 1500)
        }
    }
}