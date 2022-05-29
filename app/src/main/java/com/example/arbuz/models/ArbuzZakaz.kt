package com.example.arbuz.models

data class ArbuzZakaz(
    var id: String = "",
    var arbuzGryadka: String = "",
    var arbuzStatus: String= "",
    var arbuzWeight: String="",
    var arbuzQuantity: Int=0,
    var arbuzPrice: String = "",
    var address: String="",
    var phoneNumber: String="",
    var date: String="",
    var time: String="",
    var checkCut: Boolean = false
){}