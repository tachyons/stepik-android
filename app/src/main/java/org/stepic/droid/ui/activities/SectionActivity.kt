package org.stepic.droid.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import org.stepic.droid.R
import org.stepic.droid.base.SingleFragmentActivity
import org.stepic.droid.ui.fragments.SectionsFragment

class SectionActivity : SingleFragmentActivity() {

    override fun createFragment() = SectionsFragment.newInstance()

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_start, R.anim.slide_out_to_end)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        (fragment as? SectionsFragment)?.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.syllabus_title);
    }
}
