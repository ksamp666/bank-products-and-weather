package bankproducts

import bankproducts.enums.Currency
import bankproducts.exceptions.InsufficientFundsException
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert
import org.junit.Test
import java.util.*

@DisplayName("Currency debit card tests")
class CurrencyDebitCardTest {
    private fun createCurrencyDebitCard(balance: Double, currency: Currency): CurrencyDebitCard {
        return CurrencyDebitCard("My Card", currency, balance)
    }

    @Test
    @DisplayName("Check top up")
    fun topUpTest() {
        val card = createCurrencyDebitCard(1000.0, Currency.EUR)
        card.topUp(100.0)
        Assert.assertEquals(1100.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check top up for zero and negative value")
    fun topUpZeroAndNegativeTest() {
        val card = createCurrencyDebitCard(1000.0, Currency.EUR)
        Assert.assertThrows(IllegalArgumentException::class.java) { card.topUp(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { card.topUp(-100.0) }
    }

    @Test
    @DisplayName("Check withdraw")
    fun withdrawTest() {
        val card = createCurrencyDebitCard(1000.0, Currency.EUR)
        card.withdraw(100.0)
        Assert.assertEquals(900.0, card.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check withdraw for zero and negative value")
    fun withdrawZeroAndNegativeTest() {
        val card = createCurrencyDebitCard(1000.0, Currency.EUR)
        Assert.assertThrows(IllegalArgumentException::class.java) { card.withdraw(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { card.withdraw(-100.0) }
    }

    @Test
    @DisplayName("Check withdrawing more than available on balance")
    fun withdrawMoreThanBalanceTest() {
        val card = createCurrencyDebitCard(1000.0, Currency.EUR)
        Assert.assertThrows(InsufficientFundsException::class.java) { card.withdraw(2000.0) }
    }

    @Test
    @DisplayName("Check initializing card with negative balance")
    fun createCardWithNegativeBalanceTest() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            createCurrencyDebitCard(
                -500.0,
                Currency.EUR
            )
        }
    }

    @Test
    @DisplayName("Check for different currencies")
    fun differentCurrenciesTest() {
        val rubCard = createCurrencyDebitCard(1000.0, Currency.RUB)
        rubCard.topUp(100.0)
        Assert.assertEquals(1100.0, rubCard.getBalance(), 0.0)
        val usdCard = createCurrencyDebitCard(1000.0, Currency.USD)
        usdCard.topUp(100.0)
        Assert.assertEquals(1100.0, usdCard.getBalance(), 0.0)
    }
}