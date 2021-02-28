package com.example.p4.data

import com.example.p4.data.network.services.*

class DataManager private constructor() {

    val splashService: SplashService
        get() = SplashService.instance!!

    val signinService: SigninService
        get() = SigninService.instance!!

    val homeService: HomeService
        get() = HomeService.instance!!

    val procedureService: ProcedureService
        get() = ProcedureService.instance!!

    companion object {
        @JvmStatic
        @get:Synchronized
        var instance: DataManager? = null
            get() {
                if (field == null) {
                    field = DataManager()
                }
                return field
            }
            private set
    }
}