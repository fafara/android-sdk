package co.omisego.omisego.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 25/5/2018 AD.
 * Copyright © 2017-2018 OmiseGO. All rights reserved.
 */

/**
 * Represents an avatar containing urls of different sizes.
 *
 * @param original The url of the original image.
 * @param large The url of the large image.
 * @param small The url of the small image.
 * @param thumb The url of the thumb image.
 */
@Parcelize
data class Avatar(
    val original: String? = null,
    val large: String? = null,
    val small: String? = null,
    val thumb: String? = null
) : Parcelable
