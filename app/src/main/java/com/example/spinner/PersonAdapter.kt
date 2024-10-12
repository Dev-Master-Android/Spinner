package com.example.spinner



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PersonAdapter(context: Context, persons: List<Person>) : ArrayAdapter<Person>(context, 0, persons) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val person = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)

        val textViewFirstName = view.findViewById<TextView>(R.id.textViewFirstName)
        val textViewLastName = view.findViewById<TextView>(R.id.textViewLastName)
        val textViewAge = view.findViewById<TextView>(R.id.textViewAge)
        val textViewPosition = view.findViewById<TextView>(R.id.textViewPosition)

        textViewFirstName.text = person?.firstName
        textViewLastName.text = person?.lastName
        textViewAge.text = person?.age.toString()
        textViewPosition.text = person?.position

        return view
    }
}
