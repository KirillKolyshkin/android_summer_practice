package com.example.myapplication.fragment.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.fragment.main.MainFragment

class LoginFragment : Fragment(R.layout.fragment_login)  {

    private var users : HashMap<String, List<Task>>? = null
    private var binding: FragmentLoginBinding? = null
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

        users = UserDatabase.getUserDatabase()

        val randomValue: Int = (0..5).random()
        val quote : String = quotes[randomValue]
        val quoteAuthor : String = quotesAuthors[randomValue]

        binding?.quoteAuthor?.text = quoteAuthor
        binding?.quote?.text = quote

        binding?.button?.setOnClickListener {
            val name : String? = binding?.etUsername?.text?.toString()
            insertDataToDatabase(name)
        }
    }

    private fun insertDataToDatabase(name : String?){

        if(name != null){
            if (!users?.containsKey(name)!!){
                users!![name] = listOf()
            }
            Toast.makeText(requireContext(), "Welcome, $name",Toast.LENGTH_LONG).show()
            findNavController().navigate(
                R.id.action_fragment_login_to_fragment_main,
                 MainFragment.createBundle(name))
        }
        else{
            Toast.makeText(requireContext(), "Please write your name",Toast.LENGTH_LONG).show()
        }
    }
}