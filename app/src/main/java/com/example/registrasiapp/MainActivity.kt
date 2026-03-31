package com.example.registrasiapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tilNama: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout

    private lateinit var etNama: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText

    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var rbLaki: RadioButton
    private lateinit var rbPerempuan: RadioButton
    private lateinit var tvJenisKelaminError: TextView
    private lateinit var tvHobiError: TextView

    private lateinit var cbMembaca: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var cbMusik: CheckBox
    private lateinit var cbMasak: CheckBox
    private lateinit var cbGaming: CheckBox

    private lateinit var spinnerKota: Spinner
    private lateinit var btnSubmit: MaterialButton
    private lateinit var btnReset: MaterialButton

    private val daftarKota = listOf(
        "-- Pilih Kota --", "Bandung", "Jakarta", "Surabaya",
        "Yogyakarta", "Medan", "Makassar", "Semarang",
        "Palembang", "Bali", "Bogor"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupSpinner()
        setupRealtimeValidation()
        setupButtons()
    }

    private fun initViews() {
        tilNama = findViewById(R.id.tilNama)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)

        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)

        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        rbLaki = findViewById(R.id.rbLaki)
        rbPerempuan = findViewById(R.id.rbPerempuan)
        tvJenisKelaminError = findViewById(R.id.tvJenisKelaminError)
        tvHobiError = findViewById(R.id.tvHobiError)

        cbMembaca = findViewById(R.id.cbMembaca)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        cbMusik = findViewById(R.id.cbMusik)
        cbMasak = findViewById(R.id.cbMasak)
        cbGaming = findViewById(R.id.cbGaming)

        spinnerKota = findViewById(R.id.spinnerKota)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnReset = findViewById(R.id.btnReset)
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, daftarKota)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKota.adapter = adapter
    }

    private fun setupRealtimeValidation() {
        etNama.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrBlank()) tilNama.error = null
            }
        })

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()
                when {
                    email.isEmpty() -> tilEmail.error = null
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                        tilEmail.error = "Format email tidak valid"
                    else -> tilEmail.error = null
                }
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                when {
                    password.isEmpty() -> tilPassword.error = null
                    password.length < 8 -> tilPassword.error = "Password minimal 8 karakter"
                    else -> tilPassword.error = null
                }
                val confirm = etConfirmPassword.text.toString()
                if (confirm.isNotEmpty()) {
                    tilConfirmPassword.error = if (password != confirm) "Password tidak cocok" else null
                }
            }
        })

        etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val confirm = s.toString()
                val password = etPassword.text.toString()
                when {
                    confirm.isEmpty() -> tilConfirmPassword.error = null
                    confirm != password -> tilConfirmPassword.error = "Password tidak cocok"
                    else -> tilConfirmPassword.error = null
                }
            }
        })

        val hobiListener = CompoundButton.OnCheckedChangeListener { _, _ ->
            if (getSelectedHobi().size >= 3) tvHobiError.visibility = View.GONE
        }
        cbMembaca.setOnCheckedChangeListener(hobiListener)
        cbOlahraga.setOnCheckedChangeListener(hobiListener)
        cbMusik.setOnCheckedChangeListener(hobiListener)
        cbMasak.setOnCheckedChangeListener(hobiListener)
        cbGaming.setOnCheckedChangeListener(hobiListener)

        rgJenisKelamin.setOnCheckedChangeListener { _, _ ->
            tvJenisKelaminError.visibility = View.GONE
        }
    }

    private fun setupButtons() {
        btnSubmit.setOnClickListener {
            if (validateAllFields()) showConfirmationDialog()
        }

        // GESTURE: Long Press pada Submit
        btnSubmit.setOnLongClickListener {
            AlertDialog.Builder(this)
                .setTitle("Panduan Pengisian Form")
                .setMessage("• Isi semua field dengan benar\n• Password minimal 8 karakter\n• Pilih minimal 3 hobi\n• Email harus format yang valid\n\nTekan Submit untuk mendaftar!")
                .setPositiveButton("Mengerti") { d, _ -> d.dismiss() }
                .show()
            true
        }

        btnReset.setOnClickListener {
            resetForm()
            Snackbar.make(btnReset, "Form berhasil direset", Snackbar.LENGTH_SHORT).show()
        }

        // GESTURE: Long Press pada Reset
        btnReset.setOnLongClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Reset")
                .setMessage("Apakah Anda yakin ingin menghapus semua data yang telah diisi?")
                .setPositiveButton("Ya, Reset") { _, _ -> resetForm() }
                .setNegativeButton("Batal") { d, _ -> d.dismiss() }
                .show()
            true
        }
    }

    private fun validateAllFields(): Boolean {
        var isValid = true

        val nama = etNama.text.toString().trim()
        if (nama.isEmpty()) {
            tilNama.error = "Nama tidak boleh kosong"
            isValid = false
        } else tilNama.error = null

        val email = etEmail.text.toString().trim()
        when {
            email.isEmpty() -> { tilEmail.error = "Email tidak boleh kosong"; isValid = false }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                tilEmail.error = "Format email tidak valid"; isValid = false
            }
            else -> tilEmail.error = null
        }

        val password = etPassword.text.toString()
        when {
            password.isEmpty() -> { tilPassword.error = "Password tidak boleh kosong"; isValid = false }
            password.length < 8 -> { tilPassword.error = "Password minimal 8 karakter"; isValid = false }
            else -> tilPassword.error = null
        }

        val confirmPassword = etConfirmPassword.text.toString()
        when {
            confirmPassword.isEmpty() -> {
                tilConfirmPassword.error = "Konfirmasi password tidak boleh kosong"; isValid = false
            }
            confirmPassword != password -> {
                tilConfirmPassword.error = "Password tidak cocok"; isValid = false
            }
            else -> tilConfirmPassword.error = null
        }

        if (rgJenisKelamin.checkedRadioButtonId == -1) {
            tvJenisKelaminError.text = "Pilih jenis kelamin terlebih dahulu"
            tvJenisKelaminError.visibility = View.VISIBLE
            isValid = false
        } else tvJenisKelaminError.visibility = View.GONE

        val selectedHobi = getSelectedHobi()
        if (selectedHobi.size < 3) {
            tvHobiError.text = "Pilih minimal 3 hobi (dipilih: ${selectedHobi.size})"
            tvHobiError.visibility = View.VISIBLE
            isValid = false
        } else tvHobiError.visibility = View.GONE

        if (spinnerKota.selectedItemPosition == 0) {
            Toast.makeText(this, "Pilih kota terlebih dahulu", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    private fun getSelectedHobi(): List<String> {
        val hobi = mutableListOf<String>()
        if (cbMembaca.isChecked) hobi.add("Membaca")
        if (cbOlahraga.isChecked) hobi.add("Olahraga")
        if (cbMusik.isChecked) hobi.add("Musik")
        if (cbMasak.isChecked) hobi.add("Memasak")
        if (cbGaming.isChecked) hobi.add("Gaming")
        return hobi
    }

    private fun getJenisKelamin(): String = when (rgJenisKelamin.checkedRadioButtonId) {
        R.id.rbLaki -> "Laki-laki"
        R.id.rbPerempuan -> "Perempuan"
        else -> "-"
    }

    private fun resetForm() {
        etNama.setText("")
        etEmail.setText("")
        etPassword.setText("")
        etConfirmPassword.setText("")
        tilNama.error = null
        tilEmail.error = null
        tilPassword.error = null
        tilConfirmPassword.error = null
        rgJenisKelamin.clearCheck()
        tvJenisKelaminError.visibility = View.GONE
        cbMembaca.isChecked = false
        cbOlahraga.isChecked = false
        cbMusik.isChecked = false
        cbMasak.isChecked = false
        cbGaming.isChecked = false
        tvHobiError.visibility = View.GONE
        spinnerKota.setSelection(0)
    }

    private fun showConfirmationDialog() {
        val nama = etNama.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val message = """
            Pastikan data Anda sudah benar:
            
            Nama     : $nama
            Email    : $email
            JK       : ${getJenisKelamin()}
            Hobi     : ${getSelectedHobi().joinToString(", ")}
            Kota     : ${spinnerKota.selectedItem}
            
            Lanjutkan pendaftaran?
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Data")
            .setMessage(message)
            .setPositiveButton("Daftar") { dialog, _ ->
                dialog.dismiss()
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Selamat datang, $nama! Registrasi berhasil.",
                    Snackbar.LENGTH_LONG
                ).show()
                resetForm()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}