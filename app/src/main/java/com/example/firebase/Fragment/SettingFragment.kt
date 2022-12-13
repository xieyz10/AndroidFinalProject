package com.example.firebase.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.firebase.Entity.Order
import com.example.firebase.Entity.User
import com.example.firebase.MainActivity
import com.example.firebase.R
import com.example.firebase.RegisterActivity
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

        var btn_update = contentView.findViewById<Button>(R.id.btn_update_submit)
        var btn_logout = contentView.findViewById<Button>(R.id.btn_logout_submit)
        val editText_firstName = contentView.findViewById<EditText>(R.id.editText_firstName)
        val editText_lastName = contentView.findViewById<EditText>(R.id.editText_lastName)
        val editText_address = contentView.findViewById<EditText>(R.id.editText_address)
        val editText_city = contentView.findViewById<EditText>(R.id.editText_city)
        val editText_state = contentView.findViewById<EditText>(R.id.editText_state)
        val editText_country = contentView.findViewById<EditText>(R.id.editText_country)
        val editText_postalCode = contentView.findViewById<EditText>(R.id.editText_postalCode)

        dbRef.orderByChild("userId").equalTo(userId).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnap in snapshot.children) {
                        val userData = userSnap.getValue()
                        val userInfo= userData as HashMap<String, Any>
                        editText_firstName.setText(userInfo["firstName"].toString(), TextView.BufferType.EDITABLE)
                        editText_lastName.setText(userInfo["lastName"].toString(), TextView.BufferType.EDITABLE)
                        editText_address.setText(userInfo["address"].toString(), TextView.BufferType.EDITABLE);
                        editText_city.setText(userInfo["city"].toString(), TextView.BufferType.EDITABLE);
                        editText_state.setText(userInfo["state"].toString(), TextView.BufferType.EDITABLE);
                        editText_country.setText(userInfo["country"].toString(), TextView.BufferType.EDITABLE);
                        editText_postalCode.setText(userInfo["postalCode"].toString(), TextView.BufferType.EDITABLE);
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
            }
        })

        btn_update.setOnClickListener{
            dbRef.orderByChild("userId").equalTo(userId).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val firstName = editText_firstName.text.toString()
                    val lastName = editText_lastName.text.toString()
                    val address = editText_address.text.toString()
                    val city = editText_city.text.toString()
                    val state = editText_state.text.toString()
                    val country = editText_country.text.toString()
                    val postalCode = editText_postalCode.text.toString()
                    if(snapshot.exists()){
                        dbRef.child(userId!!).child("firstName").setValue(firstName)
                        dbRef.child(userId!!).child("lastName").setValue(lastName)
                        dbRef.child(userId!!).child("address").setValue(address)
                        dbRef.child(userId!!).child("city").setValue(city)
                        dbRef.child(userId!!).child("state").setValue(state)
                        dbRef.child(userId!!).child("country").setValue(country)
                        dbRef.child(userId!!).child("postalCode").setValue(postalCode)
                        Toast.makeText( context,"You info has been successfully updated!", Toast.LENGTH_LONG).show()
                    }else{
                        val user = User(userId,firstName,lastName,address,
                            city,state,country,postalCode
                        )
                        dbRef.child(userId!!).setValue(user).addOnCompleteListener{
                            Toast.makeText( context,"Success!!", Toast.LENGTH_LONG).show()
                        }.addOnFailureListener{ err->
                            Toast.makeText( context,"Error ${err.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
                }
            })
        }

        btn_logout.setOnClickListener {
            var intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        return contentView
    }

}