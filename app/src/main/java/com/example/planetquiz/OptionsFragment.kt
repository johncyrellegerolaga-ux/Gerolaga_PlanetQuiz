package com.example.planetquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class OptionsFragment : Fragment(R.layout.fragment_options) {

    private var questionId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionId = arguments?.getInt(ARG_QUESTION_ID) ?: 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvQuestion = view.findViewById<TextView>(R.id.tv_selected_question)
        val tvResult = view.findViewById<TextView>(R.id.tv_answer_result)
        val btnNext = view.findViewById<Button>(R.id.btn_next_question)

        tvQuestion.text = when (questionId) {
            1 -> getString(R.string.q_largest)
            2 -> getString(R.string.q_moons)
            3 -> getString(R.string.q_side)
            else -> ""
        }

        val planetButtons = mapOf(
            R.id.btn_mercury to "MERCURY",
            R.id.btn_venus to "VENUS",
            R.id.btn_earth to "EARTH",
            R.id.btn_mars to "MARS",
            R.id.btn_jupiter to "JUPITER",
            R.id.btn_saturn to "SATURN",
            R.id.btn_uranus to "URANUS",
            R.id.btn_neptune to "NEPTUNE"
        )

        for ((buttonId, planetName) in planetButtons) {
            view.findViewById<Button>(buttonId).setOnClickListener {
                checkAnswer(planetName, tvResult, btnNext)
            }
        }

        btnNext.setOnClickListener {
            if (questionId < 3) {
                val nextFragment = OptionsFragment.newInstance(questionId + 1)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, nextFragment)
                    .commit()
            } else {
                parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, QuestionsFragment())
                    .commit()
            }
        }
    }

    private fun checkAnswer(selectedPlanet: String, tvResult: TextView, btnNext: Button) {
        val (correctPlanet, detailStringId) = when (questionId) {
            1 -> "JUPITER" to R.string.ans_jupiter
            2 -> "SATURN" to R.string.ans_saturn
            3 -> "URANUS" to R.string.ans_uranus
            else -> "" to 0
        }

        tvResult.visibility = View.VISIBLE
        if (selectedPlanet == correctPlanet) {
            val fullText = getString(R.string.correct_prefix) + getString(detailStringId)
            tvResult.text = fullText
            btnNext.visibility = View.VISIBLE
            if (questionId == 3) {
                btnNext.text = "Finish Quiz"
            }
        } else {
            tvResult.text = getString(R.string.wrong_text)
            btnNext.visibility = View.GONE
        }
    }

    companion object {
        private const val ARG_QUESTION_ID = "question_id"

        fun newInstance(questionId: Int): OptionsFragment {
            val fragment = OptionsFragment()
            val args = Bundle()
            args.putInt(ARG_QUESTION_ID, questionId)
            fragment.arguments = args
            return fragment
        }
    }
}