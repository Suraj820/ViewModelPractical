# ViewModelPractical
Learning ViewModel and ViewModelFactory
<l>
<li>
<a href ="https://github.com/Suraj820/ViewModelPractical/tree/ViewModel_with_LiveData">ViewModel with LiveData</a>
</li>
<li>
<a href ="https://github.com/Suraj820/ViewModelPractical/tree/viewModel_with_LiveData_%26_dataBinding">ViewModel with LiveData & Databinding</a>
</li>
</l>


<h2>Summery</h2>
When we are using an android app, when a configuration change like screen rotation happens, app has to destroy and recreate the activity or fragment with new configurations.

As a result of that, values(states) created during the running period of the activity will also be destroyed.



The Android Jetpack View Model architecture component has introduced as a solution for above problem. We can use a view model object to safely keep and retrieve values(states) of that activity. (Note: this only available during the run time of the app, if we need a permanent data storage we need to use a database)



As its name says, view model is a model for a view. We usually create a view model for each activity.



A ViewModel’s onCleared() is called only when the app is put into the background and the app process is killed in order to free up the system’s memory.Therefore, lifecycle changes of activities and fragments does not affect to their ViewModels.(Activities and fragments may destroy and recreate, but view model will live throughout the process)



<b>Create a VIewModel class.</b>

```kotlin
class MainActivityViewModel : ViewModel() {
...........
...........
...........
}
```

<b>Get an instance(object) of a ViewModel using ViewModel provider.</b>

```kotlin
private lateinit var viewModel: MainActivityViewModel
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    .......
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    .......
}
```

<b>Create a ViewModelFactory.</b>

```kotlin
class MainActivityViewModelFactory(private val startingTotal : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
```

<b>Get an instance(object) of a ViewModel when using a ViewModelFactory.</b>
```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        .........
        viewModelFactory = MainActivityViewModelFactory(125)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)
        .........
        }
    }
}

```

</br>
<h2>Frequently Asked Questions.</h2>
<b>1) What is the difference between ViewModel() and AndroidViewModel() ?</b></br>


The AndroidViewModel class extends ViewModel class, so it has all the same functionality.

The only added functionality for AndroidViewModel is that it is context aware, when initialising AndroidViewModel we have to pass the application context as a parameter.

AndroidViewModel is helpful if we require context to get a system service or have a similar requirement(displaying a Toast message).
```kotlin
class MyAnViewModel(application: Application) : AndroidViewModel(application) {
   ........
   ........
}

```
</br>

<b>2) What is "ViewModelProvider" ?</b></br>


We can not construct a ViewModel instance on our own. We need to use the ViewModelProvider utility provided by Android to create instances of ViewModels.



<b>3) When do we need to create a ViewModelFactory class ?</b></br>


ViewModelProvider can only instantiate ViewModels with no arg constructors.

So, if the ViewModel has constructor parameters(arguments) , ViewModelProvider need a little extra support to create instances of it.

We provide that extra support by creating a Factory class and passing its instance to the ViewModelProvider.



<b>4) When we are extending AndroidViewModel, since it should always has "application" as a constructor parameter, do we need to use a ViewModelFactory ?</b></br>
No, if the ViewModel created extending AndroidViewModel, does not have parameters other than "application", we do not need to use a ViewModelFactory for that.



<b>5) What is the onCleared() function of a ViewModel?</b></br>
  When a ViewModel object is no longer required, system will call to its onCleared() function to destroy(clear) it.

   It will be called when the app is put into the background and the app process is killed in order to free up the     

   system's memory.

   When the user invokes finish() of an activity, its view model will be cleared().

   Also when we click on the back button, ViewModel of current activity will be cleared (onCleared() will be invoked)





