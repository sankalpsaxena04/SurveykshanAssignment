package com.example.surveykshanassignment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.TimeZone


object GlobalUtils {


    class EasyElements(val context: Context) {
        fun dialog(title: String, msg: String, postiveText : String, negativeText : String ,positive: () -> Unit, negative: () -> Unit) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            builder.setPositiveButton(postiveText) { dialog, which ->
                positive()
            }
            builder.setNegativeButton(negativeText) { dialog, which ->
                negative()
            }
            val dialog = builder.create()
            dialog.show()
        }



        fun singleBtnDialog(title: String, msg: String, btnText: String, positive: () -> Unit) {
            if (context is Activity) {
                val activity = context

                if (!activity.isFinishing && !activity.isDestroyed) {
                    val builder = android.app.AlertDialog.Builder(context)
                    builder.setTitle(title)
                    builder.setMessage(msg)
                    builder.setCancelable(false)
                    builder.setPositiveButton(btnText) { dialog, which ->
                        positive()
                    }

                    try {
                        val dialog = builder.create()
                        dialog.show()
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun singleBtnDialog_InputError(title: String, msg: String, btnText: String, positive: () -> Unit) {
            if (context is Activity) {
                val activity = context

                if (!activity.isFinishing && !activity.isDestroyed) {
                    val builder = android.app.AlertDialog.Builder(context)
                    builder.setTitle(title)
                    builder.setMessage(msg)
                    builder.setCancelable(false)
                    builder.setPositiveButton(btnText) { dialog, which ->
                        positive()
                    }

                    try {
                        val dialog = builder.create()
                        dialog.show()
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun twoBtnDialogNonCancellable(
            title: String,
            msg: String,
            positiveBtnText: String,
            negativeBtnText: String,
            positive: () -> Unit,
            negative: () -> Unit
        ) {
            if (context is Activity) {
                val activity = context

                if (!activity.isFinishing && !activity.isDestroyed) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(title)
                    builder.setCancelable(false)
                    builder.setMessage(msg)
                    builder.setPositiveButton(positiveBtnText) { dialog, which ->
                        positive()
                    }
                    builder.setNegativeButton(negativeBtnText) { dialog, which ->
                        negative()
                    }
                    try {
                        val dialog = builder.create()
                        dialog.show()
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun twoBtnDialog(
            title: String,
            msg: String,
            positiveBtnText: String,
            negativeBtnText: String,
            positive: () -> Unit,
            negative: () -> Unit
        ) {
            if (context is Activity) {
                val activity = context

                if (!activity.isFinishing && !activity.isDestroyed) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(title)
                    builder.setMessage(msg)
                    builder.setPositiveButton(positiveBtnText) { dialog, which ->
                        positive()
                    }
                    builder.setNegativeButton(negativeBtnText) { dialog, which ->
                        negative()
                    }
                    try {
                        val dialog = builder.create()
                        dialog.show()
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun twoBtn(
            title: String,
            msg: String,
            positiveBtnText: String,
            negativeBtnText: String,
            positive: (isPositive: Boolean) -> Unit,
            negative: (isNegative: Boolean) -> Unit
        ) {
            if (context is Activity) {
                val activity = context

                if (!activity.isFinishing && !activity.isDestroyed) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(title)
                    builder.setMessage(msg)
                    builder.setPositiveButton(positiveBtnText) { dialog, which ->
                        positive(true)
                    }
                    builder.setNegativeButton(negativeBtnText) { dialog, which ->
                        negative(true)
                    }
                    try {
                        val dialog = builder.create()
                        dialog.show()
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    }
                }
            }
        }



        fun singleBtnDialog_ErrorConnection(title: String, msg: String, btnText: String, positive: () -> Unit) {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            builder.setPositiveButton(btnText) { dialog, which ->
                positive()
            }
            val dialog = builder.create()
            dialog.show()
        }

        fun showActionSnackbar(rootView: View, msg: String, duration: Int,actionText : String, action: () -> Unit) {
            Snackbar.make(rootView, msg, duration)
                .setAction(actionText) {
                    action()
                }
                .show()
        }

        fun showSnackbar(rootView: View, msg: String, duration: Int) {
            val snackbar = Snackbar.make(rootView, msg, duration)
            snackbar.setTextColor(Color.BLACK)
            snackbar.setBackgroundTint(Color.WHITE)
            snackbar.show()
        }


        fun loader(visible: Int) {
            val progressDialog = ProgressDialog(context)
            progressDialog.setTitle("Loading...")
            progressDialog.setMessage("Please wait.")
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(false)
            when (visible) {
                1 -> progressDialog.show()
                0 -> progressDialog.dismiss()
            }

        }
    }




}