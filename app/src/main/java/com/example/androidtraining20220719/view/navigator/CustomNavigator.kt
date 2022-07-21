package com.example.androidtraining20220719.view.navigator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * When using default FragmentNavigator, fragments are recreated when switching the bottom tab.
 * This is why CustomNavigator has to be created
 * ref: https://star-zero.medium.com/navigation-bottomnavigationview%E3%81%A7fragment%E3%81%AE%E7%8A%B6%E6%85%8B%E3%82%92%E6%AE%8B%E3%81%99-46ae5a99dac2
 */

@Navigator.Name("custom_fragment")
class CustomNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {

        val tag = destination.id.toString()
        val transaction = manager.beginTransaction()

        val currentFragment = manager.primaryNavigationFragment
        if (currentFragment != null) {
            transaction.detach(currentFragment)
        }

        val className = destination.className
        var fragment = manager.findFragmentByTag(tag)
        if (fragment == null) {
            //fragment = destination.createFragment(args)
            fragment = manager.fragmentFactory.instantiate(context.classLoader, className)
            transaction.add(containerId, fragment, tag)
        } else {
            transaction.attach(fragment)
        }
        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()

        return destination
    }
}