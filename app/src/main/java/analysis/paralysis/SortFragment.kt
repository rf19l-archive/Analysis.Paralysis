package analysis.paralysis

import analysis.paralysis.databinding.FragmentHomeBinding
import analysis.paralysis.databinding.FragmentSortBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs

class Sort_Fragment : Fragment() {

    private var binding: FragmentSortBinding? = null
    private val args: Sort_FragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply{

        }
        super.onViewCreated(view, savedInstanceState)
    }

}