package manandhiman.ny_times_reader_unofficial.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import manandhiman.ny_times_reader_unofficial.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
  private lateinit var binding: FragmentAboutBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentAboutBinding.inflate(layoutInflater, container, false)

    binding.imageButtonGithub.setOnClickListener { setGithubIntent() }
    binding.imageButtonLinkedin.setOnClickListener { setLinkedinIntent() }

    return binding.root
  }

  private fun setGithubIntent() {
    try{
      val intent = Intent(Intent.ACTION_VIEW)
      intent.data = Uri.parse("https://github.com/MananDhiman/")
      startActivity(intent)
    }

    catch(e: Exception){
      Toast.makeText(activity,"Some Error Occurred. Please Try Again",Toast.LENGTH_LONG).show()
    }
  }

  private fun setLinkedinIntent() {
    try{
      val intent = Intent(Intent.ACTION_VIEW)
      intent.data = Uri.parse("https://www.linkedin.com/in/manan-dhiman/")
      startActivity(intent)
    }

    catch(e: Exception){
      Toast.makeText(activity,"Some Error Occurred. Please Try Again",Toast.LENGTH_LONG).show()
    }

  }

}