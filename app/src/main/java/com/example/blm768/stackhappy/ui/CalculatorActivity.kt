package com.example.blm768.stackhappy.ui

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.blm768.stackhappy.R

class CalculatorActivity : AppCompatActivity(),
        StackViewFragment.OnFragmentInteractionListener,
        KeyboardFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    override fun onKeyEvent(event: KeyEvent) {
        // TODO: implement.
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented")
    }
}
