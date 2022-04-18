# TEST CUPON

***

## GENERALIDADES

* El proyecto para la distribuicion de los paquetes se baso en la arquitectura por capas.
* El framework es spring boot 2.6.6
* para el manejo de dependencias se utiliza gradle
* La jdk selecionada es OpenJDK-11
* Ide de Desarrollo IntelliJ IDEA 2021.3.3 A list of frequently asked questions
* Url Documentacion swagger http://localhost:8080/swagger-ui/index.html#/
* El servicio se desplego en una instancia Ec2 de Aws

## Pre-requisitos

1. Este RESTful API fue creado con [Spring Boot](https://start.spring.io/):
   `JAVA >= 11`
2. También necesitaremos Gradle ([Gradle](https://gradle.org/)).
3. recomiendo instalar un cliente para probar el *end-points*. Recomiendo para tal propósito
   es [Postman](https://www.getpostman.com/) que tiene una aplicación gratuita para Windows, GNU/Linux y OS X.
4. Entorno de Desarrollo recomendado ([IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/)).

## Instalación para Desarrollo

1. Clonar el repositorio (ejecutar desde el directorio raiz de este
   proyecto): `git clone https://github.com/nye12r/coupon.git`
2. Tener configurado gradle en el ide desarrollo.
3. Ejecutar en terminal integrada del ide el comando `gradle build`
4. Una vez descargada las dependencias en un computador personal ejecutar el comando `gradle bootRun`
5. Validar que la aplicacion se este ejecutandose localmente.
6. Iniciar tu servidor en el puerto 8080

## Rutas disponibles en servidor local:

>  ```
>
> Ruta cupones: 
> [localhost:8080/coupon/](localhost:8080/coupon/)
>   ```
> *Request:*
>   ```
>   {
>      "amount": 3365.99,
>       "item_ids": ["MLA932320631",
>                    "MLA932320623",
>                    "MLA932320637",
>                    "MLA932320616"
>                    ]
>   }
>   ```
>
> *Response:*
>   ```
>   {
>      "total": 3365.99,
>       "item_ids": ["MLA932320631", "MLA932320623","MLA932320637"]
>   }
>  ``` 
