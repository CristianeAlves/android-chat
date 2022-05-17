package br.com.cotemig.chat.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.cotemig.chat.R
import br.com.cotemig.chat.models.Account
import br.com.cotemig.chat.services.RetrofitInitializer
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin =  findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        var login = findViewById<EditText>(R.id.login)
        var password = findViewById<EditText>(R.id.password)
        account.email = login.text.toString()
        account.password = password.text.toString()

        var call = s.auth(account)

        call.enqueue(object : retrofit2.Callback<Account>{
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if(response.code() == 200){
                    Toast.makeText(this@LoginActivity, "Tudo certo", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario ou senho invalidos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {

            }
        })


    }
}