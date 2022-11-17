package nktns.spacex.ui.launches

import androidx.recyclerview.widget.DiffUtil
import nktns.spacex.ui.LaunchUIModel

sealed class LaunchesState {
    object InitialLoading : LaunchesState()

    data class Content(
        val launchList: List<LaunchUIModel>,
        val diffResult: DiffUtil.DiffResult,
    ) : LaunchesState()
}
