<h1>WatchBase</h1>
<p>🍿 Trending TV shows & movies information app built based on MVVM pattern with Jetpack Compose, Hilt, Retrofit, Flow, Pagination and Room.
</p>
<br>

<!-- Screenshots -->
<p align="center">
  <img src="https://user-images.githubusercontent.com/57670625/233807029-2d051c7d-7f75-4055-88b1-91e842dfa970.png" width="30%"/>
  &nbsp;                                                                                                                
  <img src="https://user-images.githubusercontent.com/57670625/233807028-d8e70cfb-5047-4f70-9f29-346468d5bac7.png" width="30%"/>
  &nbsp;
  <img src="https://user-images.githubusercontent.com/57670625/233807027-aa23d402-a9fd-4f69-93f9-4df364f7b6b4.png" width="30%"/>
</p>
<br>

<!-- Tech Stack -->
<h2>Tech Stack</h2>
<ul>
<li>Minumum SDK Level: 21</li>
<li>100% Kotlin</li>
<li>Architecture
    <ul>
      <li><a href="https://developer.android.com/topic/architecture">MVVM Pattern</a>: Industry-recognized software architecure pattern supported by Google</li>
    </ul>
 </li>
<li><a href="https://developer.android.com/jetpack/compose/documentation">Jetpack Compose</a>: Modern toolkit for building Android user interfaces using a declarative programming model</li>
<li><a href="https://developer.android.com/topic/libraries/architecture/paging/v3-overview">Jetpack Pagination</a>: Library helps you load and display pages of data from a larger dataset from local storage or over network.</li>
<li><a href="https://developer.android.com/jetpack/compose/state">ViewModel</a>: Exposes data streams as a state holder</li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a>: Dependency injection library built on top of Dagger benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support </li>
<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a>: Concurrency design pattern provided by Kotlin</li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a>: Type-safe REST client for Android, Java and Kotlin developed by Square.  </li>
<li><a href="https://square.github.io/okhttp/">OkHttp</a> : 3rd party library sending and receive HTTP-based network requests built on top of the Okio library</li>
<li><a href="https://github.com/google/gson">GSON</a>: Java library that can be used to convert Java Objects into their JSON representation</li>
<li><a href="https://developer.android.com/training/data-storage/room">Room</a>: Abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.</li>
</ul>
<br>

<!-- Architecture -->
<h2>Architecture</h2>
<p>Top News app was built with Google's recommended modern app architecture - MVVM pattern. By separating multiple app components into three layers
- UI, Domain and Data, the app is scalable, maintainable and testable.</p>
<ul>
  <li>Architectural Principles</li>
    <ul>
      <li>Separations of concerns</li>
      <li>Drive UI from data models</li>
      <li>Single source of truth</li>
      <li>Unidirectional Data Flow</li>
   </ul>
</ul>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235998527-f9f34484-2fdf-42bf-b792-c2d14e5f75b0.jpg"/>
</p>

<h3>Architecture Overview</h3>
<p>WatchBase is composed with two main layers - UI layer and data layer. Each layer has app components which have different responsibilities.</p>

<h3>UI Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235998520-af3b3e40-af2e-4b47-b4bd-5681a165ed87.jpg" width="80%"/>
</p>
<p>UI layer displays the application data and serves as the primary point for user interactions. Whenever the app data changes, the UI should update to reflect changes made by either user interaction or external input.</p>
<ul>
  <li>The main activity and all the fragments - Feed, Browse, Saved, etc are UI elements and they display articles received from network requests and the database</li>
  <li>NewsViewModel holds state and plays as a bridge between UI elements and the data layer</li>
  <li>UI elements request actions to ViewModel and observer ViewModel's livedatas to automatically update screens</li>
</ul>
<br>

<h3>Data Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235998523-75cecf15-6e0b-4fbd-86f8-25b31a23b9a5.jpg" width="80%"/>
</p>
<p>Data layer is reponsible for containing application data and business logics. The data layer is consisted of repositories and data sources. It is important to keep each repository as a single source of truth.</p>
<ul>
  <li>NewsRepository is a single source of truth and requests data from NewsLocalDataSource and NewsRemoteDataSource.</li>
  <li>NewsLocalDataSource is a class managing the database built with Room library and NewsRemoteDataSource is a class requesting network response to NewsAPI server.</li>
</ul>
<br>

<!-- Open APIs -->
<h2>Open APIs</h2>
<img src="https://user-images.githubusercontent.com/57670625/235322353-9a0a67f6-757f-4df9-9cb7-0849263735af.svg" align="right" width="21%"/>
<p>Watchbase uses <a href="https://www.themoviedb.org/documentation/api">TMDB API</a> for fetching data from the server. TMDB API provides detailed information about TV shows, movies and casts.</p>
 
