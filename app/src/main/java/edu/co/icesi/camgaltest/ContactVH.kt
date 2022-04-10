package edu.co.icesi.camgaltest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.camgaltest.databinding.ContactviewBinding

class ContactVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding:ContactviewBinding by lazy{
        ContactviewBinding.bind(itemView)
    }

    val contactNameTV = binding.contactNameTV
    val contactImg = binding.contactImg

}