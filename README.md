StarWars Application
=============
This is a sample project to search for Starwars characters and show some information about them.
[SWAPI](https://swapi.py4e.com/documentation) is used for getting data from server

![img_3.png](img_3.png)

Structure of code
-------------
#### MVI + Flow + Coroutine
StarWars is implemented by The MVI (Model-View-Intent) architecture, a popular approach in Android development, emphasizes clean code and a clear separation of concerns by dividing the application into three main components: Model, View, and Intent.
also for emit data Flow is used which In coroutines, a flow is a type that can emit multiple values sequentially
#### Clean Architecture
Clean Architecture is to separate the responsibility, making it testable and avoid any strong dependencies to UI, frameworks, databases. We could change a dependency smoothly without affecting the whole structure

More
-------------
##### Jetpack Compose for UI
##### Retrofit for getting data from API