package org.test.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tsongkha.spinnerdatepicker.DatePickerDialog
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import org.test.sample.BaseFragment
import org.test.sample.BundleConstant.PROFILE_DATA

import org.test.sample.R
import org.test.sample.di.ProfileViewModelFactory
import org.test.sample.forLongTime
import org.test.sample.showAsToast
import org.test.sample.viewmodel.ProfileViewModel
import java.util.*

class EditProfileFragment : BaseFragment() {

    private var mCalendar = Calendar.getInstance()
    private var mDatePickerDialog: DatePickerDialog? = null
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        initUI(view)
        return view
    }

    private fun initUI(view: View) {
        view.edit_text_dob.setOnClickListener {
            openDatePickerDialog()
        }

        view.button_next.setOnClickListener {
            viewModel.validate(
                firstName = view.edit_text_first_name.text.toString(),
                lastName = view.edit_text_last_name.text.toString(),
                dob = view.edit_text_dob.text.toString()
            )
        }
    }

    private fun openDatePickerDialog() {
        if (mDatePickerDialog == null) {
            val calendar = Calendar.getInstance()
            mDatePickerDialog = SpinnerDatePickerDialogBuilder()
                .context(context)
                .callback(datePickerListener)
                .showTitle(true)
                .spinnerTheme(R.style.NumberPickerStyle)
                .showDaySpinner(true)
                .defaultDate(
                    mCalendar.get(Calendar.YEAR) - 20,
                    mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.DAY_OF_MONTH)
                ).maxDate(
                    calendar.get(Calendar.YEAR) - 1,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).build()
        }
        mDatePickerDialog?.show()
    }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            mCalendar.set(selectedYear, selectedMonth, selectedDay)
            view?.edit_text_dob?.setText(
                StringBuilder().append(selectedDay)
                    .append("/").append(selectedMonth + 1).append("/").append(selectedYear)
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ProfileViewModelFactory().create()
        handleObserverForFirstName()
        handleObserverForLastName()
        handleObserverForDOB()
        handleObserverForProfileData()
    }

    private fun handleObserverForFirstName() {
        viewModel.isFirstNameEmpty.observeForever {
            R.string.msg_err_first_name_missing showAsToast forLongTime
        }
    }

    private fun handleObserverForLastName() {
        viewModel.isLastNameEmpty.observeForever {
            R.string.msg_err_last_name_missing showAsToast forLongTime
        }
    }

    private fun handleObserverForDOB() {
        viewModel.isDOBEmpty.observeForever {
            R.string.msg_err_dob_missing showAsToast forLongTime
        }
    }

    private fun handleObserverForProfileData() {
        viewModel.profileData.observeForever { profile ->
            val bundle = bundleOf("profile" to profile)
            findNavController().navigate(
                R.id.action_editProfileFragment_to_profileViewFragment,
                bundle
            )
        }
    }
}