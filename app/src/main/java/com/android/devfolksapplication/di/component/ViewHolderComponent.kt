package com.android.devfolksapplication.di.component


import com.android.devfolksapplication.di.ViewModelScope
import com.android.devfolksapplication.di.module.ViewHolderModule
import com.android.devfolksapplication.ui.developer.adapter.DevFolksItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: DevFolksItemViewHolder)

}