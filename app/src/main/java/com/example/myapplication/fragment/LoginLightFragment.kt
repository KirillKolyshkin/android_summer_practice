package com.example.myapplication.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entities.User
import com.example.myapplication.data.view_model.UserViewModel


class LoginLightFragment : Fragment(R.layout.fragment_login_light)  {

    private lateinit var userViewModel : UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val view: View =  inflater.inflate(R.layout.fragment_login_light, container, false)



        val loginBtn: Button = view.findViewById(R.id.button)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        loginBtn.setOnClickListener(){

            insertDataToDatabase()
            view.findNavController().navigate(R.id.action_login_light_to_main_light)
        }

        return view
    }



    private fun insertDataToDatabase() {
        val name : String = view?.findViewById<EditText>(R.id.et_username).toString()

        if(name.isNotEmpty()){
            val user = User(name)
            userViewModel.addUser(user)
        }
    }
}














