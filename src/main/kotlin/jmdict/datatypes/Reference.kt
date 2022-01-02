package jmdict.datatypes

data class Reference<T>(
    /**
     * The string value origianlly given to refer to the object prior to processing
     */
    val referralText: String?,

    var value: T? = null
)
