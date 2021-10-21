package analysis.paralysis

import analysis.paralysis.databinding.FragmentResultBinding
import analysis.paralysis.databinding.FragmentSortBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import java.util.*

class ResultFragment : Fragment() {
    private var binding:FragmentResultBinding? = null
    private val args: ResultFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.apply{
            var buffer:StringBuffer = StringBuffer()
            args.items.forEach {
                buffer.append(it).append(" ")
            }
            etResult.setText(buffer.toString())
        }
    }

}