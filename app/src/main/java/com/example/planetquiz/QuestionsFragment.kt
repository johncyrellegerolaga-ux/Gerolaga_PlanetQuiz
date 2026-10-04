package com.example.planetquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnQ1 = view.findViewById<Button>(R.id.btn_question1)
        val btnQ2 = view.findViewById<Button>(R.id.btn_question2)
        val btnQ3 = view.findViewById<Button>(R.id.btn_question3)

        btnQ1.setOnClickListener { openOptionsFragment(1) }
        btnQ2.setOnClickListener { openOptionsFragment(2) }
        btnQ3.setOnClickListener { openOptionsFragment(3) }
    }

    private fun openOptionsFragment(questionId: Int) {
        val fragment = OptionsFragment.newInstance(questionId)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}