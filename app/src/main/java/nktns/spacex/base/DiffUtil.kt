package nktns.spacex.base

import androidx.recyclerview.widget.DiffUtil

fun <T : Any> calculateDiff(
    oldList: List<T>,
    newList: List<T>,
    itemIdProvider: T.() -> Any
): DiffUtil.DiffResult = DiffUtil.calculateDiff(UniversalDiffCallback(oldList, newList, itemIdProvider))
