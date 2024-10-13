# WeatherApp

Простенькое Android-приложение на Kotlin, которое показывает текущую погоду в Санкт-Петербурге на основе данных, полученных от OpenWeather API (https://openweathermap.org/api).

Требования, выдвигавшиеся к приложению:
- minSdk 23
- у приложения должен быть Splash экран, на котором будет иконка и progressBar.
- со Splash экрана через полсекунды-секунду приложение переходит на основной экран приложения.
- на основном экране текущая погода в городе Санкт-Петербург (температура, влажность, скорость ветра, давление).
- также на экране расположены 2 кнопки: Details, Forecast (переход на соответстующие экраны).
- на экране Details показана более детальная информация о текущей погоде (на основе полученного ответа от API).
- на экране Forecast отображается полученный от API прогноз погоды на ближайшие 5 дней каждые 3 часа.
- приложение должно уметь обрабатывать ошибки от API и отсутствие подключения к интернету, работать стабильно и не "вешать UI".
- у приложения должен быть АДЕКВАТНЫЙ и приятный дизайн.

Стек:
- MVVM
- Single Activity
- viewBinding
- Retrofit
- GSON
- Glide

Бесплатные иконки взяты с icons8.com

Скриншоты приложения:

<img src="https://github.com/aleksandrgrigorev/WeatherApp/assets/102324677/6ed95425-9021-41b8-a515-542260dc1383" width="150" />
<img src="https://github.com/aleksandrgrigorev/WeatherApp/assets/102324677/faa1dced-076d-414e-8198-f37a20e2030f" width="150" />
<img src="https://github.com/aleksandrgrigorev/WeatherApp/assets/102324677/0f4f5885-7568-475d-84ec-7cf05670e004" width="150" />
<img src="https://github.com/aleksandrgrigorev/WeatherApp/assets/102324677/b36fa51a-8fae-4d13-9ef0-af8fcd837c87" width="150" />
<img src="https://github.com/aleksandrgrigorev/WeatherApp/assets/102324677/080d1780-4958-4d11-8012-32c73768e561" width="150" />
