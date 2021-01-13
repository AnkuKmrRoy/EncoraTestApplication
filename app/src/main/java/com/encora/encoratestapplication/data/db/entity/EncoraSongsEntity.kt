package com.encora.encoratestapplication.data.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EncoraTestAssignment")
data class EncoraSongsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "audio_id") val audio_id: String?,
    @ColumnInfo(name = "icon_url") val icon_url: String?,
    @ColumnInfo(name = "image_url") val image_url:String?,
    @ColumnInfo(name = "audio_title") val title:String?,
    @ColumnInfo(name = "audio_type") val type:String?,
    @ColumnInfo(name = "audio_href") val href:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<EncoraSongsEntity> {
        override fun createFromParcel(parcel: Parcel): EncoraSongsEntity {
            return EncoraSongsEntity(parcel)
        }

        override fun newArray(size: Int): Array<EncoraSongsEntity?> {
            return arrayOfNulls(size)
        }
    }
}
