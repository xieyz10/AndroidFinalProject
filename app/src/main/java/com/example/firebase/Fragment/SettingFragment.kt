package com.example.firebase.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebase.Entity.Order
import com.example.firebase.R
import com.example.mingyuanxie_mapd711_assignment4.OrderAdapter
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var contentView: View= inflater.inflate(com.example.firebase.R.layout.fragment_setting, container, false)
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("users")
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId","")

        dbRef.orderByChild("userId").equalTo(userId).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
            }
        })

        var btn_update = contentView.findViewById<Button>(R.id.btn_update_submit)
        val firstName = contentView.findViewById<EditText>(R.id.editText_firstName)
        val lastName = contentView.findViewById<EditText>(R.id.editText_lastName)
        val address = contentView.findViewById<EditText>(R.id.editText_address)
        val city = contentView.findViewById<EditText>(R.id.editText_city)
        val state = contentView.findViewById<EditText>(R.id.editText_state)
        val country = contentView.findViewById<EditText>(R.id.editText_country)
        val postalCode = contentView.findViewById<EditText>(R.id.editText_postalCode)

        btn_update.setOnClickListener{
            dbRef.orderByChild("userId").equalTo(userId).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){

                    }else{

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
                }
            })
        }

        return contentView
    }

}