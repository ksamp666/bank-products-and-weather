package bankproducts

import bankproducts.exceptions.InsufficientFundsException
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException
import java.util.*

@DisplayName("Debit card tests")
class DebitCardTest {
    private fun createDebitCard(balance: Double): DebitCard {
        return DebitCard("My Card", balance)
    }

    @Test
    @DisplayName("Check top up")
    fun topUpTest() {
        val card = createDebitCard(1000.0)
        card.topUp(100.0)
        Assert.assertEquals(1100.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check top up with zero and negative value")
    fun topUpZeroAndNegativeTest() {
        val card = createDebitCard(1000.0)
        Assert.assertThrows(IllegalArgumentException::class.java) { card.topUp(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { card.topUp(-100.0) }
    }

    @Test
    @DisplayName("Check withdraw")
    fun withdrawTest() {
        val card = createDebitCard(1000.0)
        card.withdraw(100.0)
        Assert.assertEquals(900.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check withdraw with zero and negative value")
    fun withdrawZeroAndNegativeTest() {
        val card = createDebitCard(1000.0)
        Assert.assertThrows(IllegalArgumentException::class.java) { card.withdraw(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { card.withdraw(-100.0) }
    }

    @Test
    @DisplayName("Check withdrawing more than available on balance")
    fun withdrawMoreThanBalanceTest() {
        val card = createDebitCard(1000.0)
        Assert.assertThrows(InsufficientFundsException::class.java) { card.withdraw(2000.0) }
    }

    @Test
    @DisplayName("Check initializing card with negative balance")
    fun createCardWithNegativeBalanceTest() {
        Assert.assertThrows(IllegalArgumentException::class.java) { createDebitCard(-500.0) }
    }
}