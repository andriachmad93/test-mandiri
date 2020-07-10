package test.candidate.test_mandiri.models

import android.os.Parcel
import android.os.Parcelable

data class NewsDetailModel (
    var author: String? = ""
    , var title: String? = ""
    , var url: String? = ""
    , var urlToImage: String? = ""
    , var publishedAt: String? = ""
    , var description: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsDetailModel> {
        override fun createFromParcel(parcel: Parcel): NewsDetailModel {
            return NewsDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}