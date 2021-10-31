package com.medhdj.features.highlights.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.medhdj.core.features.R
import com.medhdj.core.features.databinding.ActivityHighlightsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HighlightsActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityHighlightsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        findNavController(R.id.navHostFragment).apply {
            setGraph(
                R.navigation.highlights_graph,
                intent.extras
            )
        }
    }
}
