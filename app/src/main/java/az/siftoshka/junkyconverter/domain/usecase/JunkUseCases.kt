package az.siftoshka.junkyconverter.domain.usecase

/**
 * Data class for use-cases related to Junks.
 */
data class JunkUseCases(
    val getJunks: GetJunks,
    val updateJunk: UpdateJunk
)