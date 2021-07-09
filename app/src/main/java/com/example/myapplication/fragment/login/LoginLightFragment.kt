package com.example.myapplication.fragment.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R


class LoginLightFragment : Fragment(R.layout.fragment_login)  {

//    private lateinit var userViewModel : UserViewModel
//    private var quotes : List<String> = listOf(
//        "Every minute you spend in planning saves 10 minutes in execution",
//        "Don’t waste your time in anger, regrets, worries, and grudges. Life is too short to be unhappy",
//        "Yesterday is gone. Tomorrow has not yet come. We have only today. Let us begin" ,
//        "Time is a created thing. To say 'I don't have time,' is like saying, 'I don't want to",
//        "Those who make the worst use of their time are the first to complain of its brevity"  ,
//        "Time is a slippery thing: lose hold of it once, and its string might sail out of your hands forever",
//    )
//    private var quotesAuthors : List<String> = listOf(
//        "©Brian Tracy",
//        "©Roy T. Bennett",
//        "©Mother Theresa",
//        "©Lao Tzu",
//        "©Jean de La Bruyère",
//        "©Anthony Doerr",
//    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val view: View =  inflater.inflate(R.layout.fragment_login, container, false)
//        val randomValue: Int = (0..5).random()
//
//        val quote : String = quotes[randomValue]
//        val quoteAuthor : String = quotesAuthors[randomValue]
//
//        val authorEt : EditText = view.findViewById<EditText>(R.id.quote_author)
//        val quoteEt : EditText = view.findViewById<EditText>(R.id.quote)
//
//        authorEt.setText(quoteAuthor)
//        quoteEt.setText(quote)
//
//
//
//        val loginBtn: Button = view.findViewById(R.id.button)
//
//        val name : String = view.findViewById<EditText>(R.id.et_username).toString()
//
//
//        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//
//
//        loginBtn.setOnClickListener(){
//
//            insertDataToDatabase(name, view)
//
//
//        }

        return view
    }



//    private fun insertDataToDatabase(name : String , view: View){
//
//        if(name.isNotEmpty()){
//            val user = User(name)
//            userViewModel.addUser(user)
//            Toast.makeText(requireContext(), "Welcome, $name",Toast.LENGTH_LONG).show()
//            view.findNavController().navigate(R.id.action_login_light_to_main_light) //MainFragment.createBundle(name)
//        }
//        else{
//            Toast.makeText(requireContext(), "Please write your name",Toast.LENGTH_LONG).show()
//        }
//
//    }
}














