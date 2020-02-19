package ua.brander.meetingroom.extensions

fun <K, V>Map<*, *>.filterIsInstance() = this.toList().filterIsInstance<Pair<K, V>>().toMap()