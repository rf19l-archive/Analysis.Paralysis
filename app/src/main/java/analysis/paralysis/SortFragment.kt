package analysis.paralysis

import analysis.paralysis.databinding.FragmentSortBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class SortFragment : Fragment() {


    private var binding: FragmentSortBinding? = null
    private val args: SortFragmentArgs by navArgs()

    var items: Array<String>? = null
    var seen:MutableSet<Pair<String,String>>? = null
    var size:Int? = null
    var opt_1: String? = null
    var opt_2: String? = null
    var minIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.apply{
            items = args.items
            seen = mutableSetOf()
            size = args.items.size
            option1.setText(items?.get(minIndex))
            option2.setText(items?.get(minIndex+1))
            opt_1 = option1.text.toString()
            opt_2 = option2.text.toString()

            option1.setOnClickListener {
                if(!onClick(option1,option2,0)){
                    val action = SortFragmentDirections.actionSortFragmentToResultFragment(items!!)
                    findNavController().navigate(action)
                }
            }

            option2.setOnClickListener {
                if(!onClick(option1,option2,1)){
                    val action = SortFragmentDirections.actionSortFragmentToResultFragment(items!!)
                    findNavController().navigate(action)
                }
            }

            btReset.setOnClickListener {
                val action = SortFragmentDirections.actionSortFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            imageButton.setOnClickListener {
                activity?.finishAndRemoveTask()
            }





        }
        super.onViewCreated(view, savedInstanceState)
    }

    /*
        Move item to the right if is clicked or do nothing, reset text of views to some other random pair in the list
     */
    private fun onClick(option_1: TextInputEditText, option_2:TextInputEditText, pos:Int):Boolean{

        // add current pair to set
        var pair = Pair(items!!.get(minIndex), items!![minIndex+1])
        seen!!.add(pair)

        //swap if appropriate
        if(pos==0){
            items?.set(minIndex+1,opt_1!!)
            items?.set(minIndex,opt_2!!)
        }
        // if this is the last possible pair return false
        if(seen!!.size == items!!.size * items!!.size){
            return false
        }

        // Make sure you aren't repeating comparisons
        var count = 0
        //  || seen!!.contains(Pair(pair.second,pair.first))
        while(seen!!.contains(pair) || seen!!.contains(Pair(pair.second,pair.first))){
            if (count > items!!.size * items!!.size){
                return false
            }
            minIndex = Random.nextInt(0,items!!.size-1)
            pair = Pair(items!!.get(minIndex), items!![minIndex+1])
            count +=1

        }
        option_1.setText(items!!.get(minIndex))
        option_2.setText(items!!.get(minIndex+1))
        opt_1 = option_1.text.toString()
        opt_2 = option_2.text.toString()

        return true
    }



}