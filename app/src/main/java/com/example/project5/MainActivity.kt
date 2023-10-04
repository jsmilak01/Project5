package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {

    lateinit var translatedText: TextView
    private var sourceLanguage = "english"
    private var targetLanguage = "german"
    var translatorToUse = Translation.getClient(TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.GERMAN)
        .build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TextToTranslateFragment>(R.id.edit_text_fragment)
            }
        }
        setContentView(R.layout.activity_main)

        var viewModel: TextToTranslateViewModel = ViewModelProvider(this).get(TextToTranslateViewModel::class.java)

        val englishGermanTranslator = Translation.getClient(TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build())

        val englishSpanishTranslator = Translation.getClient(TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build())

        val spanishEnglishTranslator = Translation.getClient(TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build())

        val spanishGermanTranslator = Translation.getClient(TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build())

        val germanEnglishTranslator = Translation.getClient(TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.GERMAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build())

        val germanSpanishTranslator = Translation.getClient(TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.GERMAN)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build())

        var translatorToUse = englishGermanTranslator
        var useTranslator = true



        fun selectTranslator() {
            if (sourceLanguage == targetLanguage) {
                useTranslator = false
            }
            if (sourceLanguage == "english" && targetLanguage == "german") {
                translatorToUse = englishGermanTranslator
            }
            if (sourceLanguage == "english" && targetLanguage == "spanish") {
                translatorToUse = englishSpanishTranslator
            }
            if (sourceLanguage == "spanish" && targetLanguage == "english") {
                translatorToUse = spanishEnglishTranslator
            }
            if (sourceLanguage == "spanish" && targetLanguage == "german") {
                translatorToUse = spanishGermanTranslator
            }
            if (sourceLanguage == "german" && targetLanguage == "english") {
                translatorToUse = germanEnglishTranslator
            }
            if (sourceLanguage == "german" && targetLanguage == "spanish") {
                translatorToUse = germanSpanishTranslator
            }
        }

        findViewById<RadioGroup>(R.id.source_language_group).setOnClickListener{
            when(findViewById<RadioGroup>(R.id.source_language_group).checkedRadioButtonId) {
                findViewById<RadioButton>(R.id.source_english).id -> sourceLanguage = "english"
                findViewById<RadioButton>(R.id.source_german).id -> sourceLanguage = "german"
                findViewById<RadioButton>(R.id.source_spanish).id -> sourceLanguage = "spanish"
                else -> {}
            }
            selectTranslator()
        }

        findViewById<RadioGroup>(R.id.translation_language_group).setOnClickListener{
            when(findViewById<RadioGroup>(R.id.translation_language_group).checkedRadioButtonId) {
                findViewById<RadioButton>(R.id.translate_english).id -> targetLanguage = "english"
                findViewById<RadioButton>(R.id.translate_german).id -> targetLanguage = "german"
                findViewById<RadioButton>(R.id.translate_spanish).id -> targetLanguage = "spanish"
                else -> {}
            }
            selectTranslator()
        }
    }

}

