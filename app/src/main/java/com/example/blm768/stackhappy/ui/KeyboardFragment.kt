package com.example.blm768.stackhappy.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import com.example.blm768.stackhappy.R


/**
 * A fragment for a calculator keyboard
 *
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeyboardFragment : Fragment() {
    private var keyboardLayout: KeyboardLayout? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val keyLayoutJSON = arguments.getString(ARG_LAYOUT)
            keyboardLayout = KeyboardLayout.fromJSON(keyLayoutJSON)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_keyboard, container, false) as GridLayout
        val keyLayout = keyboardLayout
        if(keyLayout != null) {
            view.columnCount = keyLayout.columnCount

            // TODO: support spanning.
            val columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            for(key in keyLayout.keys) {
                val button = Button(context)
                button.text = key.label
                button.setOnClickListener { onKeyEvent(key.event) }
                view.addView(button)
                (button.layoutParams as GridLayout.LayoutParams).columnSpec = columnSpec
            }
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun onKeyEvent(event: KeyEvent) = listener?.onKeyEvent(event)

    /**
     *
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        fun onKeyEvent(event: KeyEvent)
    }

    companion object {
        private val ARG_LAYOUT = "layout"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param layout A JSON string defining the layout
         * *
         * @return A new KeyboardFragment
         */
        fun newInstance(layout: String): KeyboardFragment {
            val fragment = KeyboardFragment()
            val args = Bundle()
            args.putString(ARG_LAYOUT, layout)
            fragment.arguments = args
            return fragment
        }
    }
}
