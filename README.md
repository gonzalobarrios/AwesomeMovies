# AwesomeMovies
# Segundo Obligatorio - Desarrollo Android

El objetivo de la entrega final es poner en práctica todos los conocimientos adquiridos a lo largo
del curso.
En particular, construyendo sobre los conocimientos adquiridos en la primer parte del curso,
haremos foco en lo discutido en la segunda parte del mismo.
El presente documento describe los requerimientos funcionales y no funcionales de la
aplicación que se deberá desarrollar.

## Descripción de la aplicación

El cine de tu barrio está necesitando desesperadamente atraer nuevas audiencias para
mantenerse en el negocio.
Para lograrlo, se pusieron en contacto contigo para desarrollar una aplicación móvil que permita
a los usuarios descubrir las películas más recientes, así como buscar títulos viejos.
El cliente desea incluir un conjunto básico de funcionalidades para una primer instancia, pero
anticipa un desarrollo sostenido en el tiempo, con nuevas funcionalidades ya planificadas para
el futuro.


## Requerimientos funcionales

A continuación, se describirán los requerimientos funcionales solicitados por el cliente.

### Descubrir

Al abrir la aplicación, se desea ver un listado de las películas más novedosas de la actualidad.
Las películas deberán mostrarse en una grilla, y deberá verse:
● Afiche (imagen asociada)
● Título
● Rating en formato de estrellas (ver apartado de ​filtrado​ para más información)
API Endpoint: ​https://developers.themoviedb.org/3/discover/movie-discover


### Búsqueda

En la vista descrita anteriormente, se desea tener un campo de búsqueda en la parte superior,
de forma tal que los usuarios puedan encontrar el título que están buscando.
Si el usuario ingresa algún texto en el campo de búsqueda, se deberá realizar la consultas
necesarias y mostrar los resultados.
Si el campo de texto se encuentra vacío, se mostrará el listado de descubrimiento.
La búsqueda deberá ser realizada mientras que el usuario escribe lo que está buscando, de
forma proactiva. No se debe esperar a que se presione ningún botón en particular para iniciar la
misma.
Se deberá ofrecer además un botón en forma de cruz o similar para borrar todo el contenido de
la búsqueda de manera sencilla.
API Endpoint: ​https://developers.themoviedb.org/3/search/search-movies

### Filtrado por Rating

Las películas tienen asociadas un valor de rating promedio (​vote_average​), el cual es un
valor numérico cuya escala va desde el 0 al 10, siendo 10 el mejor puntaje.
Se desea ofrecer en la pantalla de descubrimiento un mecanismo para filtrar películas según el
rating.
Colocar un campo de texto que le permita al usuario ingresar este valor no sería sencillo, por lo
cual el cliente desea que el filtrado se realice mediante un mecanismo de estrellas.
Las estrellas que se mostrarán serán 5, ubicadas horizontalmente.


La siguiente tabla muestra la conversión entre el sistema de estrellas y la escala numérica:
Estrellas Valor numérico
0 estrellas prendidas Mostrar todas las películas
1 estrella prendida Mostrar películas con rating promedio entre 0 y 2
2 estrellas prendidas Mostrar películas con rating promedio entre 2 y 4
3 estrellas prendidas Mostrar películas con rating promedio entre 4 y 6
4 estrellas prendidas Mostrar películas con rating promedio entre 6 y 8
5 estrellas prendidas Mostrar películas con rating promedio entre 8 y 10
Cuando se selecciona una estrella, se deberán prender todas las estrellas a la izquierda
(porque representan valor numérico menor).
Si se selecciona la estrella prendida más a la derecha (es decir, el filtro actual), se deberán
apagar todas las estrellas (es decir, eliminar el filtro).


### Detalle de una película

Desde la pantalla “Descubrir”, el usuario podrá seleccionar una película y acceder al detalle de
la misma.
Se deberá mostrar la siguiente información:
● Afiche (imagen asociada)
● Título
● Rating en formato de estrellas (ver apartado de ​filtrado​ para más información)
● Descripción
● Géneros
● Si se encuentra guardada en los ​favoritos​ del usuario
Adicionalmente, se desea poder acceder a la pantalla de reviews para esta película.
API Endpoint: ​https://developers.themoviedb.org/3/movies/get-movie-details


### Reviews

La pantalla de review deberá mostrar, en la parte superior, la cantidad de reviews que existen
para la película.
A continuación, deberá mostrar un listado de las reviews, con la siguiente información:
● Autor de la review
● Contenido de la review
API Endpoint: ​https://developers.themoviedb.org/3/movies/get-movie-reviews


### Favoritos

Desde la vista de detalle, el usuario podrá agregar o remover una película a favoritos.
Desde la pantalla principal, el usuario deberá poder acceder a su lista de favoritos.
La lista de favoritos deberá permitir remover las películas, y acceder al detalle de las mismas al
seleccionarlas.
La información de las películas favoritas deberá almacenarse únicamente de manera local.


