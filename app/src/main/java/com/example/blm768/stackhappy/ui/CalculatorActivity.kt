package com.example.blm768.stackhappy.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.blm768.stackhappy.R
import com.example.blm768.stackhappy.util.readRawResource

class CalculatorActivity : AppCompatActivity(),
        StackViewFragment.OnFragmentInteractionListener,
        KeyboardFragment.OnFragmentInteractionListener {

    private var stackView: StackViewFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Load keyboard layout.
        // TODO: make this configurable?
        val keyboardLayout = readRawResource(resources, R.raw.keyboard_layout)

        // Create and insert the keyboard fragment.
        val fragManager = supportFragmentManager
        stackView = fragManager.findFragmentById(R.id.stackView) as StackViewFragment
        val keyboardView = KeyboardFragment.newInstance(keyboardLayout)
        val transaction = fragManager.beginTransaction()
        transaction.replace(R.id.keyboard_placeholder, keyboardView)
        transaction.commit()
    }

    override fun onKeyEvent(event: KeyEvent) {
        if (stackView != null) event.act(stackView!!)
    }
}
