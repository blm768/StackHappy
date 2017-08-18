package com.example.blm768.stackhappy.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.blm768.stackhappy.*

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

    private var listener: OnFragmentInteractionListener? = null
    private var entryLine: EditText? = null
    private var stackAdapter: StackItemAdapter? = null

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

        entryLine = view.findViewById(R.id.entryLine)

        val stackItems = view.findViewById<ListView>(R.id.stackItems)
        // TODO: handle the second parameter properly.
        stackAdapter = StackItemAdapter(context, 0, stack)
        stackItems.adapter = stackAdapter

        return view
    }

    // TODO: rename and implement.
    fun onSomeEvent() {
        if (listener != null) {
        }
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

    override fun enterText(text: String) {
        //TODO: support text insertion?
        entryLine?.append(text)
    }

    // TODO: rename.
    override fun pushOrDuplicate() {
        if (entryLine == null || entryLine!!.text.isEmpty()) {
            Toast.makeText(context, "Nothing to push", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: duplicate existing value if the stack isn't empty.
        try {
            tryPush()
        } catch (ex: InvalidStackItemException) {
            reportSyntaxError()
        }
    }

    override fun doOperation(operation: (Stack) -> Unit) {
        // TODO: implement fully. (Push entry line before operating.)
        val text = entryLine?.text?.toString()

        if (text != null && !text.isEmpty()) {
            try {
                tryPush()
            } catch (ex: InvalidStackItemException) {
                reportSyntaxError()
                return
            }
        }

        try {
            operation(stack)
        } catch (ex: StackUnderflowException) {
            reportStackUnderflowError()
        }

        updateItemDisplay()
    }

    private fun tryPush() {
        val text = entryLine?.text?.toString()

        if (text == null || text.isEmpty()) return

        val item = StackItem.fromString(text)
        stack.push(item)
        entryLine?.text?.clear()
        updateItemDisplay()
    }

    // TODO: make common reportError method?
    private fun reportSyntaxError() {
        // TODO: improve message.
        Toast.makeText(context, "Invalid syntax", Toast.LENGTH_SHORT).show()
    }

    private fun reportStackUnderflowError() {
        Toast.makeText(context, "Not enough operands", Toast.LENGTH_SHORT).show()
    }

    // (may need additional calls; see https://stackoverflow.com/questions/2250770/how-to-refresh-android-listview)
    // TODO: make sure this works in all (reasonable) cases.
    private fun updateItemDisplay() = stackAdapter?.notifyDataSetChanged()

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

class StackItemAdapter(context: Context, resourceID: Int, stack: Stack) : ArrayAdapter<StackItem>(context, resourceID, stack.items) {
    override fun getView(index: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(index)
        val view: TextView = convertView as? TextView ?: TextView(context)

        view.text = item.text()
        view.gravity = Gravity.RIGHT
        view.textAlignment = View.TEXT_ALIGNMENT_GRAVITY
        // TODO: load dimension resource.
        view.textSize = context.resources.getDimension(R.dimen.stackEntryTextSize)
        return view
    }
}

