package co.omisego.omisego.features

/*
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 3/7/2018 AD.
 * Copyright © 2017-2018 OmiseGO. All rights reserved.
 */

import co.omisego.omisego.LiveTest
import co.omisego.omisego.model.params.LoginParams
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBe
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class AuthenticationLiveTest : LiveTest() {
    private val secret by lazy { loadSecretFile("secret.json") }

    @Test
    fun `login should return 200 and parsed the response correctly`() {
        val response = client.login(
            LoginParams(
                secret.getString("email"),
                secret.getString("password")
            )
        ).execute()

        response.isSuccessful shouldBe true
        response.body()?.data?.authenticationToken shouldNotBe null
        response.body()?.data?.authenticationToken shouldNotEqual ""
        response.body()?.data?.userId shouldNotBe null
        response.body()?.data?.userId shouldNotEqual ""
        response.body()?.data?.accountId shouldNotBe null
        response.body()?.data?.accountId shouldNotEqual ""
    }
}
