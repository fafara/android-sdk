package co.omisego.omisego.live.auth

/*
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 11/9/2018 AD.
 * Copyright © 2017-2018 OmiseGO. All rights reserved.
 */

import co.omisego.omisego.live.BaseAuthTest
import co.omisego.omisego.model.filterable.buildFilterList
import co.omisego.omisego.model.params.admin.TransactionListParams
import org.amshove.kluent.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class ListTransactionLiveTest : BaseAuthTest() {

    @Test
    fun `list transaction should be returned successfully`() {
        val response = client.getTransactions(
            TransactionListParams.create()
        ).execute()

        response.isSuccessful shouldBe true
        response.body()?.data?.pagination?.perPage shouldBe 10
        response.body()?.data?.pagination?.currentPage shouldBe 1
        response.body()?.data?.data?.size shouldBe 10
    }

    @Test
    fun `list transaction with a specific account should return transactions associated with the account`() {
        val accountId = secret.getString("account_id")

        val response = client.getTransactions(
            TransactionListParams.create(
                matchAny = buildFilterList {
                    add("from_account.id" eq accountId)
                    add("to_account.id" eq accountId)
                }
            )
        ).execute()

        response.isSuccessful shouldBe true
        response.body()?.data?.data?.forEach {
            (accountId in arrayOf(it.from.accountId, it.to.accountId)) shouldBe true
        }
    }
}
