package com.example.test13_16_17_18.test17

import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.ListPreference
import android.preference.Preference
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat
import com.example.test13_16_17_18.R

class MySettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
//
//        val idPreference: EditTextPreference? = findPreference("id")
//        val colorPreference: ListPreference? = findPreference("color")
//
//        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
//        idPreference?.summaryProvider =
//            Preference.SummaryProvider<EditTextPreference> { preference ->
//                val text = preference.text
//                if (TextUtils.isEmpty(text)) {
//                    "설정이 되지 않았습니다."
//                } else {
//                    "설정된 id 값은 $text 입니다. "
//                }
//            }
    }
}