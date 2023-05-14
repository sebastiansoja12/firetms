<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>


<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/sebastiansoja12/firetms">
    <img src="FireApplication/src/main/resources/templates/firetms.png" alt="Logo" width="198" height="80">
  </a>

<h3 align="center">fireTMS position finding application</h3>

  <p align="center">
    README
    <br />
    <a href="">View Demo</a>
    ·
    <a href="https://github.com/sebastiansoja12/firetms/issues">Report Bug</a>
    ·
    <a href="https://github.com/sebastiansoja12/firetms">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This Spring Boot application is used for detecting border crossing of vehicles on the map.
It communicates with external systems to obtain data sent from GPS trackers that are placed
inside vehicles. It consumes the data and process them.
If the certain vehicle crossed the border then event about border crossing is saved in database.



### Built With

List of technologies used to build this project:

[![Next][Spring-boot]][Spring-url]
[![Spring][Java]][Java-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

Visit sites given in Prerequisites section to run the project.

### Prerequisites
* Java 17
  ```sh
  https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
  ```
* Maven
  ```sh
  https://maven.apache.org/download.cgi
  ```

### Installation
1. Go to [https://github.com/sebastiansoja12/firetms](https://github.com/sebastiansoja12/firetms)
2. Clone the repo
   ```sh
   git clone git@github.com:sebastiansoja12/firetms.git
   ```
3. Open project with IntelliJ IDEA, select dev profile in maven tab
4. Run this command
   ```sh
   mvn clean install -DskipTests
   ```
   
4. Run the project 
   ```sh
   mvn spring-boot:run
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
Position by vehicle registration:

<img src="FireApplication/src/main/resources/templates/firetms.png" alt="Logo" width="198" height="80">
  
_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- [x] Add external system communication
- [x] Add algorithm for border detections
- [x] Integrate with PostgreSQL
- [x] Create mocks


See the [open issues](https://github.com/sebastiansoja12/firemts/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact


* Project Link: [https://github.com/sebastiansoja/firetms](https://github.com/sebastiansoja/firetms)
* Project Link: [https://github.com/sebastiansoja/faketelemetry](https://github.com/sebastiansoja/faketelemetry)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/sebastiansoja12/firetms
[contributors-url]: https://github.com/sebastiansoja12/firetms/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/sebastiansoja12/firetms?style=for-the-badge
[forks-url]: https://github.com/sebastiansoja12/firetms/network/members
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/sebastian-soja-1194761a2/
[product-screenshot]: images/screenshot.png
[Spring-boot]: https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white
[Spring-url]: https://spring.io/
[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://spring.io/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
