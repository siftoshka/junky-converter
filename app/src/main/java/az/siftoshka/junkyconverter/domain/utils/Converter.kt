package az.siftoshka.junkyconverter.domain.utils

import java.text.DecimalFormat

/**
 * Converter extension file.
 */
fun String.moneyFormat(): String {
    if (this == "0") return this
    return if (contains(".")) DecimalFormat("##,###.00").format(toFloat())
    else DecimalFormat("##,###").format(toFloat())
}