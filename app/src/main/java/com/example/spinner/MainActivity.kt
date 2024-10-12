package com.example.spinner


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var spinnerPosition: Spinner
    private lateinit var buttonSave: Button
    private lateinit var listViewPersons: ListView
    private lateinit var personAdapter: PersonAdapter
    private val personList = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextAge = findViewById(R.id.editTextAge)
        spinnerPosition = findViewById(R.id.spinnerPosition)
        buttonSave = findViewById(R.id.buttonSave)
        listViewPersons = findViewById(R.id.listViewPersons)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.title_name)

        val positions = arrayOf("Менеджер", "Разработчик", "Дизайнер", "Аналитик")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, positions)
        spinnerPosition.adapter = adapter

        personAdapter = PersonAdapter(this, personList)
        listViewPersons.adapter = personAdapter

        buttonSave.setOnClickListener {
            savePerson()
        }

        listViewPersons.setOnItemClickListener { _, _, position, _ ->
            showDeleteConfirmationDialog(position)
        }
    }

    private fun savePerson() {
        val firstName = editTextFirstName.text.toString()
        val lastName = editTextLastName.text.toString()
        val ageText = editTextAge.text.toString()
        val position = spinnerPosition.selectedItem.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageText.toInt()
        val person = Person(firstName, lastName, age, position)
        personList.add(person)
        personAdapter.notifyDataSetChanged()

        editTextFirstName.text.clear()
        editTextLastName.text.clear()
        editTextAge.text.clear()
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.are_your_sure))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                personList.removeAt(position)
                personAdapter.notifyDataSetChanged()
            }
            .setNegativeButton(getString(R.string.no), null)
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finishAffinity()
                Toast.makeText(this, getString(R.string.program_exit), Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
