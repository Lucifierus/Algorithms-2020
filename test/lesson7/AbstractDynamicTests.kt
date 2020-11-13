package lesson7

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals("", longestCommonSubSequence("", ""))
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        val expectedLength = "e kerwelkkd r".length
        assertEquals(
            expectedLength, longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
            ).length, "Answer must have length of $expectedLength, e.g. 'e kerwelkkd r' or 'erhlkrw kjk r'"
        )
        val expectedLength2 = """ дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent().length
        assertEquals(
            expectedLength2, longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )

        val expectedLength3 = """Пе т пеа кта сме ил.
евочи а
т от а как
ли ласти.
от я не р.
о т пле  .
о  ына
тни
н актк
 ет оемут ле к""".trimIndent().length
        assertEquals(
            expectedLength3, longestCommonSubSequence(
                """
Почему ты плачешь? — Так. —
Плакать так смешно и глупо.
Зареветь, не кончив супа!
Отними от глаз кулак!

Если плачешь, есть причина.
Я отец, и я не враг.
Почему ты плачешь? — Так. —
Ну какой же ты мужчина?

Отними от глаз кулак!
Что за нрав такой? Откуда?
Рассержусь, и будет худо!
Почему ты плачешь? — Так.
                """.trimIndent(),
                """
Приехали! Приехали!
Родители приехали!
С конфетами, с орехами
Родители приехали.

Девочки и мальчики
Прыгают от радости:
В каждом чемоданчике
Яблоки и сладости.

Вот для дочки
Танечки
В узелочке
Прянички.
А вот это пироги,
Для себя их береги.

Вот для сына
Петеньки
Леденцы
В пакетике.
Это Пете моему,
Это больше никому!
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength3"
        )
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(
            listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(
                listOf(
                    23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                    45, 76, 15, 99, 100, 88, 84, 35, 88
                )
            )
        )
        assertEquals(
            listOf(11, 32, 45, 46, 56, 76, 77, 123, 1245, 6755, 45450, 345673, 345674, 445674, 645674),
            longestIncreasingSubSequence(
                listOf(
                    11, 32, 45, 23, 76, 35, 85, 9, 2, 46, 56, 76, 77, 77, 70, 6, 7, 56, 4, 3, 12,
                    123, 1245, 6755, 45450, 345673, 345674, 445674, 645674
                )
            )
        )
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))
        assertEquals(28, shortestPathOnField("input/field_in4.txt"))
        assertEquals(222, shortestPathOnField("input/field_in5.txt"))
        assertEquals(15, shortestPathOnField("input/field_in6.txt"))
    }

}