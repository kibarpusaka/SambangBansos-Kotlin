package com.example.sambang.domain.proposal.entity

data class ResidentInformationEntity(
    var id : Int,
    var nik : String,
    var name : String,
    var placeOfBirth : String,
    var dateOfBirth : String,
    var address : String,
    var rt : Int? = null,
    var rw : Int? = null,
    var isNikValid : Boolean = false,
    var villageId : Int,
    var familyId : Int
)