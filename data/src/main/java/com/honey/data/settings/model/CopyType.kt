package com.honey.data.settings.model

enum class CopyType(
    val type: String
) {
    EDITED (type = "Edited"),
    RAW (type = "Raw"),
    NO_RETURN (type = "")
}