package com.bengkelsampah.bengkelsampahapp.domain.model

data class UserBankSampahModel(
    val userId:String,
    val customerId:String,
    val name:String,
    val balance:Int,
    val photoUrl:String,
    val bankSampahName:String
){
    companion object{
        val dummyData = UserBankSampahModel(
            userId = "ID-123",
            customerId = "BS-12311253",
            name = "Lesti Kejora",
            balance = 25000,
            photoUrl = "https://awsimages.detik.net.id/community/media/visual/2023/09/13/lesti-kejora_43.jpeg?w=600&q=90",
            bankSampahName = "Desa Tegalrejo"
        )
    }
}