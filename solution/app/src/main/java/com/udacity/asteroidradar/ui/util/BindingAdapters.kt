package com.udacity.asteroidradar.ui.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.R

@BindingAdapter("remoteUrl")
fun bindAsteroidPictureOfTheDay(imageView: ImageView, imageUrl: String?) {
    Picasso.with(imageView.context).apply { isLoggingEnabled = BuildConfig.DEBUG }
        .load(imageUrl)
        .placeholder(R.drawable.placeholder_picture_of_day)
        .error(R.drawable.placeholder_picture_of_day)
        .into(imageView)
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    imageView.setImageResource(
        if (isHazardous) {
            R.drawable.ic_status_potentially_hazardous
        } else {
            R.drawable.ic_status_normal
        }
    )
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    imageView.setImageResource(
        if (isHazardous) {
            R.drawable.asteroid_hazardous
        } else {
            R.drawable.asteroid_safe
        }
    )
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
