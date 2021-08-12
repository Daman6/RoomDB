package com.example.roomdb.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdb.R
import com.example.roomdb.data.User
import com.example.roomdb.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateName.editText!!.setText(args.currentUSer.name)
        view.updateAddress.editText!!.setText(args.currentUSer.address)
        view.updateState.editText!!.setText(args.currentUSer.state)
        view.updateCity.editText!!.setText(args.currentUSer.city)
        view.updateMobile.editText!!.setText("" + args.currentUSer.mobileNo)
        view.updatePincode.editText!!.setText("" + args.currentUSer.pincode)
        view.updateAadharNo.editText!!.setText("" + args.currentUSer.aadharNo)

        view.updateBtn.setOnClickListener {
            updateUser()
        }
        setHasOptionsMenu(true)
        return view
    }

    fun updateUser() {
        val name = view?.updateName!!.editText!!.text.toString()
        val address = view?.updateAddress!!.editText!!.text.toString()
        val state = view?.updateState!!.editText!!.text.toString()
        val city = view?.updateCity!!.editText!!.text.toString()
        val pincode = view?.updatePincode!!.editText!!.text
        val mobileNo = view?.updateMobile!!.editText!!.text
        val aadharNo = view?.updateAadharNo!!.editText!!.text

        if (inputCheck(name, address, state, city, pincode, mobileNo, aadharNo)) {
            val userUpdate = User(
                args.currentUSer.id,
                name,
                address,
                state,
                city,
                Integer.parseInt(pincode.toString()),
                Integer.parseInt(mobileNo.toString()),
                Integer.parseInt(aadharNo.toString())
            )
            userViewModel.updateUser(userUpdate)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "NOT Updated", Toast.LENGTH_SHORT).show()

        }

    }

    fun inputCheck(
        name: String,
        address: String,
        state: String,
        city: String,
        pincode: Editable,
        mobileNo: Editable,
        aadharNo: Editable
    ): Boolean {

        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(address) && TextUtils.isEmpty(state) && TextUtils.isEmpty(
            city
        ) && TextUtils.isEmpty(
            pincode.toString()
        ) && TextUtils.isEmpty(mobileNo.toString()) && TextUtils.isEmpty(aadharNo.toString()))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            userViewModel.deleteUser(args.currentUSer)
            Toast.makeText(requireContext(), "Removed: ${args.currentUSer.name}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.currentUSer.name}")
        builder.setMessage("Are you sure you want to delete ${args.currentUSer.name} ?")
        builder.create().show()
    }
}