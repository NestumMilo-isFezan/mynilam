package com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel

data class ProfileFormState(
    var firstName: String = "",
    var lastName: String = "",
    var motto: String = "",
    var firstNameError: String? = null,
    var lastNameError: String? = null,
    var mottoError: String? = null,
    var profileId: Int = 0,
    var userId: Int = 0
)