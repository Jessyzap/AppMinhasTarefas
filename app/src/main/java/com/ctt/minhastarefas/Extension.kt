package com.ctt.minhastarefas

import androidx.fragment.app.Fragment
import kotlin.random.Random

fun Fragment.randomId(): Int {
    return Random.nextInt(0, 1000000000)
}