import org.junit.Assert.assertEquals
import org.junit.Test


class MainKtTest {


    @Test
    fun commissionVisa() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 1000 // текущий перевод
        val card = "Visa"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(35, result)

    }

    @Test
    fun commissionMcPercent() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 1000 // текущий перевод
        val card = "Mastercard"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(0, result)

    }

    @Test
    fun commissionMCzero() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 80_000 // текущий перевод
        val card = "Mastercard"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(480, result)

    }

    @Test
    fun commissionVisaPercent() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 70_000 // текущий перевод
        val card = "Visa"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(525, result)

    }
    @Test
    fun commissionMir() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 70_000 // текущий перевод
        val card = "Мир"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(0, result)

    }
    @Test
    fun commissionDayLimit() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 200000 // текущий перевод
        val card = "Мир"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(-1, result)

    }

    @Test
    fun commissionMonthLimit() {

        val transferMonth = 1000 // сумма совершенных переводов за месяц
        val currentTransfer = 700000 // текущий перевод
        val card = "Мир"

        val result = commissionCalc(card, currentTransfer,  transferMonth)
        assertEquals(-1, result)

    }


}