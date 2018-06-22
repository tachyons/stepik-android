package org.stepic.droid.features.achievements.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import kotlinx.android.synthetic.main.dialog_achievement_details.*
import kotlinx.android.synthetic.main.dialog_achievement_details.view.*
import org.stepic.droid.R
import org.stepic.droid.base.App
import org.stepic.droid.features.achievements.util.AchievementResourceResolver
import org.stepic.droid.model.achievements.AchievementFlatItem
import org.stepic.droid.ui.util.wrapWithGlide
import org.stepic.droid.util.argument
import javax.inject.Inject

class AchievementDetailsDialog: DialogFragment() {
    companion object {
        const val TAG = "achievement_details_dialog"

        fun newInstance(achievementFlatItem: AchievementFlatItem): AchievementDetailsDialog =
                AchievementDetailsDialog().apply { achievementItem = achievementFlatItem }
    }

    private var achievementItem by argument<AchievementFlatItem>()

    @Inject
    lateinit var achievementResourceResolver: AchievementResourceResolver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component().inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_achievement_details, null, false)

        view.apply {
            achievementTitle.text = achievementResourceResolver.resolveTitleForKind(achievementItem.kind)
            achievementDescription.text = achievementResourceResolver.resolveDescription(achievementItem)
            achievementIcon.apply {
                wrapWithGlide().setImagePath(achievementResourceResolver.resolveAchievementIcon(achievementItem, this))
            }

            achievementLevelProgress.progress = achievementItem.currentScore.toFloat() / achievementItem.targetScore
            achievementLevels.progress = achievementItem.currentLevel
            achievementLevels.total = achievementItem.maxLevel
        }

        return MaterialDialog.Builder(context)
                .theme(Theme.LIGHT)
                .customView(view, false)
                .build()
    }
}