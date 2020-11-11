package com.udacity.asteroidradar.ui.util

fun String.formatWith(format: String, vararg args: Any?) = format.format(this, *args)
