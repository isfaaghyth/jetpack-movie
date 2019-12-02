package isfaaghyth.app.data.di

import dagger.Module
import dagger.Provides
import isfaaghyth.app.data.routes.NetworkServices
import isfaaghyth.app.network.Network.retrofitClient

@Module class DataModule {

    @Provides @DataScope
    fun provideServices(): NetworkServices {
        return retrofitClient().create(NetworkServices::class.java)
    }

}