## Requerimientos no funcionales

Se describirán a continuación los requerimientos no funcionales para la aplicación.

### Backend

Dado que el cliente no cuenta con un backend propio para esta primera instancia, la primera
versión de esta aplicación se construirá utilizando una API pública.

#### La misma se puede acceder aquí: ​https://developers.themoviedb.org/

La API es un conjunto de endpoints REST que se encuentran definidos detalladamente y cuya
respuesta es en formato JSON.
Los requerimientos funcionales cuentan con un link hacia los endpoints específicos, pero
cualquier endpoint provisto en la documentación de la API podrá ser utilizado, quedando a
criterio del desarrollador.
Para esta primera versión, no se requerirá registro ni autenticación.
La API pública requiere que el desarrollador cree una clave (un token de acceso) que deberá
ser enviado en cada request al servidor.
Es un proceso sencillo que está descrito en la documentación, en particular, en los siguientes
links:
● https://developers.themoviedb.org/3/getting-started/introduction
● https://developers.themoviedb.org/3/getting-started/authentication

### Arquitectura

Dado que el cliente seguirá desarrollando la aplicación por un tiempo prolongado y agregando
funcionalidades nuevas, se desea diseñar la aplicación de forma tal que realizar estos cambios
no introduzca bugs incontrolables en el código existente.
Por dicho motivo, se solicita que la aplicación se desarrolle utilizando una arquitectura MVVM,
haciendo uso de los ​Android Architecture Components​ (LiveData, ViewModel, DataBinding)
según lo visto en clase.
A su vez, se solicita que el desarrollador haga uso de la técnica de Inyección de Dependencias.


### Modo Offline

Se requiere que la aplicación pueda funcionar de forma offline, de forma tal que el usuario
pueda acceder a todas las funcionalidades de la aplicación sin estar conectado a internet.
Por lo tanto, una vez que se haga una petición al servidor, se deberá guardar dicha información
en una base de datos local de forma tal que la misma pueda ser accedida de forma offline.
Si el usuario está offline, no es necesario mostrar el campo de búsqueda. La información
exclusiva de búsquedas anteriores no debe ser guardada localmente.
Se solicita que se haga uso de la ​Room Persistence Library

### Testing

Dado que el cliente seguirá desarrollando la aplicación por un tiempo prolongado y agregando
funcionalidades nuevas, se desea mantener la consistencia y las funcionalidades sin romperse.
Para esto es que se debe agregar testing unitario, de integración y de UI.

### Diseño

El diseño de la aplicación queda a criterio del desarrollador.
Se espera que el mismo sea acorde a la plataforma Android, es decir, que sigua los
lineamientos detrás de la filosofía de Material Design.
Se solicita que el diseño sea consistente en todas las pantallas de la aplicación, y que el mismo
sea prolijo, como si se fuese a subir a la Google Play Store el mismo día de la entrega.
La aplicación a entregar deberá contar con un AppIcon y SplashScreen.

### Librerías de terceros

El desarrollador podrá hacer uso de las librerías de terceros que crea conveniente.


## Formato de entrega y evaluación

El obligatorio podrá ser realizado de manera individual o en equipo de dos personas.

### Criterio de evaluación

Se evaluará que el contenido del entregable sea acorde a lo descrito en el presente
documento.
Se hará especial hincapié en la calidad del entregable, incluyendo:
● Nombre de variables, funciones, clases, recursos intuitivos
● Sintaxis clara, format del código limpio
● Consistencia en soluciones a lógicas similares
● Modularización del código, separación de responsabilidades
● Optimización en lógicas, reutilización de código
● Uso de facilidades del lenguaje Kotlin
● Elección de librerías de terceros
● Usabilidad de la UI
● Diseño de la aplicación
Particularmente, se requiere que el entregable haga uso de la arquitectura MVVM vista durante
el curso, con las clases provistas por los ​Android Architecture Components​. El uso de estos
componentes, según lo descrito en los requerimientos no funcionales, es un requisito mínimo
para que el trabajo sea considerado y evaluado.

### Formato de entrega

El código del ejercicio deberá ser subido a un repositorio git.
Si el repositorio es privado, deberán agregar a los docentes como contribuidores.
En GitHub, los usuarios son: diegomedina248, JNahui.
Subir a Web Asignatura un link al repositorio para que quede el registro, con el nombre de los
integrantes.

### Fecha de entrega

La entrega se podrá entregar hasta el Lunes 18 de noviembre a las 23:55 hrs


### Defensa

Se realizará la defensa del proyecto entregado el miércoles 20 de noviembre a una hora de
común acuerdo con el equipo, según la defensa se asigna la nota final.


