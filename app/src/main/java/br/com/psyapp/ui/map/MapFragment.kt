package br.com.psyapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.psyapp.R
import br.com.psyapp.ui.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : BaseFragment(), OnMapReadyCallback {

    private val mapViewModel by viewModels<MapViewModel>()
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val mapFragment = activity?.supportFragmentManager?.findFragmentById(R.id.mv) as? SupportMapFragment
//        mapFragment?.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mv?.onCreate(savedInstanceState)
        mv?.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        mv?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mv?.onResume()
    }

    override fun onPause() {
        mv?.onPause()
        super.onPause()
    }

    override fun onStop() {
        mv?.onStop()
        super.onStop()
    }

    override fun onLowMemory() {
        mv?.onLowMemory()
        super.onLowMemory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let { googleMap ->
            mMap = googleMap
            mMap.setMinZoomPreference(1f)
            val worker1 = LatLng(-23.564257, -46.652689)
            mMap.addMarker(MarkerOptions().position(worker1).title("João da Silva - Psicólogo"))
            val worker2 = LatLng(-23.569197, -46.651114)
            mMap.addMarker(MarkerOptions().position(worker2).title("Everton Ribeiro - Psiquiatra"))
            val worker3 = LatLng(-23.558836, -46.656790)
            mMap.addMarker(MarkerOptions().position(worker3).title("Marcos Paulo - Coach"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(worker1, 15f))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
