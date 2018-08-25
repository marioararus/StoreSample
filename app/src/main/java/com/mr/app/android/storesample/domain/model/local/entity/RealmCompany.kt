package com.mr.app.android.storesample.domain.model.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Marioara Rus on 8/25/2018.
 */
open class RealmCompany(

        @PrimaryKey
        var id: Long = 0,
        var name: String = "") : RealmObject()
