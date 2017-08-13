package com.example.blm768.stackhappy.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.blm768.stackhappy.InvalidStackItemException
import com.example.blm768.stackhappy.R
import com.example.blm768.stackhappy.Stack
import com.example.blm768.stackhappy.StackItem

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
            // TODO: load and save the stack properly.
            //stack = arguments.get(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_stack_view, container, false)

        entryLine = view.findViewById(R.id.entryLine) as EditText

        return view
    }

    fun onKeyEvent(event: KeyEvent) {
        event.act(this)
    }

    // TODO: rename and implement.
    fun onSomeEvent() {
        if (mListener != null) {
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
        //TODO: support text insertion?
        entryLine?.append(text)
    }

    override fun push() {
        val text = entryLine?.text?.toString()
        // TODO: duplicate existing value if the stack isn't empty.
        // TODO: make a toast if there's nothing to duplicate?
        if (text == null || text.isEmpty()) return
        try {
            val item = StackItem.fromString(text)
            stack.push(item)
            // TODO: update display.
        } catch(ex: InvalidStackItemException) {
            // TODO: improve message.
            Toast.makeText(context, "Invalid syntax", Toast.LENGTH_SHORT).show()
        }
    }

    override fun doOperation(operation: (Stack) -> Unit) {
        // TODO: implement fully. (Push entry line before operating.)
        operation(stack)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_STACK = "stack"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param stack The stack (as a Parcelable)
         * *
         * @return A new StackViewFragment.
         */
        fun newInstance(stack: String): StackViewFragment {
            val fragment = StackViewFragment()
            val args = Bundle()
            args.putString(ARG_STACK, stack)
            fragment.arguments = args
            return fragment
        }
    }
}

class StackView
