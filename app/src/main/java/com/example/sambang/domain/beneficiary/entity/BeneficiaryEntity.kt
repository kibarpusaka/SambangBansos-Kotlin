package com.example.sambang.domain.beneficiary.entity

import java.io.Serializable


data class BeneficiaryEntity (
    var id : Int,
    var status: Int,
    var dateProposed: String,
    var support: Int,
    var familyId: Int,
) : Serializable