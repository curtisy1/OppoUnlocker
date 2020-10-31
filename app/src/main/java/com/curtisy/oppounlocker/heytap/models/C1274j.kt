package com.curtisy.oppounlocker.heytap.models

import android.os.Parcel
import android.os.Parcelable.Creator


class C1274j : Creator<UserEntity?> {
    /* Return type fixed from 'java.lang.Object[]' to match base method */
    // android.os.Parcelable.Creator
    /* bridge */ /* synthetic */ override fun newArray(i: Int): Array<UserEntity?> {
        return arrayOfNulls(i)
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    // android.os.Parcelable.Creator
    /* synthetic */ override fun createFromParcel(parcel: Parcel): UserEntity? {
        val readInt = parcel.readInt()
        val readString = parcel.readString()
        val readString2 = parcel.readString()
        val readString3 = parcel.readString()
        val userEntity = UserEntity()
        userEntity.mo6984a(readInt)
        userEntity.mo6989c(readString!!)
        userEntity.mo6985a(readString2!!)
        userEntity.mo6987b(readString3!!)
        return userEntity
    }
}