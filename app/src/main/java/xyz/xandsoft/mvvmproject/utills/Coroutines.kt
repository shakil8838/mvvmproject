package xyz.xandsoft.mvvmproject.utills

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

    fun main(mainWork: suspend(() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            mainWork()
        }
}