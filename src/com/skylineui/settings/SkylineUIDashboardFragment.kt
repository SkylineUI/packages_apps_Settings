/*
 * Copyright (C) 2023 SkylineUI Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skylineui.settings

import androidx.preference.Preference

import com.android.internal.logging.nano.MetricsProto
import com.android.settings.dashboard.DashboardFragment
import com.skylineui.support.fragments.ColorPickerFragment
import com.skylineui.support.preferences.ColorPickerPreference

abstract class SkylineUIDashboardFragment : DashboardFragment() {

    override fun getMetricsCategory(): Int = MetricsProto.MetricsEvent.SKYLINEUI

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (preference is ColorPickerPreference) {
            ColorPickerFragment(preference.color).apply {
                setOnConfirmListener {
                    preference.setColor(it)
                }
            }.show(childFragmentManager, COLOR_PICKER_DIALOG_KEY)
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

    companion object {
        const val COLOR_PICKER_DIALOG_KEY = "color_picker_dialog"
    }
}
