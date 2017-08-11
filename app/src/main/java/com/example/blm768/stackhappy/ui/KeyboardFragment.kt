package com.example.blm768.stackhappy.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blm768.stackhappy.R


/**
 * A fragment for a calculator keyboard
 *
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeyboardFragment : Fragment() {

    // TODO: settle on a naming convention for private instance variables.
    private var keyLayout: KeyboardLayout? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val keyLayoutYAML = arguments.getString(ARG_LAYOUT)
            keyLayout = KeyboardLayout.fromYAML(keyLayoutYAML)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_keyboard, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onKeyEvent(event: KeyEvent) {
        if (mListener != null) {
            mListener!!.onKeyEvent(event)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
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

         * @param layout A YAML string defining the layout
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

// TODO: finish implementation.
class KeyboardLayout {
    //private val columnCount: Int

    companion object {
        fun fromYAML(yaml: String): KeyboardLayout {
            return KeyboardLayout()
        }
    }
}
