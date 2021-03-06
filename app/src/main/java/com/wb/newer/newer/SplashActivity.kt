package com.wb.newer.newer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.util.Log
import android.view.Window
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    var temp = -1

    private lateinit var constraintSet: ConstraintSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val charArray = "newer".toCharArray()
        lottieFontViewGroup.max = charArray.size - 1
//        lottieFontViewGroup.alpha = 0f
//        lottieFontViewGroup.animate()
//                .alpha(1f)
//                .setDuration(3000)
//                .setListener(object :AnimatorListenerAdapter(){
//                    override fun onAnimationEnd(animation: Animator?) {
//                        Log.d("anim","test")
//                    }
//                })

        val animator = ValueAnimator.ofInt(temp, charArray.size - 1)
        animator.duration = 800
        animator.addUpdateListener {
            if (temp != it.animatedValue as Int) {
                temp = it.animatedValue as Int
                if (temp == charArray.size - 1) {
                    lottieFontViewGroup.endListener = object : LottieFontViewGroup.EndListener {
                        override fun onEnd() {
                            startMain()
                        }

                    }
                }
                lottieFontViewGroup.addLetter(charArray[it.animatedValue as Int])

            }

        }
        animator.start()

        constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.centerVertically(R.id.lottieFontViewGroup, 0)
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            val transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(1.0f)
            transition.duration = 2000
            TransitionManager.beginDelayedTransition(constraintLayout, transition)
            constraintSet.applyTo(constraintLayout)
        }, 2000)
    }
    override fun onPause() {
        super.onPause()
    }

    private fun startMain() {
        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 2000)
    }

}
