package com.example.codemate.testbot.cv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.codemate.testbot.R
import com.example.codemate.testbot.model.User
import com.example.codemate.testbot.profile.ProfilePageActivity
import org.jetbrains.anko.image
import org.jetbrains.anko.imageBitmap

/**
 * Created by Alex Lindroos on 26/10/2017.
 */

class CVAdapter(val context: Context, val array: ArrayList<User>): RecyclerView.Adapter<CVAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.img?.image = context.getDrawable(array[position].img)
        holder?.txtName?.text = array[position].name
        holder?.txtProfession?.text = array[position].profession
        holder?.button?.setOnClickListener {
            val intent = ProfilePageActivity.newIntent(context)
            val user = array[position]
            intent.putExtra("userExtra", user)
            startActivity(context, intent,null)
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_cvlist, parent, false)
        return ViewHolder(v)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img)
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtProfession = itemView.findViewById<TextView>(R.id.txtProfession)
        val button = itemView.findViewById<Button>(R.id.btn_go_to_cv)
    }
}

