package bankproducts

import bankproducts.enums.Currency
import io.qameta.allure.junit4.DisplayName
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException
import java.util.*
import javax.naming.OperationNotSupportedException

@DisplayName("Deposit tests")
class DepositTest {
    private fun createDeposit(balance: Double): Deposit {
        return Deposit("My deposit", Currency.RUB, balance)
    }

    @Test
    @DisplayName("Check top up")
    fun topUpTest() {
        val deposit = createDeposit(1000.0)
        deposit.topUp(100.0)
        Assert.assertEquals(1100.0, deposit.getBalance(), 0.0)
    }

    @Test
    @DisplayName("Check top up for zero and negative value")
    fun topUpZeroAndNegativeTest() {
        val deposit = createDeposit(1000.0)
        Assert.assertThrows(IllegalArgumentException::class.java) { deposit.topUp(0.0) }
        Assert.assertThrows(IllegalArgumentException::class.java) { deposit.topUp(-100.0) }
    }

    @Test
    @DisplayName("Check withdraw")
    fun withdrawTest() {
        val deposit = createDeposit(1000.0)
        Assert.assertThrows(OperationNotSupportedException::class.java) { deposit.withdraw(100.0) }
    }

    @Test
    @DisplayName("Check creating deposit with negative value")
    fun createDepositWithNegativeBalanceTest() {
        Assert.assertThrows(IllegalArgumentException::class.java) { createDeposit(-500.0) }
    }

    @Test
    @DisplayName("Check closing deposit")
    fun closeTest() {
        val deposit = createDeposit(1000.0)
        deposit.close()
        Assert.assertEquals(0.0, deposit.getBalance(), 0.0)
    }
}