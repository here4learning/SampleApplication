package org.test.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_view_profile.view.*
import org.test.sample.BaseFragment
import org.test.sample.R
import org.test.sample.model.Profile

class ProfileViewFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_profile,container, false)

        arguments?.getParcelable<Profile>("profile")?.run{
             view.text_view_display_first_name.text = firstName
             view.text_view_display_last_name.text = lastName
             view.text_view_display_age.text = age?.toString()
        }
        return view
    }


}