package com.a3xh1.jetpackex

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        print(D(10).code)
    }
}


abstract class Base {
    val code = caculate()
    abstract fun caculate(): Int
}

class D(private val x: Int) : Base() {
    override fun caculate() = x
}
