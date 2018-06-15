package com.wb.newer.newer

import android.os.Handler
import org.junit.Test

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/14.
 * Description:
 */
class KotlinTest {
    @Test
    fun test() {
        val person = Person(null, 10)
        val name: String = person.name ?: "unknown"
        person.name ?: return
        System.out.println(name)
    }

    data class Person(val name: String? = "default", val age: Int = 5) {

        var test1: String = ""
            set(value) {
                field = "$value test1"
            }
        var test2: String = ""
            set(value) {
                field = "$value test2"
            }


        override fun toString(): String {
            val cargo: Number = 0
            return when (cargo) {
                1 -> "tiny"
                0 -> "empty"
                in 2..10 -> "test"
                is Int -> "big inty"
                else -> "$cargo"
            }
        }

    }

    @Test
    fun stringTemplate() {
        val language = "kotlin"
        val text = "$language has ${language.length} characters"
        System.out.println(text)
    }

    @Test
    fun testWhenExpression() {
        val person = Person("kotlin", 0)
        person.toString()
        println(person.toString())
        person.test1 = "test"
        person.test2 = "test"
        println(person.test1)
        println(person.test2)

    }

    @Test
    fun testFor() {
        for (i in 1..3) {
            println("$i")
        }
        for (i in 3 downTo 1) {
            println("$i")
        }
        val array = arrayOf("a", "b", "c")
        for (i in 1 until array.size step 2)
            println(i)
        for ((index, element) in array.withIndex())
            println("$index and $element")
        val map = mapOf(1 to "one", 2 to "two")
        for ((key, value) in map)
            println("$key and $value")

    }

    @Test
    fun testDeconstruction() {
        val person = Person()
        val lisenter = object : testSealedLisenter{
            override fun doListener(result: NetworkResult) {
                when(result){
                    is Success->{}
                }
            }

        }
        val preference:String by lazy {
            ""
        }
    }

    interface testSealedLisenter{
        fun doListener(result: NetworkResult)
    }


}