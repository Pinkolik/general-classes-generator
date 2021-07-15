[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
![Build][build-badge-url]
[![MIT License][license-shield]][license-url]

[![Maven Central Plugin][maven-central-plugin-shield]][maven-central-plugin-url]
[![Maven Central Conversion][maven-central-conversion-shield]][maven-central-conversion-url]

[![LinkedIn][linkedin-shield]][linkedin-url]

<h3 align="center">General Classes Generator Maven Plugin</h3>

  <p align="center">
        This plugin is used to create generalized classes based
        on multiple versions of this class.
        It also generates mappers and a spring-based converter
        to convert general classes to versioned ones and vice versa.
    <br />
    <a href="https://github.com/Pinkolik/general-classes-generator"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Pinkolik/general-classes-generator">View Demo</a>
    ·
    <a href="https://github.com/Pinkolik/general-classes-generator/issues">Report Bug</a>
    ·
    <a href="https://github.com/Pinkolik/general-classes-generator/issues">Request Feature</a>
  </p>


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#example">Example</a></li>
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
    <li><a href="#usage">Usage</a>
      <ul>
        <li><a href="#generating-generalized-classes">Generating Generalized Classes</a></li>
        <li><a href="#generating-mappers">Generating Mappers</a></li>
        <li><a href="#generating-baseconvertersconfig">Generating BaseConvertersConfig</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## About The Project

This plugin is used to create generalized classes based on multiple versions of this class. It also generates mappers, and a
spring-based converter to convert general classes to versioned ones and vice versa.

### Example

Let's assume we have a long-living project which has a following class.

```java
package mypackage.ver1;

import lombok.Data;

@Data
public class Dog {

    private String name;
}
```

Eventually this project evolves and so does our class. Some fields get added, some removed, but we have to support all versions of
this class in our projects. So we end up with multiple version of Dog, located in corresponding packages.

```java
package mypackage.ver2;

import lombok.Data;

@Data
public class Dog {

    private String name;

    private int age;
}
```

```java
package mypackage.ver3;

import lombok.Data;

@Data
public class Dog {

    private String name;

    private int age;

    private Breed breed;

    private enum Breed {
        HUSKY,
        POODLE,
        SHIBA_INU,
        POMERANIAN
    }
}

```

For a developer it is not convenient to write separate logic for working with each version of the Dog class, it would be much
simpler to use a single representation of the Dog.

That's where this plugin comes in. This plugin generates a generalized version of the Dog class which looks like this.

```java
package mypackage.general;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
public class Dog implements Generalized {

    //GENERATED FIELDS START
    private int age;

    private mypackage.general.Dog.Breed breed;

    private java.lang.String name;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START

    public static enum Breed implements Generalized {
        //GENERATED FIELDS START
        HUSKY,
        POMERANIAN,
        POODLE,
        SHIBA_INU
        //GENERATED FIELDS END
        //GENERATED INNER CLASSES START
        //${inner_classes}
        //GENERATED INNER CLASSES END
    }
    //GENERATED INNER CLASSES END
}
```

As you can see, the generalized version contains all fields of the previous versions, so that basically means that it can be used
in code for processing any version of the Dog.

It also generates a set of mappers which will help you to convert generalized class to versioned one and vice versa. Example of a
mapper.

```java
package mypackage.mappers.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface DogMapper extends BaseMapper<mypackage.ver1.Dog, mypackage.general.Dog> {

    DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

}
```

BaseMapper interface looks
like [this](https://github.com/Pinkolik/general-classes-generator/blob/main/general-classes-generator-conversion/src/main/java/io/github/pinkolik/general_classes_generator/conversion/BaseMapper.java)
.

This plugin also generates a spring configuration
for [BaseConverter](https://github.com/Pinkolik/general-classes-generator/blob/main/general-classes-generator-conversion/src/main/java/io/github/pinkolik/general_classes_generator/conversion/BaseConverter.java)
which is useful, when you need a universal tool to convert any class from a package. So you don't have to look for a concrete
mapper, you can just use this converter.

### Built With

* [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven 3.6.3](https://maven.apache.org/docs/3.6.3/release-notes.html)

<!-- GETTING STARTED -->

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

Tools needed to build this project.

* Maven

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Pinkolik/general-classes-generator.git
   ```
2. Build the project
   ```sh
   mvn clean install
   ```

<!-- USAGE EXAMPLES -->

## Usage

### Generating Generalized Classes

Add this to your pom.xml.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    ...
    <build>
        <plugins>
            ...
            <plugin>
                <groupId>io.github.pinkolik</groupId>
                <artifactId>general-classes-generator-maven-plugin</artifactId>
                <version>1.0.0-RELEASE</version>
                <executions>
                    <execution>
                        <id>generate-general-classes</id>
                        <goals>
                            <goal>generate-general-classes</goal>
                        </goals>
                        <configuration>
                            <versionClassesBasePath>
                                ${project.parent.basedir}/versioned-classes/src/main/java/com/my/package/versioned
                            </versionClassesBasePath>
                            <versionRegexPattern>ver\d+</versionRegexPattern>
                            <outputBasePath>
                                ${project.parent.basedir}/general-classes/src/main/java
                            </outputBasePath>
                            <makeSerializable>true</makeSerializable>
                        </configuration>
                    </execution>
                </executions>
                <dependencies>
                    <!-- You have to add dependency to your versioned classes.-->
                    <dependency>
                        <groupId>com.my.package</groupId>
                        <artifactId>versioned-classes</artifactId>
                        <version>1.0.0</version>
                    </dependency>
                </dependencies>
            </plugin>
            ...
        </plugins>
    </build>
    ...
</project>
```

### Generating Mappers

Add this to your pom.xml.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    ...
    <properties>
        <org.mapstruct.version>1.4.2.Final</org.mapstruct.version>
        <lombok-mapstruct-binding.version>0.2.0</lombok-mapstruct-binding.version>
        <lombok.version>1.18.20</lombok.version>
    </properties>
    ...
    <dependencies>
        ...
        <dependency>
            <groupId>io.github.pinkolik</groupId>
            <artifactId>general-classes-generator-conversion</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${org.mapstruct.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        ...
    </dependencies>
    ...
    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>io.github.pinkolik</groupId>
                <artifactId>general-classes-generator-maven-plugin</artifactId>
                <version>1.0.0-RELEASE</version>
                <executions>
                    <execution>
                        <id>generate-mappers</id>
                        <goals>
                            <goal>generate-mappers</goal>
                        </goals>
                        <configuration>
                            <versionClassesBasePath>
                                ${project.parent.basedir}/versioned-classes/src/main/java/com/my/package/versioned
                            </versionClassesBasePath>
                            <versionRegexPattern>ver\d+</versionRegexPattern>
                            <outputBasePath>
                                ${project.parent.basedir}/mappers/src/main/java/com/my/package/mappers
                            </outputBasePath>
                        </configuration>
                    </execution>
                </executions>
                <dependencies>
                    <!-- You have to add dependency to your versioned classes.-->
                    <dependency>
                        <groupId>com.my.package</groupId>
                        <artifactId>versioned-classes</artifactId>
                        <version>1.0.0</version>
                    </dependency>
                </dependencies>
            </plugin>
            ...
        </plugins>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <!-- See https://maven.apache.org/plugins/maven-compiler-plugin/compile-mojo.html -->
                        <!-- Classpath elements to supply as annotation processor path. If specified, the compiler   -->
                        <!-- will detect annotation processors only in those classpath elements. If omitted, the     -->
                        <!-- default classpath is used to detect annotation processors. The detection itself depends -->
                        <!-- on the configuration of annotationProcessors.                                           -->
                        <!--                                                                                         -->
                        <!-- According to this documentation, the provided dependency processor is not considered!   -->
                        <annotationProcessorPaths>
                            <path>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                                <version>${lombok.version}</version>
                            </path>
                            <path>
                                <groupId>org.mapstruct</groupId>
                                <artifactId>mapstruct-processor</artifactId>
                                <version>${org.mapstruct.version}</version>
                            </path>
                            <path>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok-mapstruct-binding</artifactId>
                                <version>${lombok-mapstruct-binding.version}</version>
                            </path>
                        </annotationProcessorPaths>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
        ...
    </build>
    ...
</project>
```

### Generating BaseConvertersConfig

Add this to your pom.xml.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    ...
    <dependencies>
        ...
        <dependency>
            <groupId>io.github.pinkolik</groupId>
            <artifactId>general-classes-generator-conversion</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
        ...
    </dependencies>
    ...
    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>io.github.pinkolik</groupId>
                <artifactId>general-classes-generator-maven-plugin</artifactId>
                <version>1.0.0-RELEASE</version>
                <executions>
                    <execution>
                        <id>generate-base-converters-config</id>
                        <goals>
                            <goal>generate-base-converters-config</goal>
                        </goals>
                        <configuration>
                            <versionClassesBasePath>
                                ${project.parent.basedir}/versioned-classes/src/main/java/com/my/package/versioned
                            </versionClassesBasePath>
                            <versionRegexPattern>ver\d+</versionRegexPattern>
                            <mappersBasePath>
                                ${project.parent.basedir}/mappers/src/main/java/com/my/package/mappers
                            </mappersBasePath>
                            <outputPath>
                                ${project.parent.basedir}/converter/src/main/java/com/my/package/converter
                            </outputPath>
                        </configuration>
                    </execution>
                </executions>
                <dependencies>
                    <!-- You have to add dependency to your versioned classes.-->
                    <dependency>
                        <groupId>com.my.package</groupId>
                        <artifactId>versioned-classes</artifactId>
                        <version>1.0.0</version>
                    </dependency>
                </dependencies>
            </plugin>
            ...
        </plugins>
        ...
    </build>
    ...
</project>
```

_For more examples, please refer to the [Documentation](https://github.com/Pinkolik/general-classes-generator/wiki)_

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/Pinkolik/general-classes-generator/issues) for a list of proposed features (and known
issues).



<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions
you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->

## Contact

Simon Rusinov - mrpinkolik@gmail.com

Project Link: [https://github.com/Pinkolik/general-classes-generator](https://github.com/Pinkolik/general-classes-generator)



<!-- ACKNOWLEDGEMENTS -->

## Acknowledgements

* [MapStruct](https://mapstruct.org/)
* [Project Lombok](https://projectlombok.org/)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/Pinkolik/general-classes-generator.svg?style=plastic&logo=appveyor

[contributors-url]: https://github.com/Pinkolik/general-classes-generator/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/Pinkolik/general-classes-generator.svg?style=plastic&logo=appveyor

[forks-url]: https://github.com/Pinkolik/general-classes-generator/network/members

[stars-shield]: https://img.shields.io/github/stars/Pinkolik/general-classes-generator.svg?style=plastic&logo=appveyor

[stars-url]: https://github.com/Pinkolik/general-classes-generator/stargazers

[issues-shield]: https://img.shields.io/github/issues/Pinkolik/general-classes-generator.svg?style=plastic&logo=appveyor

[issues-url]: https://github.com/Pinkolik/general-classes-generator/issues

[license-shield]: https://img.shields.io/github/license/Pinkolik/general-classes-generator.svg?style=plastic&logo=appveyor

[license-url]: https://github.com/Pinkolik/general-classes-generator/blob/main/LICENSE

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=plastic&logo=appveyor&logo=linkedin&colorB=555

[linkedin-url]: https://www.linkedin.com/in/simon-rusinov/

[maven-central-plugin-shield]: https://img.shields.io/maven-central/v/io.github.pinkolik/general-classes-generator-maven-plugin?color=success&label=general-classes-generator-maven-plugin&style=plastic

[maven-central-plugin-url]: https://search.maven.org/artifact/io.github.pinkolik/general-classes-generator-maven-plugin/1.0.0-RELEASE/maven-plugin

[maven-central-conversion-shield]: https://img.shields.io/maven-central/v/io.github.pinkolik/general-classes-generator-conversion?color=success&label=general-classes-generator-conversion&style=plastic

[maven-central-conversion-url]: https://search.maven.org/artifact/io.github.pinkolik/general-classes-generator-conversion/1.0.0-RELEASE/jar

[build-badge-url]: https://github.com/Pinkolik/general-classes-generator/actions/workflows/maven-publish.yml/badge.svg