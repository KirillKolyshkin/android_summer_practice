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
import com.example.myapplication.data.entities.User
import com.example.myapplication.data.view_model.UserViewModel
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.fragment.main.MainFragment


class LoginFragment : Fragment(R.layout.fragment_login)  {

   // private lateinit var userViewModel : UserViewModel
    private var binding: FragmentLoginBinding? = null
    private var map : HashMap<String,String> = hashMapOf<String,String>(
        "Adelya" to "",
        "Nastya" to "",
        "Kirill" to "",)
    private var quotes : List<String> = listOf(
        "Every minute you spend in planning saves 10 minutes in execution",
        "Don’t waste your time in anger, regrets, worries, and grudges. Life is too short to be unhappy",
        "Yesterday is gone. Tomorrow has not yet come. We have only today. Let us begin" ,
        "Time is a created thing. To say 'I don't have time,' is like saying, 'I don't want to",
        "Those who make the worst use of their time are the first to complain of its brevity"  ,
        "Time is a slippery thing: lose hold of it once, and its string might sail out of your hands forever",
    )
    private var quotesAuthors : List<String> = listOf(
        "©Brian Tracy",
        "©Roy T. Bennett",
        "©Mother Theresa",
        "©Lao Tzu",
        "©Jean de La Bruyère",
        "©Anthony Doerr",
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val randomValue: Int = (0..5).random()
        val quote : String = quotes[randomValue]
        val quoteAuthor : String = quotesAuthors[randomValue]

        binding?.quoteAuthor?.text = quoteAuthor
        binding?.quote?.text = quote


        binding?.button?.setOnClickListener(){
            insertDataToDatabase(view)
        }
    }

    private fun insertDataToDatabase(view: View){

        val name : String = binding?.etUsername?.text.toString()
        if(name.isNotEmpty()){
           // val user = User(name)
           // userViewModel.addUser(user)
            if (!map.containsKey(name)){
                map.put(name,"")
            }
            Toast.makeText(requireContext(), "Welcome, $name",Toast.LENGTH_LONG).show()
            view.findNavController().navigate(
                R.id.action_fragment_login_to_fragment_main)
        }
        else{
            Toast.makeText(requireContext(), "Please write your name",Toast.LENGTH_LONG).show()
        }
    }
}