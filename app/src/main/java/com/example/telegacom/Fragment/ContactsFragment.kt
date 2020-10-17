package com.example.telegacom.Fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.telegacom.R
import java.util.*


class ContactsFragment : Fragment() {

    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View?
    {
        val view = LayoutInflater.from(getActivity()).inflate(
            R.layout.contacts_fragment,
            container,
            false
        );

        val facebook_btn : ImageButton = view.findViewById(R.id.facebook_link) as ImageButton
        val vk_btn : ImageButton = view.findViewById(R.id.vk_link) as ImageButton
        val instagram_btn : ImageButton = view.findViewById(R.id.instagram_link) as ImageButton

        facebook_btn.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse("https://www.facebook.com/alex.burchinskaya00/")
                    startActivity(intent)
                }
            }
        )

        vk_btn.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse("https://vk.com/mardbm")
                    startActivity(intent)
                }
            }
        )

        instagram_btn.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse("https://www.instagram.com/mardbm_/")
                    startActivity(intent)
                }
            }
        )

        val copy_phone : ImageButton = view.findViewById(R.id.copy_phone) as ImageButton
        val copy_email : ImageButton = view.findViewById(R.id.copy_email) as ImageButton

        val phone : TextView = view.findViewById(R.id.phone) as TextView
        val email : TextView = view.findViewById(R.id.email) as TextView


        copy_phone.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val myClipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    myClip = ClipData.newPlainText("text", phone.text);
                    myClipboard?.setPrimaryClip(myClip as ClipData);

                    val dialog = CustomDialogFragment()
                    val args = Bundle()
                    args.putString("message", "Номер телефона скопирован в буффер обмена")

                    args.putString("title", "Копирование")
                    dialog.arguments = args
                    dialog.show(getFragmentManager() as FragmentManager, "custom")
                }
            }
        )



        copy_email.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val myClipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    myClip = ClipData.newPlainText("text", email.text);
                    myClipboard?.setPrimaryClip(myClip as ClipData);

                    val dialog = CustomDialogFragment()
                    val args = Bundle()
                    args.putString("message", "Почта скопирована в буффер обмена")

                    args.putString("title", "Копирование")
                    dialog.arguments = args
                    dialog.show(getFragmentManager() as FragmentManager, "custom")
                }
            }
        )

        return view
    }
}