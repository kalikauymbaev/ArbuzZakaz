package com.example.arbuz.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arbuz.R
import com.example.arbuz.databinding.FragmentMakeZakazBinding
import com.example.arbuz.databinding.GryadkaBinding
import com.example.arbuz.models.ArbuzZakaz
import com.example.arbuz.viewmodels.MakeZakazViewModel
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class MakeZakazFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    private lateinit var binding: FragmentMakeZakazBinding
    private lateinit var viewModel: MakeZakazViewModel
    private var stat = ""
    val myCalendar: Calendar = Calendar.getInstance()

    //    private var weight = 0
//    private var weightFinal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMakeZakazBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MakeZakazViewModel::class.java]

        checkTvNull()

        binding.closeImg.setOnClickListener {
            fragmentTransaction()
        }

        binding.etgryadka.setOnClickListener {
            dialogFunc()
        }
        binding.seekBar.setOnSeekBarChangeListener(this)

        binding.tvQuantity.text = "${binding.seekBar.progress}"

        binding.etphone.setText("+7")

        val date =
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateLabel(binding.textInputDate)
            }


        binding.textInputDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        binding.textInputDate.setTextColor(resources.getColor(R.color.black))

        binding.textInputTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute -> binding.textInputTime.setText("$hourOfDay:$minute") },
                myCalendar[Calendar.HOUR],
                myCalendar[Calendar.MINUTE],
                true
            )
            timePickerDialog.show()
        }

        binding.textInputTime.setTextColor(resources.getColor(R.color.black))

        binding.btnMakeZakaz.setOnClickListener {
            when {
                binding.etgryadka.text!!.isEmpty() -> {
                    binding.etgryadka.error = "Выберите арбуз"
                }
                binding.tvQuantity.text == "Количество: 0" -> {
                    Toast.makeText(requireContext(), "Укажите количество арбуза", Toast.LENGTH_SHORT).show()
                }
                binding.etaddress.text!!.isEmpty() -> {
                    binding.etaddress.error = "Укажите адресс"
                }
                binding.etphone.text!!.isEmpty() -> {
                    binding.etphone.error = "Укажите свой номер телефона"
                }
                binding.textInputDate.text!!.isEmpty() -> {
                    binding.textInputDate.error = "Выберите дату доставки"
                }
                binding.textInputTime.text!!.isEmpty() -> {
                    binding.textInputTime.error = "Выберите время доставки"
                }
                else -> {
                    val arbuzZakaz = ArbuzZakaz(
                        UUID.randomUUID().toString(),
                        binding.etgryadka.text.toString(),
                        binding.tvStatus.text.toString(),
                        binding.tvWeight.text.toString(),
                        binding.tvQuantity.text.toString().toInt(),
                        binding.tvPrice.text.toString(),
                        binding.etaddress.text.toString(),
                        binding.etphone.text.toString(),
                        binding.textInputDate.text.toString(),
                        binding.textInputTime.text.toString(),
                        binding.checkbox.isChecked
                    )

                    val st = viewModel.addToDb(arbuzZakaz)

                    if (st == "good"){
                        fragmentTransaction()
                    }
                }
            }
        }

        return binding.root
    }

    fun countPrice(){

    }

    fun updateLabel(textInputDate: TextInputEditText) {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        textInputDate.setText(dateFormat.format(myCalendar.time))
    }

    fun checkTvNull() {
        if (viewModel.weightFinal == 0 && stat == "") {
            binding.tvWeight.visibility = View.GONE
            binding.tvStatus.visibility = View.GONE
        }
    }

    fun fragmentTransaction() {
        val fragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
        fragment.replace(R.id.fragment_container, ListOfZakazFragment())
        fragment.addToBackStack(null)
        fragment.commit()
    }

    fun dialogFunc() {
        val viewBinding = GryadkaBinding.inflate(layoutInflater)
        val view2 = viewBinding.root
        val dialog = Dialog(requireContext())
        requireActivity().window.setGravity(Gravity.NO_GRAVITY)

        dialog.setContentView(view2)

        viewBinding.rel1.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("1 ряд, 1")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel2.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("1 ряд, 2")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel3.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("1 ряд, 3")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel4.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("2 ряд, 1")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel5.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("2 ряд, 2")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel6.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("2 ряд, 3")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel7.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("3 ряд, 1")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel8.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("3 ряд, 2")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }
        viewBinding.rel9.setOnClickListener {
            stat = viewModel.randomStatus()
            binding.tvWeight.visibility = View.VISIBLE
            binding.tvStatus.visibility = View.VISIBLE
            binding.etgryadka.setText("3 ряд, 3")
            binding.tvStatus.text = "Статус арбуза: $stat"
            binding.tvWeight.text = "Вес: ${viewModel.weightFinal} кг"
            dialog.cancel()
        }

        dialog.show()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        binding.tvQuantity.text = "$p1"
        val price = viewModel.weightFinal * 100 * p1
        binding.tvPrice.text = "$price тг"
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}