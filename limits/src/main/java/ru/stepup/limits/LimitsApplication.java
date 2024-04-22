package ru.stepup.limits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Задание 8
 Реализовать REST микросервис лимитов, который имеет следующих функционал:
 - Для каждого юзера в БД хранится дневной лимит возможных платежей (первоначально 10000.00. Считаем, что раз в несколько месяцев он может меняться)
 - В 00.00 каждого дня лимит для всех пользователей должен быть сброшен
 - Про успешном проведении платежа лимит должен быть уменьшен на соответствующую сумму
 - Если вдруг платеж по какой-то причине не прошел, необходимо иметь возможность восстановить списанный лимит (тут сами выбираете стратегию уменьшения/восстановления лимитов)
 - Поскольку сервиса клиентов у нас нет, в БД храним лимиты для «клиентов» с ID 1-100
 - Поскольку считаем, что gateway не пропустит в систему несуществующего клиента, то при запросе лимита с ID, который отсутствует в БД, создаем новую запись под него со стандартным значением лимита
 */

@SpringBootApplication
@EnableScheduling
public class LimitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitsApplication.class, args);
	}

}
