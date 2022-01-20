package com.lucca.domain.useCases.subscriber

import com.lucca.delivery.dto.CreateSubscriberDto
import com.lucca.domain.doubles.IdGeneratorDouble
import com.lucca.domain.doubles.SubscriberRepositoryDouble
import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class CreateSubscriberUseCaseTest {

    @Test
    fun `create subscriber`() {
        val subscriberId = "subId"
        val idGenerator = IdGeneratorDouble(subscriberId)
        val repository = SubscriberRepositoryDouble()
        val useCase = CreateSubscriberUseCase(idGenerator, repository)
        val expectedSubscriber = givenACreateSubscriber(id = subscriberId)

        val result = useCase.execute(givenACreateSubscriberDto())

        result.shouldBe(subscriberId)
        repository.wasCalled.shouldBeTrue()
        repository.lastStoredSubscriber.shouldBe(expectedSubscriber)
    }

    @Test
    fun `create subscriber anotherId`() {
        val subscriberId = "anotherSubId"
        val idGenerator = IdGeneratorDouble(subscriberId)
        val repository = SubscriberRepositoryDouble()
        val useCase = CreateSubscriberUseCase(idGenerator, repository)
        val expectedSubscriber = givenACreateSubscriber(id = subscriberId)

        val result = useCase.execute(givenACreateSubscriberDto())

        result.shouldBe(subscriberId)
        repository.wasCalled.shouldBeTrue()
        repository.lastStoredSubscriber.shouldBe(expectedSubscriber)
    }

    @Test
    fun `validate context`() {
        val subscriberDto = givenACreateSubscriberDto(mail = "mail")
        val subscriberRepository = SubscriberRepositoryDouble()
        val useCase = CreateSubscriberUseCase(IdGeneratorDouble(),subscriberRepository)

        val result = useCase.isContextValid(subscriberDto)

        result.shouldBeTrue()
    }

    @Test
    fun `invalid context`() {
        val mail = "mail"
        val subscriberDto = givenACreateSubscriberDto(mail = mail)
        val subscriberRepository = SubscriberRepositoryDouble()
        subscriberRepository.setStoredSubscribers(listOf(givenACreateSubscriber(mail = mail)))
        val useCase = CreateSubscriberUseCase(IdGeneratorDouble(),subscriberRepository)

        val result = useCase.isContextValid(subscriberDto)

        result.shouldBeFalse()
    }

    private fun givenACreateSubscriberDto(
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        birthDate: String = "birthDate"
    ) = CreateSubscriberDto(mail, password, name, birthDate)

    private fun givenACreateSubscriber(
        id: String = "id",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        birthDate: String = "birthDate"
    ) = CreateSubscriber(id, mail, password, name, birthDate)
}

