package com.example.arbuz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arbuz.R
import com.example.arbuz.adapters.ZakazListAdapter
import com.example.arbuz.databinding.FragmentListOfZakazBinding
import com.example.arbuz.viewmodels.ListOfZakazViewModel

class ListOfZakazFragment : Fragment() {
    private lateinit var binding: FragmentListOfZakazBinding
    private lateinit var viewModel: ListOfZakazViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListOfZakazBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ListOfZakazViewModel::class.java]

        viewModel.getList()
        getListOfZakaz()

        binding.floatBtn.setOnClickListener {
            fragmentTransaction()
        }

        return binding.root
    }

    private fun getListOfZakaz() {
        viewModel.liveDataList.observe(requireActivity()) {
            if (it.isEmpty()) {
                binding.tvEmptyText.visibility = View.VISIBLE
                binding.imgEmptyFolder.visibility = View.VISIBLE
                binding.recyclerViewListOfZakaz.visibility = View.GONE
            } else {
                binding.tvEmptyText.visibility = View.GONE
                binding.imgEmptyFolder.visibility = View.GONE
                binding.recyclerViewListOfZakaz.visibility = View.VISIBLE
                binding.recyclerViewListOfZakaz.layoutManager =
                    LinearLayoutManager(requireContext())
                binding.recyclerViewListOfZakaz.adapter = ZakazListAdapter(requireContext(), viewModel.listZakaz)
            }

        }
    }

    fun fragmentTransaction() {
        val fragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
        fragment.replace(R.id.fragment_container, MakeZakazFragment())
        fragment.addToBackStack(null)
        fragment.commit()
    }

}