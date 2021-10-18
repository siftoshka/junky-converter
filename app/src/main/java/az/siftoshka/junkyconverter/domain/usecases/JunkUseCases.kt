package az.siftoshka.junkyconverter.domain.usecases

/**
 * Data class for use-cases related to Junks.
 */
data class JunkUseCases(
    val getJunks: GetJunks,
    val updateJunk: UpdateJunk
)