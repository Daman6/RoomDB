package com.example.roomdb.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb.R
import com.example.roomdb.data.User
import com.example.roomdb.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.saveBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return  view
    }

    fun insertDataToDatabase(){
        val name = view?.name!!.editText!!.text.toString()
        val address = view?.address!!.editText!!.text.toString()
        val state = view?.state!!.editText!!.text.toString()
        val city = view?.city!!.editText!!.text.toString()
        val pincode = view?.pincode!!.editText!!.text
        val mobileNo = view?.mobileNo!!.editText!!.text
        val aadharNo = view?.aadharNo!!.editText!!.text

            if(TextUtils.isEmpty(name)){
                showToast("Enter Name")
            }else if(TextUtils.isEmpty(address)){
                showToast("Enter Address")
            }else if(TextUtils.isEmpty(state)){
                showToast("Enter State")
            }else if(TextUtils.isEmpty(city)){
                showToast("Enter City")
            }else if(TextUtils.isEmpty(pincode)){
                showToast("Enter Pincode")
            }else if(TextUtils.isEmpty(mobileNo)){
                showToast("Enter MobileNo")
            }else if(TextUtils.isEmpty(aadharNo)){
                showToast("Enter AadharNo")
            }else {
                val user = User(
                    0,
                    name,
                    address,
                    state,
                    city,
                   Integer.parseInt(pincode.toString()),
                    Integer.parseInt(mobileNo.toString()),
                    Integer.parseInt(aadharNo.toString())
                )
                userViewModel.addUser(user)
                Toast.makeText(requireContext(), "ADDED", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)

            }
    }

//    fun inputCheck(
//        name: String,
//        address: String,
//        state: String,
//        city: String,
//        pincode: Editable,
//        mobileNo: Editable,
//        aadharNo: Editable
//    ): Boolean{
//
//        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(address) && TextUtils.isEmpty(state) && TextUtils.isEmpty(city) && TextUtils.isEmpty(
//            pincode.toString())&& TextUtils.isEmpty(mobileNo.toString()) && TextUtils.isEmpty(aadharNo.toString()))
//    }

    fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
}