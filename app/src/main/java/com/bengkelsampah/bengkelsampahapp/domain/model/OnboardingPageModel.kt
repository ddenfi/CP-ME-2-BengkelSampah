package com.bengkelsampah.bengkelsampahapp.domain.model

import androidx.annotation.DrawableRes
import com.bengkelsampah.bengkelsampahapp.R

data class OnboardingPageModel(
    @DrawableRes val id: Int,
    val title: String,
    val desc: String
) {
    companion object {
        val listOnboarding = listOf(
            OnboardingPageModel(
                id = R.drawable.img_onboard_1,
                title = "Make Money From Waste",
                desc = "Bengkel sampah hadir sebagai solusi manajemen limbah rumah tangga dan menciptakan nilai ekonomis sampah menjadi rupiah"
            ),
            OnboardingPageModel(
                id = R.drawable.img_onboard_2,
                title = "Waste Management",
                desc = "Memudahkan untuk mengorganisasi sampah anda"
            ),
            OnboardingPageModel(
                id = R.drawable.img_onboard_3,
                title = "Pick-Up Waste",
                desc = "Tidak perlu khawatir Benkel Sampah akan menjeput sampah anda yang siap dijadikan uang"
            ),
        )
    }
}
