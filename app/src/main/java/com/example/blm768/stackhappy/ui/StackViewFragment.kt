package com.example.blm768.stackhappy.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.example.blm768.stackhappy.R
import com.example.blm768.stackhappy.Stack
import com.example.blm768.stackhappy.StackOperation

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StackViewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StackViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StackViewFragment : Fragment(), KeyEvent.Listener {

    private var stack: Stack = Stack()

    private var mListener: OnFragmentInteractionListener? = null
    private var entryLine: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            //stack = arguments.get(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_stack_view, container, false)
    }

    fun onKeyEvent(event: KeyEvent) {
        event.act(this)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
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

    override fun enterText(text: String) {
        // TODO: implement.
    }

    override fun doOperation(operation: StackOperation) {
        // TODO: implement.
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "stack"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @return A new instance of fragment StackViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String): StackViewFragment {
            val fragment = StackViewFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
