package com.sample.todolist.navigation

import android.util.Log
import androidx.navigation.NavHostController

class NavigationManager private constructor() {

    private lateinit var _navController: NavHostController


    private constructor(navController: NavHostController) : this() {
        _navController = navController
    }

    companion object {

        @Volatile
        private var _INSTANCE_: NavigationManager? = null

        val NavController: NavHostController
            get() {
                return _INSTANCE_?._navController
                    ?: throw NullPointerException("Navigation Manager is not initialized")
            }

        fun init(navController: NavHostController) {

            val tempInstance = _INSTANCE_;

            if (tempInstance != null) return;

            synchronized(this) {
                val instance = NavigationManager(navController)
                _INSTANCE_ = instance
                Log.d("Navigation Manager", "Navigation Manager initialization complete")
                return
            }

        }

    }
}