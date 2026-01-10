# matrix-java

[![Java Version](https://img.shields.io/badge/java-21+-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

`matrix-java` is a lightweight, fast, and easy-to-use Java library for linear algebra.

It provides immutable and mutable matrix implementations, common matrix operations, and utility methods for creating and manipulating matrices.

## Features

* Immutable and mutable matrix classes
* Support for basic arithmetic operations
* Transpose, trace, norms (L1, infinity)
* Structural checks: square, zero, identity, diagonal, etc
* Simple, consistent API with defensive copying for safety
* Lightweight, no external dependencies

## Installation

### Maven

```xml
<dependency>
    <groupId>com.gmeunier</groupId>
    <artifactId>matrix-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Manual

Download the JAR from the releases and add it to your project's classpath.

## Building

Git, Maven and JDK 21 (or newer) are required for building.

```bash
git clone https://github.com/guiguitator/matrix-java.git
cd matrix-java
mvn clean install
```

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE.txt) file for details.