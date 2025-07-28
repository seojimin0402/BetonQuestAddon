package com.github.mrjimin.betonquestaddon.util

fun <T : Comparable<T>> T.compareWith(op: String, other: T): Boolean {
    return when (op.trim()) {
        "==" -> this == other
        "!=" -> this != other
        ">=" -> this >= other
        "<=" -> this <= other
        ">"  -> this > other
        "<"  -> this < other
        else -> false
    }
}
