#********** GUIDLEINES **********#
----------------------------------
* Add the these dependencies in the data layer.
    implementation "com.google.code.gson:gson:2.8.6"

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
    implementation 'com.squareup.okhttp3:okhttp:4.10.0'

* Start by defining model data in each layer (follow the naming conventions).
* Define the relevant mappers in the data and presentation layer.
* Test the mappers.
* Define the repository interfaces in the domain layer.
	- All data getting operations are named with 'retrieve' with plural nouns(ending with 's'). 
	- All data saving operations are named with 'persist'.
	- All functions should be suspend functions to support coroutines.
* Define the datasource contract interfaces in the data layer.
* Implement the datasource contracts for local and remote repositories.
* Implement the repository interfaces (from domain layer) in the data layer.
     -Pass as a constructor param the relevant datasource implementation.
* Implement the cache interface for relevant classes in the data layer.
* Put a datasource callback object for the cache implementations.
* In the relevant local repos, pass the cache interface impl as a constructor parameter.
* Also define callback interfaces for when data has loaded.
* Then define the use-cases/interactors simultaneously in the domain layer.
* Define the response as well.
* Define your presenters passing as a constructor parameter the appropriate interactor and mapper.