package edu.co.icesi.camgaltest.modal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.fragment.app.DialogFragment
import edu.co.icesi.camgaltest.R
import edu.co.icesi.camgaltest.databinding.FragmentAddContactBinding

class AddContactFragment : DialogFragment() {

   private lateinit var binding:FragmentAddContactBinding



    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddContactFragment()
    }
}