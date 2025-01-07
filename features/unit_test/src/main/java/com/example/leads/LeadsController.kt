package com.example.leads

import androidx.core.text.isDigitsOnly

class LeadsController {

    private val _leads = mutableSetOf<Lead>()
    val leads: Set<Lead> = _leads
    fun addLead(lead: Lead) {
        if(!isValidLead(lead)) return
        _leads.add(lead)
    }

    private fun isValidLead(lead: Lead) : Boolean{
        if(lead.phoneNumber.any { it.isLetter() }) throw IllegalArgumentException("Phone Number is not valid")
        if(lead.firstName.length < 3) return false
        if(lead.lastName.length < 3) return false
        if(lead.phoneNumber.length != 11) return false
        if(!lead.phoneNumber.all{ it.isDigit()}) return false
        if(!lead.phoneNumber.startsWith("01")) return false

        return true
    }


}
