package dk.itu.moapd.scootersharing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dk.itu.moapd.scootersharing.databinding.FragmentScooterSharingBinding

class ScooterSharingFragment : Fragment(){
    private var _binding: FragmentScooterSharingBinding? = null
    private val binding get() = _binding!!

    companion object {
        lateinit var ridesDB : RidesDB
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentScooterSharingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()
        val data = ArrayList<Scooter>()
        ridesDB = RidesDB.get(context)

        super.onViewCreated(view, savedInstanceState)

        // Define the UI components behavior.
        with (binding) {
            startButton.setOnClickListener {
                val intent = Intent(context, StartRideActivity::class.java)
                startActivity(intent)
            }
            editButton.setOnClickListener {
                val intent = Intent(context, EditRideActivity::class.java)
                startActivity(intent)
            }
            listRidesButton.setOnClickListener {
                data.clear()
                for (i in ridesDB.getScooters()) {
                    data.add(Scooter(i.name, i.where, i.timestamp))
                }
                val adapter = RidesList(context, R.layout.list_rides, data)
                binding.listOfRides.adapter = adapter
            }
        }
    }
}