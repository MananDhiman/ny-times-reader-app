package manandhiman.ny_times_reader_unofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import manandhiman.ny_times_reader_unofficial.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
  private lateinit var binding: FragmentAboutBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
    // Inflate the layout for this fragment
    return binding.root
  }

}