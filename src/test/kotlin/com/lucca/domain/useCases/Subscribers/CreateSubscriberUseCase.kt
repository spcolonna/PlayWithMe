package com.lucca.domain.useCases.Subscribers

import io.kotest.matchers.shouldBe
import org.junit.Test

class CreateSubscriberUseCaseTest {

    @Test
    fun `create subscriber`() {
        val subscriberId = "subId"
        val subscriber = givenASubscriber(id = subscriberId)
        val repository = SubscriberRepositoryDouble()
        val useCase = CreateSubscriberUseCase(repository)

        val result = useCase.execute(subscriber)

        result.shouldBe(subscriberId)
    }

    @Test
    fun `create subscriber anotherId`() {
        val subscriberId = "anotherSubId"
        val subscriber = givenASubscriber(id = subscriberId)
        val repository = SubscriberRepositoryDouble()
        val useCase = CreateSubscriberUseCase(repository)

        val result = useCase.execute(subscriber)

        result.shouldBe(subscriberId)
    }

    private fun givenASubscriber(
        id: String = "id",
        mail: String = "mail",
        name: String = "name",
        birthDate: String = "birthDate"
    ): CreateSubscriberDto {
        return CreateSubscriberDto(id, mail, name, birthDate)
    }
}

class CreateSubscriberUseCase(repository: ISubscriberRepository) {
    fun execute(dto: CreateSubscriberDto): String {
        return dto.id
    }

}

interface ISubscriberRepository {

}

class SubscriberRepositoryDouble : ISubscriberRepository{

}

data class CreateSubscriberDto(
    val id: String,
    val mail: String,
    val name: String,
    val birthDate: String
)
