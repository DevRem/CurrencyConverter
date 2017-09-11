package ru.currencyconverter.activity.presenter;

import java.math.BigDecimal;

import ru.currencyconverter.net.models.Valute;

public interface ICurrencyPresenter {

    /**
     * Конвертируем сумму в указанную валюту
     * @param value - Сумма, которую пользователь указал для конвертации
     * @param curFrom - Исходная валюта
     * @param curTo - Выбранная валюта
     */
    BigDecimal calculate(BigDecimal value, Valute curFrom, Valute curTo);

    /**
     * Очищаем ссылку, что бы View был доступен для сборщика
     */
    void onDestropy();

    /**
    * Метод загружает список валют
    */
    void loadCurrencies();


}
