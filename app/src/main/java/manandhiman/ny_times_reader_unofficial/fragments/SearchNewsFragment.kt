package manandhiman.ny_times_reader_unofficial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.databinding.FragmentSearchNewsBinding

class SearchNewsFragment : Fragment() {
  private lateinit var binding: FragmentSearchNewsBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentSearchNewsBinding.inflate(layoutInflater, container, false)
    // Inflate the layout for this fragment
    return binding.root
  }

}