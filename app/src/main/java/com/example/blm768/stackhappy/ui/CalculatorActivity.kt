package com.example.blm768.stackhappy.ui

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.blm768.stackhappy.R

class CalculatorActivity : AppCompatActivity(),
        StackViewFragment.OnFragmentInteractionListener,
        KeyboardFragment.OnFragmentInteractionListener {

    // TODO: just handle this completely within onCreate?
    private var stackView: StackViewFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        stackView = getSupportFragmentManager().findFragmentById(R.id.stackView) as StackViewFragment
    }

    override fun onKeyEvent(event: KeyEvent) {
            stackView?.onKeyEvent(event)
    }
}
