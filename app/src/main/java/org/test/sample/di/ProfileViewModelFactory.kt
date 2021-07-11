package org.test.sample.di

import org.test.sample.viewmodel.ProfileViewModel

class ProfileViewModelFactory : Factory<ProfileViewModel> {
    override fun create(): ProfileViewModel {
        return ProfileViewModel()
    }
}