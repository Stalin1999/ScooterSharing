package dk.itu.moapd.scootersharing
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dk.itu.moapd.scootersharing.databinding.FragmentStartRideBinding

class StartRideFragment : Fragment() {
    private var _binding: FragmentStartRideBinding? = null
    private val binding get() = _binding!!

    companion object {
        lateinit var ridesDB: RidesDB
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStartRideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val scooter : Scooter = Scooter ("", "", System.currentTimeMillis())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()
        ridesDB = RidesDB.get(context)

        super.onViewCreated(view, savedInstanceState)

        // Define the UI components behavior.
        with (binding) {
            startButton.setOnClickListener {
                scooter.name = nameText.text.toString()
                scooter.where = whereText.text.toString()
                Toast.makeText(
                    context,
                    R.string.successfully_placed_bike,
                    Toast.LENGTH_SHORT)
                    .show()
                ridesDB.addScooter(scooter.name, scooter.where)
                updateUI()
            }
            backButton.setOnClickListener {
                val intent = Intent(context, ScooterSharingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun updateUI () {
        binding.lastAddedText.text = ridesDB.getLastScooterInfo()
        binding.whereText.text.clear()
        binding.nameText.text.clear()
    }
}