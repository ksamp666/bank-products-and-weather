package bankproducts

import io.qameta.allure.junit4.DisplayName
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException
import java.util.*

@DisplayName("Credit card tests")
class CreditCardTest {
    private fun createCreditCard(balance: Double): CreditCard {
        return CreditCard("My Card", balance, 0.06f)
    }

    @Test
    @DisplayName("Check top up")
    fun topUpTest() {
        val card = createCreditCard(1000.0)
        card.topUp(100.0)
        Assert.assertEquals(1100.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check top up with zero and negative value")
    fun topUpZeroAndNegativeTest() {
        val card = createCreditCard(1000.0)
        Assert.assertThrows(IllegalArgumentException::class.java) { card.topUp(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { card.topUp(-100.0) }
    }

    @Test
    @DisplayName("Check withdraw")
    fun withdrawTest() {
        val card = createCreditCard(1000.0)
        card.withdraw(100.0)
        Assert.assertEquals(900.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check withdraw with zero and negative value")
    fun withdrawZeroAndNegativeTest() {
        val card = createCreditCard(1000.0)
        Assert.assertThrows(IllegalArgumentException::class.java) { card.withdraw(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { card.withdraw(-100.0) }
    }

    @Test
    @DisplayName("Check withdrawing more than available balance")
    fun withdrawMoreThanBalanceTest() {
        val card = createCreditCard(1000.0)
        card.withdraw(2000.0)
        Assert.assertEquals(-1000.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check receiving card debt")
    fun debtTest() {
        val card = createCreditCard(500.0)
        Assert.assertEquals(0.0, card.getDebt(), 0.0)
        card.withdraw(1000.0)
        Assert.assertEquals(500.0, card.getDebt(), 0.0)
        card.topUp(1000.0)
        Assert.assertEquals(0.0, card.getDebt(), 0.0)
    }

    @Test
    @DisplayName("Check initializing card with negative balance")
    fun createCardWithNegativeBalanceTest() {
        val card = createCreditCard(-500.0)
        Assert.assertEquals(500.0, card.getDebt(), 0.0)
        Assert.assertEquals(-500.0, card.getBalance(), 0.0)
    }
}