package org.test.sample.viewmodel

import androidx.core.text.trimmedLength
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.joda.time.LocalDateTime
import org.joda.time.Period
import org.joda.time.PeriodType
import org.test.sample.model.Age
import org.test.sample.model.Profile
import org.test.sample.util.getTimeInMillisBasedOnFormat
import java.util.Calendar.*

class ProfileViewModel : BaseViewModel() {

    private var firstNameEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val isFirstNameEmpty: LiveData<Boolean>
        get() {
            return firstNameEmpty
        }

    private var lastNameEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val isLastNameEmpty: LiveData<Boolean>
        get() {
            return lastNameEmpty
        }

    private var dobEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val isDOBEmpty: LiveData<Boolean>
        get() {
            return dobEmpty
        }

    private var modifiedData: MutableLiveData<Profile> = MutableLiveData()
    val profileData: LiveData<Profile>
        get() {
            return modifiedData
        }

    fun validate(firstName: String?, lastName: String?, dob: String?) {
         if(firstName?.trimmedLength() ?: 0 <= 1){
             firstNameEmpty.value = true
             return
         }

         if(lastName?.trimmedLength() ?: 0 <= 1){
             lastNameEmpty.value = true
             return
         }

         if(dob?.trimmedLength() == 0){
             dobEmpty.value = true
             return
         }
         // never will be 0 as timeInMilliSec, so need to check further here
         val timeInMillis = getTimeInMillisBasedOnFormat(format = "dd/MM/yyyy",dob = dob!!)

         modifiedData.value = Profile(
                firstName = firstName, lastName = lastName, age = getAge(timeInMillis)
         )
    }

    private fun getAge(dobInMillis: Long) : Age {
        val calendar = getInstance()
        calendar.timeInMillis = dobInMillis
        val birthDate = LocalDateTime(
            calendar.get(YEAR), calendar.get(MONTH) + 1, calendar.get(
                DAY_OF_MONTH
            ), 0, 0
        )
        val now = LocalDateTime()
        return Age(Period(birthDate, now, PeriodType.yearMonthDayTime()))
    }
}