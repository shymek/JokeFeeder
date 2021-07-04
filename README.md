Joke Feeder Project
===================

Master build status: ![Build status](https://app.bitrise.io/app/015bae2aa067e812/status.svg?token=e15-IyFfpvqiXfcm_b9OQQ)

This repo contains a showcase project.

**General Architecture**

In this project I used MVVM with [DataBinding](https://developer.android.com/topic/libraries/data-binding) and UseCases along with [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) from Google.

For navigation I used [Jetpack Navigation](https://developer.android.com/guide/navigation)

And for HTTP requests I used [Retrofit](https://square.github.io/retrofit/)

For Dependency Injection I used [Dagger2](https://dagger.dev/) with [Hilt](https://dagger.dev/hilt/)

**Code Analysis**

I added [Detekt](https://github.com/detekt/detekt) and [ktLint](https://ktlint.github.io/) checks to keep the code in better shape.

**Modules**

I tried to separate 

- Domain
- Data
- App

Modules to achieve cleaner code with some volume overhead.
It was my first time implementing a multi module project from scratch and I was inspired by [this](https://github.com/happysingh23828/Android-Clean-Architecture) repo.
